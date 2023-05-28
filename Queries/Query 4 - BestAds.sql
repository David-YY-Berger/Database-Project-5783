select A.ADNAME, W.Numyearsofexperience, A.HOURSTOWRITE, WB.ADTYPEWRITTEN
from Writer W natural join Writtenby WB natural join Advertisement A
order by W.Numyearsofexperience desc, A.HOURSTOWRITE desc
