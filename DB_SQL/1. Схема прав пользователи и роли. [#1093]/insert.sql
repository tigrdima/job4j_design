insert into category (text) values ('important'),
insert into category (text) values ('unimportant'),

insert into state (text) values ('new'),
insert into state (text) values ('in work'),
insert into state (text) values ('processed'),

insert into role (text) values ('admin'),
insert into role (text) values ('manager'),
insert into role (text) values ('trainee'),

insert into rules (name, rule_read, rule_create, rule_delete, rule_update) values ('admin', 'true', 'true', 'true', 'true'),
insert into rules (name, rule_read, rule_create, rule_delete, rule_update) values ('manager', 'true', 'false', 'false', 'true'),
insert into rules (name, rule_read, rule_create, rule_delete, rule_update) values ('trainee', 'true', 'false', 'false', 'false'),

insert into role_rules (id_role, id_rules) values (1, 1),
insert into role_rules (id_role, id_rules) values (2, 2),
insert into role_rules (id_role, id_rules) values (3, 3),

insert into users (name, roles_id) values ('Ivan', 1),
insert into users (name, roles_id) values ('Ira', 2),
insert into users (name, roles_id) values ('Kolya', 3),

insert into items (date_of_creation, body_item, category_id, state_id) values ('01/20/2022', '.......', 1, 1),
insert into attachs (name_file, path_file, items_id) values ('foto.jpg', 'c:/...', 1),
insert into users_items (id_users, id_items) values (1, 1),

insert into comments (comments, items_id) values ('ok', 1),


