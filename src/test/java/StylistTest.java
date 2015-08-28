import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

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

 }//end of testclass