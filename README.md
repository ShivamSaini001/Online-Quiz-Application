# Online Quiz App

A Online Quiz web Application in Java that allows users to take quizzes on various topics. 
The application should support multiple-choice questions, track user progress, and provide feedback on quiz performance.

## Features

- User registration and login
- Admin panel for managing quizzes and users
- Real-time quiz results
- random question selection
- Question categorization
- Secure authentication using Spring Security (Implement password hashing and salting to securely store user passwords)

## Technologies
- **Backend**: Spring Core, Spring MVC, Spring Security, Spring JDBC
- **Frontend**: JSP, Bootstrap
- **Database**: MySQL
- **Dependency Management Tools**: Maven
- **IDs**: Eclipse Ide

### Project Setup (Make sure your internect is connected during setup process to download dependencies of your project)
- **step 1:** Softwares Require--
    - STS or Ecliplse <a href="https://spring.io/tools#eclipse">https://spring.io/tools#eclipse</a>
    - MySQL <a href="https://dev.mysql.com/downloads/installer">https://dev.mysql.com/downloads/installer</a>
    - Apache Tomcat Server <a href="">https://tomcat.apache.org/download-90.cgi</a>
- **step 2:** Go to github repo https://github.com/ShivamSaini001/Online-Quiz-Application
- **step 3:** Download Online-Quiz-Application project. 
- **step 4:** Extract zip folder and open ecliple or STS Ide. 
- **Step 5:** In eclipse Ide goto File->Import...->Maven->Existing Maven Projects
- **Step 6:** Select Root directory of your project (Online-Quiz-Application).
- **Step 7:** After completing stpe-5, configure Apache Tomcat server.
              To configure server follow these stpes:
  (i) go to Window -> Show view -> Other
  (ii) search server and select server.
  (iii) Select Apache tomcat server root directory (root directory of bin folder) from file system.

## Usage

1. Access the application at `http://localhost:8080/Online-Quiz-App` (or the port you've configured).
2. Register a new user or log in with admin credentials: 
   - **Username**: admin@example.com
   - **Password**: admin123
3. Navigate to:
   - **Admin Dashboard**: Manage quizzes, categories, and users.
   - **User Dashboard**: Take quizzes and view results.

## Contact

Created by [Shivam saini](shivamsaini3209@gmail.com) - feel free to reach out!


## User Interface

### Login Page
![Login Page](./images/Login%20Page.png)

### Register Page
![Register Page](./images/Register%20page.png)

### Home Page
![Home Page](./images/Home%20page.png)

### User Home Page
![User Home Page](./images/User%20Home%20Page.png)

### Admin Home Page
![Admin Home Page](./images/Admin%20home%20page.png)

### Admin Panel
![Admin Panel](./images/Admin%20pannel.png)

### Create Quiz Page
![Create Quiz Page](./images/Create%20Quiz%20Page.png)

### Manage Category
![Manage Category](./images/Manage%20category.png)

### Update Category
![Update Category](./images/Update%20category.png)

### Delete Category
![Delete Category](./images/Delete%20category.png)

### Manage Questions
![Manage Questions](./images/Manage%20Questinos.png)

### Add New Question
![Add New Question](./images/Add%20new%20question.png)

### Update Question
![Update Question](./images/Update%20Question.png)

### Quiz Question
![Quiz Question](./images/Quiz%20question.png)

### Quiz Question - Correct Answer
![Quiz Question - Correct Answer](./images/Quiz%20question%20correct%20answer.png)

### Quiz Question - Wrong Answer
![Quiz Question - Wrong Answer](./images/Quiz%20question%20wrong%20answer.png)

### Quiz Question - Not Attempted
![Quiz Question - Not Attempted](./images/Quiz%20question%20not%20attempt.png)

### Quiz Result
![Quiz Result](./images/Quiz%20result.png)

### Quiz History
![Quiz History](./images/Quiz%20History.png)