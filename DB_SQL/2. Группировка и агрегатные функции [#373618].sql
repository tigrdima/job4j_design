create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('Notebook', 2004.50);
insert into devices (name, price) values ('Iphone', 3890.30);
insert into devices (name, price) values ('Smartphone', 14050.50);
insert into devices (name, price) values ('Ipad', 5487.50);
insert into devices (name, price) values ('Macbook', 6845.50);

insert into people (name) values ('Вася');
insert into people (name) values ('Вова');
insert into people (name) values ('Дима');
insert into people (name) values ('Оля');
insert into people (name) values ('Елена');

insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (1, 2);
insert into devices_people (device_id, people_id) values (2, 3);
insert into devices_people (device_id, people_id) values (3, 1);
insert into devices_people (device_id, people_id) values (5, 5);
insert into devices_people (device_id, people_id) values (4, 4);
insert into devices_people (device_id, people_id) values (3, 4);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.devices_id = d.id
group by p.name
having avg(d.price) > 5000


