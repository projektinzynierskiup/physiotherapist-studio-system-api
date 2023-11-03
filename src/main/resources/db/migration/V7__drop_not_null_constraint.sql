alter table appointment alter massage_id drop not null;
alter table appointment alter user_id drop not null;
alter table appointment drop appointment_type;
alter table appointment drop start_time;
alter table appointment drop end_time;
