import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    
    Connection connect;

    Database(){
        String url = "jdbc:postgresql://db.xrktgxajxxbwgmmpemuy.supabase.co:5432/postgres?user=postgres&password=Y1Vhx15QSbI0zO5G";
        
        try{
            Class.forName("org.postgresql.Driver");
        } catch(Exception classNotFoundException){
            System.out.println("class not found");
        }

        try{
            connect = DriverManager.getConnection(url);
        } catch (Exception sQLException){
            System.out.println("SQL Exception");
        }
    }

    Player addPlayer(String id, String firstName, String lastName, String codeName){
        try{
            Statement statement = connect.createStatement();

            // adds new row in database using player id
            String sql = "INSERT INTO player (id) VALUES (" + id + ")";
            statement.execute(sql);
           
            // inserts firstname into the row containing the player id
            sql = "UPDATE player SET first_name='"+ firstName + "' WHERE id=" + id;
            statement.execute(sql);
            
            // inserts lastname into the row containing the player id
            sql = "UPDATE player SET last_name='" + lastName + "' WHERE id=" + id;
            statement.execute(sql);

            // inserts codename into the row containing the player id
            sql = "UPDATE player SET codename='" + codeName + "' WHERE id=" + id;
            statement.execute(sql);
            
        } catch(Exception SQLException){
            System.out.println("SQL Exception");
        }
        
        return new Player(Integer.parseInt(id), codeName);
    }

    Player getExistingPlayer(int id){
        String codeName = null;
        try{
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM player WHERE id=" + id;
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            codeName = rs.getString("codename");
        } catch (Exception sQLException){
            System.out.println("Error getting player details");
        }

        return new Player(id, codeName);
    }


}
