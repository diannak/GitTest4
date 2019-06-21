[문제 5] 인천 우편번호를 임포트하고, ZIPCODE에 병합하시오.
서울 + 인천 : 데이터 총 Row

select count(*) from zipcode;

insert into zipcode
select * from zipcode2;

[문제 6] 같은 동이름을 다 찾으시오.

select distinct a.시도, a.시군구, a.법정동명
from zipcode a, zipcode b
where 법정동명(select a.법정동명 from zipcode where a.시군구 != b.시군구);

--0 테이블 병합하기.
--1. 같은 동 이름 찾기 = 중복된 데이터 찾기 .

select a.법정동명 , count (*)
from (select 시군구, 법정동명  from zipcode group by 시군구, 법정동명)
group by 법정동명 
having count (*) >1;

--2.      ------------------------------------------------------------------------------------------- 

select 시도, 시군구, 법정동 
from zipcode 
where 법정동 
in (select 법정동명 from 
                        (select 시군구, 법정동명  from zipcode group by 시군구, 법정동명) 
    group by 법정동명 having count (*) >1;)

group by 시도, 시군구, 법정동명
order by 법정동명;
-----------------------------------------------------------------------------------------------------