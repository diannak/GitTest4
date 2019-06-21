Chater 10_데이터 무결성과 제약 조건
--제약조건 확인

select table_name, constraint_name
from user_constraints
where table_name in ('exemp');

01.employee 테이블의 구조를 복사히여 EMP_SAMPLE란 이름의 테이블을 만드시오. 
사원 테이블의 사원번호 칼럼에 테이블 레벨로 primary key 제약 조건을 지정하되 
제약 조건 이름은 my_emp_pk로 지정하시오.

select *from exemp;
drop table exemp;

create table exemp as select eno,ename,job,  from employee;

select *from exemp;
alter table exemp (
add                 eno number(4) constraint exemp_my_emp_pk primary key,
                    ename varchar2(10),
                    job varchar2(9), 
                    commission number(7,2)
                    );


02.부서 테이블의 부서번호 칼럼에 테이블 레벨로 primary key 제약 조건을 지정하되 
제약 조건 이름은 my_ dept_pk로 지정하시오.

create table exdept (
                    dno number(2) constraint exdept_my_dept_pk primary key,
                    dname varchar2(14),
                    loc varchar2(13) 
                    );

03.사원 테이블의 부서번호 칼럼에 존재하지 않는 부서의 사원이 배정되지 않도록 
외래 키 제약 조건을 지정하되 제약 조건 이름은 my_emp_dept_fk로 지정하시오.



04.사원 테이블의 커미션 칼럼에 0보다 큰 값만을 입력할 수 있도록 제약조건을 지정하시오.
create table exemp (
                    eno number(4) constraint emp_eno_pk primary key,
                    ename varchar2(10),
                    job varchar2(9), 
                    commission number(7,2)
                    constraint emp_salary_chk1 CHECK(commission>0), --테이블에 제약조건.
                    );
                    