-- DML 작성
-- department
Insert into DEPARTMENT (ID,NAME) values (seq_department_id.nextval,'경영지원부');
Insert into DEPARTMENT (ID,NAME) values (seq_department_id.nextval,'마케팅부');
Insert into DEPARTMENT (ID,NAME) values (seq_department_id.nextval,'연구개발부');
Insert into DEPARTMENT (ID,NAME) values (seq_department_id.nextval,'영업부');
Insert into DEPARTMENT (ID,NAME) values (seq_department_id.nextval,'서비스부');
Insert into DEPARTMENT (ID,NAME) values (seq_department_id.nextval,'인사부');
Insert into DEPARTMENT (ID,NAME) values (seq_department_id.nextval,'기획부');
select * from department;
-- position
insert into position
values (seq_position_id.nextval, '대표', 1);
insert into position
values (seq_position_id.nextval, '임원', 2);
insert into position
values (seq_position_id.nextval, '수석', 3);
insert into position
values (seq_position_id.nextval, '책임', 4);
insert into position
values (seq_position_id.nextval, '선임', 5);
insert into position
values (seq_position_id.nextval, '전임', 6);
insert into position
values (seq_position_id.nextval, '주임', 7);
insert into position
values (seq_position_id.nextval, '사원', 8);
select * from position;
commit;
 
select * from employee;
select * from authority;

insert into authority values (seq_authority_id.nextval, 502, 'ROLE_EMP');
insert into authority values (seq_authority_id.nextval, 502, 'ROLE_HR');
insert into authority values (seq_authority_id.nextval, 502, 'ROLE_CEO');

-- employee
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '고혜진', 15, 'jindamgom@google.com', '010-1234-6864', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 251, 201);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '김명준', 15, 'myeongjun0911@google.com', '010-1234-6865', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 51, 201);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '김정효', 15, 'kimjeonghyo1@google.com', '010-1234-6866', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 101, 251);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '오승현', 15, 'ehtjsdlfqhd@google.com', '010-1234-6868', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 201, 301);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '유정호', 15, 'ryujeongho@google.com', '010-1234-6870', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 301, 451);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '임초임', 15, 'dlachdla@google.com', '010-1234-6873', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 51, 451);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '정승범', 15, 'seungbeom4382@google.com', '010-1234-6874', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 101, 451);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '정용준', 15, 'tior931108@google.com', '010-1234-6875', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 151, 501);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '정진우', 15, 'jinwoo2361@google.com', '010-1234-6876', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 201, 301);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '한보경', 15, 'bokyunghan@google.com', '010-1234-6878', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 301, 251);
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, REST, EMAIL, PHONE, BIRTHDAY, CREATED_AT, UPDATED_AT, WORK_STATUS, PROFILE_ORIGINAL_NAME, PROFILE_KEY, PROFILE_URL, SEED, CHERRY, POSITION_ID, DEPT_ID)
VALUES (seq_employee_id.nextval, '2144234', '한승훈', 15, 'nlgz@google.com/', '010-1234-6879', NULL, systimestamp, systimestamp, 'WORK', NULL, NULL, NULL, 0, 0, 351, 201);
select * from employee;

