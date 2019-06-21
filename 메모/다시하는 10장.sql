--제약조건 검색
select table_name, constraint_name
from user_constraints
where table_name in ('emp');

--테이블보여줭
select *from DDA;

create table DDA(
                    id varchar2(20) unique,
                    pwd varchar2(20) not null,
                    name varchar2(20) not null,
                    phone varchar2(30),
                    address varchar2(100)
                    );
--테이블에 정보삽입                    
insert into DDA
values ('id2', '1234', '권다연2',null,'');

 --not null제약 조건은 무조건 데이터가 삽입되어야한다.
 --싫으면 이렇게 테이블명 뒤에 조건 달아줘야해.
 insert into DDA(id,pwd,name)
 values ('id2', '1234', '권다연2');
---------------------------------------------------------------------------------- 
 --unique 제약 조건
 drop table DDA; --후 다시 만듦
 --create table DDA(
                    id varchar2(20) unique,
                    pwd varchar2(20) not null,
                    name varchar2(20) not null,
                    phone varchar2(30),
                    address varchar2(100)
                    ); 
                    
                    
 --유니크는 값을 같은걸 넣을수 없다.                   
 insert into DDA(id,pwd,name)
 values ('', '1234', '권다연3');    
 --유니크도 널값들어간다.
  insert into DDA(id,pwd,name)
 values ('', '1234', '권다연3'); 
 --한번더 해보자.
  insert into DDA(id,pwd,name)
 values ('', '1234', '권다연4'); 
 
 --다시한번 삭제합니다. DDA
 drop table DDA;
 
--constraint 를 이용하고 이름을 임의로 지정해줘서 다시만들자. 
--그냥 유니크인지 낫널인지 알아보기 힘드니까 이름을 달아서 다시 만들어주자!
 create table DDA(
                    id varchar2(20) constraint DDa_id_uk unique,
                    pwd varchar2(20)constraint DDa_pwd_nn not null,
                    name varchar2(20)constraint DDa_name_nn not null,
                    phone varchar2(30),
                    address varchar2(100)
                    );
 drop table DDA;                   
--primary key 제약조건
--프라이머리 키는 은 기본키를 생성한다.  널 안되고 중복안되!
 --ㅡPK예제
 create table DDA(
                    id varchar2(20)
                    pwd varchar2(20)constraint DDa_pwd_nn not null,
                    name varchar2(20)constraint DDa_name_nn not null,
                    phone varchar2(30),
                    address varchar2(100)
                    constraint  DADA_id_pk primary key (id)--테이블제약조건
                    
                    ); 
 create table DDA(
                    id varchar2(20) constraint  DDA_id_pk primary key,
                    pwd varchar2(20)constraint DDA_pwd_nn not null,
                    name varchar2(20)constraint DDA_name_nn not null,
                    phone varchar2(30),
                    address varchar2(100)
                    );                    --컬럼제약조건
drop table DDA; --(다했으니까 지웁시다.)
--참조무결성을 위한 외래키 제약 조건
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
values(1,'test1','job1',10);--현재 아무 정보도 없기 때문에 10이 불가능한것.


--테이블에 정보를 주자. 영로 구렁이 보다가 정보못 넣었음.

insert into emp select eno,ename,job, dno from employee;

values(1,'test1','job1',10);

drop table emp; 

--체크제약 조건--------------------------------------------------------------------------------
create table emp (
                    eno number(4) constraint emp_eno_pk primary key,
                    ename varchar2(10),
                    job varchar2(9), 
                    salary number(7,2),
                    constraint emp_salary_chk1 CHECK(salary>0), --테이블에 제약조건.
                     constraint emp_salary_chk2 CHECK(salary!=0)
                    );
                    
select table_name, constraint_name
from user_constraints
where table_name in ('emp');

 select *from emp2;        
 --------디폴트 제약 조건---------------------------------------------------------------------------
 create table emp2 (
                    eno number(4) constraint emp2_eno_pk primary key,
                    ename varchar2(10),
                    salary number(7,2)default 1000

                   );
insert into emp2
values (1,'test1',2000);

insert into emp2
values (1,'test2');--오류 컬럼부족

insert into emp2(eno,ename)--뒤 쓰기싫으면 조건 달아줘
values (3,'test3');

insert into emp2
values (2,'test2',null);--내가 굳이 넣은 널값때메 디폴트 값 입력 안됨 ㅎㅎ
--------제약조건 변경하기-------------------------------------------------------------------------
drop table emp2;
create table dept (
                    dno number(2) constraint dept_dno_pk primary key,
                    dname varchar2(14),
                    loc varchar2(13) 
                    );
 --이미 위에서 만들어서 정보가 들어가있음.                   
create table emp (
                    eno number(4) constraint emp_eno_pk primary key,
                    ename varchar2(10),
                    job varchar2(9), 
                    dno number(2) constraint emp_dno_fk references dept
                    ); 
alter table emp
modify ename varchar2(10) ; 
--다시 수정하기.
alter table emp
modify ename varchar2(10) null; 
--제약조건 검색
select table_name, constraint_name
from user_constraints
where table_name in ('emp'.'dept');

alter table dept
drop primary key;-- 누가 참조중..
alter table dept
drop primary key cascade;

-------------------------------------------------------------------------------------

insert into emp
values (1,'test1','job1',10);

select*from emp;