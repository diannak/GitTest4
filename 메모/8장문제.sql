01.���� ǥ�� ��õ� ��� DEPT ���̺��� �����Ͻÿ�.

create table dept( 
            dno number (2), --������ ���尡���� ũ��
            dname varchar2(14),
            loc varchar2(13)
);

02.���� ǥ�� ��õ� ��� EMP ���̺��� �����Ͻÿ�.

create table emp(
            eno number (4),
            ename varchar2(10),
            dno number (2)
);

03.�� �̸��� ������ �� �ֵ��� EMP ���̺��� �����Ͻÿ�(ENAME Į���� ũ��).

alter table emp
modify (ename varchar2 (25));


04.EMPLOYEE ���̺��� �����ؼ� EMPLOYEE2�� �̸��� ���̺��� �����ϵ�
�����ȣ. �̸�, �޿�, �μ���ȣ Į���� �����ϰ� 
���� ������ ���̺��� Į������ ���� EMP_ ID, NAME, SAL, DEPT_ID�� �����Ͻÿ�

create table employee2 as 
select eno as "[EMP_ID]", ename as"[DNAME]", salary as "[SAL]", dno as"[DEPT_ID]" 
from employee;

05.EMP ���̺��� �����Ͻÿ�.
drop table emp;

06.EMPLOYEE2�� �̸��� EMP�� �����Ͻÿ�.
rename employee2 to EMP;

07.DEPT ���̺��� DNAME Į���� �����Ͻÿ�.

alter table dept
drop column DNAME;

08.DEPT ���̺��� LOC Į���� UNUSED�� ǥ���Ͻÿ�.

alter table dept
set unused (loc);

09.UNUSED Į���� ��� �����Ͻÿ�.

alter table dept
drop unused columns;

commit;
