create table appointment_massage(
id serial PRIMARY KEY,
appointment_id integer,
massage_id integer,
FOREIGN KEY (appointment_id) REFERENCES appointment(id),
FOREIGN KEY (massage_id) REFERENCES massage(id)
);