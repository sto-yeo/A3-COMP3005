CREATE TABLE students(
	student_id serial primary key ,
	first_name varchar(15) not null,
	last_name varchar(15) not null,
	email varchar(30) not null unique,
	enrollment_date Date
);