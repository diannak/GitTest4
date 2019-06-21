11�� create view

create view v_emp_sample
as
select eno, ename, job, manager,dno
from employee;

----------------------------------------------------------------
create view v_emp_complex
as select e.eno,e.ename,e.salary,dno,d.dname,d.loc
from employee e natural join department d;
---------------------------------------------------------
create view v_emp_sample2
as
select dno,
            sum(salary)as "SAl_sum",
            avg(salary)as sal_avg
from employee
group by dno;
----------------------------------------------------------
create or replace view v_emp_nosalary
as select eno,ename,job, hiredate,dno,manager
from employee;
select *from v_emp_nosalary;

select view_name, text 
from user_views;

insert into v_emp_nosalary
values(8001,'�Ǵٿ�');
-------------------------------------------------------------
�پ��� �� �ɼ�

create or replace view v_emp_job_nochk
as 
select eno, ename, dno, job
from employee
where job like 'MANAGER%' with check option;

insert into v_emp_job_nochk
values(8002,'�Ǵٿ�2',10,'MANGER12');
Ȥ��
create or replace view v_emp_job_nochk
as 
select eno, ename, dno, job
from employee
where job like 'MANAGER' with check option;

-----------------------------------------------------------------
create sequence smaple_seq
increment by 10
start with 10;

select sequence_name, min_value, max_value, increment_by, cycle_flag
from user_sequence��;

-----------------------------------------------------------------
create table dept2
as
select *from department where 0=1;--���� �ȵǴ� ����,=������ ����

desc dept2;
select*from empbbs;

create sequence dno_seq
increment by 10
start with 10;
--������ �⺻Ű�� �����ϱ� 
insert into dept2
values (dno_seq.nextval,'ȸ��','����');
insert into dept2
values (dno_seq.nextval,'�ѹ�','�λ�');-�ؽ�Ʈ �� �ѹ� �Ҷ����� �����ȣ�� �ڵ����� ���� ����, � ��쿡�� ����ũ �ߺ� �ȵ�.
insert into dept2
values (dno_seq.nextval,'����','�뱸');

create table empbbs(
                    num number(4) primary key,
                    title varchar2(20) not null,
                    contents varchar2(200)
                    );
create sequence seqempnum
start with 4;
--a����� �̷��� �׽�Ʈ�غ��� ����� ������ ����ϰ� �ٽ� ���� ���.
insert into empbbs values (seqempnum.nextval,'����1','����1'); 
insert into empbbs values (seqempnum.nextval,'����2','����2'); 
insert into empbbs values (seqempnum.nextval,'����3','����3'); 
select seqempnum.nextval from dual;

desc empbbs;
select*from empbbs;
--������ ���� ���� 
drop sequence seqempnum;--������ ����
create sequence seqempnum
start with 4;--����
select seqempnum.nextval from dual;--�ؽ�Ʈ�� 4���� ������.
----------------------------------------------------------------------------------------
create sequence seqtest
minvalue 1
maxvalue 100
increment by -1
start with 100;
select seqtest.nextval from dual;
