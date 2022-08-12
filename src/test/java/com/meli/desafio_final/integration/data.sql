
use meli_fresh;

insert into warehouse values(null, "SP", "warehouse01");
insert into warehouse values(null, "SC", "warehouse02");

insert into section values(null, "REFRIGERATED", 500, 10, 2);
insert into section values(null, "FRESH", 500, 10, 2);
insert into section values(null, "FROZEN", 500, 10, 2);
insert into section values(null, "REFRIGERATED", 500, 10, 1);
insert into section values(null, "FRESH", 500, 10, 1);
insert into section values(null, "FROZEN", 500, 10,1);

insert into leader values(null, "Mauri", 1);


insert into product values(null, "REFRIGERATED", "maçã");
insert into product values(null, "REFRIGERATED", "banana");
insert into product values(null, "FRESH", "tomate");
insert into product values(null, "FRESH", "alface");
insert into product values(null, "FROZEN", "lasanha");
insert into product values(null, "FROZEN", "pizza");

insert into user values(null, "123456", "email@email.com", "senha123", "User1");
insert into user values(null, "654321", "email@email.com", "senha123", "User2");

insert into seller values(null, "Seller1", 1);

insert into seller_ad values(null, 5, 3, 1);
insert into seller_ad values(null, 4, 2, 1);
insert into seller_ad values(null, 8, 1, 1);
insert into seller_ad values(null, 5, 4, 1);
insert into seller_ad values(null, 4, 5, 1);
insert into seller_ad values(null, 8, 1, 1);

insert into buyer values(null, 1);
insert into buyer values(null, 2);

insert into SHOP_ORDER (order_id, status, buyer_buyer_id) VALUES (1, "OPEN", 1);
insert into SHOP_ORDER (order_id, status, buyer_buyer_id) VALUES (2, "OPEN", 2);
insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (1, "2022-08-08", 1.0, 2, 1, 1);
insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (2, "2022-08-08", 8.0, 5, 2, 1);
insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (3, "2022-08-08", 1.0, 2, 3, 2);
insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (4, "2022-08-08", 8.0, 5, 4, 2);

