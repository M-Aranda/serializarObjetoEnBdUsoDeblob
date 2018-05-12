CREATE DATABASE ejemploSer;

USE ejemploSer;



CREATE TABLE ejem(
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(128), 
valor_objeto BLOB 
);

SELECT * FROM ejem;

TRUNCATE ejem;

DROP TABLE ejemploSer;
