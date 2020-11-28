DROP USER IF EXISTS "admin"@"%";
DROP USER IF EXISTS "tutorial_admin"@"%";
CREATE USER "tutorial_admin"@"%" IDENTIFIED BY "password";
GRANT ALL ON tutorial.* TO "tutorial_admin"@"%";

DROP USER IF EXISTS "tutorial"@"%";
CREATE USER "tutorial"@"%" IDENTIFIED BY "password";
GRANT EXECUTE ON PROCEDURE tutorial.insertPerson TO "tutorial"@"%";
-- GRANT INSERT, UPDATE, DELETE ON tutorial.* TO "tutorial"@"%";
FLUSH PRIVILEGES;