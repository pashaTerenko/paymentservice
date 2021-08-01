create sequence my_seq1 start 111 increment 100
GO
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
    GO


alter table account
    add constraint FKkm8yb63h4ownvnlrbwnadntyn
        foreign key (client_id)
            references client client