CREATE DATABASE IF NOT EXISTS `hydro`;

USE `hydro`;

CREATE TABLE sensor_state (
  created_at VARCHAR(255) NOT NULL,
   ambient_temperature DOUBLE NOT NULL,
   ambient_humidity DOUBLE NOT NULL,
   water_temperature DOUBLE NOT NULL,
   water_tds DOUBLE NOT NULL,
   water_ph DOUBLE NOT NULL,
   fire_alarm BIT(1) NOT NULL,
   CONSTRAINT pk_sensor_state PRIMARY KEY (created_at)
);