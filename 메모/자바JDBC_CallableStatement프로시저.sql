--JDBC4
create table salary(
            name varchar2(10),
            pay number(10));
insert into salary values ('ȫ�浿',1000000);
insert into salary values ('����ġ',1000000);
insert into salary values ('�տ���',1000000);
commit;

create or replace procedure adjust(
        n in varchar2,
        rate in float)

is      newpay float;
begin 
        select pay into newpay from salary where name = n;
        newpay :=newpay + newpay * rate;
        update salary set pay = newpay where name = n;
end;
/

select*from salary;

--JDBC5
Ʈ����� 
create table test3 (
                id varchar(10),
                password varchar(10)
                );
insert into test3 values ('�̼���', '0000');
commit;

--JDBC6
������Ƽ

url= jdbc:oracle:thin:@localhost:1521:xe
user=scott
password=tiger

drop table test4;
