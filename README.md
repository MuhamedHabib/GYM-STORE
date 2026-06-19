# GYM-STORE

> A JavaFX desktop application for managing a gym store — products, customer orders, email, and sales statistics — backed by a MySQL database.

![Java](https://img.shields.io/badge/Java-8-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-8-1F8AC0?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-JDBC-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JFoenix](https://img.shields.io/badge/JFoenix-Material%20UI-2196F3?style=for-the-badge)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)

## Overview

GYM-STORE is a student project: a Java desktop application built with **JavaFX** that
provides a simple back-office for a gym store. It lets an operator manage a product
catalog, register and track customer orders, contact customers by email, and view
basic sales statistics. The UI is composed of FXML views styled with the
[JFoenix](https://github.com/sshahine/JFoenix) Material Design components and
animated with [AnimateFX](https://github.com/Typhon0/AnimateFX). Data is persisted
to a **MySQL** database through plain JDBC.

The project follows a layered structure (entities, service interfaces and
implementations, helpers, and FXML controllers) and is set up as an **IntelliJ IDEA**
project with its dependencies bundled as local JAR files.

## Features

- **Product catalog management** — list products in a `TableView`, add, edit and
  delete entries, with inline edit/delete icons rendered per row
  (`ProductViewControllor`, `ServiceProduct`).
- **Customer / order management** — full CRUD over customer orders, including a form
  with field validation (name, email and post-code checks via regular expressions),
  a live search/filter over the table, and an image picker for the customer photo
  (`commandsControllor`, `ServiceCommande`).
- **Email sending** — send a message to a customer over Gmail SMTP using JavaMail
  (`javaMail`).
- **Sales statistics** — a pie chart comparing total customers and total products,
  with a pop-up date picker (`StatisticsController`, `DatabaseHelper`).
- **Storefront-style platform view** — a card-based UI that loads product/customer
  items (including a "recommended" section) and plays background audio
  (`PlateformeUI`, `item.fxml` / `item2.fxml`).

> Note: this is coursework. Some controller actions are placeholders, validation is
> basic, and SQL is built via string concatenation — see [Notes](#notes).

## Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 8 |
| UI | JavaFX (FXML), CSS |
| UI components | JFoenix, ControlsFX, FontAwesomeFX |
| Animation | AnimateFX |
| Email | JavaMail (`javax.mail`) |
| Database | MySQL / MariaDB via JDBC (`mysql-connector-java 8.0.23`) |
| IDE / project | IntelliJ IDEA (manual JAR dependencies in `dependencies/`) |

## Project Structure

```
GYM-STORE/
├── src/
│   ├── sample/            # Application entry point (Main extends Application)
│   ├── Controllor/        # JavaFX/FXML controllers (views' logic)
│   ├── Entity/            # Domain models: Product, command, category, reservation
│   ├── IService/          # Generic CRUD service interface
│   ├── Service/           # Service implementations (ServiceProduct, ServiceCommande, ...)
│   ├── Helpers/           # DbConnect (JDBC), DatabaseHelper, javaMail
│   ├── animations/        # Animation helpers
│   ├── gui/               # FXML view definitions
│   ├── css/ , public/     # Stylesheets and image assets
│   └── resources/         # Fonts, images, media
├── dependencies/          # Bundled third-party JARs
├── hebergement.sql        # Sample SQL dump (legacy data)
└── GymStore.iml           # IntelliJ module file
```

## Getting Started

> The project is configured for **IntelliJ IDEA** with **JDK 8** and uses the JAR
> files in `dependencies/` as project libraries (there is no Maven/Gradle build
> descriptor checked in).

**Prerequisites**

- JDK 8 (the project is configured against a 1.8 language level)
- A running MySQL / MariaDB server
- IntelliJ IDEA (recommended, since the repo ships `.idea/` and `GymStore.iml`)

**Steps**

1. Clone the repository:
   ```bash
   git clone https://github.com/MuhamedHabib/GYM-STORE.git
   ```
2. Open the project in IntelliJ IDEA. The JARs under `dependencies/` are referenced
   as project libraries; make sure they are added to the module if the IDE prompts.
3. Create a MySQL database and the tables the app expects. From the code, the
   application reads/writes two main tables:
   - `products` — columns `id`, `title`, `image`, `price`, `category_id`
   - `commande` — columns `id`, `nom_c`, `prenom_c`, `email`, `ville`,
     `code_postale`, `adresse`, `telephone`
4. Configure the database connection in `src/Helpers/DbConnect.java` (host, port,
   database name, username and password) to match your local MySQL instance.
5. To use email sending, set your own SMTP credentials in `src/Helpers/javaMail.java`.
6. Run the `sample.Main` class to launch the JavaFX application.

## Notes

- This is an academic / learning project. It is shared as-is to demonstrate JavaFX
  desktop development with a JDBC-backed data layer.
- Database and email credentials are read from source files
  (`DbConnect.java`, `javaMail.java`). **Do not commit real credentials** — replace
  the placeholders with your own local values and keep them out of version control.
- SQL statements are built through string concatenation; for production use these
  should be migrated to parameterized `PreparedStatement`s to avoid SQL injection.
- The bundled `hebergement.sql` dump is legacy sample data and does not match the
  `products` / `commande` schema the application actually uses.
- Several file paths and resource references in the code are hard-coded to a local
  machine and would need to be adjusted for another environment.
- Some original UI text and comments are in French.

---
<p align="center">Built by <b>Mohamed Habib Khattat</b> — <a href="https://github.com/MuhamedHabib">GitHub (@MuhamedHabib)</a> · <a href="https://www.linkedin.com/in/mohamed-habib-khattat-2b206a173">LinkedIn</a></p>
