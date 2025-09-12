package edu.eci.arsw.blueprints.filters.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Component("redundancyFilter")
public class RedundancyFilter implements BlueprintFilter {

    @Override
    public Blueprint applyFilter(Blueprint blueprint) {
        List<Point> filteredPoints = new ArrayList<>();
        Point prev = null;
        for (Point p : blueprint.getPoints()) {
            if (prev == null || !(prev.getX() == p.getX() && prev.getY() == p.getY())) {
                filteredPoints.add(p);
            }
            prev = p;
        }
        return new Blueprint(blueprint.getAuthor(), blueprint.getName(),
                filteredPoints.toArray(new Point[0]));
    }
}
