select platform.platformname, count(*)
from appearsOn, platform
where appearson.platformname = platform.platformname
group by platform.platformname
