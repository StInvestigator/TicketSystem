CREATE TABLE IF NOT EXISTS customers
(
    id    SERIAL PRIMARY KEY,
    name  varchar(30) NOT NULL,
    email varchar(40) NOT NULL,
    phone varchar(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS places
(
    id      SERIAL PRIMARY KEY,
    address varchar(30) UNIQUE NOT NULL,
    name    varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS events
(
    id         SERIAL PRIMARY KEY,
    event_date date        NOT NULL,
    name       varchar(30) NOT NULL,
    place_id   int         NOT NULL,
    FOREIGN KEY (place_id) REFERENCES places (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ticket_statuses
(
    id   SERIAL PRIMARY KEY,
    name varchar(30) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS tickets
(
    id          SERIAL PRIMARY KEY,
    cost        numeric(10, 2) NOT NULL,
    number      int            NOT NULL,
    customer_id int,
    event_id    int            NOT NULL,
    status_id   int            NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES ticket_statuses (id) ON DELETE CASCADE
);

DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM ticket_statuses) THEN
            INSERT INTO ticket_statuses (name)
            VALUES ('FREE'),
                   ('SOLD');
        END IF;
    END
$$;