
-- EmployeeDao.findByName
select emp_no, first_name,last_name,date_format(hire_date,'%Y-%m-%d') 
from employees
where first_name like '%ko%' 
and last_name like '%ko%';
 
 -- EmployeeDao.findBySalary
select e.emp_no, e.first_name, e.last_name, s.salary
from employees e, salaries s
where s.to_date > current_date()
and e.emp_no = s.emp_no
and s.salary between 30000 and 50000
order by s.salary asc
;