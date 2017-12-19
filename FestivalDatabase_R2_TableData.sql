/* Test Data Refresh SQL Script */
/* Directions:  Copy the statements below into mySQL Workbench to add the data into the table in the DEFAULT SCHEMA. */
/* BE SURE TO SET THE DEFAULT SCHEMA TO FESTIVALDATABASE2 in the SQL EDITOR*/

DELETE FROM EVENTSESSIONSIGNUP WHERE SIGNUPID >=1;
DELETE FROM EVENTSESSION WHERE EVENTSESSIONID >=1;
DELETE FROM EVENT WHERE EVENTID >=1;
DELETE FROM VISITOR WHERE VISITORID >=1;
DELETE FROM EVENTCOORDINATOR WHERE EVENTCOORDINATORID >=1;

INSERT INTO EVENT (EVENTID,NAME,DESCRIPTION,PLACES,DURATION,EVENTTYPE)
VALUES ('1001','Rose Parade','Floats, Music and More','Rose Garden','0900-1400','Tour'),
 ('1002','NCCL Semi Finals','North Division Cricket Match','New Codington Stadium','1800-2200','Sporting Event'),
 ('1003','Fireworks Show','Spectacular Display at River','New Codington River Walk','1900-2000','Attraction'),
 ('1004','Garden Tour','Tour the Beautiful Rose Garden','Rose Garden','1000-1400','Tour'),
 ('1005','NCCL Championship','North vs. South Cricket Match','New Codington Stadium','1800-2200','Sporting Event'),
 ('1006','Pavlova  - All World Tour','Pavlova in Concert','New Codington Music Hall','1930-2130','Concert')
;  

INSERT INTO VISITOR (VISITORID,USERNAME,PASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER,ISADMIN)
VALUES ('1001','bsmith','password','Bob','Smith','bsmith@email.com','748937487',0),
 ('1002','npatel','password','Nitin','Patel','npatel@email.com','3392382',0),
 ('1003','jjones','password','James','Jones','jjones@email.com','2342343244',0),
 ('1004','jfrancois','password','Jacques','Francois','jfrancois@email.com','1234567890',0),
 ('1005','rkreiger','password','Robert','Kreiger','rkreiger@email.com','49872938',0),
 ('1006','ylee','password','Yi-Hui','Lee','ylee@email.com','21239393',0),
 ('1007','admin','admin','Admin FName','Admin LName','admin@email.com','3136789',1)
;  /* DELETE COMMA FROM PREVIOUS VALUES STATEMENT */

INSERT INTO EVENTCOORDINATOR (EVENTCOORDINATORID,USERNAME,PASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER,PLACE)
VALUES ('101','admin1','password','Chun','Yuan','admin1@email.com','2849283928','1 New Codington Ct.'),
 ('102','admin2','password','Krishna','Ramachadran','admin2@email.com','2849283930','1 New Codington Ct.'),
 ('103','admin3','password','Beau','Forget','admin3@email.com','2849283932','1 New Codington Ct.'),
 ('104','admin4','password','Heinz','Krauzer','admin4@email.com','2849283934','1 New Codington Ct.'),
 ('105','admin5','password','Jasper','Cadence','admin5@email.com','2849283936','1 New Codington Ct.')
;  /* DELETE COMMA FROM PREVIOUS VALUES STATEMENT */

INSERT INTO EVENTSESSION (EVENTSESSIONID,EVENTCOORDINATORID,EVENTID,SEATSAVAILABLE)
VALUES ('10001','101','1001','4000'),
 ('10002','102','1002','5000'),
 ('10003','103','1003','1000'),
 ('10004','104','1004','1'),
 ('10005','102','1005','5000'),
 ('10006','105','1006','1000'),
 ('10007','105','1006','500'),
 ('10008','105','1006','1200')
;  /* DELETE COMMA FROM PREVIOUS VALUES STATEMENT */
