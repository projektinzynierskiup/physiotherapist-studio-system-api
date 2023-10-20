CREATE TABLE users(
    id serial PRIMARY KEY,
    email VARCHAR(40) NOT NULL,
    username VARCHAR(25) NOT NULL,
    surname VARCHAR(25) NOT NULL,
    password VARCHAR(25) NOT NULL,
    role VARCHAR(10) NOT NULL
);
