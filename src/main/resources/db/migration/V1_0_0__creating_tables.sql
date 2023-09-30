create table users(
    id bigserial primary key not null,
    name varchar(127) not null unique,
    password varchar(255) not null,
    email varchar(96) not null,
    created_at timestamp
);

create table roles (
    id serial primary key,
    name varchar(50) not null
);

create table users_roles (
    user_id bigint not null,
    role_id int not null,
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id),
    constraint users_roles_pk primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (name, password, email)
values ('superuser', '$2a$12$7KIHOVHF1TJt2X51cMyyq.SqbX3Ek9EHt.Ol1o1O9dcQivg0v5ija', 'superuser@subclub.com');

insert into users_roles (user_id, role_id)
values (1, 2);

create table media_type (
    id serial primary key not null,
    name varchar(50) not null
);

insert into media_type (name)
values ('Films'), ('Series'), ('Short_films');

create table titles (
    id bigserial primary key not null,
    title varchar(127) not null,
    description varchar(4096) not null
);

create table content_type (
    title_id bigint primary key not null,
    media_type_id int not null,
    foreign key (title_id) references titles(id),
    foreign key (media_type_id) references media_type(id)
);

create table users_titles (
    user_id bigint not null,
    title_id bigint not null,
    foreign key (user_id) references users(id),
    foreign key (title_id) references titles(id),
    constraint users_titles_pk primary key (user_id, title_id)
);