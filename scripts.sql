CREATE DATABASE *enter database name*


CREATE TABLE Authors (
    author_id INT PRIMARY KEY,
    name VARCHAR(20)
);


CREATE TABLE Books (
    book_id INT PRIMARY KEY,
    name VARCHAR(30),
    quantity INT,
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES Authors(author_id)
);



CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(20),
    age INT
);


CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    payment INT,
    customer_id INT,
    book_id INT,
    orders_quantity INT,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
    FOREIGN KEY (book_id) REFERENCES Books(book_id)
);