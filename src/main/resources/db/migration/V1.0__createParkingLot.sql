create sequence hibernate_sequence start with 1 increment by 1;
create table parkinglot (id integer not null, capacity integer not null check (capacity>=0), name varchar(255), position varchar(255), primary key (id));
alter table parkinglot add constraint UK_p5xmo3j1cqlgucnenoyp2yy1v unique (name)