create table massage(
id serial PRIMARY KEY,
massageName varchar(45),
description varchar(255),
appointment_type varchar(255) CHECK (appointment_type IN ('RELAKSACYJNY', 'TERAPEUTYCZNY', 'GŁOWY', 'STÓP', 'TERAPIA_MANUALNA'))
)