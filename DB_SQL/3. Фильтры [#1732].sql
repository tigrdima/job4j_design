create table product(
	id serial primary key,
	name text,
	price int,
	expired_date date,
	type_id int
);

create table type(
	id serial primary key,
	name text
);

insert into type (name) values ('СЫР');
insert into type (name) values ('МОЛОКО');
insert into type (name) values ('ХЛЕБ');
insert into type (name) values ('КОНСЕРВЫ');
insert into type (name) values ('НАПИТКИ');
insert into type (name) values ('МОРОЖЕНОЕ');

insert into product (name, price, expired_date, type_id) values ('Сыр с картошкой', 500, '29/04/2022', 1);
insert into product (name, price, expired_date, type_id) values ('Сыр сметанниковый', 650, '19/04/2022', 1);
insert into product (name, price, expired_date, type_id) values ('Молоко смородиновое', 150, '24/04/2022', 2);
insert into product (name, price, expired_date, type_id) values ('Молоко вкусное', 80, '18/04/2022', 2);
insert into product (name, price, expired_date, type_id) values ('Хлеб Московский', 20, '23/04/2022', 3);
insert into product (name, price, expired_date, type_id) values ('Хлеб Татарский', 100, '24/04/2022', 3);
insert into product (name, price, expired_date, type_id) values ('Хлеб Деревенский', 120, '20/04/2022', 3);
insert into product (name, price, expired_date, type_id) values ('Консервы Яблоки', 250, '27/04/2022', 4);
insert into product(name, price, expired_date, type_id) values ('Консервы шпроты', 200, '19/04/2022', 4);
insert into product (name, price, expired_date, type_id) values ('Консервы Говядина', 345, '28/04/2022', 4);
insert into product (name, price, expired_date, type_id) values ('Напиток Апельсиновый', 90, '30/04/2022', 5);
insert into product (name, price, expired_date, type_id) values ('Напиток Фреш Айс', 450, '27/04/2022', 5);
insert into product (name, price, expired_date, type_id) values ('Напиток Томатный сок', 345, '15/04/2022', 5);
insert into product (name, price, expired_date, type_id) values ('Молочное мороженое', 40, '21/04/2022', 6);
insert into product (name, price, expired_date, type_id) values ('Мороженое пломбир', 60, '27/04/2022', 6);
insert into product (name, price, expired_date, type_id) values ('Мороженное крем-брюле', 75, '20/04/2022', 6);
insert into product (name, price, expired_date, type_id) values ('Клубничное мороженное', 85, '29/04/2022', 6);

select p.name, t.name
from product as p
join type as t
on p.type_id = t.id
where t.name like 'СЫР'

select * from product where name like '%мороженое%';

select * from product where current_date > expired_date;

select name, price, expired_date  from product where price = (select max(price) from product);

select t.name, count(*)
from product as p
join type as t
on p.type_id = t.id
group by t.name;

select p.name, t.name
from product as p
join type as t
on p.type_id = t.id
where t.name like 'СЫР' or t.name like 'МОЛОКО';

select t.name, count(*)
from product as p
join type as t
on p.type_id = t.id
group by t.name
having count(*) < 10;

select p.name, t.name
from product as p
join type as t
on p.type_id = t.id
group by p.name, t.name
order by t.name asc;



