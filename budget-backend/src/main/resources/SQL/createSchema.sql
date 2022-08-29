CREATE TABLE IF NOT EXISTS entry
(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
description VARCHAR(255) NOT NULL,
amount NUMERIC(10,2) NOT NULL,
dateCreated DATE NOT NULL,
type ENUM('einkommen', 'freizeit', 'sparen', 'transport', 'haushalt', 'sonstiges') NOT NULL
);

CREATE TABLE IF NOT EXISTS recurringEntry
(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
description VARCHAR(255) NOT NULL,
amount NUMERIC(10,2) NOT NULL,
startingDate DATE NOT NULL,
intervalCount INTEGER NOT NULL,
timeInterval ENUM('day', 'month', 'year') NOT NULL,
status ENUM('active', 'inactive') NOT NULL,
type ENUM('einkommen', 'freizeit', 'sparen', 'transport', 'haushalt', 'sonstiges') NOT NULL
);
