set serveroutput on 
declare -- 변수 상수 선언
begin --실행부분 (내용)
dbms_output.put_line('Welcome to Orable');--문장끝마다 세미콜론 
end;
/--종결

--constant = final : 변수의 값 변경 제한 
declare 
v_eno employee.eno%type; -- 데이터 타입 참조
v_ename employee.ename%type;
-------------------------------------------------------------------
set serveroutput on --프롬프트에 출력

declare --변수및 상수 선언
             V_eno number(4)--4자리까지
             v_ename employee.ename%type;
begin 
            v_eno := 7788;
            v_ename := 'scott';
            
            dbms_output.put_line('사원번호 사원이름');
            dbms_output.put_line('-----------------');
            dbms_output.put_line(v_eno ||'     '|| v_ename);
end;
/
