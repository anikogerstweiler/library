drop table if exists hiredbooks;
drop table if exists authorities;
drop table if exists users;
drop table if exists books;

create table users (
username varchar(50) not null primary key,
password varchar (60) not null,
enabled boolean not null,
firstname varchar(50) not null,
lastname varchar(50) not null);

create table authorities (
 id tinyint auto_increment primary key,
 username varchar(50) not null,
 authority varchar (50) not null,
 constraint fk_authorities_users 
 foreign key(username) references users(username));

create table books(
id tinyint auto_increment primary key,
author varchar(50) not null,
title text not null,
year year not null,
isbn varchar(20) not null,
description text
);

create table hiredbooks(
id tinyint auto_increment primary key,
username varchar(50) not null,
bookid tinyint not null,
fromdate date not null,
todate date not null,
foreign key(username) references users(username),
foreign key(bookid) references books(id)
);

insert into users (username, password, enabled, firstname, lastname) values ("user","$2a$10$Ttmad3cLv7AKLGNyx3VgquNedq4NAWpsDhRX1p9wvcjZHiAV/8Kg.",true, "user", "user");
insert into users (username, password, enabled, firstname, lastname) values ("admin","$2a$10$o7SaWxhxBYIlhTfjUlaXwO09sKAoBT6V5vmzcpX0dxMHDSYK6y1y2",true, "admin", "admin");

insert into authorities(username, authority) values("user", "ROLE_USER");
insert into authorities(username, authority) values("admin", "ROLE_ADMIN");

insert into books(author, title, year, isbn, description) values("Eric M. Burke", "Java and XSLT", 2001, "0596001436",
"The power of XSLT is its ability to change the structure or format of any content that can be converted to XML. Java and XSLT shows you how to use XSL 
transformations in Java programs ranging from stand-alone applications to servlets. After an introduction to XSLT, the book focuses on applying transformations 
in some real-world scenarios, such as developing a discussion forum, transforming documents from one form to another, and generating content for wireless devices.");
insert into books(author, title, year, isbn, description) values("Eric M. Burke", "Ant: The Definitive Guide", 2002, "0596001843",
"As the most widely used tool for cross-platform development, Ant has undergone a number of important changes in its functionality and use since its launch. 
Ant: The Definitive Guide, 2nd Edition has been reworked to reflect these changes for Java developers everywhere. Topics covered include everything from 
downloading and installing, to using Ant to build Web applications, to using Ant to test code.");
insert into books(author, title, year, isbn, description) values("James Goodwill", "Apache Tomcat 7", 2011, "978-1430237235", 
"Apache Tomcat is the most popular open-source de-facto Java Web application server, standard for today's Web developers using JSP/Servlets. 
Apache Tomcat 7 covers details on installation and administration of Apache Tomcat 7. It explains key parts of the Tomcat architecture, and 
provides an introduction to Java Servlet and JSP APIs in the context of the Apache Tomcat server. In addition to basic concepts and administration tasks, 
Apache Tomcat 7 covers some of the most frequently used advanced features of Tomcat.");
insert into books(author, title, year, isbn, description) values("Russell Miles", "AspectJ Cookbook", 2004, "0-596-00654-3",
"Intended for experienced Java programmers, this reference describes the tools and environments that support 
development using AspectJ, the different constructs that the AspectJ language adds to Java, and aspect-oriented design patterns. 
The 109 solutions capture join points on methods, class construction, and attributes, examine the different ways advice can be specified, 
and show how to implement creational, structural, and behavioral design patterns.");

insert into hiredbooks (username, bookid, fromdate, todate) values("user", 1, '2014-08-29', '2014-09-29');
insert into hiredbooks (username, bookid, fromdate, todate) values("user", 2, '2014-08-29', '2014-09-29');
insert into hiredbooks (username, bookid, fromdate, todate) values("user", 3, '2014-08-29', '2014-09-29');
