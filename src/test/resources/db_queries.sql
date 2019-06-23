drop table if exists role;
drop table if exists user;
drop table if exists user_roles;

create table role (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id));
create table user (id bigint not null auto_increment, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), username varchar(255) not null, primary key (id));
create table user_roles (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id));

alter table user_roles add constraint FKrhfovtciq1l558cw6udg0h0d3 foreign key (role_id) references role (id);
alter table user_roles add constraint FK55itppkw3i07do3h7qoclqd4k foreign key (user_id) references user (id);

INSERT INTO user (id, username, password, first_name, last_name, email) VALUES (1, 'user1', '$2a$04$Ye7/lJoJin6.m9sOJZ9ujeTgHEVM4VXgI2Ingpsnf9gXyXEXf/IlW', 'user1', 'last1', 'user1@gmail.com');
INSERT INTO user (id, username, password, first_name, last_name, email) VALUES (2, 'user2', '$2a$04$StghL1FYVyZLdi8/DIkAF./2rz61uiYPI3.MaAph5hUq03XKeflyW', 'user2', 'last2', 'user2@gmail.com');
INSERT INTO user (id, username, password, first_name, last_name, email) VALUES (3, 'user3', '$2a$04$Lk4zqXHrHd82w5/tiMy8ru9RpAXhvFfmHOuqTmFPWQcUhBD8SSJ6W', 'user3', 'last3', 'user3@gmail.com');

INSERT INTO role (id, description, name) VALUES (4, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name) VALUES (5, 'User role', 'USER');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 4);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 5);