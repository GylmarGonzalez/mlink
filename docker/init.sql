
CREATE TABLE parameters(
pk char(30) NOT NULL,
valueText char(1000) NULL,
enabled char(1) NULL,
CONSTRAINT parametersPK PRIMARY KEY (pk));

CREATE TABLE info(
pk int NOT NULL,
currentVersion char(10) NULL,
supportsVersion char(100) NULL,
creator char(30) NULL,
CONSTRAINT infoPK PRIMARY KEY (pk));

CREATE TABLE category(
pk int NOT NULL,
name char(15) NULL,
description char(200) NULL,
enabled char(1) NULL,
CONSTRAINT categoryPK PRIMARY KEY (pk));

CREATE TABLE link(
pk int NOT NULL,
urlText char(500) NULL,
description char(500) NULL,
fkCategory int not null,
enabled char(1) NULL,
CONSTRAINT linkPK PRIMARY KEY (pk));

ALTER TABLE link
ADD CONSTRAINT FK_linkCategoria FOREIGN KEY(fkCategory)
REFERENCES category (pk)
on delete restrict on update restrict;

CREATE SEQUENCE pks_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO public.parameters(
	pk, valuetext, enabled)
	VALUES ('KEY_TOKEN', 'EsteEsUnSuperSecretoDe32Caracteres!!', 'S');
