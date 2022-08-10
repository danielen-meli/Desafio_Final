use meli_fresh;


insert into warehouse values(null, "SP", "warehouse01");


insert into section values(null, "REFRIGERATED", 500, 10, 1);
insert into section values(null, "FRESH", 500, 10, 1);


insert into leader values(null, "Mauri", 1);


insert into product values(null, "REFRIGERATED", "maçã");
insert into product values(null, "REFRIGERATED", "banana");
insert into product values(null, "REFRIGERATED", "tomate");
-- insert into product values(null, "REFRIGERATED", "pera");


insert into user values(null, "123456", "email@email.com", "senha123", "User1");

insert into seller values(null, "Seller1", 1);

insert into seller_ad values(null, 5, 3, 1);
insert into seller_ad values(null, 4, 2, 1);
insert into seller_ad values(null, 8, 1, 1);

insert into buyer values(null, 1);
-- insert into seller_ad values(null, 10, 4, 1);

