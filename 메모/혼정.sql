set serveroutput on 
declare -- ���� ��� ����
begin --����κ� (����)
dbms_output.put_line('Welcome to Orable');--���峡���� �����ݷ� 
end;
/--����

--constant = final : ������ �� ���� ���� 
declare 
v_eno employee.eno%type; -- ������ Ÿ�� ����
v_ename employee.ename%type;
-------------------------------------------------------------------
set serveroutput on --������Ʈ�� ���

declare --������ ��� ����
             V_eno number(4)--4�ڸ�����
             v_ename employee.ename%type;
begin 
            v_eno := 7788;
            v_ename := 'scott';
            
            dbms_output.put_line('�����ȣ ����̸�');
            dbms_output.put_line('-----------------');
            dbms_output.put_line(v_eno ||'     '|| v_ename);
end;
/
