CREATE SCHEMA IF NOT EXISTS vending_machine; 
USE vending_machine;

DROP TABLE IF EXISTS sold;
DROP TABLE IF EXISTS restock;
DROP TABLE IF EXISTS cash_load;
DROP TABLE IF EXISTS cash_withdrawal;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS snack;
DROP TABLE IF EXISTS trademark;
DROP TABLE IF EXISTS vending_machine;
DROP TABLE IF EXISTS machine_model;
DROP TABLE IF EXISTS service_staff;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS region;

-- tables

-- Table: cash_load
CREATE TABLE cash_load (
    id int NOT NULL AUTO_INCREMENT,
    vending_machine_id int NOT NULL,
    service_staff_id int NOT NULL,
    amount decimal(10,5) NOT NULL,
    date date NOT NULL,
    CONSTRAINT cash_load_pk PRIMARY KEY (id)
);

-- Table: cash_withdrawal
CREATE TABLE cash_withdrawal (
    id int NOT NULL AUTO_INCREMENT,
    vending_machine_id int NOT NULL,
    service_staff_id int NOT NULL,
    amount decimal(10,5) NOT NULL,
    date date NOT NULL,
    CONSTRAINT cash_withdrawal_pk PRIMARY KEY (id)
);

-- Table: sold
CREATE TABLE sold (
    id int NOT NULL AUTO_INCREMENT,
    menu_vending_machine_id int NOT NULL,
    menu_slot_number int NOT NULL,
    time timestamp NOT NULL,
    CONSTRAINT sold_pk PRIMARY KEY (id)
);

-- Table: restock
CREATE TABLE restock (
    id int NOT NULL AUTO_INCREMENT,
    menu_vending_machine_id int NOT NULL,
    menu_slot_number int NOT NULL,
    quantity int NOT NULL,
    service_staff_id int NOT NULL,
    date date NOT NULL,
    CONSTRAINT restock_pk PRIMARY KEY (id)
);

-- Table: menu
CREATE TABLE menu (
    vending_machine_id int NOT NULL,
    slot_number int NOT NULL,
    snack_id int NOT NULL,
    quantity int NOT NULL,
    price_per_unit decimal(10,5) NOT NULL,
    CONSTRAINT menu_pk PRIMARY KEY (vending_machine_id,slot_number)
);

-- Table: snack
CREATE TABLE snack (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(60) NOT NULL,
    net_weight float NOT NULL,
    trademark_name varchar(50) NOT NULL,
    CONSTRAINT snack_pk PRIMARY KEY (id)
);

-- Table: trademark
CREATE TABLE trademark (
    name varchar(50) NOT NULL,
    CONSTRAINT trademark_pk PRIMARY KEY (name)
);

-- Table: vending_machine
CREATE TABLE vending_machine (
    id int NOT NULL AUTO_INCREMENT,
    machine_model_name varchar(50) NOT NULL,
    gps varchar(50) NOT NULL,
    address_id int NULL,
    contact_number varchar(13) NULL,
    is_out_of_order bool NOT NULL DEFAULT false,
    is_empty bool NOT NULL,
    CONSTRAINT vending_machine_pk PRIMARY KEY (id)
);

-- Table: machine_model
CREATE TABLE machine_model (
    name varchar(50) NOT NULL,
    company varchar(50) NOT NULL,
    CONSTRAINT machine_model_pk PRIMARY KEY (name)
);

-- Table: service_staff
CREATE TABLE service_staff (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    work_experience varchar(10) NULL,
    is_available bool NOT NULL,
    address_id int NOT NULL,
    CONSTRAINT service_staff_pk PRIMARY KEY (id)
);

-- Table: address
CREATE TABLE address (
    id int NOT NULL AUTO_INCREMENT,
    city_id int NULL,
    street varchar(50) NOT NULL,
    house_number varchar(5) NULL,
    appartment_number int NULL,
    CONSTRAINT address_pk PRIMARY KEY (id)
);

