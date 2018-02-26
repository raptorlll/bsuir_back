INSERT INTO app_role (id, role_name, description)
  VALUES (1, 'CUSTOMER', 'CUSTOMER');
INSERT INTO app_role (id, role_name, description)
  VALUES (2, 'CONSULTANT', 'CONSULTANT');
INSERT INTO app_role (id, role_name, description)
  VALUES (3, 'ADMIN_USER', 'ADMIN_USER');

-- USER
-- non-encrypted password: jwtpass
INSERT INTO app_user (id, first_name, last_name, password, username, email)
  VALUES (1, 'John', 'Doe', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'customer', 'gmail@gmail.com');
INSERT INTO app_user (id, first_name, last_name, password, username, email)
  VALUES (2, 'Admin', 'Admin', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'consultant', 'gmail@gmail.com');
INSERT INTO app_user (id, first_name, last_name, password, username, email)
  VALUES (3, 'Admin', 'Admin', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'admin', 'gmail@gmail.com');


INSERT INTO user_role(user_id, role_id)
  VALUES(1,1);
INSERT INTO user_role(user_id, role_id)
  VALUES(2,1);
INSERT INTO user_role(user_id, role_id)
  VALUES(2,2);
INSERT INTO user_role(user_id, role_id)
  VALUES(3,2);
INSERT INTO user_role(user_id, role_id)
  VALUES(3,3);

-- Populate random city table

INSERT INTO random_city(id, name)
  VALUES (1, 'Bamako');
INSERT INTO random_city(id, name)
  VALUES (2, 'Nonkon');
INSERT INTO random_city(id, name)
  VALUES (3, 'Houston');
INSERT INTO random_city(id, name)
  VALUES (4, 'Toronto');
INSERT INTO random_city(id, name)
  VALUES (5, 'New York City');
INSERT INTO random_city(id, name)
  VALUES (6, 'Mopti');
INSERT INTO random_city(id, name)
  VALUES (7, 'Koulikoro');
INSERT INTO random_city(id, name)
  VALUES (8, 'Moscow');
