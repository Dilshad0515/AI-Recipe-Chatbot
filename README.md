# 🍳 AI Recipe Chatbot

AI Recipe Chatbot is a **full-stack web application** that suggests recipes based on ingredients provided by the user.  
It uses an **AI-powered conversational flow** to recommend dishes and generate complete cooking recipes.

The application follows a chat-based interaction where users can naturally communicate with the system to get recipe ideas and step-by-step cooking instructions.

---

## 🚀 Live Demo

**Live Application:**  
🔗 https://ai-recipe-chatbot.onrender.com

---

## 📌 Project Overview

The application works in a conversational manner:

1. User enters available ingredients  
2. AI suggests possible dishes  
3. User selects a dish  
4. System asks for confirmation  
5. On confirmation, a **complete recipe** (ingredients + steps) is generated  

The **backend** handles AI communication and business logic, while the **frontend** provides an interactive chat-style UI.

---

## ✨ Features

- Ingredient-based recipe suggestions  
- AI-powered dish recommendations using **Groq API**  
- Step-by-step cooking instructions  
- Chat-style interactive interface  
- Light & Dark mode support  
- Single deployment (frontend served by backend)  

---

## 🛠️ Tech Stack

### 🚀 Backend
![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green?logo=springboot)
![Spring MVC](https://img.shields.io/badge/Spring-MVC-brightgreen)
![Spring Data JPA](https://img.shields.io/badge/Spring-Data%20JPA-blue)
![H2](https://img.shields.io/badge/H2-Database-lightgrey)

### 🌐 Frontend
![React](https://img.shields.io/badge/React-18-blue?logo=react)
![Vite](https://img.shields.io/badge/Vite-Build-purple?logo=vite)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6-yellow?logo=javascript)
![HTML](https://img.shields.io/badge/HTML-5-orange?logo=html5)
![CSS](https://img.shields.io/badge/CSS-3-blue?logo=css3)

### ☁️ DevOps & Deployment
![Docker](https://img.shields.io/badge/Docker-Container-blue?logo=docker)
![Render](https://img.shields.io/badge/Render-Cloud-black?logo=render)
![Git](https://img.shields.io/badge/Git-Version%20Control-red?logo=git)

---

## 🏗️ Project Structure

```text
AI-Recipe-Chatbot
│
├── backend
│   ├── src/main/java
│   │   └── Spring Boot application source
│   ├── src/main/resources
│   │   ├── application.yml
│   │   └── static/        # Frontend build files
│   ├── pom.xml
│   └── Dockerfile
│
├── frontend
│   ├── src
│   │   ├── components
│   │   ├── pages
│   │   └── styles
│   ├── public
│   ├── package.json
│   └── vite.config.js
│
├── Dockerfile
└── README.md

## ▶️ Run the Project Locally

### ✅ Prerequisites

- ☕ **Java 17**
- 🧰 **Maven**
- 🌐 **Node.js (v18+)**
- 🐳 **Docker** (optional)

---

### 🖥️ Backend Setup

```bash
cd backend
mvn spring-boot:run

### 🌐 Frontend Setup

```bash
cd frontend
npm install
npm run dev

## 👨‍💻 Author

**Dilshad Shaik**  
Backend / Java Developer  

🔗 **GitHub:** https://github.com/Dilshad0515  
🔗 **LinkedIn:** https://www.linkedin.com/in/dilshad-shaik-8848b423a/

---

## ⭐ Support

If you like this project, please ⭐ the repository and feel free to contribute!
