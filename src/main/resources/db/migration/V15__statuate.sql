create table if not exists statute(
    id SERIAL PRIMARY KEY,
    content varchar(65530) NOT NULL
);