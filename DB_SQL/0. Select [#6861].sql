select * from fauna where name like '%fish%';

select * from fauna where avg_age>10000 and avg_age<21000;

select * from fauna where discovery_date isnull;

select * from fauna where discovery_date > '1950';