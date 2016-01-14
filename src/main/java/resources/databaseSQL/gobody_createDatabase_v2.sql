drop database if exists seejiji;
create database seejiji;
use seejiji;

create table article(
id					 int(10)			   not null    primary key    unique     auto_increment,
articleid          varchar(255)    not null    unique,
title               varchar(255)    not null,
url                 varchar(255)    not null,
abstract         varchar(255)    not null,
author           varchar(255)    not null,
img1			     varchar(255),
img2              varchar(255),
img3              varchar(255),
supportnum    int(10)           not null,
createtime       bigint              not null
);

create table  subject(
subjectid                  int(5)             not null    primary key      unique       auto_increment,
subjectname             varchar(255)  not null
);

create table users(
id                 int(10)            not null     primary key      unique     auto_increment,
username     varchar(255)    not null,
password     varchar(255)    not null
);

create table articlesubject(
id                  int(10)            not null    primary key     unique      auto_increment,
articleid          int(10)           not null,
subjectid        int(5)             not null
);

create table userfavorite(
id              int(10)       not null       primary key      unique         auto_increment,
userid       int(10)        not null,
articleid     int(10)        not null
);

create table usercomment(
id              int(10)          not null     primary key        unique        auto_increment,
userid        int(10)          not null,
articleid      int(10)          not null,
comment     varchar(255)      not null
);

create table keywords(
id                  int(10)         not null    primary key     unique        auto_increment,
keyword             varchar(20)     not null,
articleIds          text
);

create table articlecontent(
id                      int(10)            not null           primary key     unique  auto_increment,
articleid             int(10)             unique,
content             text
);

create table historyUrl(
id                  int(10)         not null    primary key     unique  auto_increment,
url                 varchar(255)    not null
);

alter table articlecontent
   add constraint foreign key (articleid)
      references article (id);
      
alter table articlesubject
   add constraint foreign key (articleid)
      references article (id);
      
alter table articlesubject
   add constraint foreign key (subjectid)
      references subject (subjectid);
      
alter table userfavorite
   add constraint foreign key (userid)
      references users (id);
      
alter table userfavorite
   add constraint foreign key (articleid)
      references article (id);
      
alter table usercomment
   add constraint foreign key (userid)
      references users (id);     
      
alter table usercomment
   add constraint foreign key (articleid)
      references article (id);      

      
insert into subject values (1, '健身');
insert into subject values (2, '跑步');
insert into subject values (3, '瑜伽');
insert into subject values (4, '囚徒');
insert into subject values (5, '圈内');
insert into subject values (6, '体育');
insert into subject values (7, '格斗');
insert into subject values (8, '训练');
insert into subject values (9, '减肥');
insert into subject values (10, '极限');

GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.0.102' IDENTIFIED BY '111111' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.0.101' IDENTIFIED BY '111111' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.0.100' IDENTIFIED BY '111111' WITH GRANT OPTION;
