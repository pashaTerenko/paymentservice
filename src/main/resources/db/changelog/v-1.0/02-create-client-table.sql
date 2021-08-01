create sequence my_seq_cl start 111 increment 100
GO
create table client (
                        client_id int8 not null,
                        created timestamp,
                        updated timestamp,
                        first_name varchar(255) not null,
                        last_name varchar(255) not null,
                        primary key (client_id)
)