/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("Emily", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("Haider", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("Haider", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("Haider", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
                
        
    }

    @Test
    public void testSaveAndGetBlueprint() throws Exception {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Blueprint bp = new Blueprint("Emily", "TestBP", new Point[]{new Point(0,0), new Point(10,10)});
        ibpp.saveBlueprint(bp);

        Blueprint loaded = ibpp.getBlueprint("Emily", "TestBP");
        assertNotNull(loaded);
        assertEquals("Emily", loaded.getAuthor());
        assertEquals("TestBP", loaded.getName());
    }

    @Test
    public void testGetBlueprintsByAuthor() throws Exception {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Blueprint bp1 = new Blueprint("Emily", "BP1", new Point[]{new Point(1,1)});
        Blueprint bp2 = new Blueprint("Emily", "BP2", new Point[]{new Point(2,2)});
        ibpp.saveBlueprint(bp1);
        ibpp.saveBlueprint(bp2);

        Set<Blueprint> emilyBps = ibpp.getBlueprintsByAuthor("Emily");
        assertEquals(2, emilyBps.size());
    }

    @Test
    public void testGetAllBlueprints() throws Exception {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Blueprint bp1 = new Blueprint("Emily", "BP1", new Point[]{new Point(1,1)});
        Blueprint bp2 = new Blueprint("Haider", "BP2", new Point[]{new Point(2,2)});
        ibpp.saveBlueprint(bp1);
        ibpp.saveBlueprint(bp2);

        Set<Blueprint> all = ibpp.getAllBlueprints();
        assertTrue(all.size() >= 2); 
    }


    
}
