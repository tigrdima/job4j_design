create table course_number(
    id serial primary key,
    course int
);

create table students(
    id serial primary key,
    name text,
    course_id int references course_number(id)
);

insert into course_number (course) values (1);
insert into course_number (course) values (2);
insert into course_number (course) values (3);
insert into course_number (course) values (4);
insert into course_number (course) values (4);

insert into students (name, course_id) values ('Петя', 2);
insert into students (name, course_id) values ('Вася', 1);
insert into students (name, course_id) values ('Вова', 5);
insert into students (name, course_id) values ('Кирилл', 4);
insert into students (name, course_id) values ('Андрей', 3);

select *
from students as s
join course_number as c
on s.course_id = c.id

select s.name, c.course
from students as s
join course_number as c
on s.course_id = c.id

select s.name as Имя, c.course as Курс
from students as s
join course_number as c
on s.course_id = c.id