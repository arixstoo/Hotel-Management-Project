# **Hotel Management Project**

#### Hi there 👋 it's arixstoo again !
## Project Description
The project was developed in collaboration with a team (the collaborators of this repository). The project is a hotel management system, developed in Java with a user interface using Swing and a database using MongoDB. The application allows users to make room reservations, display details of available rooms, and manage existing reservations. The application also provides a complete interface and control for the hotel admins and receptionists.

## Technologies Used
**- Programming Language** : **Java**

**- GUI Library** : **Swing**

**- Database Management** : **MongoDB** (via the Java MongoDB driver)

**- Collections and Maps** : Before integrating MongoDB, the data was stored using **Maps** in Java to organize and manipulate information.

**- MVC (Model-View-Controller)** : Architecture used to **organize** the application's code in a **modular** and maintainable way.

## Project Structure: MVC Architecture (Model-View-Controller)
The **MVC** architecture divides the application into three main components:

### **Model**:
Represents the **data** of the application. In this project, it includes classes like `Chambre`, **Client**, and `Réservation` that interact with **MongoDB** to manage data. These classes centralize the manipulation and validation of data.

### **View**:
Handles the **user interface** of the application. It consists of Swing windows and panels, such as in the `reservation` class, allowing users to **select** rooms and **enter** dates.

### **Controller**:
Coordinates the **interactions** between the model and the view. It captures user actions **(button clicks)** and updates the model accordingly. For example, in the `reservation` class, the **"continuer"** button triggers model updates and displays error messages if necessary.

## Key Features
**Make Reservations**: Allows online reservations by **selecting** available rooms and providing check-in and check-out **dates**.

**Room Modification**: Available rooms are displayed with **their characteristics**, such as type and price. Users can select desired rooms for **their reservation**.

**Date Validation**: The system checks that the entered dates are in the **correct** format and that they are **valid** to avoid input errors.

## Administrative Features
The admin side of the application offers additional functionalities for managing reservations and rooms:

**View Reservations**: Administrators can access an interface displaying all **reservations** made, with options to **accept or deny** bookings. Reservations are listed with **relevant details** such as client name, stay dates, and reserved rooms.

**Room Management**: Administrators can see all **available** rooms and their status (free or occupied). They have the option to update room information, such as **price** and type.

**Hotel Management**: Administrators can **modify** details of existing reservations, including adding or **removing** rooms, as well as changing **stay dates**.

## Instructions for Running
### Clone the Repository:
Write in the bash: `git clone https://github.com/arixstoo/Hotel-Management-Project.git`

### Configure the Project:
Ensure Java is installed on your machine.  
Import the project into your preferred Java IDE.

### Install Dependencies:
Make sure you have the necessary dependencies for MongoDB (add them to the `pom.xml` file if you're using Maven or `build.gradle` for Gradle).

### Run the Application:
Execute the class `/Controller/main` to start the application.

## 🧰 Languages and Tools
        

<img align="left" alt="GitHub" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/github/github-original.svg" />
<img align="left" alt="Git" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" />
<img align="left" alt="VsCode" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/vscode/vscode-original.svg" />
<img align="left" alt="IntelliJ" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/intellij/intellij-original.svg" />
<img align="left" alt="Java" width="30px" style="padding-right:10px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg"/>
<br />

## 📊 Reach me here:

[LinkedIn]: <a href="https://www.linkedin.com/in/saidi-mohamed-rostom-21b3b525a/" target="_blank">arixstoo</a>
<br />
[E-mail]: saidi.rostom26@gmail.com
<br />
[Discord]: arixstoo
