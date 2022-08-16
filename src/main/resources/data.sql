use meli_fresh;

-- id, localization, name
insert into warehouse values(null, "SP", "warehouse01");
insert into warehouse values(null, "SP", "warehouse02");
insert into warehouse values(null, "SC", "warehouse03");
insert into warehouse values(null, "RJ", "warehouse04");
insert into warehouse values(null, "GO", "warehouse05");
insert into warehouse values(null, "AM", "warehouse06");
insert into warehouse values(null, "TO", "warehouse07");
insert into warehouse values(null, "MG", "warehouse08");
insert into warehouse values(null, "RS", "warehouse09");


-- id, category, section_capacity, section_temperature, warehouse_warehouse_id
insert into section values(null, "REFRIGERATED", 1000, 5, 2);
insert into section values(null, "REFRIGERATED", 1000, 5, 1);
insert into section values(null, "FRESH", 1000, 10, 2);
insert into section values(null, "FRESH", 1000, 10, 1);
insert into section values(null, "FROZEN", 1000, 1, 2);
insert into section values(null, "FROZEN", 1000, 1,1);

-- id, name, warehouse_warehouse_id
insert into leader values(null, "Marcos Galperin", 1);
insert into leader values(null, "Arthur Santana", 2);
insert into leader values(null, "Gabriel Gonçalves", 3);
insert into leader values(null, "Tatiane Lacerda", 4);
insert into leader values(null, "Julia Herbele", 5);
insert into leader values(null, "Adão Wypnik", 6);
insert into leader values(null, "Danielen Nunes", 7);
insert into leader values(null, "Mauri Klein", 8);
insert into leader values(null, "Emerson Paduan", 9);

-- id, category, product_name
insert into product values(null, "REFRIGERATED", "Maçã");
insert into product values(null, "REFRIGERATED", "Banana");
insert into product values(null, "REFRIGERATED", "Cenoura");
insert into product values(null, "REFRIGERATED", "Brócolis");
insert into product values(null, "REFRIGERATED", "Couve");
insert into product values(null, "FRESH", "Tomate");
insert into product values(null, "FRESH", "Alface");
insert into product values(null, "FRESH", "Laranja");
insert into product values(null, "FRESH", "Melancia");
insert into product values(null, "FROZEN", "Lasanha");
insert into product values(null, "FROZEN", "Pizza");
insert into product values(null, "FROZEN", "Frango");

-- id, cpf, email. password, user_name
insert into user values(null, "458.698.758-89", "joão@email.com", "senha123", "João Ferreira");
insert into user values(null, "587.644.324-54", "marcos@email.com", "senha123", "Marcos Silva");
insert into user values(null, "134.332.751-23", "pedro@email.com", "senha123", "Pedro Paulo");
insert into user values(null, "945.566.102-96", "david@email.com", "senha123", "David Medeiros");
insert into user values(null, "457.223.200-50", "eduardo@email.com", "senha123", "Eduarto Telles");
insert into user values(null, "565.458.233-20", "rafaela@email.com", "senha123", "Rafaela Bonnaparte");
insert into user values(null, "586.447.367-13", "marcela@email.com", "senha123", "Marcela Maques");
insert into user values(null, "897.223.778-56", "cassia@email.com", "senha123", "Cassia Eliza");
insert into user values(null, "445.908.257-29", "karla@email.com", "senha123", "Karla Barbosa");
insert into user values(null, "121.223.778-15", "winston@email.com", "senha123", "Winston Gonçalves");
insert into user values(null, "267.342.344-33", "gustavo@email.com", "senha123", "Gustavo Lima");
insert into user values(null, "789.923.903-47", "italo@email.com", "senha123", "Italo Ferreira");

-- id, seller_name, user_user_id
insert into seller values(null, "João Ltda", 1);
insert into seller values(null, "Marcos Fresh", 2);
insert into seller values(null, "Pedro Comp", 3);
insert into seller values(null, "Supermercado David", 4);
insert into seller values(null, "Edu Frozen", 5);
insert into seller values(null, "Rafa Market", 6);


-- id, price, product_product_id, seller_id
-- APPLE
insert into seller_ad values(null, 2, 1, 1);
insert into seller_ad values(null, 1.5, 1, 2);
insert into seller_ad values(null, 2, 1, 3);

-- tomato
insert into seller_ad values(null, 2.5, 6, 4);
insert into seller_ad values(null, 2.3, 6, 5);
insert into seller_ad values(null, 3, 6, 6);

-- Lazanha
insert into seller_ad values(null, 10, 10, 2);
insert into seller_ad values(null, 11, 10, 4);
insert into seller_ad values(null, 10.5, 10, 6);

-- Cenoura
insert into seller_ad values(null, 4.4, 3, 1);
insert into seller_ad values(null, 4, 3, 2);
insert into seller_ad values(null, 7, 3, 3);

-- Alface
insert into seller_ad values(null, 3.5, 7, 4);
insert into seller_ad values(null, 3, 7, 5);
insert into seller_ad values(null, 2.99, 7, 6);

-- insert into seller_ad values(null, );
-- insert into seller_ad values(null, );
-- insert into seller_ad values(null, );




-- id, quantity_purchased, user_user_id
insert into buyer values(null, 20, 1);  -- Buyer and seller
insert into buyer values(null, 300, 2);
insert into buyer values(null, 250, 3);
insert into buyer values(null, 400, 4);
insert into buyer values(null, 320, 5);
insert into buyer values(null, 500, 6);

--
-- -- id, status, buyer_buyer_id
-- insert into SHOP_ORDER (order_id, status, buyer_buyer_id) VALUES (null, "OPEN", 1);
-- insert into SHOP_ORDER (order_id, status, buyer_buyer_id) VALUES (null, "OPEN", 2);
--
--
-- -- id, date, price, quantity, seller_ad_id, shop_order_id
-- insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 1.0, 2, 1, 1);
-- insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 8.0, 5, 2, 1);
-- insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 1.0, 2, 3, 2);
-- insert into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (null, "2022-08-08", 8.0, 5, 4, 2);

