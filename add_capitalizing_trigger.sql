CREATE FUNCTION capitalize_first_letter() RETURNS TRIGGER AS $capitalize_first_letter$
  BEGIN
    NEW.nazwisko := initcap(NEW.nazwisko);
    RETURN NEW;
  END;
$capitalize_first_letter$ LANGUAGE plpgsql;

CREATE TRIGGER capitalize_first_letter BEFORE INSERT OR UPDATE ON nauczyciele
  FOR EACH ROW EXECUTE PROCEDURE capitalize_first_letter()
