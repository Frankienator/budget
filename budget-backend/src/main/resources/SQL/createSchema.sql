CREATE TABLE IF NOT EXISTS entry
(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
description VARCHAR(255) NOT NULL,
amount NUMERIC(10,2) NOT NULL,
dateCreated DATE NOT NULL,
type ENUM('freizeit', 'sparen', 'transport', 'haushalt', 'sonstiges') NOT NULL
);