-- Table: city
CREATE TABLE city (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    region_id int NULL,
    CONSTRAINT city_pk PRIMARY KEY (id)
);

-- Table: region
CREATE TABLE region (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    CONSTRAINT region_pk PRIMARY KEY (id)
);


-- foreign keys
-- Reference: adress_city (table: address)
ALTER TABLE address ADD CONSTRAINT adress_city FOREIGN KEY adress_city (city_id)
    REFERENCES city (id);

-- Reference: cash_load_service_staff (table: cash_load)
ALTER TABLE cash_load ADD CONSTRAINT cash_load_service_staff FOREIGN KEY cash_load_service_staff (service_staff_id)
    REFERENCES service_staff (id);

-- Reference: cash_load_vending_machine (table: cash_load)
ALTER TABLE cash_load ADD CONSTRAINT cash_load_vending_machine FOREIGN KEY cash_load_vending_machine (vending_machine_id)
    REFERENCES vending_machine (id);

-- Reference: cash_withdrawal_service_staff (table: cash_withdrawal)
ALTER TABLE cash_withdrawal ADD CONSTRAINT cash_withdrawal_service_staff FOREIGN KEY cash_withdrawal_service_staff (service_staff_id)
    REFERENCES service_staff (id);

-- Reference: cash_withdrawal_vending_machine (table: cash_withdrawal)
ALTER TABLE cash_withdrawal ADD CONSTRAINT cash_withdrawal_vending_machine FOREIGN KEY cash_withdrawal_vending_machine (vending_machine_id)
    REFERENCES vending_machine (id);

-- Reference: city_region (table: city)
ALTER TABLE city ADD CONSTRAINT city_region FOREIGN KEY city_region (region_id)
    REFERENCES region (id);

-- Reference: menu_snack (table: menu)
ALTER TABLE menu ADD CONSTRAINT menu_snack FOREIGN KEY menu_snack (snack_id)
    REFERENCES snack (id);

-- Reference: menu_vending_machine (table: menu)
ALTER TABLE menu ADD CONSTRAINT menu_vending_machine FOREIGN KEY menu_vending_machine (vending_machine_id)
    REFERENCES vending_machine (id);

-- Reference: restock_menu (table: restock)
ALTER TABLE restock ADD CONSTRAINT restock_menu FOREIGN KEY restock_menu (menu_vending_machine_id,menu_slot_number)
    REFERENCES menu (vending_machine_id,slot_number);

-- Reference: restock_service_staff (table: restock)
ALTER TABLE restock ADD CONSTRAINT restock_service_staff FOREIGN KEY restock_service_staff (service_staff_id)
    REFERENCES service_staff (id);

-- Reference: service_staff_adress (table: service_staff)
ALTER TABLE service_staff ADD CONSTRAINT service_staff_adress FOREIGN KEY service_staff_adress (address_id)
    REFERENCES address (id);

-- Reference: snack_trademark (table: snack)
ALTER TABLE snack ADD CONSTRAINT snack_trademark FOREIGN KEY snack_trademark (trademark_name)
    REFERENCES trademark (name);

-- Reference: sold_menu (table: sold)
ALTER TABLE sold ADD CONSTRAINT sold_menu FOREIGN KEY sold_menu (menu_vending_machine_id,menu_slot_number)
    REFERENCES menu (vending_machine_id,slot_number);

-- Reference: vending_machine_adress (table: vending_machine)
ALTER TABLE vending_machine ADD CONSTRAINT vending_machine_adress FOREIGN KEY vending_machine_adress (address_id)
    REFERENCES address (id);

-- Reference: vending_machine_machine_model (table: vending_machine)
ALTER TABLE vending_machine ADD CONSTRAINT vending_machine_machine_model FOREIGN KEY vending_machine_machine_model (machine_model_name)
    REFERENCES machine_model (name);



-- Insert values into tables

-- Region --------
INSERT INTO region (name) VALUES 
('Kyiv region'),
('Lviv region'),
('Lutsk region'),
('Vinnytsia region'),
('Odesa region'),
('Luhansk region'),
('Ivano-Frankivsk region'),
('Kharkiv region'),
('Dnipro region'),
('Ternopil region');

