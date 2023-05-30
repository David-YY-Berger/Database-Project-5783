
-- -- drop view ad1to100;
-- -- drop view ad101to200;
-- -- drop view ad201to400;

-- create view ad1to100 as
-- select writerid, count(adid) as AdSize1to100
-- from (writtenBy natural join (advertisement natural join Picturead))
-- where (length * width) <= 100 AND (length * width) > 0
-- group by writerid
-- order by writerid;
-- ;
-- create view ad101to200 as
-- select writerid, count(adid) as AdSize101to200
-- from (writtenBy natural join (advertisement natural join Picturead))
-- where (length * width) <= 200 AND (length * width) > 101 
-- group by writerid
-- order by writerid
-- ;
-- create view ad201to400 as
-- select writerid, count(adid) as AdSize201to400
-- from (writtenBy natural join (advertisement natural join Picturead))
-- where (length * width) <= 400 AND (length * width) > 201 
-- group by writerid
-- order by writerid
-- ;

-- select *
-- from ad1to100;
-- select *
-- from ad101to200;
-- select * 
-- from ad201to400;

SELECT
    writer.writerid,
    writer.writername,
    COALESCE(ad1to100.AdSize1to100, 0) AS AdSize1to100,
    COALESCE(ad101to200.AdSize101to200, 0) AS AdSize101to200,
    COALESCE(ad201to400.AdSize201to400, 0) AS AdSize201to400
FROM
    writer
LEFT JOIN
    ad1to100 ON writer.writerid = ad1to100.writerid
LEFT JOIN
    ad101to200 ON writer.writerid = ad101to200.writerid
LEFT JOIN
    ad201to400 ON writer.writerid = ad201to400.writerid
ORDER BY
    writer.writerid;
