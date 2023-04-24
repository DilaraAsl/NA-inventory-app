
insert into roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                  last_update_user_id, description)
values ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Admin'),
       ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'StudentWorker'),
       ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Employee');
INSERT INTO users (insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                   last_update_user_id,f_name, l_name,user_name, email, password,office_no,role_id)
VALUES ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Alice', 'Jones', 'alicejones@na.edu', 'alicejones@na.edu','$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK','100',1),
       ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Bob', 'Smith', 'bobsmith@na.edu', 'bobsmith@na.edu','$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK','101',2),
       ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Charlie', 'Brown', 'charliebrown@na.edu','charliebrown@na.edu', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK','102',3),
       ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'David', 'Lee', 'davidlee@na.edu','davidlee@na.edu',  '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK','103',3),
       ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Emily', 'Taylor', 'emilytaylor@na.edu', 'emilytaylor@na.edu','$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK','104',3);

INSERT INTO devices(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                    last_update_user_id,make, model, serial_number, price, quantity, check_me_out)
VALUES('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Samsung', 'Galaxy S21', 123456789012345, 89900, 10, true),
      ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Apple', 'iPhone 12 Pro', 234567890123456, 109900, 5, true),
      ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Google', 'Pixel 5', 345678901234567, 79900, 20, true),
      ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'OnePlus', '8T', 456789012345678, 59900, 15, true),
      ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Motorola', 'Moto G Power', 567890123456789, 24900, 25, true);

insert into transactions(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                         last_update_user_id,description) values ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1,'Assign'),('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Retrieve');

