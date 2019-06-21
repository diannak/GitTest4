 drop table emp ;         
 
 select rowid,rownum,eno, ename
 from employee;
 
 create table dept   (
                    dno number(2) constraint dept_dno_pk primary key,
                    dname varchar2(14),
                    loc varchar(13)
                    );
create table emp(
                    eno number(4) constraint emp_eno_pk primary key,
                    ename varchar2(10),
                    job varchar2(9),
                    dno number(2) constraint emp_dno_fk references dept
                    );
                    
 select table_name, constraint_name
 from user_constraints
 where table_name in('EMP','DEPT');
 
alter table dept
DROP primary key cascade;
 
alter table emp 
DROP constraint emp_dno_fk;
----------------------여기까진 잘됨-------------------------------------------------