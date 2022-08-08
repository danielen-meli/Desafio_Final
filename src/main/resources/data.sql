show tables;

replace into WAREHOUSE (warehouse_id, name, localization) VALUES (1, "NomeDaWarehouse1", "LocalizacaoDaWarehouse1");
replace into WAREHOUSE (warehouse_id, name, localization) VALUES (2, "NomeDaWarehouse2", "LocalizacaoDaWarehouse2");
replace into WAREHOUSE (warehouse_id, name, localization) VALUES (3, "NomeDaWarehouse3", "LocalizacaoDaWarehouse3");

replace into LEADER (leader_id, leader_name, warehouse_warehouse_id) VALUES (1, "LeaderName1", 1);
replace into LEADER (leader_id, leader_name, warehouse_warehouse_id) VALUES (2, "LeaderName2", 2);
replace into LEADER (leader_id, leader_name, warehouse_warehouse_id) VALUES (1, "LeaderName1", 3);

replace into section (section_id, category, section_capacity, section_temperature, warehouse_warehouse_id) VALUES (1, 1, "250", "-30", 1);
replace into section (section_id, category, section_capacity, section_temperature, warehouse_warehouse_id) VALUES (2, 2, "300", "5", 1);
replace into section (section_id, category, section_capacity, section_temperature, warehouse_warehouse_id) VALUES (3, 3, "350", "15", 1);

replace into inbound_order( inbound_order_id, order_date, section_id) values (1, now(), 1);

replace into PRODUCT (product_id, category, product_name) VALUES (1, 1, "maca");
replace into PRODUCT (product_id, category, product_name) VALUES (2, 1, "Pera");

replace into USER (user_id, cpf, email, password, user_name) VALUES (1, "1234", "julia.heberle", "123", "julia");

replace into BUYER (buyer_id, user_user_id) VALUES (1, 1);

replace into SHOP_ORDER (order_id, status, buyer_buyer_id) VALUES (1, 1, 1);

replace into SELLER_AD (seller_ad_id, price, seller_id, product_product_id) VALUES (1, 4.0, null, 1);

replace into SHOP_ORDER_ITEM (id, date, price, quantity, seller_ad_id, shop_order_id) VALUES (1, now(), 1.0, 2, 1, 1);