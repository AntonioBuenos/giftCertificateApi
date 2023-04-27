create table if not exists gift_certificate
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

create unique index if not exists gift_certificate_id_uindex
    on gift_certificate (id);

create table if not exists tag
(
    id   bigserial
        constraint tag_pk
            primary key,
    name varchar(50) not null
);

alter table tag
    owner to postgres;

create unique index if not exists tag_id_uindex
    on tag (id);

create unique index if not exists tag_name_uindex
    on tag (name);

create table if not exists certificates.l_certificate_tag
(
    id        bigserial
        constraint l_certificate_tag_pk
            primary key,
    certificate_id     bigint not null
        constraint l_certificate_tag_certificate_id_fk
            references certificates.gift_certificate (id)
            on update cascade on delete cascade,
    tag_id bigint      not null
        constraint l_certificate_tag_tag_id_fk
            references certificates.tag
            on update cascade on delete cascade
);

alter table certificates.l_certificate_tag
    owner to postgres;

create unique index if not exists l_certificate_tag_id_uindex
    on certificates.l_certificate_tag (id);
