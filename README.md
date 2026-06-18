# Library Management System

A desktop admin panel for managing a library's books, members, and loans.
Built with Java  and MySQL.

Handles the day-to-day of running a small library: tracking inventory, managing borrower records, issuing/returning books, and a dashboard with live stats and an interactive chart.

## Screenshots

<img width="1514" height="825" alt="image" src="https://github.com/user-attachments/assets/2b91a708-4a4a-4f13-8bd9-5e5a17eaffe5" />

<img width="1903" height="1021" alt="image" src="https://github.com/user-attachments/assets/f99b5625-2036-47dd-aa84-f273f44b1e34" />

<img width="1901" height="1019" alt="image" src="https://github.com/user-attachments/assets/f575bec3-32dd-4525-8a6d-99b449f25059" />

<img width="1902" height="1021" alt="image" src="https://github.com/user-attachments/assets/1a155d01-3571-4dd4-a73d-b90b20a3d0ff" />

<img width="1899" height="1017" alt="image" src="https://github.com/user-attachments/assets/225b45be-6111-4c35-91a6-7c6ed60b446b" />

<img width="1902" height="1021" alt="image" src="https://github.com/user-attachments/assets/a762b753-c698-4648-b190-5b254ea01e45" />


## Features

- **Dashboard** — live counts of users, books, and active loans, plus a ring chart breaking down returned/pending/overdue loans
- **Books & User Management** — full CRUD with click-to-edit table rows
- **Issue / Return Books** — validates duplicate loans, auto-updates stock quantities, and tracks due dates
- **Records** — searchable loan history with a one-click filter (All / Pending / Returned / Overdue) and automatic overdue detection
- **Auth** — sign up / login with duplicate-username validation and a personalized welcome message

## Tech Stack

Java (Swing) · MySQL · NetBeans 

---


<details>
<summary><b>Project Structure</b></summary>

```
src/jframe/
├── DBConnection.java       # MySQL connection
├── LoginPage.java           # Login + validation
├── SignUpPage.java           # Account creation + validation
├── Session.java              # Stores the logged-in user
├── HomePage.java             # Dashboard
├── BooksManagement.java      # Book CRUD
├── UserManagement.java       # User CRUD
├── IssueBook.java             # Issue books
├── ReturnBook.java            # Process returns
└── Records.java               # Loan history + filters
```

</details>
