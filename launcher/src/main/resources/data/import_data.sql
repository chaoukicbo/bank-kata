INSERT INTO  client(id, number, first_name, last_name) VALUES
            (1, 111111, 'joe', 'Smith'),
            (2, 222222, 'eric', 'Martin'),
            (3, 333333, 'bob', 'lee'),
            (4, 444444, 'marc', 'fee');
INSERT INTO account(id, number, balance, client_id) VALUES
            (1, 1212121, 100, 1),
            (2, 1313131, 200, 1),
            (3, 2121212 , 650, 2),
            (4, 4444444 , 1000, 3),
            (5, 5555555 , 5664, 4),
            (6, 6666666 , 10000, 4);