-- drop schema convertium_assignment;
-- H2 does not support triggers;

-- Convertium Assignment Schema Starts Here

CREATE SCHEMA IF NOT EXISTS "java_blog_exercise";

SET search_path TO "java_blog_exercise";

CREATE TABLE USERS (
  id serial NOT NULL,
  user_name varchar(255) NOT NULL,
  pass_word varchar(255) NOT NULL,
  full_name varchar(255) NOT NULL,
  last_modified TIMESTAMP NOT NULL,
  CONSTRAINT USERS_pk PRIMARY KEY (id)
);

CREATE TABLE ROLES (
  id serial NOT NULL,
  role varchar(255) NOT NULL,
  last_modified TIMESTAMP NOT NULL,
  CONSTRAINT ROLE_pk PRIMARY KEY (id)
);

CREATE TABLE USER_ROLE (
  id serial NOT NULL,
  user_id int4 NOT NULL,
  role_id int4 NOT NULL,
  last_modified TIMESTAMP NOT NULL,
  CONSTRAINT USER_ROLE_pk PRIMARY KEY (id)
);


CREATE TABLE POST (
  id serial NOT NULL,
  user_id int4 NOT NULL,
  title varchar(255) NOT NULL,
  image varchar,
  body varchar(255) NOT NULL,
  status varchar(255),
  last_modified TIMESTAMP NOT NULL,
  publish_date TIMESTAMP,
  CONSTRAINT POST_pk PRIMARY KEY (id)
);

CREATE TABLE PUBLIC_POST (
  id serial NOT NULL,
  title varchar(255) NOT NULL,
  image varchar,
  body varchar(255) NOT NULL,
  author varchar(255),
  last_modified TIMESTAMP NOT NULL,
  CONSTRAINT PUBLIC_POST_pk PRIMARY KEY (id)
);

CREATE TABLE LINK_POST (
  id serial NOT NULL,
  post_id int4 NOT NULL,
  public_post_id int4 NOT NULL,
  last_modified TIMESTAMP NOT NULL,
  CONSTRAINT LINK_POST_pk PRIMARY KEY (id)
);

-- FKs

ALTER TABLE POST ADD CONSTRAINT POST_fk0 FOREIGN KEY (user_id) REFERENCES USERS(id);
--ALTER TABLE PUBLIC_POST ADD CONSTRAINT PUBLIC_POST_fk0 FOREIGN KEY (user_id) REFERENCES USERS(id);
ALTER TABLE USER_ROLE ADD CONSTRAINT USER_ROLE_fk0 FOREIGN KEY (user_id) REFERENCES USERS(id);
ALTER TABLE USER_ROLE ADD CONSTRAINT USER_ROLE_fk1 FOREIGN KEY (role_id) REFERENCES ROLES(id);
ALTER TABLE LINK_POST ADD CONSTRAINT LINK_POST_fk0 FOREIGN KEY (post_id) REFERENCES POST(id);
ALTER TABLE LINK_POST ADD CONSTRAINT LINK_POST_fk1 FOREIGN KEY (public_post_id) REFERENCES PUBLIC_POST(id);


create sequence if not exists user_id_seq;
create sequence if not exists post_id_seq;
create sequence if not exists public_post_id_seq;
create sequence if not exists link_post_id_seq;
create sequence if not exists roles_id_seq