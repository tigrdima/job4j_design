select * from fauna where name like '%fish%';

select * from fauna where avg_age between 10000 and 20000;

select * from fauna where discovery_date isnull;

select * from fauna where discovery_date < '01/01/1950';