11장 create view

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
values(8001,'권다연');
-------------------------------------------------------------
다양한 뷰 옵션

create or replace view v_emp_job_nochk
as 
select eno, ename, dno, job
from employee
where job like 'MANAGER%' with check option;

insert into v_emp_job_nochk
values(8002,'권다연2',10,'MANGER12');
혹은
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
from user_sequenceㄴ;

-----------------------------------------------------------------
create table dept2
as
select *from department where 0=1;--말이 안되는 조건,=구조만 복사

desc dept2;
select*from empbbs;

create sequence dno_seq
increment by 10
start with 10;
--시퀀스 기본키에 접목하기 
insert into dept2
values (dno_seq.nextval,'회계','서울');
insert into dept2
values (dno_seq.nextval,'총무','부산');-넥스트 발 한번 할때마다 현재번호가 자동으로 숫자 증가, 어떤 경우에도 유니크 중복 안됨.
insert into dept2
values (dno_seq.nextval,'영업','대구');

create table empbbs(
                    num number(4) primary key,
                    title varchar2(20) not null,
                    contents varchar2(200)
                    );
create sequence seqempnum
start with 4;
--a만들고 이렇게 테스트해보고 제대로 나오면 드랍하고 다시 만들어서 사용.
insert into empbbs values (seqempnum.nextval,'제목1','내용1'); 
insert into empbbs values (seqempnum.nextval,'제목2','내용2'); 
insert into empbbs values (seqempnum.nextval,'제목3','내용3'); 
select seqempnum.nextval from dual;

desc empbbs;
select*from empbbs;
--시퀀스 수정 제거 
drop sequence seqempnum;--원래꺼 제거
create sequence seqempnum
start with 4;--수정
select seqempnum.nextval from dual;--넥스트발 4부터 시작함.
----------------------------------------------------------------------------------------
create sequence seqtest
minvalue 1
maxvalue 100
increment by -1
start with 100;
select seqtest.nextval from dual;
