12�� �ε��� �����
--USER IND COLUMNS ���� ������ �������� �ε��� ���� Ȯ���ϱ�.
select index_name, table_name, column_name
from user_ind_columns
where table_name in('EMPLOYEE');

select * from
user_ind_columns;
--�����ϱ� 
CREATE INDEX IDX_EMPLOYEE_ENAME ON EMPLOYEE (ENAME);
--���� �ε���
CREATE INDEX IDX_EMPLOYEE_ENAME ON EMPLOYEE (ENAME||eno);

13�� ����� ����
--����
create user scott2 identified by 1234;
create user scott3 identified by 1234;
create user scott4 identified by 1234;
--Ȯ��
select *from all_users;
--���Ѻο�
grant create session To scott2;
grant create session To scott3;
grant connect To scott4;
grant select on scott.employee to scott2;
grant select on scott.employee to scott4;
grant select on scott.employee to scott2 with grant option;
grant resource to scott4;
--��������
revoke create session from scott2;
revoke select on scott.employee to scott2 ;

--���Ǿ� ���̺� ��ȸ ���� �ο��ϱ�.
create public SYNONYM_myemp for scott.employee;
