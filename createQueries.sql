create database tbontb;
use tbontb;

CREATE TABLE User
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(40) UNIQUE NOT NULL,
	password VARCHAR(200) NOT NULL
);

CREATE TABLE Friends
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	userID INT NOT NULL,
    friendEmail VARCHAR(50) NOT NULL,
    FOREIGN KEY (userID) REFERENCES User(id)
);

CREATE TABLE UserPolls
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    creatorUserID INT NOT NULL
 );

CREATE TABLE UserPollItems
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    userPollID INT NOT NULL,
    price VARCHAR(10) NOT NULL,
    description TEXT NOT NULL,
    imageLink TEXT NOT NULL,
    buyLink TEXT NOT NULL,
    score INT DEFAULT 0
);