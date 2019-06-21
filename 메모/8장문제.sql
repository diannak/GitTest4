01.다음 표에 명시된 대로 DEPT 테이블을 생성하시오.

create table dept( 
            dno number (2), --데이터 저장가능한 크기
            dname varchar2(14),
            loc varchar2(13)
);

02.다음 표에 명시된 대로 EMP 테이블을 생성하시오.

create table emp(
            eno number (4),
            ename varchar2(10),
            dno number (2)
);

03.긴 이름을 저장할 수 있도록 EMP 테이블을 수정하시오(ENAME 칼럼의 크기).

alter table emp
modify (ename varchar2 (25));


04.EMPLOYEE 테이블을 복사해서 EMPLOYEE2란 이름의 테이블을 생성하되
사원번호. 이름, 급여, 부서번호 칼럼만 복사하고 
새로 생성된 테이블의 칼럼명은 각각 EMP_ ID, NAME, SAL, DEPT_ID로 지정하시오

create table employee2 as 
select eno as "[EMP_ID]", ename as"[DNAME]", salary as "[SAL]", dno as"[DEPT_ID]" 
from employee;

05.EMP 테이블을 삭제하시오.
drop table emp;

06.EMPLOYEE2란 이름을 EMP로 변경하시오.
rename employee2 to EMP;

07.DEPT 테이블에서 DNAME 칼럼을 제거하시오.

alter table dept
drop column DNAME;

08.DEPT 테이블에서 LOC 칼럼을 UNUSED로 표시하시오.

alter table dept
set unused (loc);

09.UNUSED 칼럼을 모두 제거하시오.

alter table dept
drop unused columns;

commit;
