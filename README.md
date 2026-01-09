# 📊 Expense Tracker API (v1)

A **high-performance, modular backend service** for tracking finances, built with **Spring Boot 4** and **Java 25**.  
Designed for **cloud-native deployments**, this API leverages **modern JVM features** like **Virtual Threads** and **JSpecify null safety** to deliver scalability, reliability, and clean architecture.

---

## 🚀 Tech Stack & Baseline

- **Java 25 (LTS)**
  - Flexible Constructor Bodies (**JEP 513**)
  - Scoped Values (**JEP 506**)
- **Spring Boot 4.0.1**
  - Built on **Spring Framework 7**
  - Internal modularization for faster startup and reduced footprint
- **Jakarta EE 11**
  - Persistence (**JPA 3.2**)
  - Validation (**3.1**)
- **Database**
  - H2 (Development)
  - PostgreSQL (Production)

---

## ✨ Key Features (Spring Boot 4)

- **Modular Auto-Configuration**  
  Reduced classpath scanning and improved startup performance through Spring Boot 4’s internal modularization.

- **Native API Versioning**  
  Uses the `version` attribute in `@RequestMapping` for clean and explicit API lifecycle management.

- **Virtual Thread–Enabled Request Handling**  
  Configured to leverage Java 25 Virtual Threads for efficient handling of concurrent requests with minimal resource overhead.

- **Clean Layered Architecture**  
  Clear separation of controller, service, and persistence layers to improve maintainability and testability.

- **RESTful API Design**  
  Consistent HTTP semantics with well-defined request and response models following REST best practices.

- **Environment-Specific Configuration**  
  Supports H2 for local development and PostgreSQL for production via externalized configuration.

- **Production-Ready Health Checks**  
  Spring Boot Actuator integration for application health and readiness monitoring.

- **GraalVM Native Image Ready**  
  Optimized build configuration to support native compilation for faster startup and reduced memory usage.


---

## 🛠️ Installation & Setup

### Prerequisites

- **JDK 25** (Oracle JDK or OpenJDK 25+)
- **Maven 3.9+** or **Gradle 9.0+**

---

### Getting Started

#### 1️⃣ Clone the Repository

```bash
git clone https://github.com/agxmm01/expense-tracker.git
cd expense-tracker
