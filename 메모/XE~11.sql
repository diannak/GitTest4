
create table Oracle_client (    id varchar2(10) primary key,
                                pw varchar2(10) not null,
                                name varchar2(10)
                             );
commit;

select*from Oracle_client;

delete from Oracle_client where name= '±Ç´Ù¿¬';