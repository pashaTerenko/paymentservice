create table account (
                         account_id int8 not null,
                         created timestamp,
                         updated timestamp,
                         account_number varchar(255),
                         account_type varchar(255),
                         balance numeric(19, 2),
                         client_id int8 not null,
                         primary key (account_id)
)

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

create table transaction (
                             payment_id int8 not null,
                             created timestamp,
                             updated timestamp,
                             amount numeric(19, 2),
                             reason varchar(255),
                             account_d_id int8 not null,
                             account_s_id int8 not null,
                             primary key (payment_id)
)

alter table account
    add constraint FKkm8yb63h4ownvnlrbwnadntyn
        foreign key (client_id)
            references client

alter table transaction
    add constraint FKf7otx0obf0kl87a8ghvol2cr
        foreign key (account_d_id)
            references account

alter table transaction
    add constraint FKst3jjekksev9ctj12clrghf0j
        foreign key (account_s_id)
            references account