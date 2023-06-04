select writerid, writername, adid, adname, adtypeappears, platformtype
from Writer w natural join Writtenby wb natural join Advertisement a natural join Appearson ao
where ao.datepublished>='&date1'
order by ao.datepublished
