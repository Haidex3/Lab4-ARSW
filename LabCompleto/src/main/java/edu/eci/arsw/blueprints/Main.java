package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.config.AppConfig;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        BlueprintsServices services = ctx.getBean(BlueprintsServices.class);

        try {
            Blueprint bp = new Blueprint("Emily", "Casa", new Point[]{
                    new Point(10, 10), new Point(20, 20), new Point(30, 30)
            });

            services.addNewBlueprint(bp);

            Blueprint loaded = services.getBlueprint("Emily", "Casa");
            System.out.println("Recovered blueprint: " + loaded);

            System.out.println("All blueprints: " + services.getAllBlueprints());

            System.out.println("Emily's Blueprints: " + services.getBlueprintsByAuthor("Emily"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            ctx.close();
        }
    }
}

