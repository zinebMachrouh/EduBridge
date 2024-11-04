# 🎓 Welcome to EduBridge! 🎓

## Hello, Education Enthusiasts! 👋

Welcome to **EduBridge**, a comprehensive REST API designed to manage a professional training platform efficiently. **EduBridge** leverages modern technologies like **Java, Spring Boot**, and **Hibernate** to provide a robust and scalable platform for handling courses, students, and instructors.

## 🚀 About EduBridge

**EduBridge** enables administrators to manage courses, students, and instructors through an intuitive API interface. With **CRUD** functionality for courses, students, and instructors, **EduBridge** ensures a seamless experience for managing educational content and user data.

## 📁 Project Structure

Here's an overview of the project structure for **EduBridge**:

- `config`: Contains configuration classes for **Spring Boot** and **Exceptions**.
- `controllers`: Handles HTTP requests for managing trainings, trainers, learners, and classes.
- `dto`: Data Transfer Objects facilitating data transfer between layers.
- `models`: Classes representing core entities: `Training`, `Trainers`, `Classes` , `Learners`, and associated enums.
- `repositories`: Managing database queries.
- `services`: Business logic layer for managing trainings, trainers, learners, and classes.
- `resources`: Contains configuration files such as `application.properties` and database setup scripts.

## 🧩 Key Features

- **Training Management**: Create, update, and delete trainings.
- **Trainer Management**: Add, update, and remove trainers.
- **Learner Management**: Manage learners by adding, updating, and deleting them.
- **Class Management**: Create, update, and delete classes.
- **Secure Data Handling**: Ensures data integrity and security.
- **Unit Tests**: Using **JUnit** and **Mockito** to test business and data access components.

## 🌐 API Endpoints

Here are the key API endpoints for **EduBridge**:

- **Trainings**:
  - `GET /api/trainings`: Get all trainings.
  - `GET /api/trainings/{id}`: Get a training by ID.
  - `POST /api/trainings`: Add a new training.
  - `PUT /api/trainings/{id}`: Update an existing training.
  - `DELETE /api/trainings/{id}`: Delete a training by ID.
  

- **Trainers**:
  - `GET /api/trainers`: Get all trainers.
  - `GET /api/trainers/{id}`: Get a trainer by ID.
  - `POST /api/trainers`: Add a new trainer.
  - `PUT /api/trainers/{id}`: Update an existing trainer.
  - `DELETE /api/trainers/{id}`: Delete a trainer by ID.


- **Learners**:
  - `GET /api/learners`: Get all learners.
  - `GET /api/learners/{id}`: Get a learner by ID.
  - `POST /api/learners`: Add a new learner.
  - `PUT /api/learners/{id}`: Update an existing learner.
  - `DELETE /api/learners/{id}`: Delete a learner by ID.


- **Classes**:
  - `GET /api/classes`: Get all classes.
  - `GET /api/classes/{id}`: Get a class by ID.
  - `POST /api/classes`: Add a new class.
  - `PUT /api/classes/{id}`: Update an existing class.
  - `DELETE /api/classes/{id}`: Delete a class by ID.

## 🎯 Project Objectives

- Develop a robust system for managing professional training platforms.
- Implement CRUD operations for managing training, trainers, learners, and classes.
- Use **JUnit** and **Mockito** for unit testing.
- Maintain clear separation of concerns with an MVC architecture.

## 🌐 SWAGGER UI

- **Swagger UI** is a tool that visually presents the API documentation. It provides a user-friendly interface for exploring the API endpoints and their functionalities.
- To access the **Swagger UI** for **EduBridge**, run the application and navigate to `http://localhost:8081/swagger-ui/index.html` in your browser.

## 🧩 Postman Collection

- **Postman** is a popular API client that allows you to test API endpoints and monitor responses.
- To access the **Postman Collection** for **EduBridge**, click [here](https://web.postman.co/workspace/My-Workspace~5068411f-ba84-490f-827d-09a1db076e70/collection/33286297-9de56598-fa5d-4226-9d84-8371935fa803).

## 🛠️ How to Use EduBridge

### Prerequisites

Before running **EduBridge**, ensure you have the following installed:

- **Java 8** or later
- **Maven** for project build and dependency management
- **Spring Boot** for developing RESTful APIs
- **H2 Database** for in-memory database testing
- **PostgreSQL** or any other relational database
- **Postman** for testing API endpoints
- **Swagger UI** for API documentation

### Running the Application

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/zinebMachrouh/EduBridge.git
   cd EduBridge
   ```

2. Update the `application.properties` file in the `resources` directory with your database connection details.

3. Build and run the application using Maven:
   ```bash
   mvn clean install
   ```

## 🎉 Get Started with EduBridge Today!

For any questions, feedback, or suggestions, feel free to reach out to us. 📧