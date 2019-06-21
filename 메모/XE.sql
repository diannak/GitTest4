

select*from employee where dno = 10;
select dno from employee where ename = 'SCOTT';
select *from employee where hiredate <= '1981.03.25';
select *from employee where salary between 1000 and 1500;
select * from employee where salary >=1000 and salary <=1500;
select *from employee where commission = 300 or commission= 500 or commission = 1400;
select * from employee where ename like 'M%';
select * from employee where job like 'M%';
select * from employee where ename like '_A%';
select * from employee where dno = 20 order by salary desc;

01��� ����� ���ؼ� $300 �� �޿� �λ��� ����� �� 
����� �̸�, �޿�, �λ�� �޿��� ����Ͻÿ�.
select all eno, ename, salary,salary+300 from employee; 
select ename, selary, salary+300 as "�λ�� �޿�" form employee order by salary;

02.����� �̸�, �޿�, ���� �Ѽ����� �� ������ ���� �ͺ��� ���� ������ ����Ͻÿ�. 
���� �Ѽ����� ���޿� 12�� ���� �� $100�� �󿩱��� ���ؼ� ����Ͻÿ�.
select ename, salary,  salary*12+100 as "�� ��" from employee order by salary asc; 
select ename, salary,  salary*12+100 as "�� ��" from employee order by (salary*12+100 )asc; 

03.�޿��� 2000�� �Ѵ� ����� �̸��� �޿��� �޿��� ���� �ͺ��� ���� ������ ����Ͻÿ�.
select ename, salary from employee where salary>2000 order by salary desc; 

04.�����ȣ�� 7788�� ����� �̸��� �μ���ȣ�� ����Ͻÿ�.
select ename, dno from employee where eno = 7788;

05.�޿��� 2000���� 3000 ���̿� ���Ե��� �ʴ� ����� �̸��� �޿��� ����Ͻÿ�.
select ename, salary from employee where not salary >=5000;
select ename, salary from employee where salary<2000 or salary >3000;

06.1981�� 2�� 20�Ϻ��� 1981�� 5�� 1�� ���̿� �Ի��� ����� �̸�, ��� ����, �Ի����� ����Ͻÿ�.
select ename, job ,hiredate from employee where hiredate <='1981 05 01'; 

07.�μ� ��ȣ�� 20 �� 30�� ���� ����� �̸��� �μ���ȣ�� ����ϵ� �̸��� ����(��������)���� �����ڼ����� ����Ͻÿ�.
select ename, eno from employee where dno <= 30 order by ename desc;

08.����� �޿��� 2000���� 3000 ���̿� ���Եǰ� �μ���ȣ�� 20 �Ǵ� 30�� ����� �̸�, 
�޿��� �μ���ȣ�� ����ϵ� �̸� ��(��������)���� ����Ͻÿ�.
select ename, salary, dno from employee where salary between 2000 and 3000 order by ename desc;
select ename, salary, dno from employee where (salary >=2000 and salary <=3000) and (dno = 20 or dno = 30)order by ename desc;
select ename, salary, dno from employee where salary between 2000 and 3000 and (dno in (20,30))order by ename desc;

09.1981�⵵�� �Ի��� ����� �̸��� �Ի����� ����Ͻÿ�(Iike �����ڿ� ���ϵ�ī�� ���).
select ename, hiredate from employee where hiredate like '81%';
select ename, hiredate from employee where hiredate between '1981 02 20' and '1981 05 01';
select ename, hiredate from employee where hiredate >= '1981 02 20' and hiredate <='1981 05 01';

10.�����ڰ� ���� ����� �̸��� ��� ������ ����Ͻÿ�.
select ename, job from employee where manager is  null;

11.Ŀ�̼��� �޴� ����� �̸�, �޿�, Ŀ�̼��� ����ϵ� �޿� �� Ŀ�̼��� �������� �������� �����Ͽ� ǥ���Ͻÿ�.
select ename, salary, commission from employee where commission is not null order by salary  desc, commission desc;

12.�̸��� �� ��° ���ڰ� R�� ����� �̸��� ǥ���Ͻÿ�
select ename from employee where ename like '__R%';

13.�̸��� A�� E�� ��� �����ϰ� �ִ� ����� �̸��� ǥ���Ͻÿ�.
select ename from employee where ename like '%A%' and ename like '%E%';

14.��� ������ �繫��(CLERK) �Ǵ� �������(SALESMAN)�̸鼭 �޿��� $1600, $950 �Ǵ� $1300�� �ƴ� 
����� �̸�, ��� ����, �޿��� ����Ͻÿ�.
select ename , job, salary from employee where job = 'CLEARK' or job = 'SALESMAN' and salary not in (1600,950, 1300);

15.Ŀ�̼��� $500�̻��� ����� �̸��� �޿� �� Ŀ�̼��� ����Ͻÿ�.
select ename, salary from employee where commission <500;

select 'Oracle mania', UPPER('Oracle mania'),LOWER('Oracle mania'), INITCAP('Oracle mania')from dual;
select*from employee where upper (ename) = 'SCOTT';
ename�� ���� �ʵ��� ���ڵ��� �� �빮�ڷ� ��ȯ�� ��

select length('Oracle mania'),length('����Ŭ �ŴϾ�') from dual;
���� �״�� �����ִ°�.
select lengthb('Oracle mania'),lengthb('����Ŭ �ŴϾ�') from dual;
����Ʈ�� ���ڸ� ������. �� ���ڴ� 3����Ʈ
select 'Oracle',' mania') concat ("Oracle', 'Mania')from dual;
