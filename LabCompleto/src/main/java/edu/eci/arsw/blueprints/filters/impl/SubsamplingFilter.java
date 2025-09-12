package edu.eci.arsw.blueprints.filters.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Component("subsamplingFilter")
public class SubsamplingFilter implements BlueprintFilter {

    @Override
    public Blueprint applyFilter(Blueprint blueprint) {
        List<Point> filteredPoints = new ArrayList<>();
        List<Point> points = blueprint.getPoints();
        for (int i = 0; i < points.size(); i++) {
            if (i % 2 == 0) { // conserva 1 de cada 2 puntos
                filteredPoints.add(points.get(i));
            }
        }
        return new Blueprint(blueprint.getAuthor(), blueprint.getName(),
                filteredPoints.toArray(new Point[0]));
    }
}
