create  database carrier_hub;
use carrier_hub;
select * from job_application;
show tables;
create table Applicant(
	Applicant_id int primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(50) not null,
    phone int not null,
    resume text(50)
    );
create table company(
	company_id int primary key,
    company_name varchar(50),
    location varchar(50)
    );
    
create table Job_application(
	application_id int,
    job_id int,
    applicant_id int,
    application_date datetime,
    cover_letter text
    );
    
create table job_listing(
	job_id int,
    company_id int,
    job_title varchar(50),
    job_description varchar(100),
    job_location varchar(50),
    salary decimal(10,2),
    job_type varchar(50),
    posted_date datetime
    );
ALTER TABLE Applicant
MODIFY COLUMN phone VARCHAR(15);

insert into Applicant(Applicant_id,first_name,last_name,email,phone,resume)
values
(1,'Alice','Sarah','alice@gmail.com','9344485795','sarah_resume.pdf'),
(2,'Rachel','chris','rachel@gmail.com','9876540278','rachel_resume.pdf'),
(3,'Elisa','Richard','elisa@gmail.com','9786547832','elisa_resume.pdf'),
(4,'Vishwa','Grace','grace@gmail.com','6789567434','grace_resume.pdf');
 
 insert into company(company_id,company_name,location)
 values
 (1,'Open ai','Chennai'),
 (2,'Google','Banglore'),
 (3,'Myntra','Pune'),
 (4,'Valeo','Noida');
 
 insert into job_application(application_id,job_id,applicant_id,application_date,cover_letter)
 values
 (1,1,1,now(),'I am passionate about AI and would love to contribute to your mission.'),
 (2,2,2,now(),'Excited to work on backend systems at Google'),
 (3,3,3,now(),'I have strong experience in data analysis and statistics.'),
 (4,4,4,now(),'Backend development aligns with my skills and interests.');
 
UPDATE job_application SET application_date = ''
WHERE applicant_id IN (1,2,3,4);

 insert into job_listing(job_id,company_id,job_title,job_description,job_location,salary,job_type,posted_date)
 values
 (1,1,'AI Researcher', 'Work on cutting-edge AI models.', 'Remote', 150000.00, 'Full-Time', NOW()),
 (2,2,'Software Engineer', 'Backend systems developer.', 'California', 120000.00, 'Full-Time', NOW()),
 (3,3,'Data Analyst', 'Analyze big data.', 'New York', 90000.00, 'Part-Time', NOW()),
 (4,4,'Software developer','Frontens systems developer','England',130000.00,'Full-Time',NOW());
    
select*from applicant;
select *from company;
select *from job_listing;
	delete from job_application where application_id=5;

    
    