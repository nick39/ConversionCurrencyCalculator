DROP TABLE IF EXISTS rate;

CREATE TABLE rate (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               code VARCHAR(6),
                               rate double,
                               date date
);