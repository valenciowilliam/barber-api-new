# 💈 Barber Booking App

A full-stack mobile application that allows users to book barber services, manage appointments, and rate barbers — built using **React Native**, **Spring Boot**, and **PostgreSQL (Neon DB)**.

---

## 🚀 Features

### 👤 Authentication

* User registration & login
* Secure password hashing (BCrypt)
* JWT-based authentication (basic)

### 💈 Barber Services

* View list of barbers
* View available services
* See ratings and experience

### 📅 Appointment System

* Book appointments with date & time
* Prevent double booking
* View appointment history
* Cancel appointments

### ⭐ Rating System

* Rate barbers
* Automatic average rating calculation

---

## 🏗️ Tech Stack

### 📱 Frontend

* React Native
* JavaScript / TypeScript

### ⚙️ Backend

* Spring Boot (Java)
* REST APIs
* Spring Data JPA

### 🗄️ Database

* PostgreSQL (Neon Cloud)

### ☁️ Deployment

* Backend: Render (Docker)
* Database: Neon
* Uptime: UptimeRobot

---

## 📂 Project Structure

barber-booking-app/
│
├── backend/ (Spring Boot)
│   ├── controller/
│   ├── entity/
│   ├── repository/
│   ├── auth/
│   └── config/
│
├── frontend/ (React Native)
│   ├── screens/
│   ├── components/
│   └── navigation/
│
└── README.md

---

## 🌐 Live API

https://barber-api-new.onrender.com

Example endpoints:

* `/auth/login`
* `/auth/register`
* `/barbers`
* `/services`
* `/appointments`
* `/ratings`

---

## ⚙️ Setup Instructions

### 🔧 Backend (Spring Boot)

[git clone https://github.com/valenciowilliam/barber-api-new.git
cd barber-api

Create `application.properties`:

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}

Run:

./mvnw spring-boot:run

---

### 📱 Frontend (React Native)

cd BarberApp
npm install

Update API base URL:

const BASE_URL = "https://barber-api-new.onrender.com";

Run app:

npx react-native run-android

---

## 📦 Build APK

cd android
./gradlew assembleRelease

APK Location:

android/app/build/outputs/apk/release/app-release.apk

---

## 🔐 Environment Variables

| Variable | Description         |
| -------- | ------------------- |
| DB_URL   | Neon PostgreSQL URL |
| DB_USER  | Database username   |
| DB_PASS  | Database password   |

---

## 🧪 Testing

* Tested on Android Emulator & Physical Device
* API tested using browser/Postman
* Release build verified before APK generation

---

## ⚠️ Notes

* Render free tier may sleep after inactivity
* First API call may take 20–30 seconds (cold start)
* UptimeRobot used to keep backend active

---

## 👨‍💻 Author

**Valencio William**

---

## 📄 License

This project is for educational purposes.
