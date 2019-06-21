16장 
union  =합집합과 같음.
            정보가 두번씩 나옴. 각각 테이블의 정보가 모두 출력.
            합쳐서 구할때 ex1학년 2학년 3학년 정보 모두 계산해서 출력해야할때
            개수와 타입만 같으면 동작한다.
select dno, dname from department 
union
select eno,ename from employee;
        
intersect

