package edu.eci.arsw.blueprints.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

@Service
public class BlueprintsServices {

    @Autowired
    BlueprintsPersistence bpp;

    @Autowired
    @Qualifier("redundancyFilter")
    BlueprintFilter filter;

    public void addNewBlueprint(Blueprint bp) {
        try {
            bpp.saveBlueprint(bp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Blueprint> getAllBlueprints() {
        Set<Blueprint> originals = bpp.getAllBlueprints();
        Set<Blueprint> filtered = new HashSet<>();
        for (Blueprint bp : originals) {
            filtered.add(filter.applyFilter(bp));
        }
        return filtered;
    }

    public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
        Blueprint bp = bpp.getBlueprint(author, name);
        return filter.applyFilter(bp);
    }

    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> originals = bpp.getBlueprintsByAuthor(author);
        Set<Blueprint> filtered = new HashSet<>();
        for (Blueprint bp : originals) {
            filtered.add(filter.applyFilter(bp));
        }
        return filtered;
    }
}
