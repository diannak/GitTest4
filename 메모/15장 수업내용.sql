15장 프로시저-- 일련의 작업을 묶어 저장
set serveroutput on
create procedure sp_salary
is 
v_salary employee.ename%type;
begin
select salary into v_salary
from employee
where ename = 'SCOTT';
dbms_output.put_line('SCOTT의 급여는 ' || v_salary);
end;
/
execute  sp_salary;
-------------------------------------------------------
프로시저 생성 확인, 제거

select name, text from user_source
where name like('%SP_SALART%');

drop procedure sp_salary;

-- in 매개변수 사용하기 
set serveroutput on
create procedure sp_salary_ename 
                 (v_ename in employee.ename%type) --괄호 매개변수
                 is v_salary employee.ename%type;--지역변수
begin 
        select salary into v_salary
        from employee
        where ename =v_ename;
        
        dbms_output.put_line(v_ename||'의 급여는 '||v_salary);
end;
/
execute sp_salary_enmae('SCOTT');

out 매개변수 사용하기--내변수 줄테니까 값넣어줘 리턴이없을때 쓰던거. 포인터 개념
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

variable v_salary varchar2(14);--이름이 같아도 서로 다른 객체
execute sp_salary_ename2('SCOTT',:V_salary);
print v_salary;
-------------------------------------------------------
함수(function)-- 매개변수 없이 결과값을 돌려주기(return) 위한 용도

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
트리거
테스트 용 테이블 생성
drop table dept_copy;
select *from dept_original;
create table dept_original
as select *from department where 0=1;

create table dept_copy
as 
select *from department where 0=1;

insert 트리거 생성하기.

set serveroutput on
create or replace trigger trigger_sample1
            after insert 
            on dept_original
            for each row
begin 
            if inserting then
            DBMS_OUTPUT.PUT_LINE('Insert Trigger 발생');
            insert into dept_copy
            values (:new.dno,:new.dname,:new.loc);
end if;
end;
/

테이블에 테데이터 추가하기
insert into dept_original
values(10, 'ACCOUTNING','NEW YORK');
---------------------------------------------------------------------
delete 트리거 생성하기 --적어놔야 다른 사람들 알고 당황안함.

set serveroutput on
create or replace trigger trigger_sample2
            after delete
            on dept_original
            for each row
begin 
            DBMS_OUTPUT.PUT_LINE('Delete Trigger 발생');
            delete from dept_copy
            where dept_copy.dno = :old.dno;
end;
/
delete from dept_original where dno =10;
select *from dept_original;
