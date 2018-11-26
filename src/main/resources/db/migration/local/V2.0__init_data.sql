insert into java_blog_exercise.users VALUES (1, 'huy.pham', '$2a$10$/OuvpG2RUTU9b.7otIKsMOK78rEL3hYh5q/NuuGuFHjr6OJpzvlT6', 'Pham Duc Huy', NOW());
insert into java_blog_exercise.roles VALUES (1, 'ADMIN', NOW());
insert into java_blog_exercise.roles VALUES (2, 'EDITOR', NOW());
insert into java_blog_exercise.user_role VALUES (1, 1, 1, NOW());