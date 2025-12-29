const API_URL = "http://localhost:8080/api/recipes";

export async function sendMessage(message) {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ message }),
  });

  if (!response.ok) {
    throw new Error("Backend error");
  }

  return response.json();
}
