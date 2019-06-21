14. PL/SQL 문제
01.IF 문을 사용하여 KING 사원의 부서번호(dno)를 얻어 와서/ 부서번호에 따른 부서명(dname)을 출력하시오.
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
            
            dbms_output.put_line('사원번호    사원이름       부서명');
            dbms_output.put_line('---------------------');
            dbms_output.put_line(v_dno ||'    '||
                                v_ename ||'     '||
                                v_dname );
end;
/
02.BASIC LOOP 문으로 1부터 10 사이의 자연수의 합을 구하여 출력하시오.

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

03.FOR LOOP 문으로 1부터 10 사이의 자연수의 합을 구하여 출력하시오.

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

04.WHILE LOOP 문으로 1부터 10 사이의 자연수의 합을 구하여 출력하시오.

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


05.사원 테이블에서 커미션이 NULL이 아닌 상태의
사원번호, 이름, 급여를 이름 기준 오름차순으로 정렬한 결과 를 출력하시오.

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
