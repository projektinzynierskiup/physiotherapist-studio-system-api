CREATE TABLE opinions (
     id SERIAL PRIMARY KEY,
     username VARCHAR(255) NOT NULL,
     description TEXT NOT NULL,
     rate INTEGER NOT NULL,
     local_date DATE
);