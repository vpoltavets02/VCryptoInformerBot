DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id BIGINT PRIMARY KEY,
    list VARCHAR[],
    notifications BOOLEAN NOT NULL
);