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
-- begin EVENTS_EVENT
create table EVENTS_EVENT (
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
    LOCATION varchar(255),
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
    DEADLINE timestamp,
    EVENT_ID uuid,
    --
    primary key (ID)
)^
-- end EVENTS_TASK
-- begin EVENTS_TASK_STUDENT_LINK
create table EVENTS_TASK_STUDENT_LINK (
    TASK_ID uuid,
    STUDENT_ID uuid,
    primary key (TASK_ID, STUDENT_ID)
)^
-- end EVENTS_TASK_STUDENT_LINK
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
