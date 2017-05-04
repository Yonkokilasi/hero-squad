import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
      staticFileLocation("/public");
      String layout = "templates/layout.vtl";

      ProcessBuilder process = new ProcessBuilder();
      Integer port;
      if (process.environment().get("PORT") != null) {
          port = Integer.parseInt(process.environment().get("PORT"));
      } else {
          port = 4567;
      }

     setPort(port);

      get("/", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object> ();
          model.put("categories",Category.all());
          model.put("squad","templates/index.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/isrealites", (request,response) -> {
            Map<String, Object> model =  new HashMap <String, Object>();
            ArrayList<Hero> heroes = request.session().attribute("heroes");
            if (heroes == null) {
                heroes = new ArrayList<Hero>();
                request.session().attribute("heroes" , heroes);
            }
            String name = request.queryParams("name");

            int age = Integer.parseInt(request.queryParams("age"));

            String power = request.queryParams("power");

            String weakness = request.queryParams("weakness");

            Hero newHero = new Hero(name,age,power,weakness);
            model.put("newHero" , newHero);
            heroes.add(newHero);

            model.put("template", "templates/hero.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

          get("/added", (request, response) -> {
              Map<String, Object> model = new HashMap<String, Object> ();
              model.put("heroes", request.session().attribute("heroes"));
              model.put("template","templates/success.vtl");
              return new ModelAndView(model, layout);
            }, new VelocityTemplateEngine());
      }
}
