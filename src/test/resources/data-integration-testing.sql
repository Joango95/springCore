-----------------USER-----------

INSERT INTO `USER` (`name`,`email`)
VALUES
  ('Lewis Burton','dictum@icloud.com'),
  ('Lewis Burton','lewis@gmail.com'),
  ('Zahir Workman','lorem@aol.net'),
  ('Scarlett Hood','taciti@protonmail.com'),
  ('Sage Conrad','parturient.montes.nascetur@aol.net'),
  ('Jaden Harrison','tellus.aenean@outlook.couk'),
  ('Kameko Camacho','enim.consequat@hotmail.edu'),
  ('Harlan Jackson','lorem@protonmail.com'),
  ('Branden Frye','pede.malesuada.vel@icloud.couk'),
  ('Xantha Buck','maecenas@google.ca'),
  ('Oleg Galloway','augue@hotmail.ca');

INSERT INTO `USER` (`name`,`email`)
VALUES
  ('Alexandra Moreno','rutrum.urna.nec@icloud.com'),
  ('Palmer Gray','fusce.diam.nunc@hotmail.org'),
  ('Kuame Patel','sed.nunc@icloud.net'),
  ('Kadeem Clark','ornare.elit.elit@outlook.org'),
  ('Luke Dotson','nunc@hotmail.edu'),
  ('Ava Flowers','ac.nulla@icloud.edu'),
  ('Kirestin Rose','et.rutrum@icloud.couk'),
  ('Vanna Curtis','risus.quisque@protonmail.edu'),
  ('Maxwell Hampton','sagittis.lobortis@icloud.edu'),
  ('Ayanna Marks','in.scelerisque@aol.net');

  -----------------------USER ACCOUNT--------------------
 INSERT INTO USER_ACCOUNT (user_balance, user_id)
 VALUES
    (10000,1),
    (15000,2),
    (20000,3),
    (25000,4),
    (30000,5),
    (35000,6),
    (40000,7),
    (45000,8),
    (50000,9),
    (55000,10),
    (60000,11),
    (65000,12),
    (70000,13),
    (75000,14),
    (80000,15),
    (85000,16),
    (90000,17),
    (95000,18),
    (85000,19),
    (65000,20),
    (15000,21);

  ----------------------- TICKET---------
insert into ticket (event_id, user_id, category, place) values (3, 20, 'STANDARD', 39);
insert into ticket (event_id, user_id, category, place) values (3, 20, 'STANDARD', 42);
insert into ticket (event_id, user_id, category, place) values (1, 16, 'STANDARD', 18);
insert into ticket (event_id, user_id, category, place) values (1, 19, 'STANDARD', 26);
insert into ticket (event_id, user_id, category, place) values (1, 12, 'STANDARD', 11);
insert into ticket (event_id, user_id, category, place) values (2, 12, 'STANDARD', 30);
insert into ticket (event_id, user_id, category, place) values (5, 9, 'STANDARD', 4);
insert into ticket (event_id, user_id, category, place) values (5, 4, 'STANDARD', 13);
insert into ticket (event_id, user_id, category, place) values (3, 2, 'STANDARD', 15);
insert into ticket (event_id, user_id, category, place) values (4, 13, 'STANDARD', 35);
insert into ticket (event_id, user_id, category, place) values (4, 19, 'PREMIUM', 47);
insert into ticket (event_id, user_id, category, place) values (1, 19, 'PREMIUM', 39);
insert into ticket (event_id, user_id, category, place) values (1, 14, 'PREMIUM', 3);
insert into ticket (event_id, user_id, category, place) values (2, 14, 'PREMIUM', 27);
insert into ticket (event_id, user_id, category, place) values (4, 12, 'PREMIUM', 44);
insert into ticket (event_id, user_id, category, place) values (3, 3, 'PREMIUM', 45);
insert into ticket (event_id, user_id, category, place) values (4, 18, 'PREMIUM', 27);
insert into ticket (event_id, user_id, category, place) values (5, 14, 'PREMIUM', 42);
insert into ticket (event_id, user_id, category, place) values (5, 5, 'PREMIUM', 4);
insert into ticket (event_id, user_id, category, place) values (3, 9, 'PREMIUM', 45);
insert into ticket (event_id, user_id, category, place) values (4, 5, 'BAR', 15);
insert into ticket (event_id, user_id, category, place) values (4, 2, 'BAR', 48);
insert into ticket (event_id, user_id, category, place) values (4, 20, 'BAR', 6);
insert into ticket (event_id, user_id, category, place) values (4, 20, 'BAR', 42);
insert into ticket (event_id, user_id, category, place) values (3, 3, 'BAR', 27);
insert into ticket (event_id, user_id, category, place) values (4, 14, 'BAR', 49);
insert into ticket (event_id, user_id, category, place) values (3, 16, 'BAR', 19);
insert into ticket (event_id, user_id, category, place) values (2, 14, 'BAR', 45);
insert into ticket (event_id, user_id, category, place) values (3, 12, 'BAR', 40);
insert into ticket (event_id, user_id, category, place) values (4, 6, 'BAR', 34);


----------------- EVENT----------

INSERT INTO `event` (`title`,`date`, TICKET_PRICE)
VALUES
  ('posuere, enim','2023-08-10', 5000),
  ('posuere, enim','2023-07-10', 4000),
  ('tristique pharetra.','2022-08-10', 3000),
  ('odio semper','2023-05-06', 100000),
  ('arcu eu','2023-01-30', 96000),
  ('elit, pharetra','2023-08-10', 2000);