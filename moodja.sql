DROP TABLE IF EXISTS admin;

SET character_set_client = utf8;

CREATE TABLE admin (
    id int(20) NOT NULL AUTO_INCREMENT,
    username varchar(20) NOT NULL,
    password varchar(50) NOT NULL,
    PRIMARY KEY (id)
) ENGINE  = InnoDB AUTO_INCREMENT = 3 DEFAULT CHARSET  = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

LOCK TABLES admin WRITE;

INSERT INTO admin VALUES (1, 'admin', '12345');

UNLOCK TABLES;

DROP TABLE IF EXISTS document;

SET character_set_client = utf8;
CREATE TABLE document (
    id int(20) NOT NULL AUTO_INCREMENT,
    status varchar(20) DEFAULT NULL,
    manager_id int(20) DEFAULT NULL,
    submit_date date DEFAULT NULL,
    view_date date DEFAULT NULL,
    content varchar(5000) DEFAULT NULL,
    employee_id int(20) NOT NULL,
    title varchar(50) DEFAULT NULL,
    comment varchar(1000) DEFAULT NULL,
    number double DEFAULT NULL,
    summary double DEFAULT NULL,
    PRIMARY KEY (id),
    KEY document_employee__fk(employee_id),
    KEY document_manager__fk (manager_id),
    CONSTRAINT document_manager__fk FOREIGN KEY (manager_id) REFERENCES manager (id),
    CONSTRAINT document_employee_fk FOREIGN KEY (employee_id) REFERENCES employee (id)
) ENGINE  = InnoDB AUTO_INCREMENT = 44 DEFAULT CHARSET = utf8mb4 COLLATE  = utf8mb4_0900_ai_ci;

LOCK TABLES document WRITE;

INSERT INTO document VALUES (8, 'Approved', 1, '2022-01-15', '2022-02-15', 'content', 123, 'inbound-test', 'test', 0 ,0);

UNLOCK TABLES;

DROP TABLE IF EXISTS attachment;

SET character_set_client = utf8;

CREATE TABLE attachment(
    id int(20) NOT NULL AUTO_INCREMENT,
    draft_id int(20) DEFAULT NULL,
    document_id int(20) DEFAULT NULL,
    upload_date date DEFAULT NULL,
    path varchar(200) DEFAULT NULL,
    name varchar(200) DEFAULT NULL,
    PRIMARY KEY (id),
    KEY attachment_document__fk (document_id),
    KEY attachment_draft__fk (draft_id),
    CONSTRAINT attachment_document__fk FOREIGN KEY (document_id) REFERENCES document (id) ON DELETE CASCADE,
    CONSTRAINT attachment_draft__fk FOREIGN KEY (draft_id) REFERENCES draft (id) ON DELETE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 13 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

LOCK TABLES attachment WRITE;

INSERT INTO attachment VALUES (10, NULL, 8, 2022-02-16, '/', 'test.pdf');

UNLOCK TABLES;

DROP TABLE IF EXISTS draft;

SET character_set_client = utf8;

CREATE TABLE draft (
    id int(20) NOT NULL AUTO_INCREMENT,
    content varchar(5000) DEFAULT NULL,
    last_edit date DEFAULT NULL,
    title varchar(50) DEFAULT NULL,
    number double DEFAULT NULL,
    summary double DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB AUTO_INCREMENT = 12 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

LOCK TABLES draft WRITE;

UNLOCK TABLES;

DROP TABLE IF EXISTS manager;

SET character_set_client = utf8;

CREATE TABLE manager (
    id int(20) NOT NULL AUTO_INCREMENT,
    firstname varchar(20) DEFAULT NULL,
    lastname varchar(20) DEFAULT NULL,
    email varchar(100) DEFAULT NULL,
    mobile varchar(20) DEFAULT NULL,
    password varchar(50) DEFAULT NULL,
    admin_id int(20) NOT NULL,
    deleted tinyint(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    KEY manager_admin__fk (admin_id),
    CONSTRAINT manager_admin__fk FOREIGN KEY (admin_id) REFERENCES admin (id)
) ENGINE = InnoDB AUTO_INCREMENT = 9 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

LOCK TABLES manager WRITE;

INSERT INTO manager VALUES (1234, 'eric', 'wang', 'eric.wang@moodja.com', '15700000000', 'ew1234', '1', 0);

UNLOCK TABLES;

DROP TABLE IF EXISTS employee;

SET character_set_client = utf8;

CREATE TABLE employee (
    id int(20) NOT NULL,
    password varchar(50) DEFAULT NULL,
    bankInfo varchar(100) DEFAULT NULL,
    email varchar(50) NOT NULL,
    mobile varchar(50) DEFAULT NULL,
    position varchar(20) DEFAULT NULL,
    firstname varchar(20) DEFAULT NULL,
    lastname varchar(20) DEFAULT NULL,
    department varchar(20) DEFAULT NULL,
    dob date DEFAULT NULL,
    gender varchar(10) DEFAULT NULL,
    nationality varchar(20) DEFAULT NULL,
    startYear varchar(20) DEFAULT NULL,
    draft_id int(20) DEFAULT NULL,
    PRIMARY KEY (id),
    KEY employee_draft__fk (draft_id),
    CONSTRAINT employee_draft__fk FOREIGN KEY (draft_id) REFERENCES draft (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

LOCK TABLES employee WRITE;

INSERT INTO employee VALUES (20, '4321', 'bank', 'test.email@gmail.com', '18500000000', 'warehouse', 'worker1', 'test', 'warehouse', 1995-06-28, 'male', 'Germany', '2020', null);

UNLOCK TABLES;