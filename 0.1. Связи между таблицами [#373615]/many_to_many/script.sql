create table client(
     id serial primary key,
     name txt
 );
 
 create table online_stores(
     id serial primary key,
     name txt
 );
 
 create table client_online_store(
     id serial primary key,
     client_id int references client(id),
     online_stores_id int references online_stores(id)
 );