create table imei(
     id serial primary key,
     n_imei int
 );
 
 create table phone(
     id serial primary key,
     name txt
 );
 
 create table phones_imei(
     id serial primary key,
     imei_id int references imei(id) unique,
     phone_id int references phone(id) unique
 );