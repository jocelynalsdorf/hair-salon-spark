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



 }//end of testclass