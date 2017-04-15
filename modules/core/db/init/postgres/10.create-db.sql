-- begin EVENTS_STUDENT
create table EVENTS_STUDENT (
    ID uuid,
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
    DEPARTMENT_ID uuid,
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    MIDDLE_NAME varchar(255),
    USER_ID uuid,
    --
    primary key (ID)
)^
-- end EVENTS_STUDENT
-- begin EVENTS_DEPARTMENT
create table EVENTS_DEPARTMENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(200),
    CHIEF_ID uuid,
    --
    primary key (ID)
)^
-- end EVENTS_DEPARTMENT
-- begin EVENTS_EVENTcreate table EVENTS_EVENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    DESCRIPTION text,
    START_DATE timestamp,
    END_DATE timestamp,
    CHIEF_ID uuid,
    LOCATION_ID uuid,
    CABINET varchar(50),
    --
    primary key (ID)
)^
-- end EVENTS_EVENT
-- begin EVENTS_TASK
create table EVENTS_TASK (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    DESCRIPTION text,
    DEADLINE date,
    EVENT_ID uuid,
    --
    primary key (ID)
)^
-- end EVENTS_TASK
-- begin EVENTS_LOCATION
create table EVENTS_LOCATION (
    ID uuid,
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
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ROLE varchar(150),
    EVENT_ID uuid,
    STUDENT_ID uuid,
    --
    primary key (ID)
)^
-- end EVENTS_PARTICIPANT
-- begin EVENTS_TASK_STUDENT_LINK
create table EVENTS_TASK_STUDENT_LINK (
    TASK_ID uuid,
    STUDENT_ID uuid,
    primary key (TASK_ID, STUDENT_ID)
)^
-- end EVENTS_TASK_STUDENT_LINK
-- begin SEC_USER
alter table SEC_USER add column STUDENT_ID uuid ^
alter table SEC_USER add column DTYPE varchar(100) ^
update SEC_USER set DTYPE = 'sec$User' where DTYPE is null ^
-- end SEC_USER
-- begin EVENTS_LOGISTICScreate table EVENTS_LOGISTICS (
    ID uuid,
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
    EVENT_ID uuid,
    --
    primary key (ID)
)^
-- end EVENTS_LOGISTICS
