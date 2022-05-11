create table category (
    id serial primary key,
    item_category text
);

create table state (
    id serial primary key,
    status text
);

create table role (
     id serial primary key,
     role text
);

create table rules (
    id serial primary key,
    name text,
    rule_read bool,
    rule_create bool,
    rule_delete bool,
    rule_update bool
);

create table role_rules (
    id serial primary key,
    id_role int references role(id),
    id_rules int references rules(id)
);

create table users (
    id serial primary key,
    name text,
    roles_id int references role(id)
);

create table items (
    id serial primary key,
    date_of_creation date,
    body_item text,
    users_id int references users(id),
    category_id int references category(id),
    state_id int references state(id)

);

create table attachs (
    id serial primary key,
    name_file text,
    path_file text,
    items_id int references items(id)
);

create table comments (
    id serial primary key,
    comments text,
    items_id int references items(id)
);







