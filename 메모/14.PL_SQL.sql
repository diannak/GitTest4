14.PL_SQL

set serveroutput on
begin
dbms_output.put_line('WElcome to Oracle');
end;
/
--constant = finalllllll~ -> 상수가 됨.

--변수선언 및 값 할당

set serveroutput on
declare 
v_eno number(4); 
v_ename employee.ename%type;

begin
v_eno:=7788;
v_ename:='scott';

dbms_output.put_line('사원번호    사원이름');
dbms_output.put_line('---------------------');
dbms_output.put_line(v_eno ||'    '||v_ename);
end;
/

--ex사원번호와 사원이름 출력하기
set serveroutput on
declare 
            v_eno number(4); 
            v_ename employee.ename%type;

begin       
            dbms_output.put_line('사원번호    사원이름');
            dbms_output.put_line('---------------------');
           
            SELECT ENO, ENAME INTO V_ENO, V_ENAME
            FROM EMPLOYEE
            WHERE ENAME = 'SCOTT';
            
            dbms_output.put_line(v_eno ||'    '||v_ename);
end;
/

--if end if연봉계산하기

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

            dbms_output.put_line('사원번호    사원이름       연봉');
            dbms_output.put_line('---------------------');
            dbms_output.put_line(v_employee.eno ||'    '||
                                 v_employee.ename ||'     '||
                                 annsal );
end;
/

//if else if
set serveroutput on
declare 
            v_eno employee.eno%type; 
            v_ename employee.ename%type;
            v_dno employee.dno%type;
            v_dname varchar2(20) := null;
begin      
            select eno, ename , dno
            into v_eno, v_dname, v_dno
            from employee,department
            where ename = 'SCOTT';

if          (v_dno =10) then
            v_dname := 'ACCOUNTIHG';

elsif       (v_dno =20) then
            v_dname := 'RESEARCH';

elsif       (v_dno =30) then
            v_dname := 'SALES';

elsif       (v_dno =40) then
            v_dname := 'OPERATIONS';
end if;
            
            dbms_output.put_line('사원번호    사원이름       부서명');
            dbms_output.put_line('---------------------');
            dbms_output.put_line(v_eno ||'    '||
                                v_ename ||'     '||
                                v_dname );
end;
/
--basic loop 구구단 2단 출력
set serveroutput on

declare 
        dan number := 2;
        i number := 1;

begin
    loop
        dbms_output.put_line (dan || ' * ' || i || ' = ' || (dan * i));
        i := i+1;
        if i > 9 then
                    exit;
                end if;
         end loop;
    end;
    /
--for loop
set serveroutput on

declare 
        dan number := 2;
        i number := 1;

begin
    for i in reverse 1..9 loop
        dbms_output.put_line (dan || ' * ' || i || ' = ' || (dan * i));

         end loop;
    end;
    /
--while loop
set serveroutput on 
declare 
        dan number := 2;
        i number :=1;
begin 
while   i <= 9 loop
        dbms_output.put_line (dan || ' * ' || i || ' = ' || (dan * i));
         i:= i+1;
     end loop;
 end;
 /
 
 --쌤 예제
set serveroutput on
declare 
        dan number := 2;
        i number := 1;
begin
    for i in 1..9 loop
        dbms_output.put('홍길동' || ' ' );
        dbms_output.put('전우치');
        dbms_output.new_line (); --옆으로 쓰고 싶을때
     while i <= 9 loop
        dbms_output.put_line (dan || ' * ' || i || ' = ' || (dan * i));
         i:= i+1;
         end loop;
    end;
    /
--커서로 테이블의 모든 내용 조회하기    
set serveroutput on 
declare     
    v_dept department%rowtype;
cursor  c1 
        is 
        select *from department;

begin               dbms_output.put_line('사원번호     사원이름    부서명');
                    dbms_output.put_line('---------------------');
                    
                    open c1;
                    loop
                        fetch c1 into v_dept.dno, v_dept.dname, v_dept.loc;
                        exit when c1 %notfound;
                        dbms_output.put_line(   v_dept.dno||'   '|| 
                                                v_dept.dname||'     '||
                                                v_dept.loc);
                     end loop;
        close c1;
end;
/
--커서와 포 루프
set serveroutput on 
declare     
    v_dept department%rowtype;
cursor  c1 
        is 
        select *from department;

begin               dbms_output.put_line('사원번호     사원이름    부서명');
                    dbms_output.put_line('---------------------');
                    
                    for v_dept in c1 loop
                        exit when c1 %notfound;
                        dbms_output.put_line(   v_dept.dno||'   '|| 
                                                v_dept.dname||'     '||
                                                v_dept.loc);
                     end loop;
end;
/
