DELIMITER //

DROP PROCEDURE IF EXISTS tutorial.insertPerson;
CREATE PROCEDURE tutorial.insertPerson(
    IN person_name_param VARCHAR(50)
)
BEGIN
    INSERT INTO Person(name) VALUE (person_name_param);
END //

DELIMITER ;