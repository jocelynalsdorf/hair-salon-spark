import java.util.List;
import org.sql2o.*;


public class Client {

	private int id;
  private String description;
  private int stylist_Id;

  public int getId() {
    return id;
  }

  public int getStylistId() {
    return stylist_Id;
  }

  public String getDescription() {
    return description;
  }

  public Client(String description, int stylist_Id) {
    this.description = description;
    this.stylist_Id = stylist_Id;
  }

 public static List<Client> all() {
  String sql = "SELECT id, description, stylist_Id FROM clients";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Client.class);
  }
 }
 
}//end of client class