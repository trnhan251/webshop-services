create table if not exists webshop_catalog.product
(
    id	bigserial not null constraint user_login_pk primary key,
    name varchar(60) not null,
    price float(4) not null,
    image_url varchar
);

insert into webshop_catalog.product (name, price, image_url ) values ('Product 1', 7.5, 'http://test');
insert into webshop_catalog.product (name, price, image_url ) values ('Product 2', 23.95, 'http://test1');
insert into webshop_catalog.product (name, price, image_url ) values ('Product 3', 32.15, 'http://test2');
insert into webshop_catalog.product (name, price, image_url ) values ('Product 4', 17.5, 'http://test3');
insert into webshop_catalog.product (name, price, image_url ) values ('Product 5', 37.5, 'http://test4');
insert into webshop_catalog.product (name, price, image_url ) values ('Product 6', 47.0, 'http://test5');
insert into webshop_catalog.product (name, price, image_url ) values ('Product 7', 75.5, 'http://test6');
insert into webshop_catalog.product (name, price, image_url ) values ('Product 8', 45.5, 'http://test7');
insert into webshop_catalog.product (name, price, image_url ) values ('Product 9', 32.5, 'http://test8');
insert into webshop_catalog.product (name, price, image_url ) values ('Product 10', 3.5, 'http://test9');
insert into webshop_catalog.product (name, price, image_url ) values ('Product 11', 43.5, 'http://test10');
insert into webshop_catalog.product (name, price, image_url ) values ('Product 12', 23.5, 'http://test11');