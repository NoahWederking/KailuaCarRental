import java.sql.*;

public class Controller {

    // Attributes
    public static final String database_url = "jdbc:mysql://localhost:3306/carrental";
    public java.sql.Connection con;
    public static final String database_url_Noah = "jdbc:mysql://localhost:3306/carrental?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    // Methods
    public void setupNCon(String sqlQuery) {
        try {
            //Connecting to database
            con = DriverManager.getConnection(database_url, "root", "Sommersko2008");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlQuery);
            //MetaData gets columnname, number of columns, column type
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            int j = 1;
            while (rs.next()) {
                System.out.print(j+". ");
                j++;
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(", ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));

                }
                System.out.println("");
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    public void delete(String sqlQuery){
        try {
            con = DriverManager.getConnection(database_url, "root", "Sommersko2008");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlQuery);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

    }


}