-- project
INSERT INTO PROJECT (ID, TITLE, Start_at, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '사내 메신저 개발', Date '2024-01-05', Date '2024-04-25', NULL, 'ING', 502);
INSERT INTO PROJECT (ID, TITLE, Start_at, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '사내카페 앱오더 개발', Date '2024-01-30', Date '2024-04-30', NULL, 'ING', 51);
INSERT INTO PROJECT (ID, TITLE, Start_at, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, 'BEMS 개발 과제', Date '2023-12-01', Date '2024-02-01', NULL, 'DONE', 201);
INSERT INTO PROJECT (ID, TITLE, Start_at, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '채용 홈페이지 UI 개선', Date '2023-12-15', Date '2024-02-01', NULL, 'DONE', 502);
INSERT INTO PROJECT (ID, TITLE, Start_at, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '취미활동 예약결재 플랫폼', Date '2023-12-12', Date '2024-04-12', NULL, 'ING', 502);
INSERT INTO PROJECT (ID, TITLE, Start_at, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, 'AI 소개팅 어플', Date '2023-12-27', Date '2024-06-30', NULL, 'ING', 1);
INSERT INTO PROJECT (ID, TITLE, Start_at, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '빅데이터 분석 도구 개발', Date '2023-11-15', Date '2024-08-31', NULL, 'ING', 101);
INSERT INTO PROJECT (ID, TITLE, Start_at, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '웹사이트 리뉴얼 프로젝트', Date '2024-03-05', Date '2024-09-30', NULL, 'NOT_YET', 301);
INSERT INTO PROJECT (ID, TITLE, Start_at, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '인공지능 기반 고객지원 시스템 구축', Date '2024-03-15', Date '2024-12-31', NULL, 'NOT_YET', 1);
INSERT INTO PROJECT (ID, TITLE, Start_at, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, '머신러닝 기반 추천 시스템 개발', Date '2024-04-20', Date '2025-03-31', NULL, 'NOT_YET', 502);
INSERT INTO PROJECT (ID, TITLE, Start_at, END_AT, UPDATED_AT, STATUS, OWNER_ID) 
VALUES (seq_project_id.nextval, 'AI 기반 영상 분석 프로젝트', Date '2024-02-20', Date '2025-06-30', NULL, 'ING', 502);
select * from project;
select * from employee;

-- prject_employee
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 1, 1, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 1, 51, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 1, 101, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 1, 151, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 1, 201, 'CREATE');

INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 51, 502, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 51, 301, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 51, 401, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 51, 451, 'CREATE');

INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 1, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 51, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 101, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 151, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 401, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 101, 251, 'READ');

INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 151, 301, 'READ');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 151, 351, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 151, 401, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 151, 451, 'CREATE');

INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE)
VALUES (seq_project_employee_id.nextval, 201, 501, 'READ');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 201, 351, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 201, 301, 'CREATE');

INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 251, 401, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 251, 451, 'READ');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 251, 1, 'READ');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 301, 51, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 301, 502, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 351, 502, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 351, 151, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 351, 201, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 351, 351, 'READ');

INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 501, 1, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 501, 51, 'READ');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 501, 101, 'READ');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 501, 151, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 501, 201, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 501, 251, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 501, 301, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 501, 351, 'CREATE');
INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 501, 552, 'CREATE');

INSERT INTO PROJECT_EMPLOYEE (ID, PROJECT_ID, EMP_ID, PROJECT_ROLE) 
VALUES (seq_project_employee_id.nextval, 401, 502, 'CREATE');

-- attend
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
  (seq_attend_id.nextval,TO_TIMESTAMP('2024-02-01 08:59:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-01 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-02 08:59:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-02 18:20:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-03 08:58:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-03 18:01:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-04 09:01:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-04 18:32:00', 'YYYY-MM-DD HH24:MI:SS'), 'LATE', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-05 08:59:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-05 18:01:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-06 08:57:12', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-06 18:06:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-07 08:48:32', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-07 18:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-08 08:22:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-08 18:15:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-09 08:32:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-09 18:17:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-10 09:22:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-10 18:32:00', 'YYYY-MM-DD HH24:MI:SS'), 'LATE', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-11 09:12:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-11 18:19:00', 'YYYY-MM-DD HH24:MI:SS'), 'LATE', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-12 08:09:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-12 18:20:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-13 08:59:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-13 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-14 08:57:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-14 18:03:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-15 09:04:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-15 18:05:00', 'YYYY-MM-DD HH24:MI:SS'), 'LATE', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-16 08:59:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-16 18:06:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-17 09:32:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-17 18:32:00', 'YYYY-MM-DD HH24:MI:SS'), 'LATE', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-18 08:59:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-18 18:42:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-19 08:52:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-19 18:52:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-20 08:54:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-20 18:22:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-21 08:58:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-21 18:13:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-22 08:22:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-22 18:14:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);
