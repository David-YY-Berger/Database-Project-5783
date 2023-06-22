import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

public class DbMetadata {
    private List<Query> queries = new ArrayList<>();
    private HashMap<String, String> tableNameId= new HashMap<String, String>();
    public DbMetadata() {

        //first term is important! case sensitive
        tableNameId.put("Writer", "writerid");
        tableNameId.put("Advertisement", "adid");
        //and so on..


        queries.add(new Query("Query 0 - CRUD operations on Tables", ""));

        queries.add(new Query("Query 1 - BestNewspapers",
                "SELECT\n" +
                        "    platANDapp.PLATFORMNAME, platANDapp.adtypeappears, platANDapp.totalAppearances,\n" +
                        "    CAST(n.numcopiesprinted / platANDapp.AvgPricePerAd AS DECIMAL(10,2)) AS CopyPerShekel,\n" +
                        "    platANDapp.AvgPricePerAd, n.numcopiesprinted\n" +
                        "FROM\n" +
                        "    platANDapp, newspaper n\n" +
                        "WHERE\n" +
                        "        platANDapp.PLATFORMNAME = n.platformname AND (platANDapp.platformType = 'NewspaperType')\n" +
                        "ORDER BY\n" +
                        "    CopyPerShekel DESC;"
                ));
        queries.add(new Query("Query 2 - WriterPicAdsBySize",
                "-- create view ad1to100 as\n" +
                        "-- select writerid, count(adid) as AdSize1to100\n" +
                        "-- from (writtenBy natural join (advertisement natural join Picturead))\n" +
                        "-- where (length * width) <= 100 AND (length * width) > 0\n" +
                        "-- group by writerid\n" +
                        "-- order by writerid;\n" +
                        "-- ;\n" +
                        "-- create view ad101to200 as\n" +
                        "-- select writerid, count(adid) as AdSize101to200\n" +
                        "-- from (writtenBy natural join (advertisement natural join Picturead))\n" +
                        "-- where (length * width) <= 200 AND (length * width) > 101 \n" +
                        "-- group by writerid\n" +
                        "-- order by writerid\n" +
                        "-- ;\n" +
                        "-- create view ad201to400 as\n" +
                        "-- select writerid, count(adid) as AdSize201to400\n" +
                        "-- from (writtenBy natural join (advertisement natural join Picturead))\n" +
                        "-- where (length * width) <= 400 AND (length * width) > 201 \n" +
                        "-- group by writerid\n" +
                        "-- order by writerid\n" +
                        "-- ;\n" +
                        "\n" +
                        "-- select *\n" +
                        "-- from ad1to100;\n" +
                        "-- select *\n" +
                        "-- from ad101to200;\n" +
                        "-- select * \n" +
                        "-- from ad201to400;\n" +
                        "\n" +
                        "SELECT\n" +
                        "    writer.writerid,\n" +
                        "    writer.writername,\n" +
                        "    COALESCE(ad1to100.AdSize1to100, 0) AS AdSize1to100,\n" +
                        "    COALESCE(ad101to200.AdSize101to200, 0) AS AdSize101to200,\n" +
                        "    COALESCE(ad201to400.AdSize201to400, 0) AS AdSize201to400\n" +
                        "FROM\n" +
                        "    writer\n" +
                        "LEFT JOIN\n" +
                        "    ad1to100 ON writer.writerid = ad1to100.writerid\n" +
                        "LEFT JOIN\n" +
                        "    ad101to200 ON writer.writerid = ad101to200.writerid\n" +
                        "LEFT JOIN\n" +
                        "    ad201to400 ON writer.writerid = ad201to400.writerid\n" +
                        "ORDER BY\n" +
                        "    writer.writerid;"
    ));
        queries.add(new Query("Query 3 - BestWriters",
                "select distinct WRITERNAME, NUMYEARSOFEXPERIENCE, \n" +
                "       cast(NUMYEARSOFEXPERIENCE/PAYPERHOUR*100 as decimal(10,2)) as Raiting,\n" +
                "       (select count(*)\n" +
                "       from Writtenby WB\n" +
                "       where WB.WRITERID = W.WRITERID) as numOfAdds\n" +
                "from (select * from Writer natural join Writtenby) W\n" +
                "order by Raiting desc"));

        queries.add(new Query("Query 4 - BestAds",
                "select A.ADNAME, W.Numyearsofexperience, A.HOURSTOWRITE, WB.ADTYPEWRITTEN\n" +
                        "from Writer W natural join Writtenby WB natural join Advertisement A\n" +
                        "order by W.Numyearsofexperience desc, A.HOURSTOWRITE desc"));
        queries.add(new Query("Query 5 - pricePaidPerAdOrderedByDate",
                "select a.datepublished, a.pricetopayplatform, a.platformtype\n" +
                        "from appearson a\n" +
                        "order by a.datepublished"));
        queries.add(new Query("Query 6 - AllAdsOrganizedByPlatform",
                "select platform.platformname, count(*)\n" +
                        "from appearsOn, platform\n" +
                        "where appearson.platformname = platform.platformname\n" +
                        "group by platform.platformname"));

        queries.add(new Query("Function 2 - Get Total Price between Given Dates",
                "").setIsFunction(true));

    }

    public String getIdFromTableName(String tableName) {
        return tableNameId.get(tableName);
    }
    public List<Query> getQueries() {
        return queries;
    }
    public List<String> getQueriesStrings() {
        List<String> res = new ArrayList<>();
        for (Query q:queries
        ) {
            res.add(q.toString());
        }
        return res;
    }
}
