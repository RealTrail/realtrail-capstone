USE realtrail_db;

INSERT INTO users(id, city, email, first_name, gender, last_name, password, phone_number, profile_image_url, state, username)
VALUES
    (1, 'San Antonio', 'shanshan123@gmail.com', 'Shanshan', 'F', 'Su', 'encrypted_password','210-876-5432', 'https://avatars.githubusercontent.com/u/78042275?v=4', 'Texas', 'Shananagan'),
    (2, 'San Antonio', 'jara.karla.43@gmail.com', 'Karla', 'F', 'Jara', 'encrypted_password', '123-435-0743', 'https://avatars.githubusercontent.com/u/78042244?v=4', 'Texas', 'Da K Js'),
    (3, 'San Antonio', 'ahmedabadi.salim@gmail.com', 'Salim', 'M', 'Ahmedabadi', 'encrypted_password', '378-987-1735', 'https://avatars.githubusercontent.com/u/78042280?v=4', 'Texas', 'Slim Lim'),
    (4, 'San Antonio', 'victor.g.hernandez87@gmail.com', 'Victor', 'M', 'Hernandez', 'encrypted_password', '988-198-2928', 'https://ca.slack-edge.com/T029BRGN0-U01KK05TXP0-99b4452cba22-512', 'Texas', 'G-man'),
    (5, 'Killeen', 'emmanuel.stephen68@gmail.com', 'Emmanuel', 'M', 'Stephen', 'encrypted_password', '943-294-9923', 'https://avatars.githubusercontent.com/u/59237112?v=4', 'Texas', 'Manii');

INSERT INTO events (id, name, date, time, lacation, meet_location, meet_time, event_details, manager_id, trail_id)
VALUES
    (1, 'Cool Trail', '06-21-21', '10:00', 'San Antonio', 'Burger King', '0700', 'yes', 2, 6),
    (2, 'Hills', '09-20-21', '13:00', 'San Antonio', 'Mcdonald', '12:00', 'The best ride of your life', 4, 7);

INSERT INTO trails(id, difficulty_level, rating, length, elevation, type, trail_details)
VALUES
  (1, 6, 5, 5, 200, 'rocky', 'This trail is so cool! Said Karla!'),
  (2, 3, 3, 10, 132, 'smooth', 'this is a great fun ride guys'),
  (3, 4, 2, 12, 321, 'medium terrain', 'Yes, this is just nature yes');