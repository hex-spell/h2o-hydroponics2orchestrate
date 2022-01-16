CREATE DATABASE IF NOT EXISTS `hydro`;
USE `hydro`;
CREATE TABLE IF NOT EXISTS `sensor_state` (
    created_at VARCHAR(20) NOT NULL,
     ambient_temperature DOUBLE NOT NULL,
     fire_alarm BIT(1) NOT NULL,
     CONSTRAINT pk_sensor_state PRIMARY KEY (created_at)
  );