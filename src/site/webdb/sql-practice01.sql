show tables;

-- create table
create table pet(
		name varchar(20),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- schema 확인
desc pet;

-- 조회
select * from pet;

-- delete table
drop table pet;

-- 데이터 넣기 
insert
	into pet
    values ('나나','배유진','dog','f','2017-09-09',null);

select * from pet;

-- 데이더 삭제
delete 
	from pet
	where name = '나나';

select * from pet;

update pet set death = null where death = '0000-00-00';