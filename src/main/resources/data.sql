use meli_fresh;

insert into warehouse values(null, "SP", "warehouse01");
insert into warehouse values(null, "SC", "warehouse02");
insert into warehouse values(null, "PR", "warehouse03");
insert into warehouse values(null, "SP", "warehouse04");
insert into warehouse values(null, "DF", "warehouse05");

insert into section values(null, "REFRIGERATED", 500, 10, 2);
insert into section values(null, "FRESH", 500, 10, 2);
insert into section values(null, "FROZEN", 500, 10, 2);
insert into section values(null, "REFRIGERATED", 500, 10, 1);
insert into section values(null, "FRESH", 500, 10, 1);
insert into section values(null, "FROZEN", 500, 10,3);
insert into section values(null, "REFRIGERATED", 500, 10, 3);
insert into section values(null, "FRESH", 500, 10, 3);
insert into section values(null, "FROZEN", 500, 10,3);

insert into leader values(null, "Mauri", 1);
insert into leader values(null, "Ana", 2);
insert into leader values(null, "João", 3);
insert into leader values(null, "Maria", 4);
insert into leader values(null, "José", 5);


insert into product values(null, "REFRIGERATED", "maçã");
insert into product values(null, "REFRIGERATED", "banana");
insert into product values(null, "FRESH", "tomate");
insert into product values(null, "FRESH", "alface");
insert into product values(null, "FROZEN", "lasanha");
insert into product values(null, "FROZEN", "pizza");
insert into product values(null, "FROZEN", "pão de queijo");
insert into product values(null, "FROZEN", "hamburguer");

insert into user values(null, "123456", "user1@email.com", "senha123", "User1");
insert into user values(null, "654321", "user2@email.com", "senha123", "User2");
insert into user values(null, "7894561", "user3@email.com", "senha123", "User3");
insert into user values(null, "654321", "user4@email.com", "senha123", "User4");

insert into seller values(null, "Seller1", 1);
insert into seller values(null, "Seller2", 2);
insert into seller values(null, "Seller3", 3);
insert into seller values(null, "Seller4", 4);


insert into seller_ad values(null, 5, 3, 1);
insert into seller_ad values(null, 4, 2, 1);
insert into seller_ad values(null, 8, 1, 1);
insert into seller_ad values(null, 5, 4, 3);
insert into seller_ad values(null, 4, 5, 3);
insert into seller_ad values(null, 8, 1, 3);

insert into buyer values(null, 1);
insert into buyer values(null, 2);
insert into buyer values(null, 3);
insert into buyer values(null, 4);

insert into SHOP_ORDER (order_id, status, buyer_buyer_id) VALUES (null, "OPEN", 1);
insert into SHOP_ORDER (order_id, status, buyer_buyer_id) VALUES (null, "OPEN", 2);

insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 1.0, 2, 1, 1);
insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 8.0, 5, 2, 1);
insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 1.0, 2, 3, 2);
insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 8.0, 5, 4, 2);


drop table IF EXISTS users_perfis;
drop table IF EXISTS users;
drop table IF EXISTS perfil;

create table users(
                      username varchar(50) not null primary key,
                      password varchar(500) not null,
                      enabled boolean not null
);

insert into users values('danielen', '$2a$10$ukdgXj8w.j/B.TFmsdZYteoMU4tOQdLbPU62P9vs/jCIqa5ampbya', '1');
insert into users values('mauri', '$2a$10$ZTD5yPb/J0nGGvnJi61kr.nbf.8rBfjGonKiYnDOjKx7mJP6g8i.y', '2');
insert into users values('joice', '$2a$10$6GweoH8l1HZ6P.Riat6/h.aSK/ebp7IZGRT2wSM1zimZfkplaU1te', '3');


create table perfil(
                       id serial not null primary key,
                       nome varchar(20) not null
);

insert into perfil(nome) values ('ADMIN');
insert into perfil(nome) values ('CUSTOMER');
insert into perfil(nome) values ('SELLER');

create table users_perfis(
                             usuario_username  varchar(50) not null,
                             perfis_id integer not null,
                             constraint pk_usuario_perfil primary key (usuario_username , perfis_id)
);

insert into users_perfis values ('danielen',1);
insert into users_perfis values ('mauri',2);
insert into users_perfis values ('joice',3);