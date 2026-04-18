# 🚀 ResolveHub – Complaint Management System

ResolveHub is a full-stack web application designed to manage and resolve user complaints efficiently. It allows users to submit and track complaints, while administrators can manage, update, and resolve them through a structured system.

The application is built using React for the frontend and Spring Boot for the backend, following RESTful architecture for seamless communication.

---

## 🔹 Features
- User Authentication (Login & Signup)
- Role-Based Access (User & Admin)
- Add, View, and Track Complaints
- Admin can Update & Delete Complaints
- Real-time updates using REST APIs
- Simple and responsive UI

---

## 🛠️ Technologies Used

### Frontend
- React.js
- Axios
- HTML, CSS, JavaScript

### Backend
- Spring Boot (Java)
- REST APIs

### Database
- MySQL (or your DB)

### Data Format
- JSON

---

## ⚙️ Project Architecture

This project follows a **client-server architecture**:

- **Frontend (React)** → Handles UI and user interaction  
- **Backend (Spring Boot)** → Handles logic and APIs  
- **Database** → Stores user and complaint data  

---

## 🔗 API Endpoints

### Authentication
- `POST /auth/login`
- `POST /auth/signup`

### Complaints
- `GET /complaints/all`
- `POST /complaints/add`
- `PUT /complaints/update/{id}`
- `DELETE /complaints/delete/{id}`

---

## ⚙️ How to Run the Project

### 🔸 Backend (Spring Boot)
1. Open project in Eclipse / IntelliJ
2. Run the main class
3. Server runs at:
