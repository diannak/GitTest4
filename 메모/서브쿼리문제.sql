select * from employee;
���� ���� ���� ���ϰ�  �ڿ� ���ǹ��� ����ִ� ������� �ϸ� ����.
���� ����
01.�����ȣ�� 7788�� ����� ��� ������ ���� ����� ǥ��(����̸��� ��� ����)�Ͻÿ�.

select ename, job, hiredate
from employee
where job = ( select job from employee where eno = 7788);

--02.�����ȣ�� 7499�� ������� �޿��� ���� ����� ǥ��(����̸��� ��� ����)�Ͻÿ�.

select ename, job, salary
from employee
where salary > (select salary from employee where eno = 7499);

03.�ּ� �޿��� �޴� ����� �̸�, ��� ���� �� �޿��� ǥ���Ͻÿ�(�׷��Լ� ���).

select ename, job, salary
from employee
where salary in (select min(salary) from employee)
order by salary desc;

04.��� �޿��� ���� ���� ����(job)�� ��� �޿��� ǥ���Ͻÿ�.
(��� �޿� = group by�׷��Լ� �����.)

select job, min(salary),round(avg(salary))b--> ��Ī
from employee
group by job
having round(avg(salary))= (select min(round(avg(salary)))from employee group by job);


05.�� �μ��� �ּ� �޿��� �޴� ����� �̸�, �޿�, �μ���ȣ�� ǥ���Ͻÿ�.
select ename,job,dno, salary
from employee
where salary in(select min(salary) from employee group by dno)
order by dno asc;
--T
select dno, ename, salary
from employee
where (dno,salary) in (select dno, min (salary)from employee group by dno);


06.��� ������ �м���(ANALYST)�� ������� �޿��� �����鼭 ������ �м���(ANALYST)�� �ƴ� 
������� ǥ ��(�����ȣ, �̸�, ������, �޿�)�Ͻÿ�.

select eno, ename, job, salary
from employee
where salary < any (select salary from employee where job = 'ANALYST') and job <>'ANALYST';
--T
select eno, ename, job, salary 
from employee 
where salary <(select min(salary)from employee where job = 'ALALYST') and job != 'ANALYST';

07.���������� ���� ����� ��ƴ�� ǥ���Ͻÿ�.
(���������� ���� ���... ���� �Ʒ����.)

select ename, job
from employee
where eno in(select eno from employee where manager is null);
--> ���� ����.
--T
select ename
from employee 
where eno not in(select distinct(nvl(manager,0)) from employee);

08.���������� �ִ� ����� �̸��� ǥ���Ͻÿ�.

select ename , job
from employee 
where eno in (select eno from employee where manager is not null);

--T
select ename
from employee 
where eno in(select distinct(nvl(manager,0)) from employee);

09.BLAKE�� ������ �μ��� ���� ����� �̸�(ename)�� �Ի���(hiredate)�� ǥ���ϴ� ���Ǹ� �ۼ��Ͻÿ�
(��. BLAKE�� ����).

select ename, hiredate
from employee
where dno in (select dno from employee where ename = 'BLAKE') and ename <> 'BLAKE';
--��������.
--T
select ename, hiredate
from employee
where dno in (select dno from employee where ename = 'BLAKE') and ename != 'BLAKE';

10.�޿��� ��� �޿� ���� ���� ������� �����ȣ�� �̸��� ǥ���ϵ�
����� �޿��� ���ؼ� ������������ ���� �Ͻÿ�.

select eno, ename, salary
from employee
where salary > all(select round(avg(salary)) from employee)
order by salary asc;
--T
select eno, ename, salary--(�� ���� ����= ��������)
from employee
where salary > (select round(avg(salary)) from employee)
order by salary;


11.�̸��� K�� ���Ե� ����� ���� �μ����� ���ϴ� �����
�����ȣ�� �̸��� ǥ���ϴ� ���Ǹ� �ۼ��Ͻÿ�

select eno, ename 
from employee
where dno in (select dno from employee where ename like '%K%') and ename not like '%K%';
--T

select eno, ename 
from employee
where dno in (select distinct dno from employee where ename like '%K%');

12.�μ� ��ġ�� DALLAS�� ����� �̸��� �μ���ȣ �� ��� ������ ǥ���Ͻÿ�.

select e.eno, e.ename, e.job, d.dno
from employee e, department d
where loc in (select loc from department where loc = 'DALLAS');
--T
select ename, dno,job 
from employee
where dno = (select dno from department where loc = 'DALLAS');

select *from department;
select *from employee;

13.KING���� �����ϴ� ����� �̸��� �޿��� ǥ���Ͻÿ�.

select e.ename, e.salary, b.dno 
from employee a, department b
where eno in(select dno from employee where manager = 'KING');

select e.ename, e.job, e.eno, d.dno, e.salary
from employee e, department d where manager is not null order by job asc;
--T
select ename, salary, dno ,manager
from employee
where manager=(select eno from employee where ename = 'KING');


14.RESEARCH �μ��� ����� ���� �μ���ȣ ����̸� �� ��� ������ ǥ���Ͻÿ�.
loc ���� �� employee �� ����.
dno, dname, loc = department �� ����.

select d.dno, d.dname, e.ename, e.job
from employee e, department d
where dno in (select dno from employee where dname = 'RESEARCH');
--T
select dno,ename, job
from employee 
where dno = (select dno from department where dname = 'RESEARCH');


select * from employee;
15.��� �޿� ���� ���� �޿��� �ް� �̸��� K�� ���Ե� ����� ���� �μ����� �ٹ��ϴ� ����� �����ȣ, �̸�, �޿��� ǥ���Ͻÿ�.
--T
select eno, ename, salary,dno
from employee
where salary > (select avg(salary from employee) and dno in (select distinct dno from employee where ename like '%K%');

16.��� �޿��� ���� ���� ������ ã���ÿ�.

--T


17.��� ������ ANALYST�� ����� �Ҽӵ� �μ��� ������ �μ��� ����� ǥ���Ͻÿ�.

select eno,ename,dno 
from employee
where dno =( select distinct dnofrom employee where job = 'ANALYST'); 
group by job;