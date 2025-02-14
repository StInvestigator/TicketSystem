drop table tickets;
drop table customers;
drop table user_role;
drop table app_user;

CREATE TABLE app_user
(
    id           SERIAL PRIMARY KEY,
    name  varchar(30) NOT NULL,
    email varchar(40) NOT NULL unique,
    phone varchar(20) NOT NULL,
    encrypted_password varchar(128),
    enabled           boolean
);

CREATE TABLE tickets
(
    id          SERIAL PRIMARY KEY,
    cost        numeric(10, 2) NOT NULL,
    number      int            NOT NULL,
    customer_id int,
    event_id    int            NOT NULL,
    status_id   int            NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES app_user (id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES ticket_statuses (id) ON DELETE CASCADE
    );


CREATE TABLE IF NOT EXISTS user_role (
    id SERIAL PRIMARY KEY,
    user_id integer,
    role_id integer,
    FOREIGN KEY (user_id) REFERENCES app_user (id)  ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);

insert into app_user (id, name, encrypted_password, enabled, email, phone)
values (1, 'admin', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true, 'admin@gmail.com','+38050505050'),
       (2, 'user', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true, 'user@gmail.com','+38053232050');


insert into  user_role (id, user_id, role_id)
values (1,1,1),
       (2,1,2),
       (3,2,2);
