create table user_role(
  id VARCHAR(32) NOT NUll PRIMARY KEY,
  user_id VARCHAR(32) ,
  role_id VARCHAR(32) ,
  created_by varchar(32),
  create_date DATE,
  last_modified_date DATE,
  updated_by varchar(32)
);


create table role(
  id VARCHAR(32) NOT NUll PRIMARY KEY,
  role_name VARCHAR(32) ,
  role_code VARCHAR(32) ,
  created_by varchar(32),
  create_date DATE,
  last_modified_date DATE,
  updated_by varchar(32)
);