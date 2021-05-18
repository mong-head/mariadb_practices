-- < inner join >
use employees;
-- ex 1 ) join the employees table and the titles table, and show employees' name and title.
	-- equijoin
select employees.first_name, titles.title
from employees, titles
where employees.emp_no = titles.emp_no ; 

	-- join on 
select employees.first_name, titles.title
from employees join titles
on employees.emp_no = titles.emp_no -- join condition
where to_date > current_date;

-- ex 2 ) join the employees table and the titles table, and show name and title of "female Engineer".
select employees.first_name, titles.title
from employees join titles
on employees.emp_no = titles.emp_no -- join condition
where employees.gender = 'F' and titles.title = 'Engineer';

-- --------------------------------
-- ANSI / ISO SQL1999 JOIN standard 
-- --------------------------------
-- 1) natural join : skip "on"
-- 		If I skip a "on" statement, then mysql finds the same column of the two tables and appends that "on" sentence.
-- 		usually not used.
select e.first_name, t.title
from employees e
natural join titles t;
-- on e.emp_no = t.emp_no; -- skip this sentence; 

-- ex 2 ) print the name , title, and salary of all employees; need 3 table
-- 	standard method; good
select count(*) -- first_name, title, salary
from employees e
join titles t on e.emp_no = t.emp_no
join salaries s on e.emp_no = s.emp_no; -- and t.from_date = s.from_date and t.to_date = s.to_date;

-- 	natural method; bad; t.from_date != s.from_date, but if I use natural join, mysql think that two columns are the same column.
select count(*)
from employees e
natural join titles t  
natural join salaries s; -- =  on e.emp_no = s.emp_no and t.from_date = s.from_date and t.to_date = s.to_date;

-- < outer join >
-- insert into dept values(1,'총무');
-- insert into dept values(2,'개발');
-- insert into dept values(3,'영업');
-- insert into dept values(4,'기획');
select * from dept;

-- insert into emp values(1,'둘리',1);
-- insert into emp values(2,'마이콜',2);
-- insert into emp values(3,'또치',3);
-- insert into emp values(4,'길동',null);
select * from emp;

select * 
from emp e
left join dept d on e.dept_no = d.no;

select * 
from emp e
right join dept d on e.dept_no = d.no;

select e.name as '이름', ifnull(d.name,'사장님') as '부서'
from emp e
left join dept d on e.dept_no = d.no;

select ifnull(e.name,'no named') as '이름', ifnull(d.name,'사장님') as '부서'
from emp e
right join dept d on e.dept_no = d.no;

-- examples
-- print emp_no, full name, department name of each employees' current department.
select e.emp_no, concat(e.first_name,' ',e.last_name) as 'full name', d.dept_name
from dept_emp de
join departments d on d.dept_no = de.dept_no
join employees e on e.emp_no = de.emp_no
where de.to_date > current_date;

-- print emp_no, full name, current salary of employees
select e.emp_no, e.first_name, salary
from employees e
join salaries s on e.emp_no = s.emp_no
where s.to_date > current_date;