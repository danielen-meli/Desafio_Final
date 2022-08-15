use meli_fresh;

-- id, localization, name
insert into warehouse values(null, "SP", "warehouse01");
insert into warehouse values(null, "SC", "warehouse03");
insert into warehouse values(null, "RJ", "warehouse04");
insert into warehouse values(null, "GO", "warehouse05");
insert into warehouse values(null, "AM", "warehouse06");
insert into warehouse values(null, "TO", "warehouse07");
insert into warehouse values(null, "MG", "warehouse08");
insert into warehouse values(null, "RS", "warehouse09");


-- id, category, section_capacity, section_temperature, warehouse_warehouse_id
insert into section values(null, "REFRIGERATED", 500, 10, 2);
insert into section values(null, "FRESH", 500, 10, 2);
insert into section values(null, "FROZEN", 500, 10, 2);
insert into section values(null, "REFRIGERATED", 500, 10, 1);
insert into section values(null, "FRESH", 500, 10, 1);
insert into section values(null, "FROZEN", 500, 10,1);

-- id, name, warehouse_warehouse_id
insert into leader values(null, "Mauri", 1);

-- id, category, product_name
insert into product values(null, "REFRIGERATED", "maçã");
insert into product values(null, "REFRIGERATED", "banana");
insert into product values(null, "FRESH", "tomate");
insert into product values(null, "FRESH", "alface");
insert into product values(null, "FROZEN", "lasanha");
insert into product values(null, "REFRIGERATED", "cenoura");
insert into product values(null, "FROZEN", "pizza");
insert into product values(null, "REFRIGERATED", "brócolis");
insert into product values(null, "REFRIGERATED", "couve");

-- id, cpf, email. password, user_name
insert into user values(null, "458.698.758-89", "email@email.com", "senha123", "User1");
insert into user values(null, "587.644.324-54", "email@email.com", "senha123", "User2");
insert into user values(null, "134.332.751-23", "email@email.com", "senha123", "User3");
insert into user values(null, "945.566.102-96", "email@email.com", "senha123", "User4");
insert into user values(null, "457.223.200-50", "email@email.com", "senha123", "User5");
insert into user values(null, "565.458.233-20", "email@email.com", "senha123", "User6");

-- id, seller_name, user_user_id
insert into seller values(null, "Seller1", 1);
insert into seller values(null, "Seller2", 2);
insert into seller values(null, "Seller3", 3);
insert into seller values(null, "Seller4", 4);
insert into seller values(null, "Seller5", 5);
insert into seller values(null, "Seller6", 6);


-- id, price, product_product_id, seller_id
insert into seller_ad values(null, 5, 3, 1);
insert into seller_ad values(null, 4, 2, 2);
insert into seller_ad values(null, 8, 1, 3);
insert into seller_ad values(null, 5, 4, 2);
insert into seller_ad values(null, 4, 5, 3);
insert into seller_ad values(null, 8, 6, 4);

insert into seller_ad values(null, 7, 3, 3);
insert into seller_ad values(null, 4, 4, 5);



-- id, quantity_purchased, user_user_id
insert into buyer values(null, 20, 1);
insert into buyer values(null, 300, 2);
insert into buyer values(null, 250, 3);
insert into buyer values(null, 400, 4);
insert into buyer values(null, 320, 5);
insert into buyer values(null, 500, 6);


-- id, status, buyer_buyer_id
insert into SHOP_ORDER (order_id, status, buyer_buyer_id) VALUES (null, "OPEN", 1);
insert into SHOP_ORDER (order_id, status, buyer_buyer_id) VALUES (null, "OPEN", 2);


-- id, date, price, quantity, seller_ad_id, shop_order_id
insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 1.0, 2, 1, 1);
insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 8.0, 5, 2, 1);
insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 1.0, 2, 3, 2);
insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 8.0, 5, 4, 2);

