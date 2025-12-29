import { useState, useEffect, useRef } from "react";
import { sendMessage } from "./services/api";
import "./App.css";

function App() {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");
  const [typing, setTyping] = useState(false);
  const [darkMode, setDarkMode] = useState(
    localStorage.getItem("dark") === "true"
  );

  const bottomRef = useRef(null);

  // ✅ AUTO SCROLL (1 line)
  useEffect(() => bottomRef.current?.scrollIntoView({ behavior: "smooth" }), [
    messages,
    typing,
  ]);

  // ✅ DARK MODE PERSIST
  useEffect(() => {
    document.body.className = darkMode ? "dark" : "";
    localStorage.setItem("dark", darkMode);
  }, [darkMode]);

  const handleSend = async (text) => {
    if (!text.trim()) return;

    setMessages((prev) => [...prev, { role: "user", text }]);
    setInput("");
    setTyping(true);

    try {
      const data = await sendMessage(text);
      setMessages((prev) => [...prev, { role: "bot", data }]);
    } catch {
      setMessages((prev) => [
        ...prev,
        { role: "bot", data: { message: "Something went wrong 😢" } },
      ]);
    } finally {
      setTyping(false);
    }
  };

  return (
    <div className="app-wrapper">
      <div className="chat-container">
        {/* HEADER */}
        <div className="chat-header">
          🍳 AI Recipe Chatbot
          <button className="dark-toggle" onClick={() => setDarkMode(!darkMode)}>
            {darkMode ? "☀️ DARK MODE " : "🌙 LIGHT MODE"}
          </button>
        </div>

        {/* MESSAGES */}
        <div className="chat-messages">
          {messages.map((msg, index) =>
            msg.role === "user" ? (
              <div key={index} className="message user">
                {msg.text}
              </div>
            ) : (
              <BotMessage
                key={index}
                data={msg.data}
                onSelect={handleSend}
              />
            )
          )}

          {/* ✅ TYPING INDICATOR */}
          {typing && (
            <div className="message bot">
              <div className="typing">
                <span></span>
                <span></span>
                <span></span>
              </div>
            </div>
          )}

          <div ref={bottomRef} />
        </div>

        {/* INPUT */}
        <div className="chat-input">
          <input
            value={input}
            onChange={(e) => setInput(e.target.value)}
            placeholder="Enter ingredients or message..."
            onKeyDown={(e) => e.key === "Enter" && handleSend(input)}
          />
          <button onClick={() => handleSend(input)}>Send</button>
        </div>
      </div>
    </div>
  );
}

function BotMessage({ data, onSelect }) {
  return (
    <div className="message bot">
      <p>{data.message}</p>

      {/* SUGGESTIONS */}
      {data.stage === "SUGGESTIONS" && data.dishes && (
        <div>
          {data.dishes.map((dish, i) => (
            <button
              key={i}
              className="dish-button"
              onClick={() => onSelect(dish)}
            >
              {dish}
            </button>
          ))}
        </div>
      )}

      {/* RECIPE */}
      {data.stage === "RECIPE" && data.recipe && (
        <div className="recipe-card">
          <h4>Ingredients</h4>
          <ul>
            {data.recipe.ingredients.map((item, i) => (
              <li key={i}>{item}</li>
            ))}
          </ul>

          <h4>Steps</h4>
          <ol>
            {data.recipe.steps.map((step, i) => (
              <li key={i}>{step}</li>
            ))}
          </ol>
        </div>
      )}
    </div>
  );
}

export default App;
