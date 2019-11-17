DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR(AUTHORID INT AUTO_INCREMENT PRIMARY KEY, SURNAME VARCHAR(255), NAME VARCHAR(255));
DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE(GENREID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255));
DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK(BOOKID INT AUTO_INCREMENT PRIMARY KEY, AUTHORID INT, GENREID INT, NAME VARCHAR(255));