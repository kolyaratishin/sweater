create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 1 );
create table message (
    id bigint not null,
    filename varchar(255),
    tag varchar(255),
    text varchar(255) NOT NULL,
    user_id bigint,
    primary key (id)
);

create table user_role (
    user_id bigint not null,
    roles varchar(255)
);
create table usr (
    id bigint not null,
    activation_code varchar(255),
    active bit not null,
    email varchar(255),
    password varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    primary key (id)
);
alter table message
    add constraint message_user_fk
    foreign key (user_id) references usr (id);
alter table user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr (id);