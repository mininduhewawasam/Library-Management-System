package connection;

import java.sql.*;

public class DBconnection {

    private Connection connection;

    //Database connection method.
    public Connection getConnection() {

        String dbName = "cw";
        String userName = "root";
        String password = "";

        try{

            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return connection;

    }


    public int returnCount(String query, String column) throws SQLException {

        Connection connection = getConnection();
        int count = 0;

        PreparedStatement ps = connection.prepareStatement(query);

        ResultSet rs = ps.executeQuery(query);
        while (rs.next()) {
            count = rs.getInt(column);
        }

        return count;

    }

}
