create table Oracle_client(name varchar(10),
                    id varchar(10) constraint client_id_nn primary key,
                    pw varchar(10) not null);
select * from Oracle_client;

insert into Oracle_client values ('±Ç´Ù¿¬','ABC','1234');
insert into Oracle_client values ('À±Çö','DEF','1234');
delete from Oracle_client where name = 'È«±æµ¿'; 
