-- 테이블간 조인(JOIN) SQL 문제입니다.
use employees;
-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.

select employees.emp_no,first_name, salary
from employees
join salaries on employees.emp_no = salaries.emp_no
order by salary desc
;

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.

select e.emp_no, concat(e.first_name,' ',e.last_name) as name , title
from employees e
join titles t on e.emp_no = t.emp_no
where t.to_date > current_date
order by e.first_name
;

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..

select e.emp_no, concat(e.first_name,' ',e.last_name) as name , d.dept_name
from employees e
join dept_emp de on e.emp_no = de.emp_no
join departments d on de.dept_no = d.dept_no
where de.to_date > current_date
order by e.first_name
;

-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.

select e.emp_no, concat(first_name,' ',last_name) as name, salary, title, d.dept_name
from dept_emp de
join employees e on e.emp_no = de.emp_no
join departments d on d.dept_no = de.dept_no
join salaries s on de.emp_no = s.emp_no
join titles t on de.emp_no = t.emp_no
where s.to_date > current_date and t.to_date > current_date
;

-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.) 이름은 first_name과 last_name을 합쳐 출력 합니다.

select e.emp_no, first_name
from employees e
join titles t on e.emp_no = t.emp_no
where title = 'Technique Leader' and t.to_date < current_date
;

-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.

select concat(first_name,' ',last_name) as name, dept_name, title
from dept_emp de
join employees e on e.emp_no = de.emp_no
join departments d on d.dept_no = de.dept_no
join titles t on t.emp_no = e.emp_no
where last_name like 'S%'
;

-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.

select e.emp_no, e.first_name, salary, title, t.to_date,s.to_date
from employees e
join titles t on t.emp_no = e.emp_no
join salaries s on e.emp_no = s.emp_no
where title = 'Engineer' 
and salary > 40000
and s.to_date >= current_date
and t.to_date >= current_date
order by salary desc
;

-- 문제8.
-- 현재 급여가 50000이 넘는 직책을 직책, 급여로 급여가 큰 순서대로 출력하시오

select title, salary
from employees e
join titles t on e.emp_no = t.emp_no
join salaries s on e.emp_no = s.emp_no
where salary > 50000
order by salary
;

-- 문제9.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.

select dept_name, avg(salary)
from dept_emp de
join departments d on d.dept_no = de.dept_no
join employees e on e.emp_no = de.emp_no
join salaries s on s.emp_no = de.emp_no
where s.to_date >= current_date
group by d.dept_no
order by salary desc
;

-- 문제10. 
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.

select title, avg(salary)
from employees e
join titles t on e.emp_no = t.emp_no
join salaries s on s.emp_no = e.emp_no
where t.to_date > current_date and s.to_date > current_date
group by title
order by avg(salary) desc
;