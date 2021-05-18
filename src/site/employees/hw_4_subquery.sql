-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?

select count(*) as '평균 연봉보다 많은 월급 받는 직원 수'
from employees e
join salaries s on e.emp_no = s.emp_no
where salary > (select avg(salary) 
				from salaries
                where to_date >= current_date)
and s.to_date >= current_date
;

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 (및?)연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 

-- sol1 : where subquery any
select e.emp_no, concat(first_name,' ',last_name) name, dept_name, salary
from dept_emp de
join departments d on de.dept_no = d.dept_no
join employees e on de.emp_no = e.emp_no
join salaries s on de.emp_no = s.emp_no
where (dept_no,salary) in (select d.dept_no,max(salary)
					from dept_emp de
					join departments d on de.dept_no = d.dept_no
					join employees e on de.emp_no = e.emp_no
					join salaries s on de.emp_no = s.emp_no
                    where s.to_date > current_date and de.to_date > current_date
                    group by d.dept_no)
and s.to_date > current_date and de.to_date > current_date
order by salary desc
;
                    
-- sol2 : from subquery
select e.emp_no, concat(first_name,' ',last_name) name, dept_name, salary
from dept_emp de, departments d , employees e ,salaries s ,(select dept_no, max(salary) 'max_salary'
															from dept_emp de
															join salaries s on de.emp_no = s.emp_no
															where s.to_date > current_date and de.to_date > current_date
															group by dept_no) temp
where salary = max_salary
and d.dept_no = temp.dept_no
and s.to_date > current_date and de.to_date > current_date
and de.dept_no = d.dept_no
and de.emp_no = e.emp_no
and de.emp_no = s.emp_no
order by max_salary desc
;

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 

select e.emp_no, dept_no, first_name, salary
from dept_emp de
join employees e on de.emp_no = e.emp_no
join salaries s on de.emp_no = s.emp_no
where salary > any(select avg(salary)
								from dept_emp de
                                join salaries s on de.emp_no = s.emp_no
                                group by dept_no)
and de.to_date > current_date and s.to_date > current_date
;

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.

-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 

-- 문제7.
-- 평균 연봉이 가장 높은 직책?

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.