-- City --------
INSERT INTO city (name, region_id) VALUES 
('Kyiv',1),
('Lviv',2),
('Lutsk',3),
('Vinnytsia',4),
('Odesa',5),
('Luhansk',6),
('Ivano-Frankivsk',7),
('Kharkiv',8),
('Dnipro',9),
('Ternopil',10);

-- Address --------
INSERT INTO address (city_id, street, house_number, appartment_number) VALUES
('1', 'Pavla Tychyny Ave', '1B', null),
('2', 'Kulparkivska St', '226A', null),
('3', 'Kyivska Square', '11', null),
('4', 'Keletska St', '121', null),
('5', 'Henuez`ka St', '5', null),
('6', 'Pasichna St', '164', null),
('7', 'Het`mana Mazepy St', '168', null),
('8', 'Akademika Pavlova St', '44Б', null),
('9', 'Andriia Fabra St', '7', null),
('10', 'Tekstyl`na St', '28Ч', null),
('2', 'Yaneva St', null, null),
('2', 'Horodotska St', null, null);


-- Service staff --------
INSERT INTO service_staff (name, last_name, work_experience, is_available, address_id) VALUES
('Danylo', 'Kovalchuk', '1 year', 1, '11'),
('Jean Pierre', 'Polnareff', '7 months', 0, '12'),
('Bohdan', 'Derunchyk', '9 months', 0, '11'),
('Olha', 'Rubinchuk', '2 years', 1, '12'),
('Maksym', 'Pasenko', '5 years', 1, '12');


-- Machine model --------
INSERT INTO machine_model (name, company) VALUES
('GPE 25', 'GPE'),
('GPE 30', 'GPE'),
('Palma+ H70', 'Azkoyen'),
('Mistral+ H70', 'Azkoyen'),
('CVM-FD60DPC21.5', 'Qingdao Easy Touch Technology Co.'),
('CVM-FD48WXT', 'Qingdao Easy Touch Technology Co.'),
('AMS 39 Outdoor Snack and Drink Vending Machine', 'GLORY-VM'),
('G-Snack Standard Twelve (XII) Master - SML', 'Vendo Sanden'),
('G-Snack Design Six (VI) Master', 'Vendo Sanden'),
('CVM-FD48DPC7', 'Qingdao Easy Touch Technology Co.');

-- Vending machine --------
INSERT INTO vending_machine (machine_model_name, gps, address_id, contact_number, is_out_of_order, is_empty) VALUES
('GPE 25', "N49°50'38.5434'' E24°1'55.3938''", '1', '0635784668', '1', '0'),
('GPE 30', "N49°50'38.5434'' E24°1'55.3938''", '2', '0635524668', '0', '0'),
('Palma+ H70', "N49°50'38.5434'' E24°1'55.3938''", '3', '0655784668', '0', '1'),
('Mistral+ H70', "N49°50'38.5434'' E24°1'55.3938''", '4', '0895784668', '0', '0'),
('AMS 39 Outdoor Snack and Drink Vending Machine', "N49°50'38.5434'' E24°1'55.3938''", '5', '0635894668', '0', '1'),
('CVM-FD60DPC21.5', "N49°50'38.5434'' E24°1'55.3938''", '6', '0623594668', '0', '0'),
('G-Snack Standard Twelve (XII) Master - SML', "N49°50'38.5434'' E24°1'55.3938''", '7', '0632487668', '1', '1'),
('CVM-FD48WXT', "N49°50'38.5434'' E24°1'55.3938''", '8', '0635111668', '0', '1'),
('CVM-FD48WXT', "N49°50'38.5434'' E24°1'55.3938''", '9', '0635684668', '0', '0'),
('GPE 25', "N49°50'38.5434'' E24°1'55.3938''", '10', '0634856968', '0', '0');


-- Trademark --------
INSERT INTO trademark (name) VALUES
('Mondelez'),
('Mars'),
('Nestlé'),
("McVitie's"),
('Glico');

