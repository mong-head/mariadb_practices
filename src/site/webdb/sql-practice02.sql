-- 조회연습 1 : where
-- 1990년 이후 태어난 아이들
select * from pet where birth > '1990-12-31';

-- Gwen과 함께 사는 아이들
select * from pet where owner = 'Gwen';

-- null 다루기(is null 사용)
-- 1. 사망하지 않은 아이들
select * from pet where death is null;
-- 2. 사망한 아이들
select * from pet where death is not null;

-- like 검색 (pattern matching)
-- 이름이 b로 시작하는 아이들?
select * from pet where name like "b%";
-- 이름이 b 포함하는 아이 중 이름이 6자리인 아이들 (_는 한 글자 의미)
select * from pet where name like "b_____%" ; -- _는 다섯개 있음

-- 집계(통계)함수
select count(*) as '총 아이들 수' from pet; -- 9: 특별히 지정할 칼럼없으면 * 를 하기
select count(death) from pet; -- 1: null을 빼고 count함 
select count(*) from pet where death is not null; -- 1: 바로 위 문장과 동일


-- 조회 연습 2 : order by
-- 나이가 어린 순 정렬, 생일 같으면 이름 순으로 정렬
select * from pet order by birth desc, name asc;

