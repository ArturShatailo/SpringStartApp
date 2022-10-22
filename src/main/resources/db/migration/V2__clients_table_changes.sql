alter table clients drop deleted;

alter table clients add if not exists deleted boolean;