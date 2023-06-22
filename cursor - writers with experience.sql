DECLARE 
  CURSOR writer_cur(p_experience NUMBER) IS 
   SELECT writername, numyearsofexperience, payperhour, writerid
   FROM writer
   WHERE numyearsofexperience >= p_experience;
BEGIN
  FOR r_writer_rec IN writer_cur(10)
 LOOP
   DBMS_OUTPUT.put_line('Writer experience: ' || r_writer_rec.numyearsofexperience);
   DBMS_OUTPUT.put_line('Writer name: ' || r_writer_rec.writername);
   DBMS_OUTPUT.put_line('Writer salary: ' || r_writer_rec.payperhour);
   DBMS_OUTPUT.new_line; 
 END LOOP;
END;
