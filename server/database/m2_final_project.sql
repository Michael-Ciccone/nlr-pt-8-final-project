-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS device, model, department, technician, users CASCADE;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

CREATE TABLE model (
    model_id serial NOT NULL,
    model_name varchar(200) NOT NULL,
    manufacturer_name varchar(200) NOT NULL,
    maintenance_schedule varchar(200) NULL,
    model_description varchar(200) NULL,
	CONSTRAINT PK_model PRIMARY KEY(model_id)
);


CREATE TABLE technician (
	technician_id serial NOT NULL,
	full_name varchar(200) NOT NULL,
    email_address varchar(200) NULL,
	CONSTRAINT PK_technician PRIMARY KEY(technician_id)
);

CREATE TABLE department (
    department_id serial NOT NULL,
    department_name varchar(200) NOT NULL,
	base_maintenance_month int NULL,
	assigned_technician varchar(200) NOT NULL,
    CONSTRAINT PK_department PRIMARY KEY(department_id)
);

CREATE TABLE device (
    device_id serial NOT NULL,
    serial_number varchar(50) NOT NULL,
	model_id int NOT NULL,
	owning_department int NOT NULL,
	install_date date NULL,
	picture varchar(200) NULL,
	CONSTRAINT PK_device PRIMARY KEY(device_id),
	CONSTRAINT FK_equipment_model FOREIGN KEY(model_id) REFERENCES model(model_id),
	CONSTRAINT FK_owning_department FOREIGN KEY(owning_department) REFERENCES department(department_id)
);
--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************
insert into model (model_name, manufacturer_name, maintenance_schedule, model_description) values ('Spectra', 'Nokia', 'biannual', 'Ventilator');
insert into model (model_name, manufacturer_name, maintenance_schedule, model_description) values ('Corvette', 'Palm', 'quarterly', 'Infusion pump');
insert into model (model_name, manufacturer_name, maintenance_schedule, model_description) values ('Impala', 'verykool', 'quarterly', 'X-ray machine');
insert into model (model_name, manufacturer_name, maintenance_schedule, model_description) values ('Yaris', 'Sony', 'monthly', 'Vital signs monitor');
insert into model (model_name, manufacturer_name, maintenance_schedule, model_description) values ('LeMans', 'Huawei', 'biannual', 'Anesthesia machine');
insert into model (model_name, manufacturer_name, maintenance_schedule, model_description) values ('Lynx', 'Samsung', 'annual', 'Ventilator');
insert into model (model_name, manufacturer_name, maintenance_schedule, model_description) values ('New Beetle', 'Nokia', 'quarterly', 'Infusion pump');
insert into model (model_name, manufacturer_name, maintenance_schedule, model_description) values ('B-Series Plus', 'LG', 'annual', 'X-ray machine');
insert into model (model_name, manufacturer_name, maintenance_schedule, model_description) values ('Suburban 2500', 'Nokia', 'monthly', 'Vital signs monitor');
insert into model (model_name, manufacturer_name, maintenance_schedule, model_description) values ('Legacy', 'Sony', 'monthly', 'Anesthesia machine');


insert into technician (full_name, email_address) values ('Hulda Dodimead', 'hdodimead0@jalbum.net');
insert into technician (full_name, email_address) values ('Felicity Ivashechkin', 'fivashechkin1@walmart.com');
insert into technician (full_name, email_address) values ('Ame Kearney', 'akearney2@networksolutions.com');
insert into technician (full_name, email_address) values ('Jessy MacConneely', 'jmacconneely3@miitbeian.gov.cn');
insert into technician (full_name, email_address) values ('Sherlocke Gooly', 'sgooly4@mail.ru');
insert into technician (full_name, email_address) values ('Adriane Conws', 'aconws5@symantec.com');
insert into technician (full_name, email_address) values ('Saree Kerne', 'skerne6@lulu.com');
insert into technician (full_name, email_address) values ('Temple Sizzey', 'tsizzey7@alibaba.com');
insert into technician (full_name, email_address) values ('Tamarra Clemas', 'tclemas8@jigsy.com');
insert into technician (full_name, email_address) values ('Mart Nelmes', 'mnelmes9@digg.com');


insert into department (department_name, base_maintenance_month, assigned_technician) values ('Emergency', 11, 'Hulda Dodimead');
insert into department (department_name, base_maintenance_month, assigned_technician) values ('NICU', 7, 'Felicity Ivashechkin');
insert into department (department_name, base_maintenance_month, assigned_technician) values ('Clinical Research', 3, 'Ame Kearney');
insert into department (department_name, base_maintenance_month, assigned_technician) values ('Urgent Care', 4, 'Jessy MacConneely');
insert into department (department_name, base_maintenance_month, assigned_technician) values ('Orthopedics', 7, 'Sherlocke Gooly');
insert into department (department_name, base_maintenance_month, assigned_technician) values ('Infectious Disease', 9, 'Adriane Conws');
insert into department (department_name, base_maintenance_month, assigned_technician) values ('Radiology', 11, 'Saree Kerne');
insert into department (department_name, base_maintenance_month, assigned_technician) values ('ENT', 5, 'Temple Sizzey');
insert into department (department_name, base_maintenance_month, assigned_technician) values ('Cardiology', 5, 'Tamarra Clemas');
insert into department (department_name, base_maintenance_month, assigned_technician) values ('Sports Medicine', 7, 'Mart Nelmes');

