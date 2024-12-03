# Stock Management System - Java

## Intro
This project features a basic Stock Management System, enabling users to perform CRUD operations on stock items stored in a JSON file. The primary goal of this project is to demonstrate the use of Java for handling JSON data.

## Features 

 - ```Project.java``` - This script gathers all the app's functions.
 Functions present in this file are:
  1. checkInput - Checks if the user's input is valid.
  2. add - Adds new products to the stock.
  3. update - Updates the products present in the stock.
  4. delete - Deletes products present in the stock.
  5. read - Reads all the products present in the stock.

 - ```stockProducts.json``` - The file gathers all the stock information like name, price and quantity of each product.
 
## Prerequisites

- Java language
- Dependencies on pom.xml: jackson, apache.

## Usage Instructions

To use this repository, follow these steps:

1. Clone the repository to your local machine.

    ```bash
    git clone https://github.com/RobisonTorres/Stock_System.git

2. Install required Maven dependencies.

3. Navigate to the directory.

    ```bash
    cd Stock_System\src\main\java\Project.

4. Execute the Main.java.

## Example

After running the file you will be able to access the app's menu:

```
*****Menu Options**** 
=====================
*********************
1. Add new product
2. Update a product
3. Show all products
4. Delete a product
5. Exit the program
*********************
Enter your option: 3

Product        Price     Quantity  
Headphones     15.99     10        
Laptop         200.0     10        
Smartphone     70.0      40        
Tablet         99.99     2         
Mouse          15.28     50        
Tv             699.99    2         

```
