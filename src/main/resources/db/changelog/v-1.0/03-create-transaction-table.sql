
create table transaction (
                             payment_id int8 not null,
                             created timestamp,
                             updated timestamp,
                             amount numeric(19, 2),
                             reason varchar(255),
                             transaction_result int4 not null,
                             account_d_id int8 not null,
                             account_s_id int8 not null,
                             primary key (payment_id)
)

GO

alter table transaction
    add constraint FKf7otx0obf0kl87a8ghvol2cr
        foreign key (account_d_id)
            references account
GO

alter table transaction
    add constraint FKst3jjekksev9ctj12clrghf0j
        foreign key (account_s_id)
            references account