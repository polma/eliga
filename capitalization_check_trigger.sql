CREATE FUNCTION assure_capitalized_surname() RETURNS TRIGGER AS $assure_capitalized_surname$
  BEGIN
    IF initcap(NEW.surname) != NEW.surname THEN
      RAISE EXCEPTION 'Surname for teacher not capitalized';
    END IF;
    RETURN NEW;
  END;
$assure_capitalized_surname$ LANGUAGE plpgsql;

CREATE TRIGGER assure_capitalized_surname BEFORE INSERT OR UPDATE ON teacher
  FOR EACH ROW EXECUTE PROCEDURE assure_capitalized_surname()
