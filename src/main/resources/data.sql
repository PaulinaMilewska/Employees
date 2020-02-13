INSERT INTO Employees(address, age, benefit, city, email, first_name, last_name, salary, start_job_date)
VALUES ('Kwiatowa 134',
        23, 1,
        'Bytom',
        'anowak@gmail.com',
        'Adam',
        'Nowak',
        56600,
        '2018-01-12'),
       ('Nowa 1',
        22, 0,
        'Warszawa',
        'jkowalski@wp.pl',
        'Jan',
        'Kowalski',
        12000,
        '2013-02-13'),
       ('Nowa 2',
        66, 0,
        'Warszawa',
        'posa@wp.pl',
        'Piotr',
        'Osa',
        17900,
        '2012-11-16');

INSERT INTO Cars(employee_id, name, model, registration_date)
VALUES (1, 'Kia', 'Ceed', '2007-04-01'),
       (2, 'Renault', 'Megane', '2018-03-19'),
       (3, 'Peugeot', '406coupe', '2010-01-01');


INSERT INTO Printers(model, producer)
VALUES ('r5', 'Canon'),
       ('deskjet', 'HP');


INSERT INTO Printer_Employees(printer_id, employee_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 1);


