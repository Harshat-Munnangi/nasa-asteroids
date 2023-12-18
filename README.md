# nasa-asteroids

#### This application is built using the following tech stack & tools :

    - Java 17
    - Spring Boot
    - Postman
    - React
    - Typescript
    - HTML & CSS

# Introduction

This application is used to verify NEO Asteroids and the details on nearby asteroids using NASA Asteroids API.

# Development

# ✨ Backend Build

Locally, build the application using:

`gradle clean build`

This will build and package only the backend application. So, try to run both frontend and backend separately

### Testing
Backend is exposed on port 8099. So, please find the local API calls as below.
- http://localhost:8099/api/asteroids/3744994 -> To fetch Asteroid details where 3744994 is the Asteroid Id
- http://localhost:8099/api/asteroids/week/2015-09-17/2015-09-18 -> Start date is the first path variable and End Date the second path variable

## 💅 Frontend Build

Use these commands within the `/frontend`-folder 🎉

```shell
# Fetch all
npm install
# Build
npm run build
# Run
npm run start
# Test
npm run test
```
### Testing
Once both Backend and Frontend are up, hit the http://localhost:3000/ to verify the app from Ux