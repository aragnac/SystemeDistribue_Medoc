-- Create Database and tables

CREATE DATABASE DB_MEDOC DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
-- SHOW TABLES;
-- DESCRIBE table; 

CREATE USER 'nico' IDENTIFIED BY '1234';
GRANT ALL ON DB_MEDOC.* TO 'nico' IDENTIFIED BY '1234';

CREATE USER 'flo' IDENTIFIED BY '1234';
GRANT ALL ON DB_MEDOC.* TO 'flo' IDENTIFIED BY '1234';


--Creation des tables

create table Patient (
	id INT NOT NULL AUTO_INCREMENT,
	nom VARCHAR(25) NOT NULL,
	prenom VARCHAR(25) NOT NULL,
	login VARCHAR(25) NOT NULL,
	PRIMARY KEY (Id)
);

create table Medecin (
	id INT NOT NULL AUTO_INCREMENT,
	nom VARCHAR(25) NOT NULL,
	prenom VARCHAR(25) NOT NULL,
	login VARCHAR(25) NOT NULL,
	PRIMARY KEY (Id)
);

create table Demande (
	id INT NOT NULL AUTO_INCREMENT,
	RefPatient INT NOT NULL,
	RefMedecin INT NOT NULL,
	DateHeureDemande DATETIME, 
	Urgent BOOLEAN,
	PRIMARY KEY (Id)
);

create table Analyses (
	id INT NOT NULL AUTO_INCREMENT,
	RefPatient INT NOT NULL,
	item VARCHAR(30) NOT NULL,
	valeur VARCHAR(25) NOT NULL,
	PRIMARY KEY (id)
);

create table Logs (
	id INT NOT NULL AUTO_INCREMENT,
	infos VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);