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

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getDescription().equals(newClient.getDescription()) &&
	      this.getId() == newClient.getId() &&
	      this.getStylistId() == newClient.getStylistId();
    }
  }

	public static List<Client> all() {
		String sql = "SELECT id, description, stylist_Id FROM clients";
		try(Connection con = DB.sql2o.open()) {
		  return con.createQuery(sql).executeAndFetch(Client.class);
		}
	}

	public void save() {
	  try(Connection con = DB.sql2o.open()) {
	    String sql = "INSERT INTO clients (description, stylist_Id) VALUES (:description, :stylist_Id)";
	    this.id = (int) con.createQuery(sql, true)
	      .addParameter("description", this.description)
	      .addParameter("stylist_Id", this.stylist_Id)
	      .executeUpdate()
	      .getKey();
	   }
	}



}//end of client class