-- ���̺� ����[create table ���Ǹ�]
create table dept(
            dno number (2), --������ ���尡���� ũ��
            dname varchar2(14),
            loc varchar2(13)
);

create table dept2 as select *from dept;

select*from dept2;

create table dept3 as select *from department;
select*from dept3;

--���� �������� [modify(loc varchar2(3))]

alter table dept3
modify loc varchar2(25);-- �����͸� ���̴� �� �Ұ��� �������� ��ĥ���� modify

--���� ����[drop column birth;]
desc dept2;
alter table dept2
drop column birth;

--
desc dept3;
alter table dept3
set unused (dno);
select *from dept3;

alter table dept3
drop unused columns;

rename dept2 to dept22;
drop table dept22;
--���̺� ������ �����ϴ� �������� ��� �����ϴ� Drop table��
truncate table dept3;
drop table dept22;

select table_name from user_tables 
order by table_name;

--�����ڰ� �ͼ� ���� ���̺�
select owner, table_name from all_tables
where owner = 'SCOTT';

select owner, table_name from dba_tables
where owner = 'SCOTT';

desc dept;
drop table dept;
drop table dept3;
------------------------------------------------------------------------
create table dept2
as 
select *from department where 0=1;

desc dept2;
INSERT INTO DEPT2
values(10, 'ACCOUNTING', 'SEOUL');

insert into dept2 (dno, dname)
values(20, 'SALES');

insert into dept2 
values(30, 'MARKET',NULL);
select *from dept2;

insert into dept2 
values(40, 'TEST',' ');--SPACE ��
----------------------------------------------------------------------------
desc dept2;
select*from dept2;
update dept2
set dname = 'PROGRAMMING'
WHERE dno=10;--10�� �ָ� �ٲٰڴ�

update dept2
set loc= 'Uijeongbu'
where dno = 30;
select*from dept2;

update dept2
set dname = (select dname from dept2 where dno =30), 
    loc= (select dname from dept2 where dno= 30) 
where dno = 10;
select*from dept2;


delete dept2 where dno = 10;
select*from dept2;
delete dept2;
select*from dept2;

drop table emp2;

create table emp2 as select *from employee;
select *from emp2;

select count (*)from emp2;

select *from emp2;

delete from emp2
where eno = 7934;
select count (*)from emp2;
rollback;
commit;--���� Ȯ�� �������� ���� ���� �ȴ�~.

