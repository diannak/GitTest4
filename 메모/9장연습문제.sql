Chater 09_������ ���۰� Ʈ�����

select *from emp;
select *from dept;
desc emp;
desc dept;

select *from emp_insert;
desc emp_insert;


01.EMP ���̺��� ������ �����Ͽ� EMP_INSERT�� �̸��� �� ���̺��� ����ÿ�.

create table emp_insert as select*from emp where 0 = 1;

02.������ EMP_INSERT ���̺� �߰��ϵ� SYSDATE�� �̿��ؼ� �Ի����� ���÷� �Է��Ͻÿ�.

alter table emp_insert add (hiredatedate as"[HIREDATE]");

insert into emp_insert values (1, 'DAYEON',null, null, sysdate);

03.EMP_INSERT ���̺� �� ����� �߰��ϵ� TO_DATE �Լ��� �̿��ؼ� �Ի����� ������ �Է��Ͻÿ�.
insert into emp_insert values(2,'EENAE',null,null, sysdate-1);
select*from emp_insert;

04.employee ���̺��� ������ ������ �����Ͽ� EMP_COPY�� �̸��� ���̺��� ����ÿ�.
create table emp_copy as select * from employee; 

05.�����ȣ�� 7788�� ����� �μ���ȣ�� 10������ �����Ͻÿ�.

update emp_copy
set dno= '10'
where eno = 7788;

06.�����ȣ 7788�� ��� ���� �� �޿��� 
   �����ȣ 7499�� ��� ���� �� �޿��� ��ġ�ϵ��� �����Ͻÿ�.
select *from emp_copy;

update emp_copy
set job =( select job from emp_copy  where  eno= 7499),
salary =( select salary form emp_copy where eno = 7499)
where eno = 7788;

07.�����ȣ 7369�� ������ ������ ��� ����� �μ���ȣ�� 
��� 7369�� ���� �μ���ȣ�� �����Ͻÿ�.
select *from emp_copy;
select job, ename, eno from emp_copy where job= 'CLERK';

update emp_copy 
set dno = (select dno from emp_copy where eno = 7369),
where job = ( select job from emp_copy where eno=7369);


08.department ���̺��� ������ ������ �����Ͽ� DEPT_COPY�� �̸��� ���̺��� ����ÿ�.

create table dept_copy as select * from department; 

09.DEPT_COPY ���̺��� �μ����� RESEARCH�� �μ��� �����Ͻÿ�.
delete from dept_copy where DNAME= 'RESEARCH';

10.DEPT_COPY ���̺��� �μ� ��ȣ�� 1001�ų� 40�� �μ��� �����Ͻÿ�.
delete from dept_copy where dno = 1001 or dno = 40;