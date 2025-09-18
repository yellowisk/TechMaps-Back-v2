\c techmaps techmaps-app

insert into techmaps_platform.user(id, email, position, username, password)
    values('8eb72e1e-532e-4160-a0dd-46332bb40847', 'user@gmail.com', 'ADMIN', 'user', 'password');

insert into techmaps_platform.dashboard(id, user_id, total_roadmaps)
values('018fbc06-ddba-7673-a46c-ff2230be161f', '8eb72e1e-532e-4160-a0dd-46332bb40847', 0);

insert into techmaps_platform.roadmap(id, name, language)
    values('018fbc07-1164-7f3b-887f-0321123a3085', 'Python 101 Roadmap', 'PYTHON');

insert into techmaps_platform.roadmap_user(id, roadmap_id, user_id, is_done, start_time, end_time)
    values('018fbc07-4f47-7227-9e85-91a283fb4d56', '018fbc07-1164-7f3b-887f-0321123a3085', '8eb72e1e-532e-4160-a0dd-46332bb40847', false, '2021-01-01', NULL);

insert into techmaps_platform.group(id, name, parent_id)
    values ('018fbc07-1164-7f3b-887f-0321123a3085', 'Escola Vitor Meirelles', NULL);

insert into techmaps_platform.group(id, name, parent_id)
    values ('0192b1e1-396a-79ba-bd30-7edd8e2c32a1', 'Estudos de Python', '018fbc07-1164-7f3b-887f-0321123a3085');

insert into techmaps_platform.group_user(id, group_id, user_id, role)
    values ('0192b1e3-04be-7d72-b6f5-4c3784c50c17', '018fbc07-1164-7f3b-887f-0321123a3085', '8eb72e1e-532e-4160-a0dd-46332bb40847', 'OWNER');

insert into techmaps_platform.group_user(id, group_id, user_id, role)
    values ('0192b1e2-3f39-7b5e-a252-c04bda0807b3', '0192b1e1-396a-79ba-bd30-7edd8e2c32a1', '8eb72e1e-532e-4160-a0dd-46332bb40847', 'STUDENT');

insert into techmaps_platform.stage(id, roadmap_id, name, position)
    values('018fbc0b-7da3-790f-948b-135f7a523df9', '018fbc07-1164-7f3b-887f-0321123a3085', 'Python Basics', 1);

insert into techmaps_platform.stage_user(id, stage_id, roadmap_user_id, is_done, position)
    values('018fbc0b-b41c-7d2a-bc7f-cc62b091cd3b', '018fbc0b-7da3-790f-948b-135f7a523df9', '018fbc07-4f47-7227-9e85-91a283fb4d56', false, 1);

insert into techmaps_platform.task(id, stage_id, title, description, position)
    values('018fbc0e-25b4-795d-8af8-5b19c2bec080', '018fbc0b-7da3-790f-948b-135f7a523df9', 'Basic Syntax', 'In this stage you will learn the basic syntax of Python', 1);

insert into techmaps_platform.task_user(id, task_id, roadmap_user_id, is_done)
    values('018fbc0f-3f27-778d-8546-d663ad680ddc', '018fbc0e-25b4-795d-8af8-5b19c2bec080', '018fbc07-4f47-7227-9e85-91a283fb4d56', false);

insert into techmaps_platform.step(id, task_id, position, text, link)
    values('018fbc0f-f2ca-7a5f-944d-25ef416c82a8', '018fbc0e-25b4-795d-8af8-5b19c2bec080', 1, 'Python Syntax', 'https://www.w3schools.com/python/python_syntax.asp');

insert into techmaps_platform.step_user(id, step_id, roadmap_user_id, is_done)
    values('018fbc11-e2e3-7957-a9c8-906263a9a5db', '018fbc0f-f2ca-7a5f-944d-25ef416c82a8', '018fbc07-4f47-7227-9e85-91a283fb4d56', false);