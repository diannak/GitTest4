10.������ ���Ἲ�� �������� ���� ����
drop table customer;
select *from customer;
-------------------------------------------------------------------------------
create table customer ( id varchar2(20),
                        pwd varchar2(20) constraint customer_PWD_NN not null,
                        name varchar2(20) not null,
                        phone varchar2(30),
                        address varchar2(100),
                        constraint customer_id_uk primary key (id)--primary key = unique+notnull ���̺� �ϳ��� 
                        );
insert into customer (id, pwd,name,phone,address)
values ('a3','a3','ȫ�浿2','010-1234','null');

select table_name, constraint_name
from user_constraints
where table_name = 'CUSTOMER';

update customer
set phone= '010'
where phone is null;
-----------------------------------------------------------------------------------------
�������� �ٽ��غ� ��.
create table dept1-1 (     
                        dno NUMBER(2) constraint dept_dno_pk primary key,
                        dname VARCHAR2(14), 
                        loc VARCHAR2(13)
                     );

create table emp11(
                eno number(4) constraint emp_eno_pk primary key,
                ename varchar2(10),
                job varchar2(9),
                dno number(2)constraint emp_dno_fk references dept
                );
insert into emp1_1
values (1234,'ȫ�浿', 'clerk',10 )
delete from emp1-1;

insert into emp1-1
select eno, ename, job, dno from employee;

select *from emp1-1;
desc emp1-1;

-------------------------------------------------------------------------------------------------
CREATE TABLE EMP3 (
                    eno number(4) constraint emp_eno_pk primary key,
                    ename varchar2(10),
                    salary number(7,2) constraint emp_salary_chk CHECK(salary>0)
                    );
insert into EMP3
values (123,'ȫ�浿','0');
------------------------------------------------------------------------------------------------------
CREATE TABLE emp4 (
                    eno number(4) constraint emp4_eno_pk primary key,
                    ename varchar2(10),
                    salary number(7,2) default 1000--�Է��� ���Ǽ�
                    );
insert into emp4(eno, ename)
values (123,'ȫ�浿');

desc emp4;
select * from emp4;