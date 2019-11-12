INSERT INTO user(id, full_name, username, password) values ('1', '汪大亨', 'dhwang', '123456');

INSERT INTO role(id, role_name, role_code, created_by) values('1', '管理员', 'ADMIN', 'dhwang');

INSERT INTO user_role(id, user_id, role_id, created_by) values('1', '1', '1', 'dhwang');