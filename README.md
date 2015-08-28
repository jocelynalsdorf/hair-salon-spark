#Hair Salon With Spark and postgres/psql
<h3>Author:</h3>
Jocelyn Alsdorf

<h3>Description:</h3>
This app allows a user to enter enter and manage stylists and their clients. Crud funtionality available for both clients and stylists. Data is persisted via postgres/psql. 


<h2>Setup instructions:</h2>
Dependencies:
Spark
Gradle
Java 
Testing Suite:
FluentLenium
JUnit
---------
In PSQL:
CREATE DATABASE hair_salon;
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
CREATE TABLE clients (id serial PRIMARY KEY, description varchar, stylist_id int);

<h2>Copyright</h2>
MIT License. Copyright 2015  Jocelyn Alsdorf



