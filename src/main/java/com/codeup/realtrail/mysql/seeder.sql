USE realtrail_db;

INSERT INTO users(id, city, email, first_name, gender, last_name, password, phone_number, profile_image_url, state, username)
VALUES
    (1, 'Universal City', 'shanshan@shan.com', 'Shanshan', 'F', 'Su', '$2a$10$IjGrLooGQ0EiT6OS4K96Ou6/7m7d7ZIYcIIZZS3FX0yTVDEurzrIK','210-123-4567', 'https://avatars.githubusercontent.com/u/78042275?v=4', 'TX', 'Shan'),
    (2, 'San Antonio', 'jara.karla.43@gmail.com', 'Karla', 'F', 'Jara', 'encrypted_password', '123-435-0743', 'https://avatars.githubusercontent.com/u/78042244?v=4', 'TX', 'Da K Js'),
    (3, 'San Antonio', 'salimkk780@gmail.com', 'Salim', 'M', 'Ahmedabadi', '$2a$10$Pr1mk/AzGVKXBr6retEEYuVNOtRqnajAIgiCB0iuEDmb0mzDHetfi', '378-987-1735', 'https://avatars.githubusercontent.com/u/78042280?v=4', 'TX', 'salimk785'),
    (4, 'San Antonio', 'victor.g.hernandez87@gmail.com', 'Victor', 'M', 'Hernandez', 'encrypted_password', '988-198-2928', 'https://ca.slack-edge.com/T029BRGN0-U01KK05TXP0-99b4452cba22-512', 'TX', 'G-man'),
    (5, 'Killeen', 'emmanuel.stephen68@gmail.com', 'Emmanuel', 'M', 'Stephen', '$2a$10$FQzRQY.zOlvbL4EGwVTR5.P3i8S5as30fVVg9ApYQHD3r3sn2kZDO', '943-294-9923', 'https://avatars.githubusercontent.com/u/59237112?v=4', 'TX', 'manii1');

INSERT INTO interests(interest) VALUES
   ('Hiking'),
   ('Biking'),
   ('Mountain Hiking'),
   ('Mountain Biking');


INSERT INTO events (id, name, date, time, location, meet_location, meet_time, event_details, manager_id, trail_id)
VALUES
    (1, 'Cool Trail', '2021-06-21', '10:00', 'San Antonio', 'Burger King', '0700', 'yes', 2, 6),
    (2, 'Hills', '2021-09-20', '13:00', 'San Antonio', 'Mcdonald', '12:00', 'The best ride of your life', 4, 7);

INSERT INTO trails(id, difficulty_level, rating, length, elevation, type, trail_details)
VALUES
  (1, 6, 5, 5, 200, 'rocky', 'This trail is so cool! Said Karla!'),
  (2, 3, 3, 10, 132, 'smooth', 'this is a great fun ride guys'),
  (3, 4, 2, 12, 321, 'medium terrain', 'Yes, this is just nature yes');