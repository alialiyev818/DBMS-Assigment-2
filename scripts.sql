-- Create Authors Table
CREATE TABLE Authors (
    AuthorID SERIAL PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Bio TEXT
);


-- Create Books Table
CREATE TABLE Books (
    BookID SERIAL PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    AuthorID INT REFERENCES Authors(AuthorID),
    ISBN VARCHAR(20),
    Price DECIMAL(10, 2),
    Stock INT NOT NULL,
    PublishedDate DATE
);


-- Create Customers Table
CREATE TABLE Customers (
    CustomerID SERIAL PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100),
    Address TEXT,
    PhoneNumber VARCHAR(20)
);

-- Create Orders Table
CREATE TABLE Orders (
    OrderID SERIAL PRIMARY KEY,
    CustomerID INT REFERENCES Customers(CustomerID),
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TotalAmount DECIMAL(10, 2)
);

-- Create OrderDetails Table to manage the many-to-many relationship between Orders and Books
CREATE TABLE OrderDetails (
    OrderID INT REFERENCES Orders(OrderID),
    BookID INT REFERENCES Books(BookID),
    Quantity INT NOT NULL,
    PriceAtTimeOfOrder DECIMAL(10, 2),
    PRIMARY KEY (OrderID, BookID)
);
