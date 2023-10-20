create table offer(
id serial PRIMARY KEY,
offer_name varchar(45),
description varchar(255),
appointment_type varchar(255) CHECK (appointment_type IN ('RELAKSACYJNY', 'TERAPEUTYCZNY', 'GŁOWY', 'STÓP', 'TERAPIA_MANUALNA')),
duration double precision,
price integer
);