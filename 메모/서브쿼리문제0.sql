select ename, salary
from employee
where salary >(  select salary
                from employee
                where ename ='SCOTT');
select dno, min(salary)
from employee
group by dno;
=�μ��� min���� ����.

�μ���fh �ּ� �޿��� �޴� ����� ��ȣ�� �̾��ּ��� 
select dno, ename
from employee
where salary in (select min(salary) from employee group by dno);

select eno, ename, job, salary
from employee 
where salary< all (select salary from employee where job = 'SALESMAN') and job<>'SALESMAN';

where ���������� ���޺��� ������ ���� �׿� ��������  ������ ��� �����϶�.

---------------------------------------------------------------------------------------------
select replace('abcdefg','c','123')from dual;
c = 123���� ġȯ��.
->replace = ��ģ�� �ٲٴ°�
but ġȯ�� ��밡 ���� ������ �������� ���ڿ� ��ü �۾�����.
ex--;
select replace ('abcdefg','cfg','123') from dual;

select translate('abcdefg','cfg','123') from dual;
translate =  ���ڸ����� �ٲٴ� ��.
�ش� �ڸ��� ���ʴ�� �ش� ���ڷ� ��ü��.
c=1 f=2 g=3
 ġȯ�� ���ڰ� ������ ������.
 (' (����)  ')=��ü�� ���ڰ� ������ �� ���� ����. 
-----------------------------------------------------------------
���ڴ� ���ڷ� ġȯ
���ڴ� ����.(ġȯ�ɰ��̾��⶧����)->��ȭ��ȣ select�Ҷ����� ����.

select translate('abc1def2', '0123456789' || 'abc1def2', '0123456789') from dual;
--------------------------------------------------------------------------------------------

