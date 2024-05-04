ALTER TABLE users
ADD COLUMN favorite_address_id BIGINT,
ADD FOREIGN KEY (favorite_address_id) REFERENCES addresses(id);
