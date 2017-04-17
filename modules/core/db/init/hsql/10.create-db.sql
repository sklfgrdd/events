-- begin EVENTS_STUDENT
create table EVENTS_STUDENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    UNIVERSITY_GROUP varchar(10),
    PHONE varchar(11),
    BIRTHDAY date,
    POST varchar(200),
    DEPARTMENT_ID varchar(36),
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    MIDDLE_NAME varchar(255),
    USER_ID varchar(36),
    --
    primary key (ID)
)^
-- end EVENTS_STUDENT
-- begin EVENTS_DEPARTMENT
create table EVENTS_DEPARTMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(200),
    CHIEF_ID varchar(36),
    --
    primary key (ID)
)^
-- end EVENTS_DEPARTMENT
-- begin EVENTS_EVENT
create table EVENTS_EVENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    DESCRIPTION longvarchar,
    START_DATE timestamp,
    END_DATE timestamp,
    CHIEF_ID varchar(36),
    LOCATION_ID varchar(36),
    CABINET varchar(50),
    --
    primary key (ID)
)^
-- end EVENTS_EVENT
-- begin EVENTS_TASK
create table EVENTS_TASK (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    DESCRIPTION longvarchar,
    DEADLINE date,
    EVENT_ID varchar(36),
    --
    primary key (ID)
)^
-- end EVENTS_TASK
-- begin EVENTS_LOCATION
create table EVENTS_LOCATION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ADDRESS varchar(300),
    PHONE varchar(20),
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end EVENTS_LOCATION
-- begin EVENTS_PARTICIPANT
create table EVENTS_PARTICIPANT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ROLE varchar(150),
    EVENT_ID varchar(36),
    STUDENT_ID varchar(36),
    --
    primary key (ID)
)^
-- end EVENTS_PARTICIPANT
-- begin EVENTS_LOGISTICS
create table EVENTS_LOGISTICS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    AVAILABLE boolean,
    CHIEF varchar(255),
    EVENT_ID varchar(36),
    --
    primary key (ID)
)^
-- end EVENTS_LOGISTICS
-- begin EVENTS_TASK_STUDENT_LINK
create table EVENTS_TASK_STUDENT_LINK (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    STUDENT_ID varchar(36),
    TASK varchar(36),
    --
    primary key (ID)
)^
-- end EVENTS_TASK_STUDENT_LINK
-- begin SEC_USER
alter table SEC_USER add column DTYPE varchar(100) ^
update SEC_USER set DTYPE = 'sec$User' where DTYPE is null ^
-- end SEC_USER
