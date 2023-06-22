import java.util.ArrayList;
import java.util.List;

public class Query {
    public String content;
    public String queryName;


    public Query(String queryName, String content){

        this.content = content;
        this.queryName = queryName;
    }

    @Override
    public String toString() {
        return queryName;
    }
}
