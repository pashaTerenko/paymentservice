create table client (
                        client_id int8 not null,
                        created timestamp,
                        updated timestamp,
                        first_name varchar(255),
                        last_name varchar(255),
                        login varchar(255),
                        password varchar(255),
                        primary key (client_id)
)