BEGIN TRANSACTION;

-- Users
INSERT INTO users (username, password_hash, role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user3','user3','ROLE_USER');

insert into model (model_name, manufacturer_name, maintenance_schedule) values ('Model 1', 'Nokia', 'biannual');
insert into model (model_name, manufacturer_name, maintenance_schedule) values ('Model 2', 'Palm', 'quarterly');
insert into model (model_name, manufacturer_name, maintenance_schedule) values ('Model 3', 'verykool', 'quarterly');
insert into model (model_name, manufacturer_name, maintenance_schedule) values ('Model 4', 'Sony', 'monthly');

insert into technician (full_name, email_address) values ('Technician 1', 'tech1@yahoo.com');
insert into technician (full_name, email_address) values ('Technician 2', 'tech2@yahoo.com');
insert into technician (full_name, email_address) values ('Technician 3', 'tech3@yahoo.com');
insert into technician (full_name, email_address) values ('Technician 4', 'tech4@yahoo.com');

insert into department (department_name, base_maintenance_month, assigned_technician) values ('Department 1', 1, 1);
insert into department (department_name, base_maintenance_month, assigned_technician) values ('Department 2', 7, 2);
insert into department (department_name, base_maintenance_month, assigned_technician) values ('Department 3', 3, 3);
insert into department (department_name, base_maintenance_month, assigned_technician) values ('Department 4', 4, 4);

insert into device (serial_number, model_id, owning_department, install_date) values ('sn1', 4, 1, '2002-08-05');
insert into device (serial_number, model_id, owning_department, install_date) values ('sn2', 2, 3, '2018-11-23');
insert into device (serial_number, model_id, owning_department, install_date) values ('sn3', 3, 2, '2014-01-21');
insert into device (serial_number, model_id, owning_department, install_date) values ('sn4', 1, 3, '2001-06-04');

COMMIT TRANSACTION;

