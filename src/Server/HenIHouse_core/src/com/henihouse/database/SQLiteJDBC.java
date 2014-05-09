import java.sql.*;

public class SQLiteJDBC
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:/root/HenIHouse/database/instance/HenIHouse.db");

      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM C_POSITION;" );
      while ( rs.next() ) {
         int id = rs.getInt("ID");
         int floor_lev = rs.getInt("FLOOR_LEVEL");
         int front_wall  = rs.getInt("DIST_FRONT_WALL");
         int left_wall = rs.getInt("DIST_LEFT_WALL");
         String  desc = rs.getString("DESC");
         System.out.println( "ID = " + id );
         System.out.println( "FLOOR_LEVEL = " + floor_lev );
         System.out.println( "DIST_FRONT_WALL = " + front_wall);
         System.out.println( "DIST_LEFT_WALL = " + left_wall);
         System.out.println( "DESC = " + desc );
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();

    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");
  }
}