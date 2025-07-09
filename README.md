<p align="center">
  <img src="src/main/resources/media/techmaps-brand-shortened-logo.png" height="128" alt="TechMaps Logo">
</p>

# What is TechMaps?

**TechMaps** is an open-source project that aims to provide students with roadmaps covering various topics in the software development world.

This repository's project is built using **Java 22**, the **Spring ecosystem**, and **PostgreSQL**, and it serves as a back-end API for the TechMaps application.

---

# Setting Environment Variables

To run the application, you need to set up some environment variables. You can do this by creating a file named `.env` in the root directory of the project and adding the following lines:

```bash
SECRET_KEY=your_really_really_strong_secret_key
```

---

# Running the Application Through Docker

To run the application through Docker, you need to have Docker installed on your machine along with **swarm mode enabled**.

To enable swarm mode, run the following command:

```bash
docker swarm init
```

Then, open the terminal in the root directory of the project and run the following command to deploy the application:

```bash
make deploy
```

To check other commands available in the project's Makefile, you can run:

```bash
make help
```

---

# Accessing the Application

Great! The application is now running on:

[http://localhost:8757](http://localhost:8757)

---

# Checking the Documentation

To check the API documentation, you can visit:

[http://localhost:8757/swagger-ui/index.html](http://localhost:8757/swagger-ui/index.html)

---

# Thank you, contributors!

<a href="https://github.com/yellowisk/TechMaps-API/graphs/contributors" target="_blank" rel="noopener noreferrer">
  <img src="https://contrib.rocks/image?repo=yellowisk/TechMaps-Back-v2" alt="Contributors">
</a>
