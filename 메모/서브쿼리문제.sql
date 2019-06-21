select * from employee;
서브 쿼리 먼저 구하고  뒤에 조건문에 집어넣는 방식으로 하면 쉽다.
서브 쿼리
01.사원번호가 7788인 사원과 담당 업무가 같은 사원을 표시(사원이름과 담당 업무)하시오.

select ename, job, hiredate
from employee
where job = ( select job from employee where eno = 7788);

--02.사원번호가 7499인 사원보다 급여가 많은 사원을 표시(사원이름과 담당 업무)하시오.

select ename, job, salary
from employee
where salary > (select salary from employee where eno = 7499);

03.최소 급여를 받는 사원의 이름, 담당 업무 및 급여를 표시하시오(그룹함수 사용).

select ename, job, salary
from employee
where salary in (select min(salary) from employee)
order by salary desc;

04.평균 급여가 가장 적은 직급(job)과 평균 급여를 표시하시오.
(평균 급여 = group by그룹함수 써야함.)

select job, min(salary),round(avg(salary))b--> 별칭
from employee
group by job
having round(avg(salary))= (select min(round(avg(salary)))from employee group by job);


05.각 부서의 최소 급여를 받는 사원의 이름, 급여, 부서번호를 표시하시오.
select ename,job,dno, salary
from employee
where salary in(select min(salary) from employee group by dno)
order by dno asc;
--T
select dno, ename, salary
from employee
where (dno,salary) in (select dno, min (salary)from employee group by dno);


06.담당 업무가 분석가(ANALYST)인 사원보다 급여가 적으면서 업무가 분석가(ANALYST)가 아닌 
사원들을 표 시(사원번호, 이름, 담당업무, 급여)하시오.

select eno, ename, job, salary
from employee
where salary < any (select salary from employee where job = 'ANALYST') and job <>'ANALYST';
--T
select eno, ename, job, salary 
from employee 
where salary <(select min(salary)from employee where job = 'ALALYST') and job != 'ANALYST';

07.부하직원이 없는 사원의 이틈을 표시하시오.
(부하직원이 없는 사람... 제일 아랫사람.)

select ename, job
from employee
where eno in(select eno from employee where manager is null);
--> 완전 오답.
--T
select ename
from employee 
where eno not in(select distinct(nvl(manager,0)) from employee);

08.부하직원이 있는 사원의 이름을 표시하시오.

select ename , job
from employee 
where eno in (select eno from employee where manager is not null);

--T
select ename
from employee 
where eno in(select distinct(nvl(manager,0)) from employee);

09.BLAKE와 동일한 부서에 속한 사원의 이름(ename)과 입사일(hiredate)을 표시하는 질의를 작성하시오
(단. BLAKE는 제외).

select ename, hiredate
from employee
where dno in (select dno from employee where ename = 'BLAKE') and ename <> 'BLAKE';
--완전정답.
--T
select ename, hiredate
from employee
where dno in (select dno from employee where ename = 'BLAKE') and ename != 'BLAKE';

10.급여가 평균 급여 보다 많은 사원들의 사원번호와 이름을 표시하되
결과를 급여에 대해서 오름차순으로 정렬 하시오.

select eno, ename, salary
from employee
where salary > all(select round(avg(salary)) from employee)
order by salary asc;
--T
select eno, ename, salary--(에 대한 조건= 서브쿼리)
from employee
where salary > (select round(avg(salary)) from employee)
order by salary;


11.이름에 K가 포함된 사원과 같은 부서에서 일하는 사원의
사원번호와 이름을 표시하는 질의를 작성하시오

select eno, ename 
from employee
where dno in (select dno from employee where ename like '%K%') and ename not like '%K%';
--T

select eno, ename 
from employee
where dno in (select distinct dno from employee where ename like '%K%');

12.부서 위치가 DALLAS인 사원의 이름과 부서번호 및 담당 업무를 표시하시오.

select e.eno, e.ename, e.job, d.dno
from employee e, department d
where loc in (select loc from department where loc = 'DALLAS');
--T
select ename, dno,job 
from employee
where dno = (select dno from department where loc = 'DALLAS');

select *from department;
select *from employee;

13.KING에게 보고하는 사원의 이름과 급여를 표시하시오.

select e.ename, e.salary, b.dno 
from employee a, department b
where eno in(select dno from employee where manager = 'KING');

select e.ename, e.job, e.eno, d.dno, e.salary
from employee e, department d where manager is not null order by job asc;
--T
select ename, salary, dno ,manager
from employee
where manager=(select eno from employee where ename = 'KING');


14.RESEARCH 부서의 사원에 대한 부서번호 사원이름 및 담당 업무를 표시하시오.
loc 빼고 다 employee 에 있음.
dno, dname, loc = department 에 있음.

select d.dno, d.dname, e.ename, e.job
from employee e, department d
where dno in (select dno from employee where dname = 'RESEARCH');
--T
select dno,ename, job
from employee 
where dno = (select dno from department where dname = 'RESEARCH');


select * from employee;
15.평균 급여 보다 많은 급여를 받고 이름에 K가 포함된 사원과 같은 부서에서 근무하는 사원의 사원번호, 이름, 급여를 표시하시오.
--T
select eno, ename, salary,dno
from employee
where salary > (select avg(salary from employee) and dno in (select distinct dno from employee where ename like '%K%');

16.평균 급여가 가장 적은 업무를 찾으시오.

--T


17.담당 업무가 ANALYST인 사원이 소속된 부서와 동일한 부서의 사원을 표시하시오.

select eno,ename,dno 
from employee
where dno =( select distinct dnofrom employee where job = 'ANALYST'); 
group by job;