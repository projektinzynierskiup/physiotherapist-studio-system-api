create table if not exists physioterapist.restart_paassword(
    id serial not null PRIMARY KEY,
    uuid varchar(64) not null,
    users_id integer not null,
    start_time timestamp without time zone,
    end_time timestamp without time zone
)