-- Snack --------
INSERT INTO snack (name, net_weight, trademark_name) VALUES
('Biscuit "Barni" Chocolate', '30', 'Mondelez'),
('Biscuit "Barni" Milk', '30', 'Mondelez'),
('OREO Original', '70', 'Mondelez'),
('OREO Chocolate', '70', 'Mondelez'),
('OREO Double Stuff', '70', 'Mondelez'),
('SKITTLES Original Fruity Candy Single Pack', '62', 'Mars'),
('SKITTLES Wild Berry Candy Single Pack', '62', 'Mars'),
('SKITTLES Brightside Candy Single Pack', '62', 'Mars'),
('SKITTLES Sour Candy Single Pack', '51', 'Mars'),
('SKITTLES Original Fruity Candy Share Size Bag', '133', 'Mars'),
('SKITTLES Wild Berry Candy Share Size Bag', '133', 'Mars'),
('SKITTLES Brightside Candy Share Size Bag', '133', 'Mars'),
('SKITTLES Sour Candy Share Size Bag', '105', 'Mars'),
('CHOCOLATE Cream Covered Biscuit Stics', '70', 'Glico'),
('STRAWBERRY Cream Covered Biscuit Stics', '70', 'Glico'),
('COOKIES & CREAM Cream Covered Biscuit Stics', '70', 'Glico'),
('Chocolate Hobnobs', '262', "McVitie's"),
('SMARTIES Tube of Milk Chocolate', '28', 'Nestlé'),
('SMARTIES Sharing Pouch of Milk Chocolate', '105', 'Nestlé'),
('4 FINGER KITKAT', '42', 'Nestlé'),
('Kit Kat Chunky Popcorn', '42', 'Nestlé');

-- Menu --------
INSERT INTO menu (vending_machine_id, slot_number, snack_id, quantity, price_per_unit) VALUES
('1', '101', '6', '5', '15.5'),
('1', '102', '6', '6', '15.5'),
('1', '103', '7', '7', '15.5'),
('1', '104', '7', '8', '15.5'),
('1', '201', '8', '9', '15.5'),
('1', '202', '8', '0', '15.5'),
('1', '203', '8', '5', '15.5'),
('1', '204', '8', '2', '15.5'),
('1', '301', '9', '4', '20'),
('1', '302', '9', '8', '20'),
('1', '303', '10', '7', '20'),
('1', '304', '10', '9', '20'),
('1', '401', '11', '2', '20'),
('1', '402', '12', '0', '20'),
('1', '403', '13', '5', '20'),
('1', '404', '13', '0', '20');

INSERT INTO menu (vending_machine_id, slot_number, snack_id, quantity, price_per_unit) VALUES
('2', '101', '1', '2', '10'),
('2', '102', '1', '5', '10'),
('2', '103', '2', '0', '10'),
('2', '201', '3', '4', '12'),
('2', '202', '3', '1', '12'),
('2', '203', '3', '8', '12'),
('2', '301', '4', '5', '12'),
('2', '302', '4', '9', '12'),
('2', '303', '4', '2', '12'),
('2', '401', '5', '0', '15'),
('2', '402', '5', '1', '15'),
('2', '403', '5', '0', '15');

INSERT INTO menu (vending_machine_id, slot_number, snack_id, quantity, price_per_unit) VALUES
('4', '101', '20', '5', '17'),
('4', '102', '20', '4', '17'),
('4', '103', '20', '6', '17'),
('4', '104', '20', '0', '17'),
('4', '201', '21', '4', '17.5'),
('4', '202', '21', '1', '17.5'),
('4', '203', '21', '5', '17.5'),
('4', '204', '21', '2', '17.5');

