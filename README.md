# Library Management System

A desktop admin panel for managing a library's books, members, and loans.
Built with Java  and MySQL.

Built to handle the day-to-day of running a small library: tracking inventory, managing borrower records, issuing/returning books, and a dashboard with live stats and an interactive chart.

## Screenshots

*Sign up page*
<img width="1514" height="825" alt="image" src="https://github.com/user-attachments/assets/2b91a708-4a4a-4f13-8bd9-5e5a17eaffe5" />

*Dashboard: live user/book/loan counts and a ring chart breaking down loan status*
<img width="1902" height="1020" alt="image" src="https://github.com/user-attachments/assets/dd9e66ab-4902-4579-83a1-5e9a0e8353c6" />

*Books Management: full CRUD with click-to-edit table rows*
<img width="1902" height="1021" alt="image" src="https://github.com/user-attachments/assets/26671b66-136e-4df0-878c-ca104a4a52ec" />

*Issue Book: looks up a book and a user by ID, validates before creating the loan*
<img width="1901" height="1021" alt="image" src="https://github.com/user-attachments/assets/31637910-a2a8-4071-8057-87e3c3123748" />

*Records: full loan history with one-click status filtering and automatic overdue detection*
<img width="1904" height="1022" alt="image" src="https://github.com/user-attachments/assets/caeb9507-a0d8-488e-95bf-931e4cf22809" />


## Features

- **Auth**: Sign up and login backed by MySQL, with duplicate-username detection on signup and credential validation on login. Empty or invalid fields are caught before hitting the database.
- **Dashboard**: Three live stat cards (users, books, active loans), scrollable tables of all users and books, and a ring chart showing the percentage of loans that are pending, returned, or overdue.
- **Books & User Management** — Full CRUD for the catalog and the member list; clicking a row auto-fills the form for editing.
- **Issue Book**: Looks up a book and a user by ID, blocks the loan if either isn't found or if that user already has that book out, then creates the loan and decrements stock.
- **Return Book**: Looks up the active loan by book + user ID, marks it returned, and restores stock.
- **Records**: Searchable loan history by date range, plus a one-click filter (Pending / Returned / Overdue) 

## Technical Highlights

- **Strict Input Handling**: Every form validates input and reports specific errors before touching the database. No silent failures or blank screens.
- **Real-Time Analytics**: The dashboard and pie chart pull live counts straight from MySQL on every page load instead of using hardcoded placeholder data.
- **Dynamic Calculation**: Overdue status is calculated on the fly by comparing `due_date` against the current date — no extra column or manual status updates needed.
- **Instant UI Synchronization**: All CRUD operations (Books, Users, Issue/Return) write directly to MySQL through JDBC and refresh the UI immediately after each change.
- **Custom Design**: Custom UI built from scratch with Netbeans. Every screen, color, and layout decision was designed manually.

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
