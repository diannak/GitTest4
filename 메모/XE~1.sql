DESC ZIPCODE;
SELECT COUNT(*)FROM ZIPCODE;
SELECT *FROM ZIPCODE
WHERE ROWNUM <10;

1. 서울시의 모든 구의 이름
select 시군구용건물명
from zipcode
where 시군구용건물명 like '%국민은행%'
order by 시군구용건물명;

2. 서울시 모든 성당
select 시군구용건물명
from zipcode
where 시군구용건물명 like '%성당%'
order by 시군구용건물명;

3.
select 시군구용건물명
from zipcode
where 시군구용건물명 like '%공영주차장%'
order by 시군구용건물명;

4.
select distinct 시군구, 법정동명, count(*) as"중학교"
from zipcode
where 시군구용건물명 like '%중학교%'
group by 시군구, 법정동명
order by 법정동명 desc;


select A.eno, A.ename, A.manager, B.eno,B.ename
from employee A, TEST B
where A.manager = B.eno;

select A.ename
, A.manager, B.ename , B.eno
from employee A, employee B
where A.manager = B.eno;

select A.ename, A.manager,B.eno,B.ename
from employee A left outer join employee B 
on A.manager = B.eno;

select employees.ename||'의 직속상관은 '|| manager.ename
from employee employees left outer join employee manager 
on employees.manager = manager.eno;