insert into device (serial_number, model_id, owning_department, install_date, picture) values ('7ed2a', 4, 9, '2002-08-05', 'EMBIM50.jpg');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('708d2a1', 3, 9, '2018-11-23', 'Mobile-DR-Series500x500.png');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('3a379ea078efce', 3, 2, '2014-01-21', 'Mobile-DR-Series500x500.png');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('0f620cc45', 5, 2, '2001-06-04', 'A5-with-N17-753x1024-753x1024-1.jpg');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('32751f8b', 1, 8, '2021-01-05', 'medical-artificial-lung-ventilator-GqD1Y89-600.jpg');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('5ae7a3eb1cb5', 9, 7, '2011-04-18', 'EMBIM50.jpg');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('4fc38d8', 6, 10, '2006-10-01', 'medical-artificial-lung-ventilator-GqD1Y89-600.jpg');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('ad7ccffd89699', 10, 10, '2020-06-01', 'A5-with-N17-753x1024-753x1024-1.jpg');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('9a9859e4812', 2, 4, '2009-06-13', 'Medfusion_4000_pump_larger.png');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('57d2893', 1, 1, '2001-06-16', 'medical-artificial-lung-ventilator-GqD1Y89-600.jpg');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('ca3cc6a2bb', 6, 5, '2021-03-20', 'medical-artificial-lung-ventilator-GqD1Y89-600.jpg');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('268564a', 9, 6, '2009-09-14', 'EMBIM50.jpg');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('b7a68da1', 2, 5, '2020-02-15', 'Medfusion_4000_pump_larger.png');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('b0c4491a', 9, 4, '2022-04-28', 'EMBIM50.jpg');
insert into device (serial_number, model_id, owning_department, install_date, picture) values ('33e94', 10, 3, '2007-11-29', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('ae218', 5, 9, '2012-02-10', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('55187', 5, 5, '2018-08-20', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('92caf', 10, 4, '2016-02-26', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('0ba16', 5, 2, '2017-12-19', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('ed2f0', 7, 3, '2019-09-13', 'Medfusion_4000_pump_larger.png'),
('45c87', 9, 2, '2013-10-05', 'EMBIM50.jpg'),
('22392', 2, 10, '2006-09-11', 'Medfusion_4000_pump_larger.png'),
('75d53', 4, 6, '2020-12-12', 'EMBIM50.jpg'),
('3962f', 4, 6, '2001-02-16', 'EMBIM50.jpg'),
('ea33d', 6, 1, '2006-09-03', 'medical-artificial-lung-ventilator-GqD1Y89-600.jpg'),
('3749c', 6, 5, '2004-04-08', 'medical-artificial-lung-ventilator-GqD1Y89-600.jpg'),
('a28a2', 2, 8, '2003-12-04', 'Medfusion_4000_pump_larger.png'),
('2d7d7', 10, 10, '2011-03-03', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('c058e', 3, 2, '2010-08-17', 'Mobile-DR-Series500x500.png'),
('cecee', 8, 6, '2005-01-15', 'Mobile-DR-Series500x500.png'),
('a239d', 5, 9, '2018-04-28', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('1693a', 6, 4, '2024-11-16', 'medical-artificial-lung-ventilator-GqD1Y89-600.jpg'),
('0f780', 10, 5, '2014-05-28', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('58c74', 5, 1, '2001-09-05', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('044f2', 4, 5, '2010-10-28', 'EMBIM50.jpg'),
('e4460', 8, 6, '2011-12-09', 'Mobile-DR-Series500x500.png'),
('be7c2', 7, 9, '2006-02-04', 'Medfusion_4000_pump_larger.png'),
('73cfe', 5, 8, '2003-04-20', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('d7b44', 3, 1, '2016-02-25', 'Mobile-DR-Series500x500.png'),
('cae20', 7, 7, '2007-07-08', 'Medfusion_4000_pump_larger.png'),
('f5c12', 10, 8, '2007-11-29', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('dd5b9', 10, 3, '2009-11-06', 'A5-with-N17-753x1024-753x1024-1.jpg'),
('12ece', 1, 8, '2018-04-10', 'medical-artificial-lung-ventilator-GqD1Y89-600.jpg'),
('f29e5', 8, 8, '2004-06-13', 'Mobile-DR-Series500x500.png'),
('94739', 3, 2, '2021-11-01', 'Mobile-DR-Series500x500.png');


-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');

COMMIT TRANSACTION;
