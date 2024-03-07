DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id INT PRIMARY KEY,
    chat_id INT NOT NULL,
    list VARCHAR[]
);