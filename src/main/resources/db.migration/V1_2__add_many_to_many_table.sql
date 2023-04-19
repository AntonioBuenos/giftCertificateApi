create table certificates.l_certificate_tag
(
    id        bigserial
        constraint l_certificate_tag_pk
            primary key,
    certificate_id     bigint not null
        constraint l_certificate_tag_certificate_id_fk
            references certificates.gift_certificate (id)
            on update cascade on delete cascade,
    guitar_id bigint      not null
        constraint l_certificate_tag_tag_id_fk
            references certificates.tag
            on update cascade on delete cascade
);

alter table certificates.l_certificate_tag
    owner to postgres;

create unique index l_certificate_tag_id_uindex
    on certificates.l_certificate_tag (id);
