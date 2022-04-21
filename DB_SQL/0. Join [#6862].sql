create table departments (
    id serial primary key,
    name text
);

create table employees (
    id serial primary key,
    name text,
    departments_id int references departments(id)
);

insert into departments (name) values ('department 1');
insert into departments (name) values ('department 2');
insert into departments (name) values ('department 3');
insert into departments (name) values ('department 4');
insert into departments (name) values ('department 5');

insert into employees (name, departments_id) values ('Вася', 1);
insert into employees (name, departments_id) values ('Вова', 2);
insert into employees (name, departments_id) values ('Коля', null);
insert into employees (name, departments_id) values ('Оля', 4);
insert into employees (name, departments_id) values ('Ира', null);

select *
from departments d
left join employees e
on d.id = e.departments_id;

select *
from departments d
right join employees e
on d.id = e.departments_id;

select *
from departments d
full join employees e
on d.id = e.departments_id;

select *
from departments d
left join employees e
on d.id = e.departments_id
where departments_id is null;

select d.name, e.name
from departments as d
left join employees as e
on d.id = e.departments_id;

select d.name, e.name
from employees as e
right join departments as d
on d.id = e.departments_id;

create table teens (
    id serial primary key,
    name text,
	gender char(1)
);

insert into teens (name, gender) values ('Катя','Ж');
insert into teens (name, gender) values ('Дима','М');
insert into teens (name, gender) values ('Вася','М');
insert into teens (name, gender) values ('Оля','Ж');
insert into teens (name, gender) values ('Коля','М');
insert into teens (name, gender) values ('Иван','М');
insert into teens (name, gender) values ('Елена','Ж');

select (n1.name, n2.name)
from teens n1
cross join teens n2
where n1.gender != n2.gender and n1.id < n2.id;

