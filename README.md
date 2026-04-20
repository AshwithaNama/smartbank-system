# SmartBank System

A simple **Java-based Banking System** that simulates real-world banking operations like registration, login, deposits, withdrawals, and transfers using **OOP principles**.

---

## Features

* User Registration & Login
* Secure Authentication (Password Hashing)
* Deposit & Withdraw Money
* Transfer Funds Between Users
* Transaction History Tracking
* Account Types:

  * Savings Account (minimum balance rules)
  * Current Account (overdraft support)

---

## Concepts Used

This project demonstrates core Java and OOP concepts:

* Classes & Objects
* Inheritance
* Polymorphism
* Abstraction
* Encapsulation
* Exception Handling
* Collections (`HashMap`, `ArrayList`)

---

##  Project Structure

```
SmartBank/
│
├── BankingApp.java        # Main (CLI UI)
├── BankUI.java            # GUI (Swing UI)
├── BankService.java       # Business Logic
├── Customer.java          # User Model
├── BankAccount.java       # Abstract Base Class
├── SavingsAccount.java    # Savings Logic
├── CurrentAccount.java    # Current Logic
├── AccountType.java       # Enum
└── Transactable.java      # Interface
```

---

## How to Run

### 1. Compile

```
javac *.java
```

### 2. Run (CLI version)

```
java BankingApp
```

### 3. Run (GUI version)

```
java BankUI
```

---

## Example Flow

```
1. Register User
2. Login
3. Perform Operations:
   - Deposit
   - Withdraw
   - Transfer
   - View Transactions
```

---

## Limitations

* No database (data resets on restart)
* Basic password hashing (not production secure)
* Single account per user

---

## 💡 Future Improvements

* Add database (MySQL / MongoDB)
* Build mobile or web UI
* Use stronger encryption (bcrypt)
* upport multiple accounts per user

---

## Author

**Ashwitha Nama**

---

## Project Goal

This project was built to understand how real banking systems work using Java and to practice clean code architecture with separation of concerns.

---

## 🎬 Final Note

> “This project separates UI, business logic, and data models — just like real-world applications.”

---
