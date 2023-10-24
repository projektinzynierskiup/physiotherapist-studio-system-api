drop table offer;
CREATE TABLE offer (
    id SERIAL PRIMARY KEY,
    offer_name VARCHAR(255) NOT NULL,
    duration DOUBLE PRECISION NOT NULL,
    price INT NOT NULL,
    masage_id INT,
    FOREIGN KEY (masage_id) REFERENCES massage(id)
);
