# Mariadb practices

* 환경 
	* server : linux
	* client : mysql workbench

## 1. sql practices (src/site/)

#### 1) webdb

* sql-practices01 : create,delete,drop,update

* sql-practices02 : 기본 select 연습

#### 2) employees 

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
	* practice : hw_4_subquery
	
## 2. jdbc-practices

### 2-1. JDBC practices

[application] <-- CRUD --> [DB]

#### 1) test

* ConnectionTest : linux server 내의 webdb와 connection test
* SelectTest : linux server 내의 employees와 connection 후 sql 실행
* 완성된 sql문
	* InsertTest01 : connection 후 insert문 실행
	* DeleteTest01
	* UpdateTest01
* 완성되지 않은 sql문 : binding
	* InsertTest02
	* DeleteTest02
	* UpdateTest02
	
#### 2) driver

* 직접 driver 구현 - connection만

### 2-2. DAO pattern

[application] <-- USE : VO --> [DAO] <-- CRUD --> [DB]

#### 1) hr 

* HRMain  : findByName 사용 - 사원 이름 입력 받아서 사원 정보 출력
* HRMain2 : findBySalary 사용 - 최소 봉급 및 최대 봉급 입력받아 사원 정보 출력
* EmployeeDao : connection ~ query
	* findByName
	* findBySalary
* EmployeeVo : employee 정보 객체
		
