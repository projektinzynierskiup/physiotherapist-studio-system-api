CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);

create table massage(
                        id serial PRIMARY KEY,
                        massage_name varchar(45),
                        description varchar(255),
                        appointment_type varchar(255)
);

CREATE TABLE offer (
                       id SERIAL PRIMARY KEY,
                       offer_name VARCHAR(255) NOT NULL,
                       duration DOUBLE PRECISION NOT NULL,
                       price INT NOT NULL,
                       masage_id INT,
                       FOREIGN KEY (masage_id) REFERENCES massage(id)
);

create table appointment(
                            id serial PRIMARY KEY,
                            start_date date not null ,
                            end_date date not null ,
                            appointment_type varchar(255),
                            user_id integer not null
);

create table offer_photo(
                            id serial PRIMARY KEY,
                            photo_name varchar(45),
                            photo_byte bytea[],
                            photo_type varchar(45),
                            offer_id integer
);

create table appointment_massage(
                                    id serial PRIMARY KEY,
                                    appointment_id integer,
                                    massage_id integer,
                                    FOREIGN KEY (appointment_id) REFERENCES appointment(id),
                                    FOREIGN KEY (massage_id) REFERENCES massage(id)
);