INSERT INTO menu (vending_machine_id, slot_number, snack_id, quantity, price_per_unit) VALUES 
('6', '11', '14', '2', '24'),
('6', '12', '14', '4', '24'),
('6', '13', '14', '2', '24'),
('6', '21', '15', '1', '24'),
('6', '22', '15', '3', '24'),
('6', '23', '15', '1', '24'),
('6', '31', '16', '4', '24'),
('6', '32', '16', '5', '24'),
('6', '33', '16', '1', '24'),
('6', '41', '17', '5', '25'),
('6', '42', '17', '7', '25'),
('6', '43', '17', '6', '25');

INSERT INTO menu (vending_machine_id, slot_number, snack_id, quantity, price_per_unit) VALUES 
('7', '11', '18', '0', '5'),
('7', '12', '18', '0', '5'),
('7', '13', '18', '0', '5'),
('7', '21', '19', '0', '9'),
('7', '22', '19', '0', '9'),
('7', '23', '19', '0', '9'),
('7', '31', '20', '0', '16'),
('7', '32', '20', '0', '16'),
('7', '33', '20', '0', '16'),
('7', '41', '21', '0', '17'),
('7', '42', '21', '0', '17'),
('7', '43', '21', '0', '17');




-- Cash load --------
INSERT INTO cash_load (vending_machine_id, service_staff_id, amount, date) VALUES
('1', '1', '200', '2022-09-01'),
('1', '2', '250', '2022-09-19'),
('2', '3', '100', '2022-09-20'),
('2', '5', '300', '2022-08-30'),
('3', '1', '200', '2022-08-29'),
('5', '4', '250', '2022-09-15'),
('7', '4', '260', '2022-09-14'),
('8', '3', '200', '2022-09-25'),
('8', '3', '150', '2022-07-09'),
('4', '2', '300', '2022-09-12'),
('4', '5', '200', '2022-09-13');

-- Cash withdrawal --------
INSERT INTO cash_withdrawal (vending_machine_id, service_staff_id, amount, date) VALUES 
('1', '1', '500', '2022-09-01'),
('1', '2', '650', '2022-09-19'),
('2', '3', '800', '2022-09-20'),
('2', '5', '400', '2022-08-30'),
('3', '1', '500', '2022-08-29'),
('5', '4', '350', '2022-09-15'),
('7', '4', '260', '2022-09-14'),
('8', '3', '400', '2022-09-25'),
('8', '3', '250', '2022-07-09'),
('4', '2', '700', '2022-09-12'),
('4', '5', '400', '2022-09-13');

-- Restock --------------
INSERT INTO restock (menu_vending_machine_id, menu_slot_number, quantity, service_staff_id, date) VALUES
('1', '101', '12', '1', '2022-09-13'),
('1', '102', '12', '1', '2022-09-13'),
('1', '103', '12', '1', '2022-09-13'),
('1', '104', '12', '1', '2022-09-13'),
('1', '201', '12', '1', '2022-09-13'),
('1', '202', '12', '1', '2022-09-13'),
('1', '203', '12', '1', '2022-09-13'),
('1', '204', '12', '1', '2022-09-13'),
('1', '301', '12', '5', '2022-09-14'),
('1', '302', '12', '5', '2022-09-14'),
('1', '303', '12', '5', '2022-09-14'),
('1', '304', '12', '5', '2022-09-14'),
('1', '401', '12', '5', '2022-09-14'),
('1', '402', '12', '5', '2022-09-14'),
('1', '403', '12', '5', '2022-09-14'),
('1', '404', '12', '5', '2022-09-14'),
('6', '11', '10', '4', '2022-09-17'),
('6', '12', '10', '4', '2022-09-17'),
('6', '13', '10', '4', '2022-09-17'),
('6', '21', '10', '4', '2022-09-17'),
('6', '22', '10', '4', '2022-09-17'),
('6', '23', '10', '4', '2022-09-17'),
('6', '31', '10', '4', '2022-09-17'),
('6', '32', '10', '4', '2022-09-17'),
('6', '33', '10', '4', '2022-09-17'),
('6', '41', '10', '4', '2022-09-17'),
('6', '42', '10', '4', '2022-09-17'),
('6', '43', '10', '4', '2022-09-17');

