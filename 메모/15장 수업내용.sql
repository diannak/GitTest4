15�� ���ν���-- �Ϸ��� �۾��� ���� ����
set serveroutput on
create procedure sp_salary
is 
v_salary employee.ename%type;
begin
select salary into v_salary
from employee
where ename = 'SCOTT';
dbms_output.put_line('SCOTT�� �޿��� ' || v_salary);
end;
/
execute  sp_salary;
-------------------------------------------------------
���ν��� ���� Ȯ��, ����

select name, text from user_source
where name like('%SP_SALART%');

drop procedure sp_salary;

-- in �Ű����� ����ϱ� 
set serveroutput on
create procedure sp_salary_ename 
                 (v_ename in employee.ename%type) --��ȣ �Ű�����
                 is v_salary employee.ename%type;--��������
begin 
        select salary into v_salary
        from employee
        where ename =v_ename;
        
        dbms_output.put_line(v_ename||'�� �޿��� '||v_salary);
end;
/
execute sp_salary_enmae('SCOTT');

out �Ű����� ����ϱ�--������ ���״ϱ� ���־��� �����̾����� ������. ������ ����
-------------------------------------------------------
set severoutput on
create procedure sp_salary_ename2
(v_ename in employee.ename%type, v_salary out employee.salary%type)
is
begin
        select salary into v_salary
        from employee
        where ename = v_ename;
end;
/

variable v_salary varchar2(14);--�̸��� ���Ƶ� ���� �ٸ� ��ü
execute sp_salary_ename2('SCOTT',:V_salary);
print v_salary;
-------------------------------------------------------
�Լ�(function)-- �Ű����� ���� ������� �����ֱ�(return) ���� �뵵

set serveroutput on
create or replace function fn_salary_ename
                            
        (v_ename in employee.ename%type)
         RETURN number is v_salary number(7,2);
begin 
        select salary into v_salary
        from employee
        where enme =v_ename;
        RETURN v_salary;
end;
/

variable v_salary number;
execute :v_salary :=fn_salary_ename('SCOTT')
print v_salary;

select ename, fn_salary_ename('SCOTT')
from employee
where ename='SCOTT';
-------------------------------------------------------
Ʈ����
�׽�Ʈ �� ���̺� ����
drop table dept_copy;
select *from dept_original;
create table dept_original
as select *from department where 0=1;

create table dept_copy
as 
select *from department where 0=1;

insert Ʈ���� �����ϱ�.

set serveroutput on
create or replace trigger trigger_sample1
            after insert 
            on dept_original
            for each row
begin 
            if inserting then
            DBMS_OUTPUT.PUT_LINE('Insert Trigger �߻�');
            insert into dept_copy
            values (:new.dno,:new.dname,:new.loc);
end if;
end;
/

���̺� �׵����� �߰��ϱ�
insert into dept_original
values(10, 'ACCOUTNING','NEW YORK');
---------------------------------------------------------------------
delete Ʈ���� �����ϱ� --������� �ٸ� ����� �˰� ��Ȳ����.

set serveroutput on
create or replace trigger trigger_sample2
            after delete
            on dept_original
            for each row
begin 
            DBMS_OUTPUT.PUT_LINE('Delete Trigger �߻�');
            delete from dept_copy
            where dept_copy.dno = :old.dno;
end;
/
delete from dept_original where dno =10;
select *from dept_original;
