CREATE TABLE doctor (
    id BIGINT IDENTITY PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255),
    speciality VARCHAR(255) NOT NULL,
);

CREATE TABLE patient (
    id BIGINT IDENTITY PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255),
    phonenumber VARCHAR(255) NOT NULL,
);

CREATE TABLE recipe (
    id BIGINT IDENTITY PRIMARY KEY,
    description VARCHAR(512),
    idPatient BIGINT NOT NULL,
    idDoctor BIGINT NOT NULL,
    creationDate DATE NOT NULL,
    validityDate DATE NOT NULL,
    priority INTEGER NOT NULL,
    FOREIGN KEY (idDoctor) REFERENCES doctor(id),
    FOREIGN KEY (idPatient) REFERENCES patient(id)
);

