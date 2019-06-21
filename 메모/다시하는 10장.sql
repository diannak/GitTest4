--�������� �˻�
select table_name, constraint_name
from user_constraints
where table_name in ('emp');

--���̺����a
select *from DDA;

create table DDA(
                    id varchar2(20) unique,
                    pwd varchar2(20) not null,
                    name varchar2(20) not null,
                    phone varchar2(30),
                    address varchar2(100)
                    );
--���̺� ��������                    
insert into DDA
values ('id2', '1234', '�Ǵٿ�2',null,'');

 --not null���� ������ ������ �����Ͱ� ���ԵǾ���Ѵ�.
 --������ �̷��� ���̺�� �ڿ� ���� �޾������.
 insert into DDA(id,pwd,name)
 values ('id2', '1234', '�Ǵٿ�2');
---------------------------------------------------------------------------------- 
 --unique ���� ����
 drop table DDA; --�� �ٽ� ����
 --create table DDA(
                    id varchar2(20) unique,
                    pwd varchar2(20) not null,
                    name varchar2(20) not null,
                    phone varchar2(30),
                    address varchar2(100)
                    ); 
                    
                    
 --����ũ�� ���� ������ ������ ����.                   
 insert into DDA(id,pwd,name)
 values ('', '1234', '�Ǵٿ�3');    
 --����ũ�� �ΰ�����.
  insert into DDA(id,pwd,name)
 values ('', '1234', '�Ǵٿ�3'); 
 --�ѹ��� �غ���.
  insert into DDA(id,pwd,name)
 values ('', '1234', '�Ǵٿ�4'); 
 
 --�ٽ��ѹ� �����մϴ�. DDA
 drop table DDA;
 
--constraint �� �̿��ϰ� �̸��� ���Ƿ� �������༭ �ٽø�����. 
--�׳� ����ũ���� �������� �˾ƺ��� ����ϱ� �̸��� �޾Ƽ� �ٽ� ���������!
 create table DDA(
                    id varchar2(20) constraint DDa_id_uk unique,
                    pwd varchar2(20)constraint DDa_pwd_nn not null,
                    name varchar2(20)constraint DDa_name_nn not null,
                    phone varchar2(30),
                    address varchar2(100)
                    );
 drop table DDA;                   
--primary key ��������
--�����̸Ӹ� Ű�� �� �⺻Ű�� �����Ѵ�.  �� �ȵǰ� �ߺ��ȵ�!
 --��PK����
 create table DDA(
                    id varchar2(20)
                    pwd varchar2(20)constraint DDa_pwd_nn not null,
                    name varchar2(20)constraint DDa_name_nn not null,
                    phone varchar2(30),
                    address varchar2(100)
                    constraint  DADA_id_pk primary key (id)--���̺���������
                    
                    ); 
 create table DDA(
                    id varchar2(20) constraint  DDA_id_pk primary key,
                    pwd varchar2(20)constraint DDA_pwd_nn not null,
                    name varchar2(20)constraint DDA_name_nn not null,
                    phone varchar2(30),
                    address varchar2(100)
                    );                    --�÷���������
drop table DDA; --(�������ϱ� ����ô�.)
--�������Ἲ�� ���� �ܷ�Ű ���� ����
create table dept (
                    dno number(2) constraint dept_dno_pk primary key,
                    dname varchar2(14),
                    loc varchar2(13) 
                    );
insert into dept select * from department;                    
create table emp (
                    eno number(4) constraint emp_eno_pk primary key,
                    ename varchar2(10),
                    job varchar2(9), 
                    dno number(2) constraint emp_dno_fk references dept
                    );                   

--   

insert into emp
values(1,'test1','job1',10);--���� �ƹ� ������ ���� ������ 10�� �Ұ����Ѱ�.


--���̺� ������ ����. ���� ������ ���ٰ� ������ �־���.

insert into emp select eno,ename,job, dno from employee;

values(1,'test1','job1',10);

drop table emp; 

--üũ���� ����--------------------------------------------------------------------------------
create table emp (
                    eno number(4) constraint emp_eno_pk primary key,
                    ename varchar2(10),
                    job varchar2(9), 
                    salary number(7,2),
                    constraint emp_salary_chk1 CHECK(salary>0), --���̺� ��������.
                     constraint emp_salary_chk2 CHECK(salary!=0)
                    );
                    
select table_name, constraint_name
from user_constraints
where table_name in ('emp');

 select *from emp2;        
 --------����Ʈ ���� ����---------------------------------------------------------------------------
 create table emp2 (
                    eno number(4) constraint emp2_eno_pk primary key,
                    ename varchar2(10),
                    salary number(7,2)default 1000

                   );
insert into emp2
values (1,'test1',2000);

insert into emp2
values (1,'test2');--���� �÷�����

insert into emp2(eno,ename)--�� ��������� ���� �޾���
values (3,'test3');

insert into emp2
values (2,'test2',null);--���� ���� ���� �ΰ����� ����Ʈ �� �Է� �ȵ� ����
--------�������� �����ϱ�-------------------------------------------------------------------------
drop table emp2;
create table dept (
                    dno number(2) constraint dept_dno_pk primary key,
                    dname varchar2(14),
                    loc varchar2(13) 
                    );
 --�̹� ������ ���� ������ ������.                   
create table emp (
                    eno number(4) constraint emp_eno_pk primary key,
                    ename varchar2(10),
                    job varchar2(9), 
                    dno number(2) constraint emp_dno_fk references dept
                    ); 
alter table emp
modify ename varchar2(10) ; 
--�ٽ� �����ϱ�.
alter table emp
modify ename varchar2(10) null; 
--�������� �˻�
select table_name, constraint_name
from user_constraints
where table_name in ('emp'.'dept');

alter table dept
drop primary key;-- ���� ������..
alter table dept
drop primary key cascade;

-------------------------------------------------------------------------------------

insert into emp
values (1,'test1','job1',10);

select*from emp;