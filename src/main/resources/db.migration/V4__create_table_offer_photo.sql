create table offer_photo(
id serial PRIMARY KEY,
photo_name varchar(45),
photo_byte bytea[],
photo_type varchar(45),
offer_id integer
);