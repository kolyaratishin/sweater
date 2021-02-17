delete from user_role;
delete from usr;


insert into usr (id,active,username,password) values
 (1,true,'kolya','{t9eRel2W5tOSk9rY1/iWQdOL7haxbR5t/+KPpT944dA=}7499acc11a4a7966253ec4c062cc176e'),
 (2,true,'mike','{t9eRel2W5tOSk9rY1/iWQdOL7haxbR5t/+KPpT944dA=}7499acc11a4a7966253ec4c062cc176e');

insert into user_role (user_id,roles) values (1,'USER'),(1,'ADMIN'),(2,'USER');