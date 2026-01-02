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
  Reduced classpath scanning and improved startup performance.

- **Native API Versioning**  
  Uses the new `version` attribute in `@RequestMapping` for clean API lifecycle management.

- **Enhanced Observability**  
  Built-in **OpenTelemetry** integration via  
  `spring-boot-starter-opentelemetry`.

- **Virtual Threads (Java 25)**  
  Handles high-concurrency workloads with minimal resource usage.

- **JSpecify Null Safety**  
  Compile-time null checking for safer, more reliable code.

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
