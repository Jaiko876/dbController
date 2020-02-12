INSERT INTO qas.role_qas (id_role, name) VALUES (1, 'Директор');
INSERT INTO qas.role_qas (id_role, name) VALUES (2, 'Заместитель директора');
INSERT INTO qas.role_qas (id_role, name) VALUES (3, 'Начальник отдела');
INSERT INTO qas.role_qas (id_role, name) VALUES (4, 'Сотрудник');
INSERT INTO qas.status (id_status, name) VALUES (2, 'active');
INSERT INTO qas.status (id_status, name) VALUES (3, 'sended');
INSERT INTO qas.status (id_status, name) VALUES (4, 'wait');
INSERT INTO qas.status (id_status, name) VALUES (5, 'agreed');
INSERT INTO qas.status (id_status, name) VALUES (6, 'denied');
INSERT INTO qas.status (id_status, name) VALUES (7, 'canceled');
INSERT INTO qas.user_qas (id_user, fio, login, password, email, telegram_chat_id) VALUES (2, 'Гусев Григорий Владимирович', 'gusevgv', 'gusevgv', 'gusevgv@mail.ru', 123);
INSERT INTO qas.user_qas (id_user, fio, login, password, email, telegram_chat_id) VALUES (3, 'Пирогов Андрей Алексеевич', 'pirogovaa', 'pirogovaa', 'pirogovaa@mail.ru', 124);
INSERT INTO qas.user_qas (id_user, fio, login, password, email, telegram_chat_id) VALUES (4, 'Гуров Денис Витальевич', 'gurovdv', 'gurovdv', 'gurovdv@mail.ru', 125);
INSERT INTO qas.user_qas (id_user, fio, login, password, email, telegram_chat_id) VALUES (5, 'Гривко Ирина Вячеславовна', 'grivkoiv', 'grivkoiv', 'gtivkoiv@mail.ru', 126);
INSERT INTO qas.user_qas (id_user, fio, login, password, email, telegram_chat_id) VALUES (6, 'Котова Елена Александровна', 'kotovaea', 'kotovaea', 'kotovaea@mail.ru', 127);
INSERT INTO qas.user_role (user_role_id, user_id, role_id) VALUES (1, 2, 1);
INSERT INTO qas.user_role (user_role_id, user_id, role_id) VALUES (2, 3, 2);
INSERT INTO qas.user_role (user_role_id, user_id, role_id) VALUES (3, 4, 3);
INSERT INTO qas.user_role (user_role_id, user_id, role_id) VALUES (4, 5, 4);
INSERT INTO qas.user_role (user_role_id, user_id, role_id) VALUES (5, 6, 4);
INSERT INTO qas.process_type (id_process_type, name, description, time_to_do) VALUES (2, 'На ознакомление', 'Приказы, другие документы руководства', 24);
INSERT INTO qas.process_type (id_process_type, name, description, time_to_do) VALUES (3, 'Предложения к разработке', 'Участие сотрудников по производству', 72);
INSERT INTO qas.process_type (id_process_type, name, description, time_to_do) VALUES (1, 'На согласование', 'Заявления от сотрудников', 24);
INSERT INTO qas.process_stage (id_process_stage, process_type_id, stage, role_id) VALUES (1, 1, 1, 3);
INSERT INTO qas.process_stage (id_process_stage, process_type_id, stage, role_id) VALUES (2, 1, 2, 1);
INSERT INTO qas.process_stage (id_process_stage, process_type_id, stage, role_id) VALUES (3, 2, 1, 4);
INSERT INTO qas.process_stage (id_process_stage, process_type_id, stage, role_id) VALUES (4, 3, 1, 3);
INSERT INTO qas.process (id_process, process_type_id, name, description, user_start_id, date_start, date_end_planning, date_end_fact, status_id) VALUES (2, 1, 'На согласование', 'Заявление на отпуск Гривко И.В. с 03.02.2020 по 21.02.2020', 5, '2020-01-27 00:00:00.002000', '2020-01-28 13:37:41.967000', null, null);
INSERT INTO qas.process (id_process, process_type_id, name, description, user_start_id, date_start, date_end_planning, date_end_fact, status_id) VALUES (4, 1, 'На согласование', 'Направление на прохождение повышения квалификации Котовой Е.А. со 02.03.2020 ', 4, '2020-01-28 14:05:45.766000', '2020-01-28 14:06:40.388000', null, null);
INSERT INTO qas.task (id_task, process_id, user_performer_id, role_performer_id, date_start, date_end_planning, date_end_fact, status_id) VALUES (1, 2, 4, 3, '2020-01-27 14:54:42.561000', '2020-01-28 14:52:25.262000', null, 2);
INSERT INTO qas.task (id_task, process_id, user_performer_id, role_performer_id, date_start, date_end_planning, date_end_fact, status_id) VALUES (2, 4, 4, 3, '2020-01-28 14:57:28.067000', '2020-01-28 14:57:49.488000', null, 2);