-- Sold --------
INSERT INTO sold (menu_vending_machine_id, menu_slot_number, time) VALUES 
('1', '101', '2022-09-20 01:20:00'),
('1', '102', '2022-09-20 10:10:00'),
('1', '204', '2022-09-20 10:15:00'),
('1', '202', '2022-09-20 10:20:00'),
('1', '301', '2022-09-20 10:30:00'),
('1', '104', '2022-09-20 10:40:00'),
('1', '401', '2022-09-20 10:55:00'),
('1', '404', '2022-09-20 11:00:00'),
('1', '402', '2022-09-20 11:20:00'),
('2', '103', '2022-09-20 11:32:00'),
('2', '103', '2022-09-20 12:50:00'),
('2', '102', '2022-09-20 12:59:00'),
('2', '401', '2022-09-20 12:59:30'),
('2', '403', '2022-09-20 14:10:00'),
('2', '101', '2022-09-20 14:11:00'),
('2', '201', '2022-09-20 15:24:00'),
('2', '201', '2022-09-20 16:56:00'),
('2', '203', '2022-09-20 16:59:00'),
('4', '102', '2022-09-20 18:20:00'),
('4', '104', '2022-09-20 18:42:00'),
('1', '103', '2022-09-20 18:49:00'),
('4', '202', '2022-09-20 18:50:00'),
('4', '201', '2022-09-20 18:55:00'),
('1', '101', '2022-09-20 18:59:00'),
('1', '104', '2022-09-20 20:16:00'),
('2', '201', '2022-09-20 20:34:00'),
('1', '401', '2022-09-20 20:39:00'),
('1', '403', '2022-09-20 20:46:00');

INSERT INTO sold (menu_vending_machine_id, menu_slot_number, time) VALUES 
('1', '101', '2022-09-21 01:20:00'),
('1', '102', '2022-09-21 10:10:00'),
('1', '204', '2022-09-21 10:15:00'),
('1', '202', '2022-09-21 10:20:00'),
('1', '301', '2022-09-21 10:30:00'),
('1', '104', '2022-09-21 10:40:00'),
('1', '401', '2022-09-21 10:55:00'),
('1', '404', '2022-09-21 11:00:00'),
('1', '402', '2022-09-21 11:20:00'),
('2', '103', '2022-09-21 11:32:00'),
('2', '103', '2022-09-21 12:50:00'),
('2', '102', '2022-09-21 12:59:00'),
('2', '401', '2022-09-21 12:59:30'),
('2', '403', '2022-09-21 14:10:00'),
('2', '101', '2022-09-21 14:11:00'),
('2', '201', '2022-09-21 15:24:00'),
('2', '201', '2022-09-21 16:56:00'),
('2', '203', '2022-09-21 16:59:00'),
('4', '102', '2022-09-21 18:20:00'),
('4', '104', '2022-09-21 18:42:00'),
('1', '103', '2022-09-21 18:49:00'),
('4', '202', '2022-09-21 18:50:00'),
('4', '201', '2022-09-21 18:55:00'),
('1', '101', '2022-09-21 18:59:00'),
('1', '104', '2022-09-21 20:16:00'),
('2', '201', '2022-09-21 20:34:00'),
('1', '401', '2022-09-21 20:39:00'),
('1', '403', '2022-09-21 20:46:00');


CREATE INDEX vending_machine_id ON vending_machine(id);
CREATE INDEX vending_machine_slot ON menu(vending_machine_id, slot_number);
CREATE INDEX service_staff_name ON service_staff(name, last_name);

-- Add procedures
use vending_machine;

delimiter //

# Task 2.a
# Забезпечити параметризовану вставку нових значень у довільну таблицю
drop procedure if exists insert_into_trademark //
create procedure insert_into_trademark(
	in name_in varchar (50),
	out name_out varchar (50)
)
begin    
	insert into country(name) values (name_in);
    select last_insert_id() into name_out;
end //

