14. PL/SQL ����
01.IF ���� ����Ͽ� KING ����� �μ���ȣ(dno)�� ��� �ͼ�/ �μ���ȣ�� ���� �μ���(dname)�� ����Ͻÿ�.
select *from employee;
set serveroutput on
declare 
            v_employee employee%rowtype; 
            v_ename employee.ename%type;
            v_dno employee.dno%type;
            v_dname varchar2(20) := null;
begin      
            select  ename , dno
            into v_ename, v_dno
            from employee,department
            where ename = 'KING';

if          (v_dno =10) then
            v_dname := 'ACCOUNTIHG';
end if;
            
            dbms_output.put_line('�����ȣ    ����̸�       �μ���');
            dbms_output.put_line('---------------------');
            dbms_output.put_line(v_dno ||'    '||
                                v_ename ||'     '||
                                v_dname );
end;
/
02.BASIC LOOP ������ 1���� 10 ������ �ڿ����� ���� ���Ͽ� ����Ͻÿ�.

set serveroutput on

declare 
        dan number := 1;
        i number := 1;

begin
    loop
        dbms_output.put_line (dan || ' + ' || i || ' = ' || (dan + i));
        i := i+1;
        if i > 9 then
                    exit;
                end if;
         end loop;
    end;
    /

03.FOR LOOP ������ 1���� 10 ������ �ڿ����� ���� ���Ͽ� ����Ͻÿ�.

set serveroutput on

declare 
        dan number := 1;
        i number := 1;

begin
    for i in reverse 1..9 loop
        dbms_output.put_line (dan || ' + ' || i || ' = ' || (dan + i));

         end loop;
    end;
    /

04.WHILE LOOP ������ 1���� 10 ������ �ڿ����� ���� ���Ͽ� ����Ͻÿ�.

set serveroutput on 
declare 
        dan number := 1;
        i number :=1;
begin 
while   i <= 9 loop
        dbms_output.put_line (dan || ' + ' || i || ' = ' || (dan + i));
         i:= i+1;
     end loop;
 end;
 /


05.��� ���̺��� Ŀ�̼��� NULL�� �ƴ� ������
�����ȣ, �̸�, �޿��� �̸� ���� ������������ ������ ��� �� ����Ͻÿ�.

set serveroutput on
declare 
            v_employee employee%rowtype; 
           temp number(4):=1;
           annsal number(7,2);
begin      
            select *into v_employee
            from employee
            where ename = 'SCOTT';

if          (v_employee.commission is null) then
             v_employee.commission :=0;

end if;

annsal := v_employee.salary*12+v_employee.commission;

            dbms_output.put_line('�����ȣ    ����̸�       ����');
            dbms_output.put_line('---------------------');
            dbms_output.put_line(v_employee.eno ||'    '||
                                 v_employee.ename ||'     '||
                                 annsal );
end;
/
