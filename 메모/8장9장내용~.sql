-- 테이블 생성[create table 임의명]
create table dept(
            dno number (2), --데이터 저장가능한 크기
            dname varchar2(14),
            loc varchar2(13)
);

create table dept2 as select *from dept;

select*from dept2;

create table dept3 as select *from department;
select*from dept3;

--기존 정보수정 [modify(loc varchar2(3))]

alter table dept3
modify loc varchar2(25);-- 데이터를 줄이는 건 불가능 기존것을 고칠때는 modify

--퀄럼 제거[drop column birth;]
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
--테이블 구조를 제거하는 정보까지 모두 제거하는 Drop table문
truncate table dept3;
drop table dept22;

select table_name from user_tables 
order by table_name;

--관리자가 와서 보는 테이블
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
values(40, 'TEST',' ');--SPACE 들어감
----------------------------------------------------------------------------
desc dept2;
select*from dept2;
update dept2
set dname = 'PROGRAMMING'
WHERE dno=10;--10인 애만 바꾸겠다

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
commit;--삭제 확인 완전인정 완전 삭제 안뇽~.

