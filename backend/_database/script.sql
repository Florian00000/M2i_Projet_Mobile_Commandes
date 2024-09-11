CREATE DATABASE IF NOT EXISTS `db_ms_authentication_service`;
USE `db_ms_authentication_service`;
CREATE TABLE `account` (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    email       VARCHAR(255) UNIQUE NOT NULL,
    firstname   VARCHAR(255),
    lastname    VARCHAR(255),
    password    VARCHAR(255)
);
CREATE TABLE `role` (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    role        VARCHAR(255) UNIQUE
);
CREATE TABLE `account_role` (
    user_id     INT NOT NULL,
    roles_id    INT NOT NULL,
    FOREIGN KEY (user_id)   REFERENCES account (id),
    FOREIGN KEY (roles_id)  REFERENCES role (id)
);

INSERT INTO role (role) VALUES ('ADMIN'), ('USER');
INSERT INTO account (email, firstname, lastname, password)
VALUES ('gabitbol@mail.com', 'Georges', 'Abitbol', '$2a$10$SUflVwKauPy49wDses5YDO89RCAJjSjNPO/DByNBNFSM7ZrHowEQe'),
       ('user@mail.com', 'user', 'user', '$2a$10$NhS8pBeH8tb/0L9sTR00IOAIsHb18K6a27cB12ZaRSJggMrU2XGS6');
INSERT INTO account_role (user_id, roles_id)
VALUES (1, 1), (1,2), (2,2);


CREATE DATABASE IF NOT EXISTS `db_ms_product_service`;
USE `db_ms_product_service`;
CREATE TABLE `category` (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)
);
CREATE TABLE `product` (
    product_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(255),
    price       DOUBLE NOT NULL,
    stock       INT NOT NULL
);
CREATE TABLE `category_product` (
    category_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    FOREIGN KEY (category_id)   REFERENCES category (category_id),
    FOREIGN KEY (product_id)    REFERENCES product (product_id)
);
