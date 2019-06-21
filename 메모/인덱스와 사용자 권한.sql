12장 인덱스 만들기
--USER IND COLUMNS 으로 데이터 사전으로 인덱스 생성 확인하기.
select index_name, table_name, column_name
from user_ind_columns
where table_name in('EMPLOYEE');

select * from
user_ind_columns;
--생성하기 
CREATE INDEX IDX_EMPLOYEE_ENAME ON EMPLOYEE (ENAME);
--결합 인덱스
CREATE INDEX IDX_EMPLOYEE_ENAME ON EMPLOYEE (ENAME||eno);

13장 사용자 권한
--생성
create user scott2 identified by 1234;
create user scott3 identified by 1234;
create user scott4 identified by 1234;
--확인
select *from all_users;
--권한부여
grant create session To scott2;
grant create session To scott3;
grant connect To scott4;
grant select on scott.employee to scott2;
grant select on scott.employee to scott4;
grant select on scott.employee to scott2 with grant option;
grant resource to scott4;
--권한제거
revoke create session from scott2;
revoke select on scott.employee to scott2 ;

--동의어 테이블 조회 권한 부여하기.
create public SYNONYM_myemp for scott.employee;
