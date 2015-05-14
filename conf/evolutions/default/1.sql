# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

<<<<<<< HEAD
create table product (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  type                      varchar(255),
  constraint uq_product_name unique (name),
  constraint pk_product primary key (id))
=======
<<<<<<< HEAD
create table stores (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  adresse1                  varchar(255),
  adresse2                  varchar(255),
  code_postal               integer,
  ville                     varchar(255),
  constraint pk_stores primary key (id))
=======
create table stock (
  stock_id                  bigint auto_increment not null,
  entity_id                 bigint,
  name                      varchar(255),
  type                      varchar(255),
  unit                      varchar(255),
  quantity                  integer,
  threshold_max             integer,
  threshold_min             integer,
  date_creation             datetime,
  constraint uq_stock_name unique (name),
  constraint pk_stock primary key (stock_id))
>>>>>>> master
>>>>>>> master
;

create table token (
  token                     varchar(255) not null,
  user_id                   bigint,
  type                      varchar(8),
  date_creation             datetime,
  email                     varchar(255),
  constraint ck_token_type check (type in ('password','email')),
  constraint pk_token primary key (token))
;

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(255),
  fullname                  varchar(255),
  confirmation_token        varchar(255),
  password_hash             varchar(255),
  date_creation             datetime,
  validated                 tinyint(1) default 0,
  constraint uq_user_email unique (email),
  constraint uq_user_fullname unique (fullname),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

<<<<<<< HEAD
drop table product;
=======
<<<<<<< HEAD
drop table stores;
=======
drop table stock;
>>>>>>> master
>>>>>>> master

drop table token;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

