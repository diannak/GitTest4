--채팅창 만들기
create table CUSTOMER (
                name varchar (10)   not null, 
                id varchar(10)     primary key,
                pw varchar(10) not null);          
commit;

select*from CUSTOMER;
drop table NewClientEX;

create table NewClientEX (
                name varchar (10)   not null, 
                id varchar(10)     primary key,
                pw varchar(10) not null);  
commit;
drop table NewClientEX;
select *from NewClientEX;



