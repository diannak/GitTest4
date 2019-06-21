01.모든 사원의 급여 최고액, 최저액, 총액 및 평균 급여를 출력하시오.
칼럼의 별칭은 결과 화면과 동일하게 지정 하고 평균에 대해서는 정수로 반올림하시오.
[Maximum]	[Minimum]	[Sum]		[Average]

select  sum(salary)as "[Sum]",
        Round(avg(salary)) as "[Average]",
        max(salary)as "[Maximun]",
        min(salary)as "[minmum]"
from employee; 

select round(avg(salary)) as"[평균]"
from employee;

02.각 담당 업무 유형별로 급여 최고액, 최저액, 총액 및 평균액을 출력하시오. 
칼럼의 별칭은 결과 화면과 동일하게 지정하고 평균에 대해서는 정수로 반올림하시오.
[Job]	[Maximum]	[Minimum]	[Sum]		[Average]

select  JOB as"[JOB]",
        sum(salary)as "[Sum]",
        Round(avg(salary)) as "[Average]",
        max(salary)as "[Maximun]",
        min(salary)as "[minmum]"
from employee
group by job;

03.count(*) 함수를 이용하여 담당 업무가 동일한 사원 수를 출력하시오.
select distinct job as "담당업무" , count (*) as "사원의 수" from employee group by job order by job desc;

04.관리자수를 나열하시오.
select count(distinct(manager))
from employee 
where manager is not null;

05.급여 최고액, 급여 최저액의 차액을 출력하시오. 칼럼의 별칭은 결과 화연과 동일하게 지정하시오.
[DIFFERENCE]
select (max(salary)- min(salary)) as "[difference]"
from employee;


06.직급별 사원의 최저 급여를 출력하시오. 
관리자를 알 수 없는 사원 및 최저 급여가 2000 미만인 그룹은 제외시키고 결과를 급여에 대한 내림차순으로 정렬하여 출력하시오.
 
 select job, min(salary)as"[min]" 
 from employee
 where manager is not null
 group by job 
 having  min(salary)>=2000
 order by job;

//having 그룹에 대한 조건제한

07.각 부서에 대해 부서번호, 사원수, 부서 내의 모든 사원(grouping)의 평균 급여를 출력하시오.
칼럼의 별칭은 결과 화면과 동일하게 지정하고 평균 급여는 소수점 둘째 자리로 반올림하시오.
[DNO]		[Number of People]		[Salary]

select  dno as "[DNO]", ename, 
        count(*) as "[Number of People]",  
        Round(avg(salary,2) as "[Salary]", 
from employee
group by dno
order by dno asc ;      


(*) all

08.각 부서에 대해 부서번호 이름, 지역명, 사원수, 부서내의 모든 사원의 평균 급여를 출력하시오. 
칼럼의 별칭은 결과 화면과 동일하게 지정하고 평균 급여는 정수로 반올림하시오.
[dname]	[Location]	[Number of People]		[Salary]

select dno,
        decode(dno, 10,'accounting',
        decode(dno, 20,'research',
        decode(dno, 30,'salse',
        decode(dno, 40,'operations'as "[dname]",
        decode(dno, 10,'newyork',
        decode(dno, 20,'dallas',
        decode(dno, 30,'chicago',
        decode(dno, 40,'boston' as "[Location]",
        count(*)as "[Number of people]",
        round (avg(salary))as"[Average of Salary]"
from employee
group by dno
order by dno;

09.업무를 표시한 다음 해당 업무에 대해 부서번호별 급여 및 부서 10, 20, 30의 급여 총액을 각각 출력하시오.
각 칼럼에 별칭은 각각 Job, 부서 10, 부서 20, 부서 30, 총액으로 지정하시오.
[Job]		[DNO]		[부서 10]	[부서 20]	[부서 30]	[총액]

select dno,job,
        decode(dno, 10, to_ char (sum (salary)),'-') as "[부서10]",
        decode(dno, 20,sum (salary)),'-')as "[부서20]",
        decode(dno, 30,sum (salary)),0)as"[부서30]"
       sum(salary)as"[총액]" 
from empolyee
group by dno
order by dno;