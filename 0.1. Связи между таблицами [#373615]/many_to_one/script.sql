create table course_number(
    id serial primary key,
    course: int
);

create table students(
    id serial primary key,
    name txt,
    course_id int references course_number(id)
);