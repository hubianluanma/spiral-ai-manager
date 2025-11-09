# Spiral AI Manager

[中文版 README](README_zh.md)

## Project Overview

Spiral AI Manager is a server-side application built with Spring Boot, Spring Security, and JPA, designed to provide a flexible and secure platform for managing and integrating various AI services. It currently supports Ollama, DeepSeek, and OpenAI, and is designed to easily extend support for more AI providers as needed.

## Technology Stack

- **Backend Framework**: Spring Boot
- **Database**: PostgreSQL
- **Persistence Layer**: JPA (Java Persistence API)
- **Security Authentication**: Spring Security (Role-based access control)

## Features

- **Multi-AI Provider Support**: Integrated support for Ollama, DeepSeek, and OpenAI.
- **Extensibility**: By defining a unified `AIProvider` interface, it's easy to add new AI service providers.
- **Security**: Utilizes Spring Security to implement robust authentication and authorization mechanisms, ensuring that only authorized users can access specific resources or perform specific actions.
- **Data Persistence**: Uses JPA for object-relational mapping (ORM), simplifying interactions with the PostgreSQL database.

## Project Status

Please note that this project is still under active development. We are working hard to improve various features and resolve known issues. If you have any suggestions or find any problems, please feel free to contact us!

## How to Contribute

We welcome contributions in any form! Whether it's submitting bug reports, proposing new features, or directly participating in code development, we greatly appreciate your help. Please refer to the [CONTRIBUTING.md](CONTRIBUTING.md) file for more information on how to contribute.

## Contact Information

For any questions or feedback, please email huhailong1121@gmail.com or huhailong1121@icloud.com.

---

This README provides an initial introduction to the Spiral AI Manager project. As the project continues to develop and improve, we will keep updating the documentation. Thank you for your interest and support in this project!