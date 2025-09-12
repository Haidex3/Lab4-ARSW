package edu.eci.arsw.blueprints;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.eci.arsw.blueprints.filters.impl.RedundancyFilter;
import edu.eci.arsw.blueprints.filters.impl.SubsamplingFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;


public class BlueprintFilterTest {

    private Blueprint redundantBlueprint;
    private Blueprint normalBlueprint;

    @Before
    public void setUp() {
        redundantBlueprint = new Blueprint("Ana", "PlanoRedundante", new Point[]{
                new Point(0,0),
                new Point(0,0),
                new Point(10,10),
                new Point(10,10), 
                new Point(20,20)
        });

        normalBlueprint = new Blueprint("Luis", "PlanoSubmuestreo", new Point[]{
                new Point(1,1),
                new Point(2,2),
                new Point(3,3),
                new Point(4,4)
        });
    }

    @Test
    public void testRedundancyFilterRemovesConsecutiveDuplicates() {
        RedundancyFilter filter = new RedundancyFilter();
        Blueprint filtered = filter.applyFilter(redundantBlueprint);

        Assert.assertEquals("Debe quedar con 3 puntos", 
                3, filtered.getPoints().size());
        Assert.assertEquals(0, filtered.getPoints().get(0).getX());
        Assert.assertEquals(10, filtered.getPoints().get(1).getX());
        Assert.assertEquals(20, filtered.getPoints().get(2).getX());
    }

    @Test
    public void testSubsamplingFilterKeepsEveryOtherPoint() {
        SubsamplingFilter filter = new SubsamplingFilter();
        Blueprint filtered = filter.applyFilter(normalBlueprint);

        Assert.assertEquals("Debe quedar con 2 puntos",
                2, filtered.getPoints().size());
        Assert.assertEquals(1, filtered.getPoints().get(0).getX());
        Assert.assertEquals(3, filtered.getPoints().get(1).getX());
    }
}
