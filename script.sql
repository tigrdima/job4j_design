create table students(
 	id serial primary key,
	name varchar(255),
	course int,
	phone text
);
insert into students(name, course, phone) values ('Иван Иванов Иванович', 3, '+7 999 999 99 99');
select * from students;
update students set name = 'Иван Иванович';
select * from students;
delete from students;