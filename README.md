# DBMS-Assigment-2

## Schema Creation

Design a bookstore database schema with tables for Books, detailing general book information and stock levels, and Customers, capturing order details.

Establish primary keys for each table to uniquely identify records.
Set up relationships between tables using foreign keys that adhere to a relational data model.
Visualize the relationships with an Entity-Relationship (ER) diagram.
Write SQL statements for creating the database tables based on the schema.

## Java Database Connectivity (JDBC)

Develop a Java application to interact with the database.

Implement robust exception handling to manage potential errors during database operations.

## CRUD Operations

Perform Create, Read, Update, and Delete (CRUD) operations on the database entities, which include:
i. Inserting new records into the database.
ii. Retrieving all book information, including details about authors and linked orders.
iii. Updating book records with new information.
iv. Deleting books from the database.
Handle exceptions effectively to ensure database integrity.

## Transaction Management

Manage transactions to ensure consistency, especially when orders affect book stock levels.
Coordinate inserts into the Orders table with updates to the Books table, ensuring inventory levels are considered.

## Metadata Access

Implement methods to retrieve database metadata, such as:
i. Names and structures of the database tables.
ii. Details on columns within these tables.
iii. Information on the primary and foreign keys.