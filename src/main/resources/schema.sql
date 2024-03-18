DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id            BIGINT PRIMARY KEY,
    list          VARCHAR[],
    notifications BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS tokens;

CREATE TABLE tokens
(
    symbol VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL,
    price FLOAT NOT NULL
);