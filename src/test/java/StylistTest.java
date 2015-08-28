import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.Arrays;

public class StylistTest {

	@Rule
	public DatabaseRule database = new DatabaseRule();

	@Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

	@Test
	  public void equals_returnsTrueIfNamesAreTheSame() {
	    Stylist firstStylist = new Stylist("Becky");
	    Stylist secondStylist = new Stylist("Becky");
	    assertTrue(firstStylist.equals(secondStylist));
	  }
	  
	@Test
	  public void save_savesIntoDatabase_true() {
	    Stylist newStylist = new Stylist("Becky");
	    newStylist.save();
	    assertTrue(Stylist.all().get(0).equals(newStylist));
	  }

	@Test
	  public void find_findsStylistInDatabase_true() {
	    Stylist myStylist = new Stylist("Becky");
	    myStylist.save();
	    Stylist savedStylist = Stylist.find(myStylist.getId());
	    assertTrue(myStylist.equals(savedStylist));
	  }

	@Test
	  public void getClients_retrievesAllClientsFromDatabase_clientsList() {
	    Stylist myStylist = new Stylist("Banking");
	    myStylist.save();
	    Client firstClient = new Client("steal money", myStylist.getId());
	    firstClient.save();
	    Client secondClient = new Client("lots of money", myStylist.getId());
	    secondClient.save();
	    Client[] clients = new Client[] { firstClient, secondClient };
	    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
	  }

	@Test
	  public void delete_deletesStylistFromDatabase_true() {
	    Stylist myStylist = new Stylist("Becky");
	    myStylist.save();
	    myStylist.delete();
	    assertEquals(Stylist.all().size(), 0);
	  }

	@Test
	  public void update_changesStylistNameInDatabase_true() {
	    Stylist myStylist = new Stylist("Becky");
	    myStylist.save();
	    String name = "Mable";
	    myStylist.update(name);
	    assertTrue(Stylist.all().get(0).getName().equals(name));
	  }

 }//end of testclass