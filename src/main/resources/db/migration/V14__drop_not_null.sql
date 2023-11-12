alter table physioterapist.users alter username drop not null;
alter table physioterapist.users alter surname drop not null;
alter table physioterapist.users alter password drop not null;
alter table physioterapist.users alter enabled drop not null;
alter table physioterapist.users add column phone varchar(255);



