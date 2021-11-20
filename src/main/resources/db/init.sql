insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$y2XtxuF2tr45Qu.Q/K.DUewAD6rTLu/3XVtXNb09L7uyi2jq9yzIa',
        (select id from authorities where authority = 'ROLE_ADMIN'));

insert into posts (name) values ('О чем этот форум?');
insert into posts (name) values ('Правила форума.');