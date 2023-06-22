CREATE OR REPLACE FUNCTION get_price_sum_between_dates(
  p_platform_name IN VARCHAR2,
  p_start_date    IN DATE,
  p_end_date      IN DATE
) RETURN NUMBER IS
  v_total_price NUMBER := 0;
BEGIN
  FOR price_rec IN (
    SELECT a.pricetopayplatform
    FROM appearson a
    WHERE a.platformname = p_platform_name
      AND a.datepublished BETWEEN p_start_date AND p_end_date
  ) LOOP
    v_total_price := v_total_price + price_rec.price;
  END LOOP;
 
  RETURN v_total_price;
END;
/

DECLARE
  v_total_price NUMBER;
BEGIN
  v_total_price := system.get_price_sum_between_dates('Yated_neeman', DATE '2004-01-01', DATE '2007-02-01');
 
  DBMS_OUTPUT.PUT_LINE('Total price between dates: ' || v_total_price);
END;
/
