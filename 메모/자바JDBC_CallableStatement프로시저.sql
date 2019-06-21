--JDBC4
create table salary(
            name varchar2(10),
            pay number(10));
insert into salary values ('홍길동',1000000);
insert into salary values ('전우치',1000000);
insert into salary values ('손오공',1000000);
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
트랜잭션 
create table test3 (
                id varchar(10),
                password varchar(10)
                );
insert into test3 values ('이순신', '0000');
commit;

--JDBC6
프로퍼티

url= jdbc:oracle:thin:@localhost:1521:xe
user=scott
password=tiger

drop table test4;
