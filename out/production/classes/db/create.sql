SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS locations (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  terrain VARCHAR,
  condition VARCHAR,
  mission VARCHAR,
  site VARCHAR,
  map VARCHAR,
  );

CREATE TABLE IF NOT EXISTS operations (
  id int PRIMARY KEY auto_increment,
  name VARCHAR
);

CREATE TABLE IF NOT EXISTS assessments (
  id int PRIMARY KEY auto_increment,
  rater VARCHAR,
  rating VARCHAR,
  createdat TIMESTAMP,
  locationid INTEGER
);

CREATE TABLE IF NOT EXISTS locations_operations (
  id int PRIMARY KEY auto_increment,
  locationId INTEGER,
  operationId INTEGER
);