# 🏨 Tourism Agency Management System

[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📋 About the Project

**Tourism Agency Management System** is a comprehensive management system that enables companies operating in the hotel sector to manage their daily operations more effectively and optimize customer reservation processes.

### 🎯 Main Purpose

Patika Tourism Agency makes agreements with many hotels and reserves hotel rooms for customers. With this system:

- **Admin Management**: The first admin user is registered in the database by the software developer
- **User Management**: People with admin authority can add both admin and agency employees to the system
- **Hotel and Room Management**: Agency employees register hotels and rooms in the system
- **Reservation System**: Room search and reservation according to customer demands
- **Flexible Pricing**: Automatic calculation with detailed hotel and room management

## ✨ Features

### 🔐 User Management

- Admin and employee user roles
- Secure login system
- User authorization

### 🏨 Hotel Management

- Add, edit, delete hotels
- Hotel details and information
- Hotel search and filtering

### 🛏️ Room Management

- Add and edit rooms
- Room types and features
- Room availability status

### 📅 Season Management

- Season definition
- Dynamic pricing
- Date-based price settings

### 🏠 Pension Management

- Pension types
- Meal plans
- Pension pricing

### 📋 Reservation System

- Customer reservations
- Room search and filtering
- Automatic price calculation
- Reservation history

## 🛠️ Technology Stack

- **Programming Language**: Java 8+
- **Database**: PostgreSQL
- **Interface**: Java Swing
- **Architecture**: MVC (Model-View-Controller) Pattern
- **Database Connection**: JDBC

## 📁 Project Structure

```
src/
├── App.java                 # Main application entry point
├── business/               # Business logic layer
│   ├── HotelManager.java
│   ├── PensionManager.java
│   ├── ReservationManager.java
│   ├── RoomManager.java
│   ├── SeasonManager.java
│   └── UserManager.java
├── core/                   # Core components
│   ├── ComboItem.java
│   ├── Db.java            # Database connection
│   └── Helper.java        # Helper functions
├── dao/                    # Data access layer
│   ├── HotelDao.java
│   ├── PensionDao.java
│   ├── ReservationDao.java
│   ├── RoomDao.java
│   ├── SeasonDao.java
│   └── UserDao.java
├── entity/                 # Data models
│   ├── Hotel.java
│   ├── Pension.java
│   ├── Reservation.java
│   ├── Room.java
│   ├── Season.java
│   └── User.java
└── view/                   # User interface
    ├── AdministrationView.java
    ├── AdminView.java
    ├── EmployeeView.java
    ├── HotelView.java
    ├── Layout.java
    ├── LoginView.java
    ├── PensionView.java
    ├── ReservationView.java
    ├── RoomView.java
    ├── SeasonView.java
    └── UserView.java
```

## 🚀 Installation and Setup

### Requirements

- **JDK**: Minimum JDK 8
- **PostgreSQL**: Database server
- **IDE**: IntelliJ IDEA, Eclipse or NetBeans

### Installation Steps

1. **Clone the project**

   ```bash
   git clone https://github.com/halilibrahimsaltas/TourismAgency.git
   cd TourismAgency
   ```

2. **Set up the database**

   - Start your PostgreSQL server
   - Run the `tuorismagency.sql` file in your database

3. **Configure database connection**

   - Edit the `database.properties` file
   - Enter your database information

4. **Run the project**
   ```bash
   javac -cp ".:postgresql-42.7.3.jar" src/*.java src/*/*.java
   java -cp ".:postgresql-42.7.3.jar:src" App
   ```

## 📸 Screenshots

### Login Screen

![Login Screen](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/4ccb0846-e065-4b32-a0f8-7281fc6b7dbc)

### User Addition

![User Addition](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/f886c6c5-01b9-4cb3-a98e-a5aa61e5a13f)

### Hotel Management

![Hotel Management](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/859ad5a8-0c00-4df8-84b7-76f9b58afedd)

### Room Management

![Room Management](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/efe60788-8502-4565-96c6-693c1e8dfcbf)

### Reservation System

![Reservation](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/5f112df8-6b03-4e9c-8370-8681ab8861f1)

## 🎥 Demo Video

To watch the detailed demo video of the project:
[Patika + | Tourism Agency Management Project](https://www.youtube.com/watch?v=vKMdBdOzGSQ&ab_channel=Halil%C4%B0brahimSALTA%C5%9E)

## 🤝 Contributing

1. Fork this repository
2. Create a new branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push your branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## 👨‍💻 Developer

**Halil İbrahim SALTAŞ**

- GitHub: [@halilibrahimsaltas](https://github.com/halilibrahimsaltas)

### Screenshots

![entery-0](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/4ccb0846-e065-4b32-a0f8-7281fc6b7dbc)
![entery](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/81a50d43-3f44-4df2-84d6-d1c99898b208)
![user add](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/f886c6c5-01b9-4cb3-a98e-a5aa61e5a13f)
![otel](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/859ad5a8-0c00-4df8-84b7-76f9b58afedd)
![otel add](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/38fb44e9-3ae1-49a7-bc04-8ab095e9798e)
![season](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/7b0ff27b-e01b-40d9-81e3-44136c9b412a)
![pension](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/9f4d104e-4e4b-4c73-adbc-d74e19a7f726)
![room](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/efe60788-8502-4565-96c6-693c1e8dfcbf)
![Room add](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/a639c601-7581-4c4a-a845-ce5606204904)
![resevation](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/5f112df8-6b03-4e9c-8370-8681ab8861f1)
![resevation add](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/50db8e1a-9250-46fe-bb0e-0485c0adc0c9)
![resevation2](https://github.com/halilibrahimsaltas/TourismAgency/assets/82754847/86ae5b68-a25b-4e5f-82b7-c441467990e8)

---
