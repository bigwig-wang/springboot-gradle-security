create table schedule(
  id VARCHAR(32) NOT NUll PRIMARY KEY,
  start_time DATE ,
  end_time DATE ,
  content varchar(1000),
  title varchar(256),
  type varchar(32),
  finished boolean,
  created_by varchar(32),
  create_date DATE,
  last_modified_date DATE,
  updated_by varchar(32)
);