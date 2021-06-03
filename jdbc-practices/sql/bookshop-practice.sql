use webdb;
-- schema 확인 --
desc author;
desc book;

-- Author: insert 
-- insert into author values(null,'원수연');

-- Author : select
select no,name from author;

select b.no,title,status,a.no, name
from book b
join author a on b.author_no = a.no;

-- Book : insert
-- insert into book values(null,'풀하우스','대여가능', 1);

-- Book : update
-- update book set status = '대여가능' where no = 1;

