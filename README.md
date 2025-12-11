# AI Task Suite – Automation Test Framework
### Selenium | Java | TestNG | Page Object Model | Maven

This repository contains the automated test framework for the **AI Task Suite** platform.  
The framework is designed using **Selenium WebDriver**, **Java**, **TestNG**, **Page Object Model (POM)**, and **Maven**, ensuring clean code structure, reusability, and scalability.

---

# 🧪 Automation Coverage – Complete Module-wise Summary

This document provides the consolidated automation coverage implemented across all modules of the platform. It includes positive, negative, validation, edge-case, and role-based test scenarios.

---

## 🔐 Authentication Module

-  Login (valid & invalid scenarios)
-  Forgot Password flow
-  Email case-sensitivity handling
-  OTP validation
-  Session invalidation after password reset
-  Inline validation (password mismatch, invalid format)
-  Handling inactive user during password reset

---

## 👤 User Management Module

-  Add User flow automation
-  Edit User (including missing first name mandatory-field scenario)
-  Delete User
-  Role assignment & unassignment
-  Scope validation (Annotator shouldn’t access Admin/Dashboard)
-  Trim fields prior to API submission
-  Duplicate email/phone validations
-  Inline form validations

---

## 🗂 Dataset Management Module

-  Dataset creation (Drag & Drop)
-  Dataset creation (Browse Files)
-  File type restrictions (JPEG/PNG only)
-  Unique dataset name (case-insensitive)
-  Trim dataset name before DB save
-  View images in grid/list mode
-  Image zoom functionality
-  Download image
-  Upload behavior under:
    - Network loss
    - Network change
-  Large dataset upload (10,000+ images)
-  Subfolder handling
-  Corrupted image handling
-  Empty folder validation
-  Assign dataset to projects
-  Project mapping list on dataset card
-  Raw images visible only after linking dataset to a project

---

## 📁 Project Management Module

-  Create / Edit / Delete projects
-  Unique project name validation (case-insensitive)
-  Trim project name before save
-  Date validations:
    - Past dates
    - End date earlier than start date
-  Edit project allowing past date scenario
-  Sector → Sub-sector → Class visibility checks
-  Mapping dataset to project
-  Prevent duplicate dataset mapping
-  Assign annotators
-  Progress calculation
-  Search project functionality
-  Pagination behavior
-  Tooltip validation
-  Annotation-type icon before class tag name

---

## 🏷 Class Tags Module

-  Create class tag
-  Edit/Delete class tag
-  Annotation-type icon before class name
-  Trim class tag name before API
-  Special character validations
-  Duplicate class tag name validation
-  Display tags based on subsector selection

---

## 📊 Quick Stats Panel

-  GPU, CPU, Memory real-time stats validation
-  Active vs. Inactive user count
-  Server info accuracy
-  Performance under delayed API responses

---

## 🖼 Annotation Workspace Module

-  Load raw & annotated images
-  Correct image filtering
-  Zoom boundaries (max/min)
-  Drag & pan image movement
-  Annotating multiple shapes
-  Clear annotation action
-  Overlapping shapes crash validation
-  Auto-save functionality
-  Manual save button issue tested (failure scenario)
-  Duplicate annotations on drag issue
-  Annotation color based on class tag (not annotation type)
-  Slow image loading performance validation
-  Annotator sees only assigned images
-  Admin sees all annotation details

---

## 👥 Role-Based Access (Scopes)

-  Annotator restricted from Admin Dashboard
-  Developer access limited to assigned projects
-  Annotator progress calculated only from assigned images
-  404 redirection logic based on role
-  Logout issue validation
-  Restricted modules hidden/disabled as per scope

---

## 🚀 Project Overview

The AI Task Suite is a web-based annotation and project management platform.  
This automation framework validates critical modules such as:

- Authentication (Login, Logout, Forgot Password)
- User Management
- Dataset Management
- Projects
- Sub-sectors
- Class Tags
- Annotation Workflows
- Quick Stats Dashboard

The goal of this framework is to provide **fast, reliable, and repeatable automated tests** suitable for CI pipelines.

---

## 🚀 Key Features

- 🌐 **Browser Management:** Supports Chrome, Firefox, and Edge with headless mode options
- 📄 **Page Object Model (POM):** Clean, maintainable structure with dedicated page classes
- ✅ **TestNG Integration:** Parallel execution, grouping, reporting
- ⚙️ **Config Management:** `config.properties` for URL, browser, and credentials
- 🛠️ **Utility Methods:** Waits, dropdown handlers, alerts, advanced user actions
- 📸 **Screenshot on Failure:** Automatic screenshot capture
- ⏳ **Explicit Waits:** To support dynamic and asynchronous UI behavior
- 📤 **File Upload:** AWT Robot automation
- 🧑‍💻 **Multi-Role Support:** Admin, annotator, and developer workflow automation
- 📚 **Comprehensive Page Objects:** For all modules (Login, Users, Datasets, Projects, Annotation, etc.)

---

## 🛠️ Tech Stack

| Component       | Version/Tool                           |
|-----------------|----------------------------------------|
| Language        | Java (JDK 21 or above)                 |
| Automation Tool | Selenium WebDriver                     |
| Test Framework  | TestNG                                 |
| Build Tool      | Maven                                  |
| Design Pattern  | Page Object Model                      |
| Logging         | TestNG + Console                       |
| Reporting       | TestNG HTML / ExtentReports (optional) |

---

## 📦 Getting Started

### Prerequisites
- Java (JDK 21+)
- Maven
- Chrome / Firefox / Edge
- Browser WebDriver

### Installation

1.  Clone the repository:
    
    ```bash
    git clone https://github.com/aakashkumar97/AI-Task-Suite.git
    ```
    
2.  Navigate to the project directory:
    
    ```bash
    cd AI-Task-Suite
    ```
    
3.  Build the project with Maven:
    
    ```bash
    mvn clean install
    ```
    
4.  Configure the `config.properties` file with the appropriate application URLs, browser settings, and user credentials.
    

### Running Locally

1.  Ensure that the WebDriver executable for your chosen browser is in your system's PATH or specify the path in the `config.properties` file.
    
2.  Run the tests using TestNG:
    
    ```bash
    mvn test
    ```
    
    Alternatively, you can run the tests from your IDE (e.g., IntelliJ IDEA, Eclipse) by configuring a TestNG run configuration.

---

## 🧱 **Framework Architecture**

This project follows the **Page Object Model (POM)** design pattern:

```
AI-Task-Automation/
 ├── src/
 │    ├── main/
 │    │    ├── java/
 │    │    │    ├── base/         → WebDriver setup, launch/teardown
 │    │    │    ├── pages/        → Page Object classes (Login, Dataset, Projects…)
 │    │    │    └── utils/        → Waits, reusable actions, config loader, screenshots
 │    │    └── resources/
 │    │         ├── Screenshots/  → Auto screenshots (passed/failed)
 │    │         ├── config/       → config.properties
 │    │         └── testdata/     → Excel/CSV/JSON files
 │    └── test/
 │         └── java/
 │              └── tests/        → TestNG test classes for all modules
 ├── pom.xml                       → Maven dependencies
 └── testng.xml                    → Test suite runner
                
```

## 👤 Author

**Aakash Kumar**  
*Software Test Engineer*    

**GitHub:** [https://github.com/aakashkumar97](https://github.com/aakashkumar97)

