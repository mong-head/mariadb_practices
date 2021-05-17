-- select practice
select * from departments;
select dept_no,dept_name from departments;

-- concat, alias(as)
select concat(first_name,' ',last_name) as name ,gender,hire_date from employees where gender = "M" and hire_date > '2000-01-01';
select concat(first_name,' ',last_name) name ,gender,hire_date from employees where gender = "M" and hire_date > '2000-01-01'; -- as 생략 가능

-- distinct : 중복 제거
-- 1. 모든 직급 이름 중복없이 출력
select distinct title from titles;

-- where #1
-- 1991년 이전 입사 직원의 이름,성별, 입사일
select concat(first_name,' ',last_name) as name ,gender, hire_date 
  from employees
 where hire_date < '1991-01-01';
 
-- where #2 논리 연산자
-- 1989이전 입사한 여직원 이름, 입사일 출력
select concat(first_name,' ',last_name) as name, hire_date
	from employees
	where hire_date < '1989-01-01'
	and gender = 'F';
    
-- where #3 in
-- dept_emp 테이블서 부서번호가 d005,d009에 속한 사원 사번 부서
select *
from dept_emp
where dept_no = 'd005' or dept_no = 'd009';

select *
from dept_emp
where dept_no in ('d005','d009');

-- like : 게시판 검색시 많이 사용
-- 1989 입사한 직원의 이름, 입사일 출력
select first_name, hire_date
from employees
where hire_date like '1989-%';

-- between
-- 1989 입사한 직원의 이름, 입사일 출력
select first_name, hire_date
from employees
where hire_date between '1988-12-31' and '1990-01-01';

-- order by #1
-- 남직원 이름, 성별, 입사일을 입사일 순으로
select concat(first_name, ' ',last_name)as name, gender, hire_date
from employees
where gender = 'M'
order by hire_date;

-- (salaries) 2001년 월급 가장 높은 순으로 월급순으로 출력
select emp_no, salary
from salaries
where from_date like '2001-%' and to_date like '2001-%'
order by salary desc;
-- 사원,월급 순으로 출력
select *
from salaries
order by emp_no,salary desc;

    