INSERT INTO attend (id, start_at, end_at, state, employee_id) VALUES
(seq_attend_id.nextval,TO_TIMESTAMP('2024-02-23 08:32:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-23 18:02:00', 'YYYY-MM-DD HH24:MI:SS'), 'WORK', 502);

-- dayoff
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2024-02-10', 'YYYY-MM-DD'), TO_DATE('2024-02-10', 'YYYY-MM-DD'), 1, 502, '결혼식 참석');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2024-03-10', 'YYYY-MM-DD'), TO_DATE('2024-03-10', 'YYYY-MM-DD'), 1, 502, '몸살 휴가');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2024-04-10', 'YYYY-MM-DD'), TO_DATE('2024-04-12', 'YYYY-MM-DD'), 3, 502, '가족 여행');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2024-09-10', 'YYYY-MM-DD'), TO_DATE('2024-09-12', 'YYYY-MM-DD'), 3, 502, '해외 여행');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2024-10-10', 'YYYY-MM-DD'), TO_DATE('2024-10-12', 'YYYY-MM-DD'), 3, 502, '해외 여행');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2024-11-12', 'YYYY-MM-DD'), TO_DATE('2024-11-12', 'YYYY-MM-DD'), 1, 502, '개인 사유');

INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2023-02-10', 'YYYY-MM-DD'), TO_DATE('2023-02-10', 'YYYY-MM-DD'), 1, 502, '결혼식 참석');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2023-03-10', 'YYYY-MM-DD'), TO_DATE('2023-03-10', 'YYYY-MM-DD'), 1, 502, '몸살 휴가');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2023-04-10', 'YYYY-MM-DD'), TO_DATE('2023-04-12', 'YYYY-MM-DD'), 3, 502, '가족 여행');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2023-09-10', 'YYYY-MM-DD'), TO_DATE('2023-09-12', 'YYYY-MM-DD'), 3, 502, '해외 여행');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2023-10-10', 'YYYY-MM-DD'), TO_DATE('2023-10-12', 'YYYY-MM-DD'), 3, 502, '해외 여행');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2023-11-12', 'YYYY-MM-DD'), TO_DATE('2023-11-12', 'YYYY-MM-DD'), 1, 502, '개인 사유');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2023-12-12', 'YYYY-MM-DD'), TO_DATE('2023-12-12', 'YYYY-MM-DD'), 1, 502, '개인 사유');

INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2022-02-10', 'YYYY-MM-DD'), TO_DATE('2022-02-10', 'YYYY-MM-DD'), 1, 502, '개인 사유');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2022-03-10', 'YYYY-MM-DD'), TO_DATE('2022-03-10', 'YYYY-MM-DD'), 1, 502, '해외 여행');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2022-04-10', 'YYYY-MM-DD'), TO_DATE('2022-04-12', 'YYYY-MM-DD'), 3, 502, '몸살 감기');


INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2021-04-10', 'YYYY-MM-DD'), TO_DATE('2021-04-12', 'YYYY-MM-DD'), 2, 502, '가족 여행');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2021-09-10', 'YYYY-MM-DD'), TO_DATE('2021-09-12', 'YYYY-MM-DD'), 2, 502, '해외 여행');
INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2021-10-10', 'YYYY-MM-DD'), TO_DATE('2021-10-12', 'YYYY-MM-DD'), 3, 502, '해외 여행');


INSERT INTO dayoff (id, type, start_at, end_at, count, employee_id, content)
VALUES (seq_dayoff_id.NEXTVAL, '연차', TO_DATE('2020-02-10', 'YYYY-MM-DD'), TO_DATE('2020-02-10', 'YYYY-MM-DD'), 1, 502, '결혼식 참석');


-- schedule_category
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'Red', 'Meeting', 51);
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'Blue', 'Workshop', 101);
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'Green', 'Training', 201);
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'Yellow', 'Presentation', 251);
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'Purple', 'Project', 801);
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'Red', 'Meeting', 902);
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'blue', 'test1', 902);
insert into schedule_category (id, color, name, emp_id) values 
(seq_schedule_category_id.nextval, 'green', 'tes2', 902);



