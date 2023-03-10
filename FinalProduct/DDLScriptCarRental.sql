CREATE SCHEMA carrental;
USE carrental;

DROP TABLE IF EXISTS accessorie;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS contract;
DROP TABLE IF EXISTS model;


CREATE TABLE accessorie
(
car_id					INT						AUTO_INCREMENT				PRIMARY KEY UNIQUE NOT NULL,
car_geartype			ENUM('Automatic', 'Manuel')							NOT NULL,
car_aircondition		BOOLEAN												NOT NULL,
car_cruisecontrol		BOOLEAN												NOT NULL,
car_leatherseats		BOOLEAN												NOT NULL,
car_carseats			INT													NOT NULL,
car_horsepower			INT													NOT NULL,
car_type				ENUM('Luxury', 'Sport', 'Family', 'Unknown')
);

CREATE TABLE car
(
car_id					INT						AUTO_INCREMENT				UNIQUE NOT NULL,
car_brand				VARCHAR(20)											NOT NULL,
car_fueltype			ENUM('Diesel', 'Benzin', 'Hybrid', 'Electric')		NOT NULL,
car_plate				VARCHAR(8)											PRIMARY KEY NOT NULL UNIQUE,
car_registration		DATE												NOT NULL,
car_miles				INT													NOT NULL,
car_isrented			BOOLEAN												NOT NULL,					
																FOREIGN KEY(car_id) REFERENCES accessorie(car_id)
);

CREATE TABLE model
(
car_id					INT						AUTO_INCREMENT				UNIQUE NOT NULL,
car_model				VARCHAR(30)											NOT NULL
);



CREATE TABLE customer
(
customer_id				INT						AUTO_INCREMENT			UNIQUE 	NOT NULL,
customer_fullname		VARCHAR(30)										NOT NULL,
customer_address		VARCHAR(30)										NOT NULL,
customer_city			VARCHAR(30)										NOT NULL,
customer_phone			VARCHAR(30),										
customer_mobile			VARCHAR(30)										NOT NULL,
customer_email			VARCHAR(30)										NOT NULL,
customer_licence_number	INT												PRIMARY KEY UNIQUE NOT NULL,
customer_licence_date	DATE											        NOT NULL
);

CREATE TABLE contract
(
contract_id				INT						AUTO_INCREMENT			UNIQUE NOT NULL,			
customer_licence_number	INT												UNIQUE NOT NULL,
contract_date_from		DATE											NOT NULL,
contract_date_to		DATE											NOT NULL,
contract_max_km			INT												NOT NULL,
contract_km_driven		INT												NOT NULL,
car_plate				VARCHAR(8)										NOT NULL, 
																		FOREIGN KEY(car_plate) REFERENCES car(car_plate), 
																		FOREIGN KEY(customer_licence_number) REFERENCES customer(customer_licence_number)
);