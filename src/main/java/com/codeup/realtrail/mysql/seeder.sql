USE realtrail_db;

INSERT INTO users(id, city, email, first_name, gender, last_name, password, phone_number, profile_image_url, state, username)
VALUES
    (1, 'Universal City', 'shanshan@shan.com', 'Shanshan', 'F', 'Su', '$2a$10$IjGrLooGQ0EiT6OS4K96Ou6/7m7d7ZIYcIIZZS3FX0yTVDEurzrIK','210-123-4567', 'https://cdn.filestackcontent.com/072ckNiNS8iImzI53sMY', 'TX', 'Shan'),
    (2, 'San Antonio', 'kjar@gmail.com', 'Karla', 'F', 'Jara', '$2a$10$bzg3Tcw4Q/srFDwlmUYEYeJrT5CFZvCUsQMXiZO3QLTB7Icj3kwRa', '123-435-0743', 'https://cdn.filestackcontent.com/PBwhSh4nSAGwF1Tlcr2U', 'TX', 'kjar007'),
    (3, 'San Antonio', 'salimkk780@gmail.com', 'Salim', 'M', 'Ahmedabadi', '$2a$10$Pr1mk/AzGVKXBr6retEEYuVNOtRqnajAIgiCB0iuEDmb0mzDHetfi', '378-987-1735', 'https://cdn.filestackcontent.com/odZiQGMQXiObAbybdNrO', 'TX', 'salimk785'),
    (4, 'San Antonio', 'victor.g.hernandez87@gmail.com', 'Victor', 'M', 'Hernandez', 'encrypted_password', '988-198-2928', 'https://ca.slack-edge.com/T029BRGN0-U01KK05TXP0-99b4452cba22-512', 'TX', 'G-man'),
    (5, 'Killeen', 'emmanuel.stephen68@gmail.com', 'Emmanuel', 'M', 'Stephen', '$2a$10$FQzRQY.zOlvbL4EGwVTR5.P3i8S5as30fVVg9ApYQHD3r3sn2kZDO', '943-294-9923', 'https://avatars.githubusercontent.com/u/59237112?v=4', 'TX', 'manii1'),
   (7, 'Princeton', 'cat123@cat.com', 'Kitty', 'M', 'Cute',  '$2a$10$Vm0gJalOoURhce98oeYhYOhKZvD8F0/3F/Vgx4dKj714RJuQRyetC', '993-156-9231', 'https://cdn.filestackcontent.com/luOfEwtiThetHDkagICG', 'NJ', 'cat'),
   (8, null, 'momo80@hotmail.com', null, null, null, '$2a$10$VH4m1vAKLQ7fIexUbx5CUOY3eTuvSWDbVL3c.z5DTsql3vqYSKp6G', null, null, null, 'mono');

INSERT INTO interests(interest) VALUES
   ('Hiking'),
   ('Biking'),
   ('Mountain Hiking'),
   ('Mountain Biking');


# INSERT INTO events (id, name, date, time, location, meet_location, meet_time, event_details, owner_id, trail_id)
# VALUES
#     (1, 'Cool Trail', '2021-06-21', '10:00', 'San Antonio', 'Burger King', '0700', 'yes', 2, 6),
#     (2, 'Hills', '2021-09-20', '13:00', 'San Antonio', 'Mcdonald', '12:00', 'The best ride of your life', 4, 7);

INSERT INTO trails(id, difficulty_level, rating, length, elevation, name, type, trail_details)
VALUES
  (1, 6, 5, 5, 200, 'Salado Creek Greenway Trail', 'rocky', 'This trail is so cool! Said Karla!'),
  (2, 3, 3, 10, 132, 'Olmos Basin Greenway Trail', 'smooth', 'this is a great fun ride guys'),
  (3, 4, 2, 12, 321, 'Stone Oak South Trail', 'medium terrain', 'Yes, this is just nature yes');