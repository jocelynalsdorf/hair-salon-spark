import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
  staticFileLocation("/public");
  String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylists", (request, response) -> {
    HashMap<String,Object> model = new HashMap<String, Object>();
    String name = request.queryParams("name");
    Stylist newStylist = new Stylist(name);
    newStylist.save();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/stylists/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("stylist", Stylist.find(Integer.parseInt(request.params(":id"))));
    model.put("template", "templates/stylist.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylists/:id/delete", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Stylist stylist= Stylist.find(Integer.parseInt(request.params(":id")));
    model.put("template", "templates/index.vtl");
    stylist.delete();
    model.put("stylists", Stylist.all());
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());


  }//end of main
}//end of app class