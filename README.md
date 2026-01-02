# 🍳 AI Recipe Chatbot

AI Recipe Chatbot is a full-stack web application that suggests recipes based on the ingredients provided by the user.  
It uses an AI service to generate dish suggestions and full cooking recipes through a conversational flow.

---

## 📌 Project Description

The application works in a chat-based format:

1. User enters available ingredients  
2. The system suggests possible dishes  
3. User selects a dish  
4. The system asks for confirmation  
5. On confirmation, a complete recipe with ingredients and steps is returned  

The backend handles all AI communication and business logic, while the frontend provides an interactive chat interface.

---

## 🧠 Features

- Ingredient-based recipe suggestions  
- AI-powered responses using Groq API  
- Step-by-step cooking instructions  
- Chat-style user interaction  
- Light and Dark mode UI  
- Single application deployment (frontend served by backend)

---

## 🛠️ Technologies Used

### Backend
- Java 17  
- Spring Boot  
- Spring MVC  
- Spring Data JPA  
- H2 In-Memory Database  
- REST APIs  
- Environment variable based configuration  

### Frontend
- React (Vite)  
- HTML  
- CSS  
- JavaScript  

### Deployment & DevOps
- Docker  
- Render (Cloud Hosting)  

---

## 🏗️ Project Structure

AI-Recipe-Chatbot
│
├── backend
│ ├── Spring Boot application
│ ├── REST APIs
│ └── Serves frontend static files
│
├── frontend
│ ├── React (Vite)
│ └── Build output copied into backend
│
├── Dockerfile
└── README.md


---

## ▶️ Run the Project Locally

### Backend

```bash
cd backend
mvn spring-boot:run

### Frontend

```bash
cd frontend
npm install
npm run dev

Deployment

The application is deployed on Render using Docker.

Live URL:

https://ai-recipe-chatbot.onrender.com

Deployment

The application is deployed on Render using Docker.

Live URL:

https://ai-recipe-chatbot.onrender.com

👨‍💻 Author

Dilshad Shaik

Backend / Java Developer

GitHub: https://github.com/Dilshad0515

LinkedIn: https://www.linkedin.com/in/dilshad-shaik-8848b423a/
