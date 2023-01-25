select * from students;

select * from students where age > 15 and age < 20;

select name from students;

select * from students where name like '%i%';

select * from students where age < id;

select * from students order by age;

select * from students, faculty
where students.faculty_id = faculty.id
and faculty.id = 1;

select s.id, s.name, s.age from students as s, faculty as f
where s.faculty_id = f.id
and f.id = 1;

select s.* from students as s, faculty as f
where s.faculty_id = f.id
and f.id = 1;

select * from faculty;