DESC ZIPCODE;
SELECT COUNT(*)FROM ZIPCODE;
SELECT *FROM ZIPCODE
WHERE ROWNUM <10;

1. ������� ��� ���� �̸�
select �ñ�����ǹ���
from zipcode
where �ñ�����ǹ��� like '%��������%'
order by �ñ�����ǹ���;

2. ����� ��� ����
select �ñ�����ǹ���
from zipcode
where �ñ�����ǹ��� like '%����%'
order by �ñ�����ǹ���;

3.
select �ñ�����ǹ���
from zipcode
where �ñ�����ǹ��� like '%����������%'
order by �ñ�����ǹ���;

4.
select distinct �ñ���, ��������, count(*) as"���б�"
from zipcode
where �ñ�����ǹ��� like '%���б�%'
group by �ñ���, ��������
order by �������� desc;


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

select employees.ename||'�� ���ӻ���� '|| manager.ename
from employee employees left outer join employee manager 
on employees.manager = manager.eno;