--schedule
insert into schedule (id, title, content, start_time, end_time, emp_id, schedule_category_id) values 
(seq_schedule_id.nextval, 'Team Meeting', 'Discuss project updates', timestamp '2024-02-07 09:00:00', timestamp '2024-02-07 10:00:00', 51, 1);

insert into schedule (id, title, content, start_time, end_time, emp_id, schedule_category_id) values 
(seq_schedule_id.nextval, 'Training Session', 'New technology training', timestamp '2024-02-07 14:00:00', timestamp '2024-02-07 16:00:00', 101, 201);

insert into schedule (id, title, content, start_time, end_time, emp_id, schedule_category_id) values 
(seq_schedule_id.nextval, 'Project Kickoff', 'Start of a new project', timestamp '2024-02-08 10:00:00', timestamp '2024-02-08 13:00:00', 51, 1);

insert into schedule (id, title, content, start_time, end_time, emp_id, schedule_category_id) values 
(seq_schedule_id.nextval, 'Client Presentation', 'Presenting to a new client', timestamp '2024-02-09 15:00:00', timestamp '2024-02-09 16:00:00', 251, 151);

insert into schedule (id, title, content, start_time, end_time, emp_id, schedule_category_id) values 
(seq_schedule_id.nextval, 'Team Workshop', 'Brainstorming session', timestamp '2024-02-10 11:00:00', timestamp '2024-02-10 13:00:00', 801, 201);


--schedule_join_member
insert into schedule_join_member (id, schedule_id, emp_id) values 
(seq_schedule_join_member_id.nextval, 101, 51);
insert into schedule_join_member (id, schedule_id, emp_id) values 
(seq_schedule_join_member_id.nextval, 101, 101);
insert into schedule_join_member (id, schedule_id, emp_id) values 
(seq_schedule_join_member_id.nextval, 101, 201);
insert into schedule_join_member (id, schedule_id, emp_id) values 
(seq_schedule_join_member_id.nextval, 151, 251);
insert into schedule_join_member (id, schedule_id, emp_id) values 
(seq_schedule_join_member_id.nextval, 151, 801);

-- authority


-- 재준
-- chat_log
insert into chat_log values (seq_chat_log_id.nextval, '잘되나요?', default, 902, 2152);
insert into chat_log values (seq_chat_log_id.nextval, '잘됩니다', default, 852, 2152);
insert into chat_log values (seq_chat_log_id.nextval, '아마..?', default, 852, 2152);
insert into chat_log values (seq_chat_log_id.nextval, '제발~~~~~~~', default, 902, 2152);

-- resource
insert into tb_resource values (seq_tb_resource_id.nextval, '회의실 A', '호산빌딩 2층', '테이블 7개/의자 14개/스크린 1개(47인치)/무선 마이크 2개', 'Room');
insert into tb_resource values (seq_tb_resource_id.nextval, '회의실 B', '호산빌딩 3층', '테이블 5개/의자 10개/스크린 1개(38인치)/무선 마이크 2개', 'Room');
insert into tb_resource values (seq_tb_resource_id.nextval, '회의실 C', '호산빌딩 4층', '테이블 10개/의자 20개/스크린 1개(60인치)/무선 마이크 2개', 'Room');

insert into tb_resource values (seq_tb_resource_id.nextval, '맥북 에어', '인사과 이민정 사원에게 대여 가능', '13.3인치/CPU : M1/RAM : 8GB/SSD : 256GB', 'Notebook');
insert into tb_resource values (seq_tb_resource_id.nextval, '갤럭시북4 프로', '인사과 이민정 사원에게 대여 가능', '16인치/CPU : Ultra7/RAM : 16GB/SSD : 512GB', 'Notebook');
insert into tb_resource values (seq_tb_resource_id.nextval, '그램 2022', '인사과 이민정 사원에게 대여 가능', '15.6인치/CPU : i5-12세대/RAM : 16GB/SSD : 256GB', 'Notebook');

