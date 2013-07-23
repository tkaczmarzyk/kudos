# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table kudos (
  id                        bigint not null,
  reason                    varchar(255),
  details                   varchar(255),
  date                      timestamp,
  target_id                 bigint,
  constraint pk_kudos primary key (id))
;

create table person (
  id                        bigint not null,
  name                      varchar(255),
  small_photo_url           varchar(255),
  big_photo_url             varchar(255),
  constraint pk_person primary key (id))
;

create sequence kudos_seq;

create sequence person_seq;

alter table kudos add constraint fk_kudos_target_1 foreign key (target_id) references person (id) on delete restrict on update restrict;
create index ix_kudos_target_1 on kudos (target_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists kudos;

drop table if exists person;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists kudos_seq;

drop sequence if exists person_seq;

