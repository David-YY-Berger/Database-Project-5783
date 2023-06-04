select a.adid, a.adname, a.hourstowrite
from advertisement a
where a.hourstowrite>'&minHour' and a.hourstowrite<'&maxHour'
