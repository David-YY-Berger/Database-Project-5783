select distinct WRITERNAME, NUMYEARSOFEXPERIENCE, 
       cast(NUMYEARSOFEXPERIENCE/PAYPERHOUR*100 as decimal(10,2)) as Raiting,
       (select count(*)
       from Writtenby WB
       where WB.WRITERID = W.WRITERID) as numOfAdds
from (select * from Writer natural join Writtenby) W
order by Raiting desc
