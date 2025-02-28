# News Aggregator

## Overview
This is a full-stack news aggregator application that allows users to browse, search, and save news articles. It integrates a **React + Vite** frontend with a **Spring Boot (Java 17)** backend and uses **Firebase authentication** for user sign-in. News data is fetched from an external API and cached to optimize performance.

## Features
- **Search & Filtering:** Search for articles and filter by category and country.
- **User Authentication:** Google sign-in via Firebase.
- **Favorites System:** Save and view favorite articles when logged in.
- **Data Caching:** Stores recent news articles in-memory to reduce API calls.
- **Lightweight Database:** Uses H2 for local testing.
- **Docker Support:** Pre-configured for containerized deployment.

## Tech Stack
### Frontend
- **React** (Vite for fast builds)
- **Firebase Authentication**
- **React Router**

### Backend
- **Spring Boot (Java 17)**
- **H2 Database** (for development)
- **REST API** (integrates with News API)
- **Spring Data JPA**

### Deployment
- **Docker** (with multi-stage build)
- **Environment Variables** for API keys and configurations
