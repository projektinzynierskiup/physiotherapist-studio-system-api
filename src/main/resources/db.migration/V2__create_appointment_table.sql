create table appointment(
id serial PRIMARY KEY,
start_date date,
end_date date,
appointment_type varchar(255) CHECK (appointment_type IN ('RELAKSACYJNY', 'TERAPEUTYCZNY', 'GŁOWY', 'STÓP', 'TERAPIA_MANUALNA')),
user_id integer
);