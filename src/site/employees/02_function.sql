-- <문자열 함수>
-- 1. upper
select upper('busaN'), upper('buSan');
select upper(first_name) from employees;

-- 2. lower
select lower('BUsaN');

-- 3. substring
select substring('Happy Day',7,9); -- index? 1부터 시작
-- 		예제 ) 직원의 입사년도를 출력 (1989)
select first_name,hire_date from employees where substring(hire_date,1,4) = '1989';

-- 4. lpad(오른쪽정렬), rpad(왼쪽정렬) : padding
select lpad('1234',10, '-'); -- ------1234
select rpad('1234',10, '-'); -- 1234------
-- 		예제 ) 직원들의 월급을 오른쪽 정렬, 빈 공간은 * 로 채우기
select emp_no, lpad(salary,10,'*') from salaries where from_date like '2001-%' order by salary desc;

-- 5. trim, ltrim, rtrim : 옆의 space없앰
select concat('---',ltrim('   hello   '),'---'); -- 왼쪽 space 없앰
select concat('---',rtrim('   hello   '),'---');
select concat('---',trim('   hello   '),'---'); -- 양쪽 space 없앰
select concat('---',trim(both 'x' from 'xxxxhello     '),'---');


-- <숫자 함수>

-- 1. abs() : 절대값
select abs(-1);

-- 2. mod() : 나머지 값
select mod(10,3);

-- 3. floor() : 소수 걍 버린거
select floor(5.9);

-- 4. ceil() : 무조건 올린거a
select ceil(5.1);

-- 5. round(x) : x에 가장 근접한 정수 변화 //많이씀
-- 	  round(x,d) : x값 중 소수점 d자리게 가장 근접한 실수
select round(1.298), round(1.298,1),round(1.298,0);

-- 6. pow(x,y), power(x,y) : x의 y승
select pow(2,10), power(10,3);

-- 7. sign(x) : 양수면 1, 음수면 -1, 0 이면 0
select sign(2),sign(-2),sign(0);

-- 8. greatest(x,y,....), least(x,y,....)
select greatest(10,40,20,30), least(10,240,20,30);
select greatest('a','B','C');


-- <날짜 함수>

-- 1. CURDATE(), CURRENT_DATE 
select curdate(),current_date;

-- 2. CURTIME(), CURRENT_TIME
select curtime(),current_time;

-- 3. now() vs sysdate()
select now(), sysdate();
select now(), sleep(2), now(); -- sleep(2) : 2초 잔 후 실행 , 앞과 뒤의 now()는 같은 시간
select sysdate(), sleep(2),sysdate(); -- 뒤의 sysdate는 2초뒤에 실행됨(앞 뒤 서로 차이남)
-- insert values (now()),(now()),....하면 다~같은 시간 #now()를 많이 사용함
-- insert values (sysdate()),(sysdate()),....하면 다 다른 시간 (쿼리 때릴 때마다 시간들어감)

-- 4. date_format(date, format)
select date_format(now(),'%Y년 %m월 %d일 %h시 %i분 %s초') as 현재시각;
select date_format(now(),'%Y년 %c월 %d일 %h시 %i분 %s초') as 현재시각;

-- 5. period_diff
-- YYMM
-- YYYYMM
-- 		예제 ) 근무 개월 수를 출력하라.
select first_name,period_diff(date_format(current_date,'%Y%m'), date_format(hire_date,'%Y%m')) as '근무 개월 수'
from employees;
 
-- 6. date_add(=adddate),date_sub(=subdate)
-- 		날짜 date에 type(day,month, year)형식으로 expr값을 더하거나 뺀다.
-- 		예제 ) 각 사원들의 근무 년 수가 5년이 되는 날이 언제인지?

select first_name,
	   hire_date as '입사일',
	   date_add(hire_date , interval 5 year) as '입사일로부터 5년 후'
from employees;

-- 7. cast ; 문자열을 date로 casting ; cast는 안하는 게 좋음. 잘 하지 않음
-- type : VARCHAR,CHAR, text
-- signed(unsigned), int, medium int, big int, int(11) 
-- float, double
-- time, date, datetype
-- bob(big object), lob(large object)
select cast('2021-05-07' as date);
select cast('12345' as int)+10000;
select now() , cast(now() as date);
select cast(1-2 as unsigned); 
select cast(cast(1-2 as unsigned) as signed);

-- <집계함수; 그룹함수>
-- 		예제 ) 10060 사원의 평균 연봉?
-- group by로 묶어주지 않은 경우 select절에 집계함수를 썼을 때, 일반 칼럼을 적으면 안됨
select avg(salary) from salaries where emp_no='10060'; -- 임시테이블1 : where절로 거른거 -> 임시테이블2(=결과) : 집계

-- query 실행 순서
-- 	(1) from : 접근 테이블 확인
-- 	(2) where : 조건에 맞는 row 선택 -> 임시테이블
-- 	(3) 집계
-- 	(4) projection

select avg(salary) from salaries where to_date like '9999-%'; -- now

select salaries.emp_no, concat(first_name, ' ',last_name) as name,avg(salary)
from salaries
join employees
on salaries.emp_no = employees.emp_no
group by salaries.emp_no
order by salaries.emp_no;

-- 		예제 ) 11060 사원의 최저 임금 받은 시기, 최대 임금 받은 시기?
-- 문법적으로 오류임 but 의미적으로는 ok
select emp_no, avg(salary) as 평균임금, min(salary) as 최저임금, max(salary) as 최대임금
from salaries
where emp_no = 10060;

-- having : 집계 결과 임시 테이블에서 row를 선택해야 하는 경우 
select emp_no, avg(salary)
from salaries
group by emp_no
having avg(salary) > 60000
order by avg(salary);