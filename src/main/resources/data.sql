INSERT INTO RUSER(email, password, full_Name, phone_num) VALUES('falehyasser@gmail.com', 'yasser123', 'YasserFaleh', '0646750942');
INSERT INTO RUSER(email, password, full_Name, phone_num) VALUES('user2@gmail.com', 'password', 'user2', '0612345678');

INSERT INTO PRODUCT(id,title, description, price,category, product_status, user_email ,date) VALUES (-1,'Iphone 11' , 'en très bonne état',200,'MULTIMEDIA', 'AVAILABLE', 'falehyasser@gmail.com' ,CURRENT_TIMESTAMP());
INSERT INTO PRODUCT(id,title, description, price,category, product_status, user_email ,date) VALUES (-2,'PLOMBIER' , 'PLOMBIER EXPERT plus de 10ans',100,'SERVICE', 'AVAILABLE', 'falehyasser@gmail.com',CURRENT_TIMESTAMP() );

INSERT INTO VIEW(id,description,view_status,viewer_email,viewed_user_email) VALUES (-1,'Ca marchait trés bien, testestsetestes testes testest le produit fonctionne comme decrit dans loffre','GOOD','user2@gmail.com','falehyasser@gmail.com');
INSERT INTO VIEW(id,description,view_status,viewer_email,viewed_user_email) VALUES (-2,'Tres mauvais le produit, le produit ne fonctionne  pas comme decrit dans loffre','BAD','user2@gmail.com','falehyasser@gmail.com');
INSERT INTO VIEW(id,description,view_status,viewer_email,viewed_user_email) VALUES (-3,'Ca marchait trés bien, le produit fonctionne comme decrit dans loffre','GOOD','user2@gmail.com','falehyasser@gmail.com');
INSERT INTO VIEW(id,description,view_status,viewer_email,viewed_user_email) VALUES (-4,'Tres mauvais le produit, le produit ne fonctionne  pas comme decrit dans loffre','BAD','user2@gmail.com','falehyasser@gmail.com');
INSERT INTO VIEW(id,description,view_status,viewer_email,viewed_user_email) VALUES (-5,'Ca marchait trés bien, le produit fonctionne comme decrit dans loffre','GOOD','user2@gmail.com','falehyasser@gmail.com');
INSERT INTO VIEW(id,description,view_status,viewer_email,viewed_user_email) VALUES (-6,'Tres mauvais le produit, le produit ne fonctionne  pas comme decrit dans loffre','BAD','user2@gmail.com','falehyasser@gmail.com');

INSERT INTO OFFER(id,title, description, price,category, offer_status, user_email ,date) VALUES (-1,'je cherche Iphone 11' , 'en très bonne état',200,'MULTIMEDIA', 'AVAILABLE', 'falehyasser@gmail.com' ,CURRENT_TIMESTAMP());
INSERT INTO OFFER(id,title, description, price,category, offer_status, user_email ,date) VALUES (-2,'je cherche un PLOMBIER' , 'PLOMBIER EXPERT plus de 10ans',100,'SERVICE', 'AVAILABLE', 'user2@gmail.com',CURRENT_TIMESTAMP() );
