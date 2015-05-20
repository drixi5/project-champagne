# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table meals (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_meals primary key (id))
;

create table model_employee (
  stock_id                  bigint auto_increment not null,
  entity_id                 bigint,
  constraint pk_model_employee primary key (stock_id))
;

create table orders_stores (
  id                        bigint auto_increment not null,
  reference                 varchar(255),
  status                    varchar(255),
  creation_date             datetime,
  delivry_date              datetime,
  store_id                  bigint,
  constraint pk_orders_stores primary key (id))
;

create table orders_stores_prod_suppliers (
  id                        bigint auto_increment not null,
  quantity                  integer,
  product_id                bigint,
  store_id                  bigint,
  supplier_id               bigint,
  constraint pk_orders_stores_prod_suppliers primary key (id))
;

create table orders_suppliers (
  id                        bigint auto_increment not null,
  reference                 varchar(255),
  status                    varchar(255),
  creation_date             datetime,
  delivry_date              datetime,
  supplier_id               bigint,
  constraint pk_orders_suppliers primary key (id))
;

create table products (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  type_product_id           bigint,
  constraint uq_products_name unique (name),
  constraint pk_products primary key (id))
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

create table stores_products (
  id                        bigint auto_increment not null,
  product_id                bigint,
  unit_id                   bigint,
  quantity                  integer,
  threshold_max             integer,
  threshold_min             integer,
  constraint pk_stores_products primary key (id))
;

create table suppliers (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  email                     varchar(255),
  adress1                   varchar(255),
  adress2                   varchar(255),
  code_postal               integer,
  ville                     varchar(255),
  user_id                   bigint,
  constraint pk_suppliers primary key (id))
;

create table suppliers_products (
  id                        bigint auto_increment not null,
  product_id                bigint,
  unit_id                   bigint,
  quantity                  integer,
  threshold_max             integer,
  threshold_min             integer,
  constraint pk_suppliers_products primary key (id))
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

create table type_user (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_type_user primary key (id))
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

alter table orders_stores add constraint fk_orders_stores_store_1 foreign key (store_id) references stores (id) on delete restrict on update restrict;
create index ix_orders_stores_store_1 on orders_stores (store_id);
alter table orders_stores_prod_suppliers add constraint fk_orders_stores_prod_suppliers_product_2 foreign key (product_id) references products (id) on delete restrict on update restrict;
create index ix_orders_stores_prod_suppliers_product_2 on orders_stores_prod_suppliers (product_id);
alter table orders_stores_prod_suppliers add constraint fk_orders_stores_prod_suppliers_store_3 foreign key (store_id) references stores (id) on delete restrict on update restrict;
create index ix_orders_stores_prod_suppliers_store_3 on orders_stores_prod_suppliers (store_id);
alter table orders_stores_prod_suppliers add constraint fk_orders_stores_prod_suppliers_supplier_4 foreign key (supplier_id) references suppliers (id) on delete restrict on update restrict;
create index ix_orders_stores_prod_suppliers_supplier_4 on orders_stores_prod_suppliers (supplier_id);
alter table orders_suppliers add constraint fk_orders_suppliers_supplier_5 foreign key (supplier_id) references suppliers (id) on delete restrict on update restrict;
create index ix_orders_suppliers_supplier_5 on orders_suppliers (supplier_id);
alter table products add constraint fk_products_typeProduct_6 foreign key (type_product_id) references type_product (id) on delete restrict on update restrict;
create index ix_products_typeProduct_6 on products (type_product_id);
alter table stores_products add constraint fk_stores_products_product_7 foreign key (product_id) references products (id) on delete restrict on update restrict;
create index ix_stores_products_product_7 on stores_products (product_id);
alter table stores_products add constraint fk_stores_products_unit_8 foreign key (unit_id) references unit (id) on delete restrict on update restrict;
create index ix_stores_products_unit_8 on stores_products (unit_id);
alter table suppliers add constraint fk_suppliers_user_9 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_suppliers_user_9 on suppliers (user_id);
alter table suppliers_products add constraint fk_suppliers_products_product_10 foreign key (product_id) references products (id) on delete restrict on update restrict;
create index ix_suppliers_products_product_10 on suppliers_products (product_id);
alter table suppliers_products add constraint fk_suppliers_products_unit_11 foreign key (unit_id) references unit (id) on delete restrict on update restrict;
create index ix_suppliers_products_unit_11 on suppliers_products (unit_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table meals;

drop table model_employee;

drop table orders_stores;

drop table orders_stores_prod_suppliers;

drop table orders_suppliers;

drop table products;

drop table stores;

drop table stores_products;

drop table suppliers;

drop table suppliers_products;

drop table token;

drop table type_product;

drop table type_user;

drop table unit;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

