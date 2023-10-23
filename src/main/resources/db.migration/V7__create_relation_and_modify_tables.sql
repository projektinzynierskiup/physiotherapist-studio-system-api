ALTER TABLE offer ADD COLUMN massage_id integer;
ALTER TABLE offer ADD FOREIGN KEY (massage_id) REFERENCES massage(id);
ALTER TABLE offer DROP COLUMN appointment_type;
ALTER TABLE offer DROP COLUMN description;
ALTER TABLE appointment DROP COLUMN appointment_type;
ALTER TABLE appointment ADD FOREIGN KEY (user_id) REFERENCES users(id);