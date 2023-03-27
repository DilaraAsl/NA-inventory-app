
INSERT INTO devices(make, model, serial_number, price, quantity, check_me_out,is_deleted)
VALUES('Samsung', 'Galaxy S21', 123456789012345, 89900, 10, false,false),('Apple', 'iPhone 12 Pro', 234567890123456, 109900, 5, true,false),
      ('Google', 'Pixel 5', 345678901234567, 79900, 20, false,false),
      ('OnePlus', '8T', 456789012345678, 59900, 15, true,false),('Motorola', 'Moto G Power', 567890123456789, 24900, 25, true,false);
insert into transactions(description) values ('Assigned'),('Retrieved');

-- Insert user 1
INSERT INTO users (f_name, l_name,user_name, email, password,office_no, is_deleted)
VALUES ('Alice', 'Jones', 'alicejones@na.edu', 'alicejones@na.edu','password123','100', false);

-- Insert user 2
INSERT INTO users (f_name, l_name,user_name, email, password,office_no, is_deleted)
VALUES ('Bob', 'Smith', 'bobsmith@na.edu', 'bobsmith@na.edu','secret123','101', false);

-- Insert user 3
INSERT INTO users (f_name, l_name, user_name,email, password,office_no, is_deleted)
VALUES ('Charlie', 'Brown', 'charliebrown@na.edu','charliebrown@na.edu', '123456','102', false);

-- Insert user 4
INSERT INTO users (f_name, l_name,user_name, email, password,office_no, is_deleted)
VALUES ('David', 'Lee', 'davidlee@na.edu','davidlee@na.edu',  'letmein123','103', false);

-- Insert user 5
INSERT INTO users (f_name, l_name,user_name, email, password,office_no, is_deleted)
VALUES ('Emily', 'Taylor', 'emilytaylor@na.edu', 'emilytaylor@na.edu','pa$$w0rd','104', false);