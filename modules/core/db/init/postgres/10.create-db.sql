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
