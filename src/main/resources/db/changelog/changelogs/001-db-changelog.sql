--liquibase formatted sql

--changeset Arthur:1
alter table order_requests add foreign key (client_id) references clients;