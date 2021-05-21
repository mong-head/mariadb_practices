-- delete 거꾸로 진행
delete from category where name = '소설' or name = '경제' or name = '컴퓨터/IT';
delete from book where no = 1 or no = 2 or no = 3;
delete from customer where no = 1 or no = 2;
delete from order_ where no = 1;
delete from cart where customer_no = 1 or customer_no = 2;
delete from order_book where book_no = 1 or book_no = 2 or book_no = 3;

-- category
select no,name from category;
insert into category values(null, '소설');
delete from category where name = '소설' or name = '경제' or name = '컴퓨터/IT';

-- book
select b.no,title,price,category_no, c.name
from book b
join category c on b.category_no = c.no
;
select * from book;
insert into book values(1,'소설1',10000,1);

-- customer
select * from customer;

select no,name,email,phone,password from customer;
insert into customer values(1, '고객1', 'one@gmail.com','010-2222-2222','1111*');

-- order
select * from order_;
select no,order_info,payment,address,customer_no from order_;
select o.no,order_info,payment,address,customer_no,c.name, c.email, c.phone, c.password
from order_ o
join customer c on c.no = o.customer_no
;
insert into order_ values(1,'20210909-123A',15000,'부산시 남구 오륙도로85', 1);

-- cart
select * from cart;
select customer_no, book_no, num from cart;

select ca.num, cu.no, cu.name, cu.email, cu.phone, cu.password, b.no, b.title, b.price, cg.no, cg.name
from cart ca
join customer cu on cu.no = ca.customer_no
join book b on b.no = book_no
join category cg on cg.no = b.category_no
;
insert into cart values(1,2,5);

-- orderbook
select * from order_book;
select book_no, order_no, price,num from order_book;

select * from order_;

select ob.price, ob.num, b.no, b.title, b.price, b.category_no, o.no, o.order_info, o.payment, o.address, o.customer_no
from order_book ob
join book b on b.no = ob.book_no
join order_ o on o.no = ob.order_no
;

insert into order_book values(1,1,3000,2);