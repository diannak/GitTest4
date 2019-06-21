Chater 10_������ ���Ἲ�� ���� ����
--�������� Ȯ��

select table_name, constraint_name
from user_constraints
where table_name in ('exemp');

01.employee ���̺��� ������ �������� EMP_SAMPLE�� �̸��� ���̺��� ����ÿ�. 
��� ���̺��� �����ȣ Į���� ���̺� ������ primary key ���� ������ �����ϵ� 
���� ���� �̸��� my_emp_pk�� �����Ͻÿ�.

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


02.�μ� ���̺��� �μ���ȣ Į���� ���̺� ������ primary key ���� ������ �����ϵ� 
���� ���� �̸��� my_ dept_pk�� �����Ͻÿ�.

create table exdept (
                    dno number(2) constraint exdept_my_dept_pk primary key,
                    dname varchar2(14),
                    loc varchar2(13) 
                    );

03.��� ���̺��� �μ���ȣ Į���� �������� �ʴ� �μ��� ����� �������� �ʵ��� 
�ܷ� Ű ���� ������ �����ϵ� ���� ���� �̸��� my_emp_dept_fk�� �����Ͻÿ�.



04.��� ���̺��� Ŀ�̼� Į���� 0���� ū ������ �Է��� �� �ֵ��� ���������� �����Ͻÿ�.
create table exemp (
                    eno number(4) constraint emp_eno_pk primary key,
                    ename varchar2(10),
                    job varchar2(9), 
                    commission number(7,2)
                    constraint emp_salary_chk1 CHECK(commission>0), --���̺� ��������.
                    );
                    