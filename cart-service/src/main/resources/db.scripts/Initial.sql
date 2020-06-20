create table if not exists webshop_catalog.product
(
    id	bigserial not null constraint user_login_pk primary key,
    name varchar(60) not null,
    price float(4) not null
);

insert into webshop_catalog.product (name, price) values ('Product 1', 7.5);
insert into webshop_catalog.product (name, price) values ('Product 2', 23.95);
insert into webshop_catalog.product (name, price) values ('Product 3', 32.15);
insert into webshop_catalog.product (name, price) values ('Product 4', 17.5);
insert into webshop_catalog.product (name, price) values ('Product 5', 37.5);
insert into webshop_catalog.product (name, price) values ('Product 6', 47.0);
insert into webshop_catalog.product (name, price) values ('Product 7', 75.5);
insert into webshop_catalog.product (name, price) values ('Product 8', 45.5);
insert into webshop_catalog.product (name, price) values ('Product 9', 32.5);
insert into webshop_catalog.product (name, price) values ('Product 10', 3.5);
insert into webshop_catalog.product (name, price) values ('Product 11', 43.5);
insert into webshop_catalog.product (name, price) values ('Product 12', 23.5);