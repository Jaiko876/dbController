INSERT INTO qas.role_qas (name) VALUES ('Директор');
INSERT INTO qas.role_qas (name) VALUES ('Заместитель директора');
INSERT INTO qas.role_qas (name) VALUES ('Начальник отдела');
INSERT INTO qas.role_qas (name) VALUES ('Сотрудник');
INSERT INTO qas.status (name) VALUES ('active');
INSERT INTO qas.status (name) VALUES ('sended');
INSERT INTO qas.status (name) VALUES ('wait');
INSERT INTO qas.status (name) VALUES ('agreed');
INSERT INTO qas.status (name) VALUES ('denied');
INSERT INTO qas.status (name) VALUES ('canceled');
INSERT INTO qas.user_qas (fio, login, password, email, telegram_chat_id) VALUES ('Гусев Григорий Владимирович', 'gusevgv', 'gusevgv', 'gusevgv@mail.ru', 123);
INSERT INTO qas.user_qas (fio, login, password, email, telegram_chat_id) VALUES ('Пирогов Андрей Алексеевич', 'pirogovaa', 'pirogovaa', 'pirogovaa@mail.ru', 124);
INSERT INTO qas.user_qas (fio, login, password, email, telegram_chat_id) VALUES ('Гуров Денис Витальевич', 'gurovdv', 'gurovdv', 'gurovdv@mail.ru', 125);
INSERT INTO qas.user_qas (fio, login, password, email, telegram_chat_id) VALUES ('Гривко Ирина Вячеславовна', 'grivkoiv', 'grivkoiv', 'gtivkoiv@mail.ru', 126);
INSERT INTO qas.user_qas (fio, login, password, email, telegram_chat_id) VALUES ('Котова Елена Александровна', 'kotovaea', 'kotovaea', 'kotovaea@mail.ru', 127);
INSERT INTO qas.user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO qas.user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO qas.user_role (user_id, role_id) VALUES (3, 3);
INSERT INTO qas.user_role (user_id, role_id) VALUES (4, 4);
INSERT INTO qas.user_role (user_id, role_id) VALUES (5, 4);
INSERT INTO qas.process_type (name, description, time_to_do) VALUES ('На согласование', 'Заявления от сотрудников', 24);
INSERT INTO qas.process_type (name, description, time_to_do) VALUES ('На ознакомление', 'Приказы, другие документы руководства', 24);
INSERT INTO qas.process_type (name, description, time_to_do) VALUES ('Предложения к разработке', 'Участие сотрудников по производству', 72);
INSERT INTO qas.process_stage (process_type_id, stage, role_id) VALUES (1, 1, 3);
INSERT INTO qas.process_stage (process_type_id, stage, role_id) VALUES (1, 2, 1);
INSERT INTO qas.process_stage (process_type_id, stage, role_id) VALUES (2, 1, 4);
INSERT INTO qas.process_stage (process_type_id, stage, role_id) VALUES (3, 1, 3);
INSERT INTO qas.process (process_type_id, name, description, user_start_id, date_start, date_end_planning, date_end_fact, status_id) VALUES (1, 'На согласование', 'Заявление на отпуск Гривко И.В. с 03.02.2020 по 21.02.2020', 5, '2020-01-27 00:00:00.002000', '2020-01-28 13:37:41.967000', null, null);
INSERT INTO qas.process (process_type_id, name, description, user_start_id, date_start, date_end_planning, date_end_fact, status_id) VALUES (1, 'На согласование', 'Направление на прохождение повышения квалификации Котовой Е.А. со 02.03.2020 ', 4, '2020-01-28 14:05:45.766000', '2020-01-28 14:06:40.388000', null, null);
INSERT INTO qas.task (process_id, user_performer_id, role_performer_id, date_start, date_end_planning, date_end_fact, status_id) VALUES (1, 4, 3, '2020-01-27 14:54:42.561000', '2020-01-28 14:52:25.262000', null, 2);
INSERT INTO qas.task (process_id, user_performer_id, role_performer_id, date_start, date_end_planning, date_end_fact, status_id) VALUES (2, 4, 3, '2020-01-28 14:57:28.067000', '2020-01-28 14:57:49.488000', null, 2);