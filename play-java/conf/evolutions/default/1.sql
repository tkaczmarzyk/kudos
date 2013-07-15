# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table kudos (
  id                        bigint not null,
  reason                    varchar(255),
  details                   varchar(255),
  date                      timestamp,
  constraint pk_kudos primary key (id))
;

create table person (
  id                        bigint not null,
  name                      varchar(255),
  small_photo_url           varchar(255),
  big_photo_url             varchar(255),
  constraint pk_person primary key (id))
;


create table kudos_person (
  kudos_id                       bigint not null,
  person_id                      bigint not null,
  constraint pk_kudos_person primary key (kudos_id, person_id))
;
create sequence kudos_seq;

create sequence person_seq;




alter table kudos_person add constraint fk_kudos_person_kudos_01 foreign key (kudos_id) references kudos (id) on delete restrict on update restrict;

alter table kudos_person add constraint fk_kudos_person_person_02 foreign key (person_id) references person (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists kudos;

drop table if exists kudos_person;

drop table if exists person;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists kudos_seq;

drop sequence if exists person_seq;

