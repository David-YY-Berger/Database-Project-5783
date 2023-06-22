import java.util.ArrayList;
import java.util.List;

public class Query {
    public String content;
    public String queryName;
    public boolean isFunction = false;



    public Query(String queryName, String content){

        this.content = content;
        this.queryName = queryName;
    }

    @Override
    public String toString() {
        return queryName;
    }
    public Query setIsFunction(boolean val){
        this.isFunction = val;
        return this;
    }
}
