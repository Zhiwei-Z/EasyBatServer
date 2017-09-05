CREATE TABLE customer_info(
	customer_id       VARCHAR (64),
  street_number     VARCHAR (20),
  unit_number       VARCHAR (20),
  street_name       VARCHAR (32),
  street_type       VARCHAR (32),
  city              VARCHAR (32),
  state             VARCHAR (32),
  zip_code          VARCHAR (64),
  status            INT (4),
  combined_address  VARCHAR (128),
  nickname          VARCHAR (20),
	PRIMARY KEY (customer_id)
);

CREATE TABLE volunteer_info(
	volunteer_id      VARCHAR (64),
  street_number     VARCHAR (20),
  unit_number       VARCHAR (20),
  street_name       VARCHAR (32),
  street_type       VARCHAR (32),
  city              VARCHAR (32),
  state             VARCHAR (32),
  zip_code          VARCHAR (64),
  status            INT (4),
  combined_address  VARCHAR (128),
  username          VARCHAR (20),
  email             VARCHAR (64),
  password          VARCHAR  (64),
	PRIMARY KEY (volunteer_id)
);

CREATE TABLE batti_order_record(
  order_id        VARCHAR (64),
  customer_id     VARCHAR (64),
  pick_status     INT(4),
  created_date    datetime(6),
  created_time    TIME(3),
  modified_date   datetime(6),
  modified_time   TIME (3),
  address         VARCHAR (128),
  if_occupied     INT(4),
  PRIMARY KEY (order_id)
);

CREATE TABLE volunteer_task(
  choice_id        VARCHAR (64),
  order_id         VARCHAR (64),
  volunteer_id     VARCHAR (64),
  pick_up_status   INT (4),
  PRIMARY KEY (choice_id)
);