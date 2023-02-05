select * from student;

select * from student where age > 15 and age < 20;

select name from student;

select * from student where name like '%i%';

select * from student where age < id;

select * from student order by age;

select * from student, faculty
where student.faculty_id = faculty.id
and faculty.id = 1;

select s.id, s.name, s.age from student as s, faculty as f
where s.faculty_id = f.id
and f.id = 1;

select s.* from student as s, faculty as f
where s.faculty_id = f.id
and f.id = 1;

select * from faculty;