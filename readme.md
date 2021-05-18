# Mariadb practices

* 환경 
	* server : linux
	* client : mysql workbench

### webdb

* sql-practices01 : create,delete,drop,update

* sql-practices02 : 기본 select 연습

### employees 

* 01_basic_select : 기본 select 연습

	* concat
	* as
	* in
	* like : search시 많이 사용
	* order by : 시간이 많이 걸림
	* practice : hw_1_basic
	
* 02_function

	* 문자열 함수
	* 숫자 함수
	* 날짜 함수
	* 집계 함수
	* practice : hw_2_aggregate

* 03_join

	* inner join
		* equijoin
		* join on (standard)
		* natural join, join ~using (not used)
	* outer join
	* practice : hw_3_join
	
* 04_subquery
	
	* from절 subquery
	* where절 subquery
		* 단일행 - 단일행 연산자 : = > < <= >= !=
		* 복수행 - 복수행 연산자 : in, not in, any, all
		
