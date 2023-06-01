
select writername, numAds
from (select w.writername, w.writerid, count(*) as numAds
      from writer w, writtenBy wb
      where w.writerid = wb.writerid
      group by w.writerid, w.writername
      )
where numAds > '&numberOfAdds'
