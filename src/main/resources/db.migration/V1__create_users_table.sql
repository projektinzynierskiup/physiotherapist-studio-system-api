CREATE TABLE users (
    id serial PRIMARY KEY,
    username VARCHAR(25) NOT NULL,
    password VARCHAR(25) NOT NULL,
    role VARCHAR(10) NOT NULL
);