insert into tb_resource values (seq_tb_resource_id.nextval, '아반떼', 'B2층 A-2', 'LPG/트렁크 공간 좁음', 'Car');
insert into tb_resource values (seq_tb_resource_id.nextval, '소나타', 'B2층 A-1', '가솔린/범퍼 우측 찌그러짐, 조수석 문 긁힘', 'Car');
insert into tb_resource values (seq_tb_resource_id.nextval, '그렌저', 'B2층 A-3', '가솔린/침수차량', 'Car');


-- 민정
INSERT INTO task (ID, NAME, CONTENT, PRIORITY, START_AT, END_AT, STATUS, OWNER_ID, EMP_ID, PROJECT_ID)
VALUES (seq_task_id.nextval, 'Task 1', 'Sample content for Task 1', 1, TIMESTAMP '2024-02-14 08:00:00', TIMESTAMP '2024-02-14 16:00:00', 'INPROGRESS', 551, 151, 5952);
INSERT INTO task (ID, NAME, CONTENT, PRIORITY, START_AT, END_AT, STATUS, OWNER_ID, EMP_ID, PROJECT_ID)
VALUES (seq_task_id.nextval, 'Task 2', 'Sample content for Task 2', 2, TIMESTAMP '2024-02-15 09:00:00', TIMESTAMP '2024-02-15 17:00:00', 'TODO', 201, 351, 5952);
INSERT INTO task (ID, NAME, CONTENT, PRIORITY, START_AT, END_AT, STATUS, OWNER_ID, EMP_ID, PROJECT_ID)
VALUES (seq_task_id.nextval, 'Task 3', 'Sample content for Task 3', 3, TIMESTAMP '2024-02-16 10:00:00', TIMESTAMP '2024-02-16 18:00:00', 'DONE', 902, 401, 5952);
INSERT INTO task (ID, NAME, CONTENT, PRIORITY, START_AT, END_AT, STATUS, OWNER_ID, EMP_ID, PROJECT_ID)
VALUES (seq_task_id.nextval, 'Task 4', 'Sample content for Task 4', 4, TIMESTAMP '2024-02-17 11:00:00', TIMESTAMP '2024-02-17 19:00:00', 'INPROGRESS', 101, 801, 5952);
INSERT INTO task (ID, NAME, CONTENT, PRIORITY, START_AT, END_AT, STATUS, OWNER_ID, EMP_ID, PROJECT_ID)
VALUES (seq_task_id.nextval, 'Task 5', 'Sample content for Task 5', 2, TIMESTAMP '2024-02-18 12:00:00', TIMESTAMP '2024-02-18 20:00:00', 'TODO', 902, 651, 5952);
INSERT INTO task (ID, NAME, CONTENT, PRIORITY, START_AT, END_AT, STATUS, OWNER_ID, EMP_ID, PROJECT_ID)
VALUES (seq_task_id.nextval, 'Task 6', 'Sample content for Task 6', 1, TIMESTAMP '2024-02-14 08:00:00', TIMESTAMP '2024-02-14 16:00:00', 'INPROGRESS', 551, 151, 5952);
INSERT INTO task (ID, NAME, CONTENT, PRIORITY, START_AT, END_AT, STATUS, OWNER_ID, EMP_ID, PROJECT_ID)
VALUES (seq_task_id.nextval, 'Task 7', 'Sample content for Task 7', 2, TIMESTAMP '2024-02-15 09:00:00', TIMESTAMP '2024-02-15 17:00:00', 'TODO', 201, 351, 5952);
INSERT INTO task (ID, NAME, CONTENT, PRIORITY, START_AT, END_AT, STATUS, OWNER_ID, EMP_ID, PROJECT_ID)
VALUES (seq_task_id.nextval, 'Task 8', 'Sample content for Task 8', 3, TIMESTAMP '2024-02-16 10:00:00', TIMESTAMP '2024-02-16 18:00:00', 'DONE', 902, 401, 5952);
INSERT INTO task (ID, NAME, CONTENT, PRIORITY, START_AT, END_AT, STATUS, OWNER_ID, EMP_ID, PROJECT_ID)
VALUES (seq_task_id.nextval, 'Task 9', 'Sample content for Task 9', 4, TIMESTAMP '2024-02-17 11:00:00', TIMESTAMP '2024-02-17 19:00:00', 'INPROGRESS', 101, 902, 5952);
INSERT INTO task (ID, NAME, CONTENT, PRIORITY, START_AT, END_AT, STATUS, OWNER_ID, EMP_ID, PROJECT_ID)
VALUES (seq_task_id.nextval, 'Task 10', 'Sample content for Task 10', 2, TIMESTAMP '2024-02-18 12:00:00', TIMESTAMP '2024-02-18 20:00:00', 'TODO', 101, 902, 5952);

