01.��� ����� �޿� �ְ��, ������, �Ѿ� �� ��� �޿��� ����Ͻÿ�.
Į���� ��Ī�� ��� ȭ��� �����ϰ� ���� �ϰ� ��տ� ���ؼ��� ������ �ݿø��Ͻÿ�.
[Maximum]	[Minimum]	[Sum]		[Average]

select  sum(salary)as "[Sum]",
        Round(avg(salary)) as "[Average]",
        max(salary)as "[Maximun]",
        min(salary)as "[minmum]"
from employee; 

select round(avg(salary)) as"[���]"
from employee;

02.�� ��� ���� �������� �޿� �ְ��, ������, �Ѿ� �� ��վ��� ����Ͻÿ�. 
Į���� ��Ī�� ��� ȭ��� �����ϰ� �����ϰ� ��տ� ���ؼ��� ������ �ݿø��Ͻÿ�.
[Job]	[Maximum]	[Minimum]	[Sum]		[Average]

select  JOB as"[JOB]",
        sum(salary)as "[Sum]",
        Round(avg(salary)) as "[Average]",
        max(salary)as "[Maximun]",
        min(salary)as "[minmum]"
from employee
group by job;

03.count(*) �Լ��� �̿��Ͽ� ��� ������ ������ ��� ���� ����Ͻÿ�.
select distinct job as "������" , count (*) as "����� ��" from employee group by job order by job desc;

04.�����ڼ��� �����Ͻÿ�.
select count(distinct(manager))
from employee 
where manager is not null;

05.�޿� �ְ��, �޿� �������� ������ ����Ͻÿ�. Į���� ��Ī�� ��� ȭ���� �����ϰ� �����Ͻÿ�.
[DIFFERENCE]
select (max(salary)- min(salary)) as "[difference]"
from employee;


06.���޺� ����� ���� �޿��� ����Ͻÿ�. 
�����ڸ� �� �� ���� ��� �� ���� �޿��� 2000 �̸��� �׷��� ���ܽ�Ű�� ����� �޿��� ���� ������������ �����Ͽ� ����Ͻÿ�.
 
 select job, min(salary)as"[min]" 
 from employee
 where manager is not null
 group by job 
 having  min(salary)>=2000
 order by job;

//having �׷쿡 ���� ��������

07.�� �μ��� ���� �μ���ȣ, �����, �μ� ���� ��� ���(grouping)�� ��� �޿��� ����Ͻÿ�.
Į���� ��Ī�� ��� ȭ��� �����ϰ� �����ϰ� ��� �޿��� �Ҽ��� ��° �ڸ��� �ݿø��Ͻÿ�.
[DNO]		[Number of People]		[Salary]

select  dno as "[DNO]", ename, 
        count(*) as "[Number of People]",  
        Round(avg(salary,2) as "[Salary]", 
from employee
group by dno
order by dno asc ;      


(*) all

08.�� �μ��� ���� �μ���ȣ �̸�, ������, �����, �μ����� ��� ����� ��� �޿��� ����Ͻÿ�. 
Į���� ��Ī�� ��� ȭ��� �����ϰ� �����ϰ� ��� �޿��� ������ �ݿø��Ͻÿ�.
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

09.������ ǥ���� ���� �ش� ������ ���� �μ���ȣ�� �޿� �� �μ� 10, 20, 30�� �޿� �Ѿ��� ���� ����Ͻÿ�.
�� Į���� ��Ī�� ���� Job, �μ� 10, �μ� 20, �μ� 30, �Ѿ����� �����Ͻÿ�.
[Job]		[DNO]		[�μ� 10]	[�μ� 20]	[�μ� 30]	[�Ѿ�]

select dno,job,
        decode(dno, 10, to_ char (sum (salary)),'-') as "[�μ�10]",
        decode(dno, 20,sum (salary)),'-')as "[�μ�20]",
        decode(dno, 30,sum (salary)),0)as"[�μ�30]"
       sum(salary)as"[�Ѿ�]" 
from empolyee
group by dno
order by dno;