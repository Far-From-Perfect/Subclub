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
    user_id bigint not null primary key,
    role_id int not null,
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id)
);

insert into roles (name)
values ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (name, password, email)
values ('superuser', '$2a$12$7KIHOVHF1TJt2X51cMyyq.SqbX3Ek9EHt.Ol1o1O9dcQivg0v5ija', 'superuser@subclub.com');

insert into users_roles (user_id, role_id)
values (1, 2);