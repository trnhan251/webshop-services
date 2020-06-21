create table if not exists webshop_cart.order
(
    id	bigserial not null constraint order_pk primary key,
    session_id varchar not null,
    product_id int not null,
    quantity int not null
);