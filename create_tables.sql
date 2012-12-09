
    alter table Course 
        drop constraint FK78A7CC3B936D145A;

    alter table Mark 
        drop constraint FK247AED9D76435C;

    alter table Mark 
        drop constraint FK247AEDD6A4B5FA;

    alter table Notice 
        drop constraint FK8B6C82F89D76435C;

    alter table Notice 
        drop constraint FK8B6C82F8936D145A;

    alter table Student_Parent 
        drop constraint FK53B7536E9D76435C;

    alter table Student_Parent 
        drop constraint FK53B7536E862662DB;

    drop table if exists Course cascade;

    drop table if exists Mark cascade;

    drop table if exists Notice cascade;

    drop table if exists Parent cascade;

    drop table if exists Student cascade;

    drop table if exists Student_Parent cascade;

    drop table if exists Teacher cascade;

    drop sequence hibernate_sequence;

    create table Course (
        id int4 not null,
        description varchar(255),
        name varchar(255) not null,
        timer timestamp,
        teacher_id int4 not null,
        primary key (id)
    );

    create table Mark (
        id int4 not null,
        date date not null,
        mailSent boolean not null,
        number int4 not null,
        sign char(1) not null,
        course_id int4 not null,
        student_pesel varchar(11) not null,
        primary key (id)
    );

    create table Notice (
        id int4 not null,
        date date not null,
        description varchar(255) not null,
        mailSent boolean not null,
        student_pesel varchar(11) not null,
        teacher_id int4 not null,
        primary key (id)
    );

    create table Parent (
        id int4 not null,
        email varchar(255) not null,
        name varchar(255) not null,
        phone varchar(255),
        surname varchar(255) not null,
        primary key (id)
    );

    create table Student (
        pesel varchar(11) not null,
        name varchar(255) not null,
        surname varchar(255) not null,
        primary key (pesel)
    );

    create table Student_Parent (
        Student_pesel varchar(11) not null,
        parents_id int4 not null,
        primary key (Student_pesel, parents_id)
    );

    create table Teacher (
        id int4 not null,
        name varchar(255) not null,
        surname varchar(255) not null,
        primary key (id)
    );

    alter table Course 
        add constraint FK78A7CC3B936D145A 
        foreign key (teacher_id) 
        references Teacher;

    alter table Mark 
        add constraint FK247AED9D76435C 
        foreign key (student_pesel) 
        references Student;

    alter table Mark 
        add constraint FK247AEDD6A4B5FA 
        foreign key (course_id) 
        references Course;

    alter table Notice 
        add constraint FK8B6C82F89D76435C 
        foreign key (student_pesel) 
        references Student;

    alter table Notice 
        add constraint FK8B6C82F8936D145A 
        foreign key (teacher_id) 
        references Teacher;

    alter table Student_Parent 
        add constraint FK53B7536E9D76435C 
        foreign key (Student_pesel) 
        references Student;

    alter table Student_Parent 
        add constraint FK53B7536E862662DB 
        foreign key (parents_id) 
        references Parent;

    create sequence hibernate_sequence;
