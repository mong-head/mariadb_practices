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

#### 2) bookshop

|DB|결과|
|---|-----|
|<img src="https://user-images.githubusercontent.com/52481037/118934740-a54ce100-b985-11eb-9630-156d82e06eae.jpg" width="250" height="250">|<img src="https://user-images.githubusercontent.com/52481037/118942694-ee089800-b98d-11eb-97df-515f19772772.jpg" width="500" height="500">|

* Application : bookshop.main/BookShop.main
* DAO
	* AuthorDao : insert, findAll
	* BookDao : insert, findAll(select all), update(대여정보)
* VO (DB대로함)
	* AuthorVo
	* BookVo
	
## 3. bookmall

* modeling
![K-037](https://user-images.githubusercontent.com/52481037/119115015-a8b59a80-ba61-11eb-9d2b-a6dc262f3c80.jpg)


* 결과
```text
====================Category====================
category 입력 성공
category 입력 성공
category 입력 성공
CategoryVo [no=1, name=소설]
CategoryVo [no=2, name=경제]
CategoryVo [no=3, name=컴퓨터/IT]
============================================
====================Book====================
book 입력 성공
book 입력 성공
book 입력 성공
BookVo [no=1, title=소설1, price=10000, categoryVo=CategoryVo [no=1, name=소설]]
BookVo [no=2, title=경제1, price=20000, categoryVo=CategoryVo [no=2, name=경제]]
BookVo [no=3, title=경제2, price=50000, categoryVo=CategoryVo [no=2, name=경제]]
============================================
====================Customer====================
customer 입력 성공
customer 입력 성공
CustomerVo [no=1, name=고객1, email=one@gmail.com, phone=010-2222-2222, password=1111*]
CustomerVo [no=2, name=고객2, email=two@gmail.com, phone=010-1122-3344, password=3333&]
============================================
====================Order====================
order 입력 성공
OrderVo [no=1, order_info=20210909-123A, payment=15000, address=부산시 남구 오륙도로85, customerVo=CustomerVo [no=1, name=고객1, email=one@gmail.com, phone=010-2222-2222, password=1111*]]
============================================
====================Cart====================
cart 입력 성공
cart 입력 성공
cart 입력 성공
CartVo [customerVo=CustomerVo [no=2, name=고객2, email=two@gmail.com, phone=010-1122-3344, password=3333&], bookVo=BookVo [no=3, title=경제2, price=50000, categoryVo=CategoryVo [no=2, name=경제]], num=5]
CartVo [customerVo=CustomerVo [no=1, name=고객1, email=one@gmail.com, phone=010-2222-2222, password=1111*], bookVo=BookVo [no=1, title=소설1, price=10000, categoryVo=CategoryVo [no=1, name=소설]], num=10]
CartVo [customerVo=CustomerVo [no=2, name=고객2, email=two@gmail.com, phone=010-1122-3344, password=3333&], bookVo=BookVo [no=2, title=경제1, price=20000, categoryVo=CategoryVo [no=2, name=경제]], num=2]
============================================
====================OrderBook====================
OrderBook 입력 성공
OrderBook 입력 성공
OrderBookVo [bookVo=BookVo [no=1, title=소설1, price=10000, categoryVo=CategoryVo [no=1, name=null]], orderVo=OrderVo [no=1, order_info=20210909-123A, payment=15000, address=부산시 남구 오륙도로85, customerVo=CustomerVo [no=1, name=null, email=null, phone=null, password=null]], price=8000, num=2]
OrderBookVo [bookVo=BookVo [no=3, title=경제2, price=50000, categoryVo=CategoryVo [no=2, name=null]], orderVo=OrderVo [no=1, order_info=20210909-123A, payment=15000, address=부산시 남구 오륙도로85, customerVo=CustomerVo [no=1, name=null, email=null, phone=null, password=null]], price=5000, num=5]
============================================

```
