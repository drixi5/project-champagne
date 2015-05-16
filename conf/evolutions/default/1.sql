# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table model_product (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  type_id                   bigint,
  unit_id                   bigint,
  constraint pk_model_product primary key (id))
;

create table stock (
  stock_id                  bigint auto_increment not null,
  product_id                bigint,
  store_id                  bigint,
  name                      varchar(255),
  type                      varchar(255),
  unit                      varchar(255),
  quantity                  integer,
  threshold_max             integer,
  threshold_min             integer,
  constraint uq_stock_name unique (name),
  constraint pk_stock primary key (stock_id))
;

create table stores (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  email                     varchar(255),
  adress1                   varchar(255),
  adress2                   varchar(255),
  code_postal               integer,
  ville                     varchar(255),
  constraint pk_stores primary key (id))
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

create table type_product (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_type_product primary key (id))
;

create table unit (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_unit primary key (id))
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

alter table model_product add constraint fk_model_product_type_1 foreign key (type_id) references type_product (id) on delete restrict on update restrict;
create index ix_model_product_type_1 on model_product (type_id);
alter table model_product add constraint fk_model_product_unit_2 foreign key (unit_id) references unit (id) on delete restrict on update restrict;
create index ix_model_product_unit_2 on model_product (unit_id);
alter table stock add constraint fk_stock_product_3 foreign key (product_id) references model_product (id) on delete restrict on update restrict;
create index ix_stock_product_3 on stock (product_id);
alter table stock add constraint fk_stock_store_4 foreign key (store_id) references stores (id) on delete restrict on update restrict;
create index ix_stock_store_4 on stock (store_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table model_product;

drop table stock;

drop table stores;

drop table token;

drop table type_product;

drop table unit;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

