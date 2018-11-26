create database BCHousingDB;

use BCHousingDB;

/**
  Notes and Assumptions:
  1. BC student ID and staff ID are both 9 digits long.
  2. Bellevue College gives seven options for defining gender identity:
     Feminine, Masculine, Androgynous, Gender Neutral, Transgender, Other and Prefer Not To Answer.
  3. For full international support, the length phone number has to be 15 digits.
  4. Assume that admin passwords are limited to 32 digits long.
 */

CREATE TABLE Applicant (
  SSN               	INT(9) PRIMARY KEY,
  StudentStatus     	ENUM ('Not Applied', 'Applied', 'Processing', 'Accepted', 'Rejected') NOT NULL,
  SuitePreference   	ENUM ('One Bedroom', 'Two Bedroom'),
  ApartmentPreference 	ENUM('Two Bedroom', 'Four Bedroom'),  
  VillagePreference 	ENUM('East','West'),
  ApplicationDate 		DATE DEFAULT NULL    #tracking application date
);

CREATE TABLE Students (
  IdNumber      INT(9) PRIMARY KEY,
  Name          VARCHAR(255)                                                                                                    NOT NULL,
  Gender        ENUM ('Feminine', 'Masculine', 'Androgynous', 'Gender Neutral', 'Transgender', 'Other', 'Prefer Not To Answer') NOT NULL,
  College       VARCHAR(255),
  Department    VARCHAR(255),
  MaritalStatus ENUM ('Single', 'Married', 'Other', 'Prefer Not To Answer')                                                     NOT NULL
);

CREATE TABLE Assignment (
  BuildingNo   		INT,
  ApartmentSuiteNo  INT,                     
  Bedroom 			INT,   				
  Bed 				INT,           		   	
  AptSuite 			ENUM('Apartment','Suite'),   	# maybe name this ArrangementType for clarity?
  Village 			ENUM('East','West'),
  MarriageEligable 	ENUM ('Yes','No'),
  SSN 				INT(9) DEFAULT NULL,
  CheckoutDate 		DATE DEFAULT NULL,
  PRIMARY KEY (BuildingNo, ApartmentSuiteNo, Bed),
  FOREIGN KEY (SSN) REFERENCES Applicant (SSN)
);

CREATE TABLE MaintenanceRequest (
  RequestNo      INT,
  IDNumber       INT(9),
  Employee       VARCHAR(255),
  DateCompleted  DATE,
  SubmissionDate DATE,
  Description    TEXT,
  PRIMARY KEY (RequestNo, IDNumber),
  FOREIGN KEY (IDNumber) REFERENCES Students (IdNumber)
);

CREATE TABLE FamilyHead (											# mainly considering making this into a self relation on the student table like
  SSN         INT(9),												# Sara suggested but also like the idea of tracking family heads indivdiually
  IDNoStudent INT(9),
  College     VARCHAR(255),
  Department  VARCHAR(255),
  PRIMARY KEY (SSN, IDNoStudent),
  FOREIGN KEY (IDNoStudent) REFERENCES Students (IdNumber)
);

CREATE TABLE RoommateRequest (
  ReqestedSSN    INT(9),
  ApplicantSSN   INT(9),
  ReqestedSpouse VARCHAR(255),            # should this be a boolean? IE true when the requested roommate is the spouse of the applicant
  PRIMARY KEY (ReqestedSSN, ApplicantSSN),
  FOREIGN KEY (ApplicantSSN) REFERENCES Applicant (SSN)
);

CREATE TABLE AccountBalance (
  IDNumber    INT(9) PRIMARY KEY,
  PaymentOwed DOUBLE NOT NULL,
  TotalPaid   DOUBLE NOT NULL,
  FOREIGN KEY (IDNumber) REFERENCES Students (IdNumber)
);

CREATE TABLE Administrator (
  SSN        INT(9) PRIMARY KEY,
  Password   VARCHAR(32),
  StaffID    INT(9)                                                                                                          NOT NULL,
  Name       VARCHAR(255)                                                                                                    NOT NULL,
  Phone      VARCHAR(15)                                                                                                     NOT NULL,
  Gender     ENUM ('Feminine', 'Masculine', 'Androgynous', 'Gender Neutral', 'Transgender', 'Other', 'Prefer Not To Answer') NOT NULL,
  Job        VARCHAR(255),
  title      VARCHAR(255),
  Department VARCHAR(255)
);