# Task 2.b
# Забезпечити реалізацію зв’язку М:М між 2ма таблицями, 
# тобто вставити в стикувальну таблицю відповідну стрічку за реально-існуючими значеннями 
# (напр. surname, name) в цих основних таблицях
drop procedure if exists insert_into_cash_withdrawal //
create procedure insert_into_cash_withdrawal(
	in service_member_name varchar (50),
    in service_member_last_name varchar (50),
    in vending_machine_model_name varchar (50),
    in amount int,
	out cash_withdrawal_id int
)
begin
	declare vending_machine_id, service_staff_id int;
	set vending_machine_id = (select id from vending_machine 
								where vending_machine.machine_model_name = vending_machine_model_name 
                                limit 1);
    set service_staff_id = (select id from service_staff 
								where service_staff.name = service_member_name 
								and service_staff.last_name = service_member_last_name 
								limit 1);
	if (vending_machine_id is null) then
        signal sqlstate '45000' 
        set message_text = 'No vending machines with such model name found';
    end if;
	if (service_staff_id is null) then
        signal sqlstate '45000' 
        set message_text = 'No service staff member with such name found';
    end if;
    insert into cash_withdrawal (vending_machine_id, service_staff_id, amount, date) 
		values (vending_machine_id, service_staff_id, amount, date(now()));
    select last_insert_id() into cash_withdrawal_id;
end //

# Task 2.c
# Створити пакет, який вставляє 10 стрічок у довільну таблицю БД у форматі <Noname+№>
# наприклад: Noname5, Noname6, Noname7 і т.д.
drop procedure if exists insert_nonames_into_country //
create procedure insert_nonames_into_country()
begin
    declare n int;
    set n = 1;
    label1: while n < 11 do
        insert into country values (CONCAT('Noname_', n));
        set n = n + 1;
    end while label1;
end //

# Task 2.d
# Написати користувацьку функцію, яка буде шукати Max, Min, Sum чи Avg для стовпця довільної таблиці у БД
# Написати процедуру, яка буде у SELECT викликати цю функцію
drop procedure if exists get_avg_cash_withdrawal_amount //
create procedure get_avg_cash_withdrawal_amount(
	out average double)
begin
    set average = get_avg_cash_withdrawal_amount_f();
end // 


# Task 2.e
# Використовуючи курсор, забезпечити динамічне створення таблиць з назвами+штамп часу, взятими зі стовпця з довільної таблиці БД,
# з випадковою кількістю стовпців (від 1 до 9). Імена та тип стовпців довільні
drop procedure if exists create_tables_by_country_names //
create procedure create_tables_by_country_names()
begin
	declare done bool default false;
	declare country_name varchar(50); 
    declare table_name_with_time varchar(50);
    
    declare counter, random_num int;

	declare country_cursor cursor
	for select name from country;

	declare continue handler
	for not found set done = true;
	
	open country_cursor;   
    	
	country_names_loop: loop

		fetch country_cursor into country_name;
        set table_name_with_time = concat(country_name, '_', replace(time(now()), ':', '_'));
        
		if done then leave country_names_loop;
		end if;
        
		set @drop_table_query = concat('drop table if exists ', table_name_with_time, ';');
		set @create_table_query = concat('create table ', table_name_with_time, ' (id int NOT NULL AUTO_INCREMENT, CONSTRAINT ', table_name_with_time, '_pk PRIMARY KEY (id));');
        
		set counter = 1;
        set random_num = (select floor(rand()*10)+1);
            
        prepare drop_table_query_ from @drop_table_query;
		execute drop_table_query_;
		deallocate prepare drop_table_query_;
	
		prepare create_table_query_ from @create_table_query;
		execute create_table_query_;
		deallocate prepare create_table_query_;
        
        columns_loop: loop

			set @add_column_query = concat('alter table ', table_name_with_time, ' add column column_', counter, ' varchar(50);');

			prepare add_column_query_ from @add_column_query;
			execute add_column_query_;
			deallocate prepare add_column_query_;

			if counter = random_num then leave columns_loop;
			end if;

			set counter = counter + 1;
		end loop;
        
	end loop;
    
	close country_cursor;
end //

delimiter ;