INSERT INTO ISSUE (ID, NAME, CONTENT, PRIORITY, STATUS, OWNER_ID, EMP_ID, PROJECT_ID, TASK_ID)
VALUES (seq_issue_id.nextval, 'Issue 1', 'Sample content for Issue 1', 2, 'OPEN', 651, 551, 5952, 1);
INSERT INTO ISSUE (ID, NAME, CONTENT, PRIORITY, STATUS, OWNER_ID, EMP_ID, PROJECT_ID, TASK_ID)
VALUES (seq_issue_id.nextval, 'Issue 2', 'Sample content for Issue 2', 2, 'CLOSED', 451, 551, 5952, 51);
INSERT INTO ISSUE (ID, NAME, CONTENT, PRIORITY, STATUS, OWNER_ID, EMP_ID, PROJECT_ID, TASK_ID)
VALUES (seq_issue_id.nextval, 'Issue 3', 'Sample content for Issue 3', 4, 'INPROGRESS', 351, 401, 5952, 101);
INSERT INTO ISSUE (ID, NAME, CONTENT, PRIORITY, STATUS, OWNER_ID, EMP_ID, PROJECT_ID, TASK_ID)
VALUES (seq_issue_id.nextval, 'Issue 4', 'Sample content for Issue 4', 1, 'OPEN', 251, 401, 5952, null);
INSERT INTO ISSUE (ID, NAME, CONTENT, PRIORITY, STATUS, OWNER_ID, EMP_ID, PROJECT_ID, TASK_ID)
VALUES (seq_issue_id.nextval, 'Issue 5', 'Sample content for Issue 5', 2, 'CLOSED', 201, 201, 5952, null);
INSERT INTO ISSUE (ID, NAME, CONTENT, PRIORITY, STATUS, OWNER_ID, EMP_ID, PROJECT_ID, TASK_ID)
VALUES (seq_issue_id.nextval, 'Issue 6', 'Sample content for Issue 6', 3, 'INPROGRESS', 101, 201, 5952, 151);
INSERT INTO ISSUE (ID, NAME, CONTENT, PRIORITY, STATUS, OWNER_ID, EMP_ID, PROJECT_ID, TASK_ID)
VALUES (seq_issue_id.nextval, 'Issue 7', 'Sample content for Issue 7', 1, 'OPEN', 201, 151, 5952, null);
INSERT INTO ISSUE (ID, NAME, CONTENT, PRIORITY, STATUS, OWNER_ID, EMP_ID, PROJECT_ID, TASK_ID)
VALUES (seq_issue_id.nextval, 'Issue 8', 'Sample content for Issue 8', 4, 'CLOSED', 51, 351, 5952, 401);
INSERT INTO ISSUE (ID, NAME, CONTENT, PRIORITY, STATUS, OWNER_ID, EMP_ID, PROJECT_ID, TASK_ID)
VALUES (seq_issue_id.nextval, 'Issue 9', 'Sample content for Issue 9', 3, 'INPROGRESS', 401, 651, 5952, null);
INSERT INTO ISSUE (ID, NAME, CONTENT, PRIORITY, STATUS, OWNER_ID, EMP_ID, PROJECT_ID, TASK_ID)
VALUES (seq_issue_id.nextval, 'Issue 10', 'Sample content for Issue 10', 1, 'OPEN', 101, 451, 5952, null);

