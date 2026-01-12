# Java Docker Weather Converter

This repository contains a simple Java application that converts temperatures from Celsius to Fahrenheit. The project is fully containerized using Docker.

## Project Structure
* `WeatherConverter.java`: The main Java source code.
* `Dockerfile`: Instructions for Docker to build and run the Java app.
* `README.md`: Project documentation.

## How to Run with Docker
If you have Docker installed, you can build and run this project with two commands:

1. **Build the Image:**
   ```bash
      docker build -t java-weather-app .
2. Run the Container:
docker run java-weather-app

## Formula Used
The conversion uses the standard formula:

```
F = (C × 9/5) + 32
```
