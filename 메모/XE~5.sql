[���� 5] ��õ �����ȣ�� ����Ʈ�ϰ�, ZIPCODE�� �����Ͻÿ�.
���� + ��õ : ������ �� Row

select count(*) from zipcode;

insert into zipcode
select * from zipcode2;

[���� 6] ���� ���̸��� �� ã���ÿ�.

select distinct a.�õ�, a.�ñ���, a.��������
from zipcode a, zipcode b
where ��������(select a.�������� from zipcode where a.�ñ��� != b.�ñ���);

--0 ���̺� �����ϱ�.
--1. ���� �� �̸� ã�� = �ߺ��� ������ ã�� .

select a.�������� , count (*)
from (select �ñ���, ��������  from zipcode group by �ñ���, ��������)
group by �������� 
having count (*) >1;

--2.      ------------------------------------------------------------------------------------------- 

select �õ�, �ñ���, ������ 
from zipcode 
where ������ 
in (select �������� from 
                        (select �ñ���, ��������  from zipcode group by �ñ���, ��������) 
    group by �������� having count (*) >1;)

group by �õ�, �ñ���, ��������
order by ��������;
-----------------------------------------------------------------------------------------------------