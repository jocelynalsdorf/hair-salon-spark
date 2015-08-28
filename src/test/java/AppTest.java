import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.junit.rules.ExternalResource;
import org.sql2o.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest{
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver(){
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Hair Salon");
  }

  @Test
  public void stylistIsDisplayedWhenCreated() {
    goTo("http://localhost:4567/");
    fill("#name").with("Becky");
    submit(".submit-name");
    assertThat(pageSource()).contains("Becky");
  }

  @Test
  public void categoryIsDeleted() {
    Stylist myStylist = new Stylist("Becky");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    submit(".btn-danger");
    assertThat(pageSource()).doesNotContain("Becky");
  }

  @Test
  public void stylistIsUpdated() {
    Stylist myStylist = new Stylist("becky");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    click("a", withText("Update Stylist Info"));
    fill("#name").with("rebecca");
    submit(".btn-success");
    assertThat(pageSource()).contains("rebecca");
  }

  @Test
  public void clientIsDisplayedWhenCreated() {
    Stylist myStylist = new Stylist("joan");
    myStylist.save();
    goTo("http://localhost:4567/");
    fill("#description").with("sam");
    submit(".btn-client");
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("sam");
  }

  @Test
  public void allclientsDisplayDescriptionOnStylistPage() {
    Stylist myStylist = new Stylist("Leon");
    myStylist.save();
    Client firstClient = new Client("fred", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("greg", myStylist.getId());
    secondClient.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("fred");
    assertThat(pageSource()).contains("greg");
  }

  @Test
  public void clientIsDeleted() {
    Stylist myStylist = new Stylist("jim");
    myStylist.save();
    Client firstClient = new Client("tim", myStylist.getId());
    Client secondClient = new Client("tom", myStylist.getId()); 
    firstClient.save();
    secondClient.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    submit(".tim");
    click("a", withText("jim"));
    assertThat(pageSource()).doesNotContain("tim");
    assertThat(pageSource()).contains("tom");
  }

  @Test
  public void clientIsUpdated(){
    Stylist myStylist = new Stylist("mable");
    myStylist.save();
    Client firstClient = new Client("mike", myStylist.getId());
    firstClient.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    click("a", withText("Edit Client"));
    fill("#description").with("tony");
    submit(".btn-success");
    click("a", withText("mable"));
    assertThat(pageSource()).contains("tony");
  }

}