create table car_body (
      id serial primary key,
      name text
);

create table engine (
    id serial primary key,
    name text
);

create table transmission (
    id serial primary key,
    name text
);


create table car (
    id serial primary key,
    name text,
    car_body_id int references car_body(id),
    engine_id int references engine(id),
    transmission_id int references  transmission(id)
);

insert into car_body (name) values ('Крыша');
insert into car_body (name) values ('Капот');
insert into car_body (name) values ('Дверь');
insert into car_body (name) values ('Фары');
insert into car_body (name) values ('Ветровое стекло');

insert into engine (name) values ('2,5l V6');
insert into engine (name) values ('1,4 l');
insert into engine (name) values ('1,9 l');
insert into engine (name) values ('3,5 l v8');
insert into engine (name) values ('0,8 l');

insert into transmission (name) values ('Automatic transmission');
insert into transmission (name) values ('CVTF');
insert into transmission (name) values ('Manual transmission');
insert into transmission (name) values ('Robotic transmission');

insert into car (name, car_body_id, engine_id, transmission_id) values ('Audi A4', 1, 1, 1);
insert into car (name, car_body_id, engine_id, transmission_id) values ('Bmw X5', 4, 4, 3);
insert into car (name, car_body_id, engine_id, transmission_id) values ('VW Polo', null, 2, 2);
insert into car (name, car_body_id, engine_id, transmission_id) values ('Toyota Camry', 3, null, 2);
insert into car (name, car_body_id, engine_id, transmission_id) values ('Lada Granta', 3, null, null);
insert into car (name, car_body_id, engine_id, transmission_id) values ('Scoda Octavia', 2, 3, 1);
insert into car (name, car_body_id, engine_id, transmission_id) values ('Kia Spectra', null, 2, 3);

select c.name as Модель , cb.name as Кузов , e.name as Двигатель , t.name as "Коробка передач"
from car c
left join car_body cb
on c.car_body_id = cb.id
left join engine e
on c.engine_id = e.id
left join transmission t
on c.transmission_id = t.id;

select cb.name as Кузов
from car c
right join car_body cb
on c.car_body_id = cb.id
where c.name isnull;

select e.name as Двигатель
from car c
right join engine e
on c.engine_id = e.id
where c.name isnull;

select t.name as "Коробка передач"
from car c
right join transmission t
on c.transmission_id = t.id
where c.name isnull;