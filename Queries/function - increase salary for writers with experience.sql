CREATE OR REPLACE FUNCTION increase_salary(p_experience NUMBER, p_increase_amount NUMBER) RETURN NUMBER IS
  v_total_updated NUMBER := 0;
BEGIN
  FOR writer_rec IN (
    SELECT w.writerid, w.payperhour
    FROM writer w
    WHERE w.numyearsofexperience > p_experience
  ) LOOP
    UPDATE writer w
    SET w.payperhour = writer_rec.payperhour + p_increase_amount
    WHERE w.writerid = writer_rec.writerid;
   
    v_total_updated := v_total_updated + 1;
  END LOOP;

  COMMIT;
 
  RETURN v_total_updated;
END;
/

DECLARE
  v_updated_count NUMBER;
BEGIN
  v_updated_count := increase_salary(10, 1000); -- Example: Increase salary for writers with more than 10 years of experience by 1000
  
  DBMS_OUTPUT.PUT_LINE('Number of writers whose salary was updated: ' || v_updated_count);
END;
/
