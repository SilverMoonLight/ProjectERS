CREATE TABLE ers_user_roles (
ur_id NUMBER(*,0),
ur_role VARCHAR2(40),
PRIMARY KEY (ur_id)
);

INSERT INTO ERS_USER_ROLES VALUES(1, 'EMPLOYEE');
INSERT INTO ERS_USER_ROLES VALUES(2, 'MANAGER');

CREATE TABLE ers_users (
u_id NUMBER(*,0),
u_username VARCHAR2(40) UNIQUE NOT NULL,
u_password VARCHAR2(40) NOT NULL,
u_firstname VARCHAR2(30),
u_lastname VARCHAR2(30),
u_email VARCHAR2(100) UNIQUE,
ur_id NUMBER(*,0) NOT NULL,
PRIMARY KEY (u_id),
FOREIGN KEY (ur_id) REFERENCES ers_user_roles(ur_id)
);

INSERT INTO ERS_USERS(u_username, U_PASSWORD, u_firstname, u_lastname, u_email, ur_id) VALUES('jonnM', '0258api', 'Jon', 'Mora','jam072093@gmail.com',2);
INSERT INTO ERS_USERS(u_username, U_PASSWORD, u_firstname, u_lastname, u_email, ur_id) VALUES('joeSmash', '0258smash', 'Joe', 'Smith','j7@gmail.com',1);

CREATE TABLE ers_reimbursement_type (
rt_id NUMBER(*,0),
rt_type VARCHAR2(30) NOT NULL,
PRIMARY KEY (rt_id)  
);

INSERT INTO ERS_REIMBURSEMENT_TYPE VALUES(1,'TRANSPORTATION');
INSERT INTO ERS_REIMBURSEMENT_TYPE VALUES(2,'HOTEL');
INSERT INTO ERS_REIMBURSEMENT_TYPE VALUES(3,'MISCELLANEOUS');

CREATE TABLE ers_reimbursement_status (
rs_id NUMBER(*,0),
rs_status VARCHAR2(30) NOT NULL,
PRIMARY KEY (rs_id)  
);

INSERT INTO ERS_REIMBURSEMENT_STATUS VALUES(1, 'PENDING');
INSERT INTO ERS_REIMBURSEMENT_STATUS VALUES(2, 'APPROVED');
INSERT INTO ERS_REIMBURSEMENT_STATUS VALUES(3, 'DENIED');

CREATE TABLE ers_reimbursements (
r_id NUMBER(*,0),
r_amount NUMBER(22,2) NOT NULL,
r_description VARCHAR2(100),
r_receipt BLOB,
r_submitted TIMESTAMP NOT NULL,
r_resolved TIMESTAMP,
u_id_author NUMBER(*,0) NOT NULL,
u_id_resolver NUMBER(*,0),
rt_type NUMBER(*,0) NOT NULL,
rt_status NUMBER(*,0) NOT NULL,
PRIMARY KEY (r_id),
FOREIGN KEY (u_id_author) REFERENCES ers_users(u_id),
FOREIGN KEY (u_id_resolver) REFERENCES ers_users(u_id),
FOREIGN KEY (rt_type) REFERENCES ers_reimbursement_type(rt_id),
FOREIGN KEY (rt_status) REFERENCES ers_reimbursement_status(rs_id)
);

CREATE SEQUENCE eu_seq
 START WITH 1
 INCREMENT BY 1;

CREATE SEQUENCE er_seq
 START WITH 1
 INCREMENT BY 1;
 
CREATE OR REPLACE TRIGGER eu_seq_trigger
 BEFORE INSERT ON ers_users
 FOR EACH ROW 
 BEGIN
  SELECT eu_seq.nextval INTO :new.u_id FROM DUAL;
END;

CREATE OR REPLACE TRIGGER er_seq_trigger
 BEFORE INSERT ON ers_reimbursements
 FOR EACH ROW 
 BEGIN
  SELECT er_seq.nextval INTO :new.r_id FROM DUAL;
END;

commit;