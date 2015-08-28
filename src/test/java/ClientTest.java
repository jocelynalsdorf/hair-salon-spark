import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class ClientTest {

	@Rule
	public DatabaseRule database = new DatabaseRule();

	@Test
	public void all_emptyAtFirst() {
	  assertEquals(Client.all().size(), 0);
	}

	@Test
	public void equals_returnsTrueIfDescriptionsAreTheSame() {
	  Client firstClient = new Client("Tom", 1);
	  Client secondClient = new Client("Tom", 1);
	  assertTrue(firstClient.equals(secondClient));
	}

	@Test
	public void equals_returnsFalseIfDescriptionsAreTheSame() {
		Client firstClient = new Client("Tom", 2);
	  Client secondClient = new Client("Tom", 1);
		assertTrue(!firstClient.equals(secondClient));
	}

	@Test
	public void save_savesIntoDatabase_true() {
		Client myClient = new Client("Ted", 1);
		myClient.save();
		assertTrue(Client.all().get(0).equals(myClient));
	}



 }//end of test class