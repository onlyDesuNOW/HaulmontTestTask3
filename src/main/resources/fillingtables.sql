INSERT INTO patient (firstname, lastname, patronymic, phonenumber)
values
    ('Mark', 'Ivanov', 'Ivanovich', '79272224481'),
    ('Peter', 'Koshkin', 'Vladislavovich', '79272223382'),
    ('Kirill', 'Ostankin','Leonidovich', '79272564483')
    ;

INSERT INTO doctor (firstname, lastname, patronymic, speciality)
values
    ('Vlad', 'Vladenko', 'Vladimirovich', 'Urologist'),
    ('Ivan', 'Smirnov', 'Ivanovich', 'Proctolog'),
    ('Peter', 'Petrenko', 'Petrovich', 'Oncologist')
    ;

INSERT INTO recipe (description, idPatient, idDoctor, creationDate, validityDate, priority)
values
    ('Nothing to do.', 2, 0, DATE '2019-09-23', DATE '2019-10-24', 1),
    ('Do not sit on cold surfaces.', 1, 0, DATE '2019-10-05', DATE '2019-10-11', 2),
    ('Take some medicine twice a day.', 2, 2, DATE '2019-11-10', DATE '2019-11-17', 0)
    ;
