create database iot;
use iot;
drop table if exists device;
drop table if exists devicetype;
drop table if exists environment;
drop table if exists operation;
drop table if exists user;

CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `devicetype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `power` varchar(255) DEFAULT NULL,
  `voltage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `environment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(255) DEFAULT NULL,
  `temperature` varchar(255) DEFAULT NULL,
  `humidity` varchar(255) DEFAULT NULL,
  `ownerState` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceid` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into devicetype (name,type,power,voltage) values ("A1","AirConditioner","1000","220");
insert into devicetype (name,type,power,voltage) values ("A2","AirConditioner","1500","220");
insert into devicetype (name,type,power,voltage) values ("L1","Light","20","220");
insert into devicetype (name,type,power,voltage) values ("L2","Light","50","220");
insert into devicetype (name,type,power,voltage) values ("L3","Light","75","220");
insert into devicetype (name,type,power,voltage) values ("T1","TV","100","220");
insert into devicetype (name,type,power,voltage) values ("T2","TV","250","220");
insert into devicetype (name,type,power,voltage) values ("C1","Curtain","50","220");
insert into devicetype (name,type,power,voltage) values ("C2","Curtain","60","220");
insert into devicetype (name,type,power,voltage) values ("C3","Curtain","70","220");
insert into devicetype (name,type,power,voltage) values ("H1","Humidifier","15","220");
insert into devicetype (name,type,power,voltage) values ("H2","Humidifier","20","220");
insert into devicetype (name,type,power,voltage) values ("B1","Box","10","220");
insert into devicetype (name,type,power,voltage) values ("B2","Box","20","220");
