select ename, salary
from employee
where salary >(  select salary
                from employee
                where ename ='SCOTT');
select dno, min(salary)
from employee
group by dno;
=부서별 min월급 나옴.

부서별fh 최소 급여를 받는 사원의 번호를 뽑아주세요 
select dno, ename
from employee
where salary in (select min(salary) from employee group by dno);

select eno, ename, job, salary
from employee 
where salary< all (select salary from employee where job = 'SALESMAN') and job<>'SALESMAN';

where 셀러리맨의 월급보다 월급이 적은 그외 직원들의  월급을 모두 축출하라.

---------------------------------------------------------------------------------------------
select replace('abcdefg','c','123')from dual;
c = 123으로 치환됨.
->replace = 뭉친걸 바꾸는것
but 치환될 상대가 각각 떨어져 있을때는 문자열 대체 작업안함.
ex--;
select replace ('abcdefg','cfg','123') from dual;

select translate('abcdefg','cfg','123') from dual;
translate =  각자리마다 바꾸는 것.
해당 자리에 차례대로 해당 문자로 대체됨.
c=1 f=2 g=3
 치환될 문자가 없을시 삭제됨.
 (' (공백)  ')=대체할 문자가 없으면 다 지워 버림. 
-----------------------------------------------------------------
숫자는 숫자로 치환
문자는 제거.(치환될것이없기때문에)->전화번호 select할때많이 쓰임.

select translate('abc1def2', '0123456789' || 'abc1def2', '0123456789') from dual;
--------------------------------------------------------------------------------------------

