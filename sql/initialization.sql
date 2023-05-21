USE nemne_insurance;

create table if not exists customers
(
    id                        bigint auto_increment
        primary key,
    bank_account              varchar(100) not null,
    birth                     varchar(100) null,
    family_history            mediumtext   not null,
    gender                    tinyint(1)   null,
    health_examination_record mediumtext   not null,
    job                       varchar(255) null,
    name                      varchar(255) not null
)
    collate = utf8mb4_bin;

create table if not exists contracts
(
    id                    bigint auto_increment
        primary key,
    compensation_terms    text         not null,
    fee                   int          not null,
    name                  int          not null,
    rate                  float        null,
    terms_of_subscription text         not null,
    underwriting_report   varchar(255) null,
    customer_id           bigint       null,
    constraint contracts_customers_id_fk
        foreign key (customer_id) references customers (id)
            on update cascade on delete cascade
)
    collate = utf8mb4_bin;

create table if not exists employees
(
    id         bigint auto_increment
        primary key,
    birth      varchar(100) charset utf8mb4                                                              null,
    department enum ('marketing', 'investigating', 'supporting', 'sales', 'development') charset utf8mb4 not null,
    gender     tinyint(1)                                                                                null,
    name       varchar(100) charset utf8mb4                                                              not null
)
    collate = utf8mb4_bin;

create table if not exists claims
(
    id           bigint auto_increment
        primary key,
    compensation int                                                     not null,
    customer_id  bigint                                                  not null,
    date         varchar(100)                                            not null,
    description  text                                                    null,
    employee_id  bigint                                                  not null,
    is_paid      tinyint(1)                                              null,
    location     varchar(255)                                            not null,
    report       varchar(255)                                            null,
    reviewer     bigint                                                  null,
    review       enum ('reviewing', 'reporting', 'accepted', 'rejected') not null,
    constraint claims_customers_id_fk
        foreign key (customer_id) references customers (id)
            on update cascade on delete cascade,
    constraint claims_employees_id_fk
        foreign key (employee_id) references employees (id)
            on update cascade,
    constraint claims_employees_id_fk2
        foreign key (reviewer) references employees (id)
            on update cascade
)
    collate = utf8mb4_bin;

create table if not exists insurance_development
(
    id                     bigint       not null
        primary key,
    product_name           varchar(255) null,
    target                 varchar(255) null,
    compensation_detail    text         null,
    rate                   int          null,
    profit_n_loss_analysis varchar(255) null
);