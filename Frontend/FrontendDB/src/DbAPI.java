import javax.swing.text.html.Option;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class DbAPI {
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;
    String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // Replace with your Oracle JDBC URL
    String username = "system"; // Replace with your username
    String password = "BH"; // Replace with your password
    DbMetadata dbMetadata = new DbMetadata();

    /**
     * first item of list is column names!
     * @param tableName
     * @return
     */
    public List<List<String>> selectAllFromTable(String tableName, Optional<String> optionalCond) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            List<List<String>> queryResults = new ArrayList<>();

            // Execute the query
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + tableName;
            if( !Objects.isNull(optionalCond) &&  optionalCond.isPresent() && !optionalCond.get().isEmpty()) {
                query += " " + optionalCond.get();
            }
            ResultSet resultSet = statement.executeQuery( query );
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            List<String> colNames = new ArrayList<>();
            // The column count starts from 1
            for (int i = 1; i <= columnCount; i++ ) {
                colNames.add(rsmd.getColumnName(i));
            }
            queryResults.add(colNames);
            // Retrieve the values from the result set
            while (resultSet.next()) {
                List<String> rowValues = new ArrayList<>();
                for (int i = 0; i < columnCount; i++) {
                    rowValues.add(resultSet.getString(colNames.get(i)));
                }
                queryResults.add(rowValues);
            }

//            // Print the result - for debugging
//            for (List<String> row : queryResults) {
//                for (String col: row
//                     ) {
//                    System.out.print(col + " ");
//                }
//                System.out.println("");
//            }

            return queryResults;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void runUpdateQuery(String tableName, List<List<String>> lst, List<String> idList) {
        String query = "UPDATE " + tableName + "\n";
        query += "Set ";
        for (int i = 0; i < lst.get(0).size(); i++) {
            query += lst.get(0).get(i) + " = '" + lst.get(1).get(i) + "', ";
        }
        //remove last comma:
        query = query.substring(0, query.length() - 2);
        query += "\n Where " + idList.get(0) + " = " + idList.get(1) + "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            // Execute the query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery( query );
            ResultSetMetaData rsmd = resultSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<List<String>> runViewQuery(Query q){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            List<List<String>> queryResults = new ArrayList<>();

            // Execute the query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    q.content
            );
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            List<String> colNames = new ArrayList<>();
            // The column count starts from 1
            for (int i = 1; i <= columnCount; i++ ) {
                colNames.add(rsmd.getColumnName(i));
            }
            queryResults.add(colNames);
            // Retrieve the values from the result set
            while (resultSet.next()) {
                List<String> rowValues = new ArrayList<>();
                for (int i = 0; i < columnCount; i++) {
                    rowValues.add(resultSet.getString(colNames.get(i)));
                }
                queryResults.add(rowValues);
            }

//            // Print the result - for debugging
//            for (List<String> row : queryResults) {
//                for (String col: row
//                     ) {
//                    System.out.print(col + " ");
//                }
//                System.out.println("");
//            }

            return queryResults;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getIdFromTableName(String tableName) {
        return dbMetadata.getIdFromTableName(tableName);
    }
}