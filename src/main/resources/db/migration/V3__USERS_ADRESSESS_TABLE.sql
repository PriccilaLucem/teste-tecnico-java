CREATE TABLE `users_addresses` (
    `users_id` BIGINT,
    `addresses_id` BIGINT,
    PRIMARY KEY (users_id, addresses_id),
    FOREIGN KEY (users_id) REFERENCES users(id),
    FOREIGN KEY (addresses_id) REFERENCES addresses(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;