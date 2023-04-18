create table gift_certificate
(
    id               bigserial
        constraint gift_certificate_pk
            primary key,
    name             varchar(50)      not null,
    description      varchar(200)     not null,
    price            double precision not null,
    duration         integer          not null,
    create_date      timestamp(6)     not null,
    last_update_date timestamp(6)
);

alter table gift_certificate
    owner to postgres;

create unique index gift_certificate_id_uindex
    on gift_certificate (id);

create table tag
(
    id   bigserial
        constraint tag_pk
            primary key,
    name varchar(50) not null
);

alter table tag
    owner to postgres;

create unique index tag_id_uindex
    on tag (id);

create unique index tag_name_uindex
    on tag (name);

