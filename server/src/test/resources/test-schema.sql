-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS technician, model, department, device, users;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE model (
    model_id serial NOT NULL,
    model_name varchar(200) NOT NULL,
    manufacturer_name varchar(200) NOT NULL,
    maintenance_schedule varchar(200) NULL,
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
	assigned_technician int NOT NULL,
    CONSTRAINT PK_department PRIMARY KEY(department_id),
    CONSTRAINT FK_assigned_technician FOREIGN KEY(assigned_technician) REFERENCES technician(technician_id)
);

CREATE TABLE device (
    device_id serial NOT NULL,
    serial_number varchar(50) NOT NULL,
	model_id int NOT NULL,
	owning_department int NOT NULL,
	install_date date NULL,
	CONSTRAINT PK_device PRIMARY KEY(device_id),
	CONSTRAINT FK_equipment_model FOREIGN KEY(model_id) REFERENCES model(model_id),
	CONSTRAINT FK_owning_department FOREIGN KEY(owning_department) REFERENCES department(department_id)
);

COMMIT TRANSACTION;

