-- begin EVENTS_DEPARTMENT
alter table EVENTS_DEPARTMENT add constraint FK_EVENTS_DEPARTMENT_CHIEF12 foreign key (CHIEF_ID) references EVENTS_STUDENT(ID)^
create index IDX_EVENTS_DEPARTMENT_CHIEF12 on EVENTS_DEPARTMENT (CHIEF_ID)^
-- end EVENTS_DEPARTMENT

-- begin EVENTS_STUDENT
alter table EVENTS_STUDENT add constraint FK_EVENTS_STUDENT_DEPARTMENT foreign key (DEPARTMENT_ID) references EVENTS_DEPARTMENT(ID)^
alter table EVENTS_STUDENT add constraint FK_EVENTS_STUDENT_USER foreign key (USER_ID) references SEC_USER(ID)^
create index IDX_EVENTS_STUDENT_DEPARTMENT on EVENTS_STUDENT (DEPARTMENT_ID)^
create index IDX_EVENTS_STUDENT_USER on EVENTS_STUDENT (USER_ID)^
-- end EVENTS_STUDENT
-- begin EVENTS_EVENT
alter table EVENTS_EVENT add constraint FK_EVENTS_EVENT_CHIEF foreign key (CHIEF_ID) references EVENTS_STUDENT(ID)^
create index IDX_EVENTS_EVENT_CHIEF on EVENTS_EVENT (CHIEF_ID)^
-- end EVENTS_EVENT
-- begin EVENTS_TASK
alter table EVENTS_TASK add constraint FK_EVENTS_TASK_EVENT foreign key (EVENT_ID) references EVENTS_EVENT(ID)^
create index IDX_EVENTS_TASK_EVENT on EVENTS_TASK (EVENT_ID)^
-- end EVENTS_TASK
-- begin EVENTS_TASK_STUDENT_LINK
alter table EVENTS_TASK_STUDENT_LINK add constraint FK_ETSL_TASK foreign key (TASK_ID) references EVENTS_TASK(ID)^
alter table EVENTS_TASK_STUDENT_LINK add constraint FK_ETSL_STUDENT foreign key (STUDENT_ID) references EVENTS_STUDENT(ID)^
-- end EVENTS_TASK_STUDENT_LINK
-- begin EVENTS_PARTICIPANT
alter table EVENTS_PARTICIPANT add constraint FK_EVENTS_PARTICIPANT_EVENT foreign key (EVENT_ID) references EVENTS_EVENT(ID)^
alter table EVENTS_PARTICIPANT add constraint FK_EVENTS_PARTICIPANT_STUDENT foreign key (STUDENT_ID) references EVENTS_STUDENT(ID)^
create index IDX_EVENTS_PARTICIPANT_EVENT on EVENTS_PARTICIPANT (EVENT_ID)^
create index IDX_EVENTS_PARTICIPANT_STUDENT on EVENTS_PARTICIPANT (STUDENT_ID)^
-- end EVENTS_PARTICIPANT
