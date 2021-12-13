create table if not exists faculty (
    id bigserial primary key,
    title varchar(255)
);

create table if not exists student
(
    id    bigserial primary key,
    name  varchar(255),
    score float,
    faculty_id bigint references faculty (id)
);

insert into faculty (title)
values ('Java'), ('Android');

insert into student(name, score, faculty_id)
values ('Иван', 99, 1),
       ('Андрей', 76, 1),
       ('Сергей', 86, 2);
