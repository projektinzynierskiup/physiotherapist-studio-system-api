create table if not exists newsletter(
      id SERIAL PRIMARY KEY,
      user_email varchar(255) NOT NULL
);