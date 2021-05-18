-- < subquery >
use employees;
-- 1) from 절 
select now() as n, sysdate() s, 3+1 c;

select s.n, s.s, s.c
from (select now() as n, sysdate() s, 3+1 c) as s;

-- 2-1) where : 단일행
-- 단일행 연산자 : = > < <= >= !=

-- ex ) print emp_no, full name of each employee who works in the department which "Fai Bale" currently works
select e.emp_no, concat(first_name, ' ',last_name) as name
from dept_emp de
join employees e on e.emp_no = de.emp_no
join departments d on d.dept_no = de.dept_no
where d.dept_name = (select d.dept_name
					from dept_emp de
					join employees e on e.emp_no = de.emp_no
					join departments d on d.dept_no = de.dept_no
					where de.to_date >= current_date
					and e.first_name = "Fai" and e.last_name = "Bale")
;

-- ex ) show name, salary of each employee who's current salary is less than average employees' salary
select first_name, salary
from employees e
join salaries s on e.emp_no = s.emp_no
where salary < (select avg(salary)
				from salaries
                where to_date >= current_date)
and s.to_date >= current_date
order by salary desc
;

-- ex ) show current lowest average salary of the title
-- 		method 1) min(avg(salary))
select title, min(s.title_salary)
from (select title, avg(salary) as title_salary
		from titles t
		join salaries s on s.emp_no = t.emp_no
		where t.to_date >= current_date and s.to_date >= current_date
		group by title) as s
;
-- 		method 2) top-k : mysql에서만 제공하는 것
select title, avg(salary) as title_salary
from titles t
join salaries s on s.emp_no = t.emp_no
where t.to_date >= current_date and s.to_date >= current_date
group by title
order by title_salary
limit 0,1 -- 0,3
;
-- 		method 3) having
select title, avg(salary) as title_salary
from titles t
join salaries s on s.emp_no = t.emp_no
where t.to_date >= current_date and s.to_date >= current_date
group by title
having title_salary = (select min(title_salary) 
						from (select avg(salary) as title_salary
							from titles t
							join salaries s on s.emp_no = t.emp_no
							where t.to_date >= current_date and s.to_date >= current_date
							group by title)as a)
;

-- 2-2) where : 복수행
-- 복수행 연산자 : in, not in, any, all

-- any 사용법
-- 1. = any : equals with "in"
-- 2. > any : minimum
-- 3. > any : maximum
-- 4. <> any : equals with "not in"

-- ex ) show employee's name who currently got paid more than 50000 
select first_name, salary
from employees e
join salaries s on e.emp_no = s.emp_no
where to_date >= current_date
and salary in (select salary
				from salaries
				where to_date >= current_date and salary > 50000)
order by salary
;