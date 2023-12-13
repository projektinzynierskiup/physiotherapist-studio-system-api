CREATE TABLE IF NOT EXISTS physioterapist.number_type
(
    id integer NOT NULL,
    number_of integer,
    type_of character varying(255),
    CONSTRAINT number_type_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS physioterapist.statistics
(
    id serial NOT NULL,
    number_of_appointments_a_year integer,
    number_of_appointments_a_month integer,
    year_income double precision,
    average_month_income double precision,
    most_wanted_massage_year character varying(255),
    most_wanted_massage_month character varying(255),
    year_number integer,
    month_number integer,
    number_of_massages_a_year integer,
    number_of_massages_a_month integer,
    CONSTRAINT statistics_pkey PRIMARY KEY (id),
    CONSTRAINT massages_a_month_fk FOREIGN KEY (number_of_massages_a_month)
        REFERENCES physioterapist.number_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT massages_a_year_fk FOREIGN KEY (number_of_massages_a_year)
        REFERENCES physioterapist.number_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);