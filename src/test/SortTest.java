package test;

import static org.junit.Assert.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import constant.Constants;
import game.Company;
import game.Game;
import game.Sort;
import helper.LoaderHelper;

public class SortTest {
    
    Map<Integer, Game> originGamesMap;
    Map<Integer, Game> fullGamesMap;
    Map<String, Company> originCompMap;
    Map<String, Set<String>> tagMap;
    TreeMap<Integer, TreeSet<Entry<Integer, Game>>> mapByTotalRating;
    
    Sort s = new Sort();
    
    private static final double DELTA = 0.001;

    @Before
    public void setUp() throws Exception {
        originGamesMap = LoaderHelper.readGames(Constants.GAME_TEST_FILE);
        originCompMap = LoaderHelper.readCompany(Constants.COMPANY_FILE);
        fullGamesMap = LoaderHelper.readGames(Constants.GAME_FILE);
        tagMap = LoaderHelper.readTags(Constants.TAG_FILE);
        mapByTotalRating = s.byTotalRatings(originGamesMap);
    }
    
    @Test
    public void testByPrice() {
        Set<Game> set = s.byLeastTotalRating(mapByTotalRating, 0);
        TreeMap<Double, TreeSet<Entry<Integer, Game>>> map = s.byPrice(set);
        assertEquals(6, map.size());
        assertEquals(0, map.firstKey(), DELTA);
        assertEquals(39.99, map.lastKey(), DELTA);
        TreeSet<Entry<Integer, Game>> treeSet = map.get(3.99);
        assertEquals(7, treeSet.size());
        assertEquals("Day of Defeat", treeSet.first().getValue().getName());
        assertEquals("Zombie Exodus: Safe Haven", treeSet.last().getValue().getName());
    }
    
    @Test
    public void testByReleaseYear() {
        Set<Game> set = s.byLeastTotalRating(mapByTotalRating, 0);
        TreeMap<Integer, TreeSet<Entry<Integer, Game>>> map = s.byReleaseYear(set);
        assertEquals(9, map.size());
        TreeSet<Entry<Integer, Game>> treeSet = map.get(2016);
        assertEquals(4, treeSet.size());
        assertEquals(413150, (int) treeSet.first().getKey());
        assertEquals(275850, (int) treeSet.last().getKey());
    }
    
    @Test
    public void testTotalRatings() {
        assertEquals(15, mapByTotalRating.size());
        assertEquals(91, (int) mapByTotalRating.firstKey());
        assertEquals(127873, (int) mapByTotalRating.lastKey());
    }
    
    @Test
    public void testByLeastTotalRating() {
        Set<Game> set = s.byLeastTotalRating(mapByTotalRating, 100000);
        assertEquals(6, set.size());
    }
    
    @Test
    public void testByRating() {
        Set<Game> set = s.byLeastTotalRating(mapByTotalRating, 0);
        TreeMap<Double, TreeSet<Entry<Integer, Game>>> map = s.byRating(set);
        assertEquals(15, map.size());
        assertEquals(9.739, map.firstKey(), DELTA);
        assertEquals(5.29, map.lastKey(), DELTA);
    }
    
    @Test
    public void testSearchByTag() {
        Set<Game> set = s.byLeastTotalRating(mapByTotalRating, 0);
        Set<Game> result = s.searchByTag(set, tagMap, "Action");
        assertEquals(8, result.size());
        result = s.searchByTag(set, tagMap, "RPG");
        assertEquals(2, result.size());
    }
    
    @Test
    public void testByThreeConditions() {
        Set<Game> set = s.byLeastTotalRating(mapByTotalRating, 0);
        TreeSet<Entry<Integer, Game>> treeSet = s.byThreeConditions(set, 
                "0 - 9.99", "8 - 9.999", "Action", true, tagMap);
        assertEquals(8, treeSet.size());
        assertEquals(10, (int) treeSet.first().getKey());
        assertEquals(60, (int) treeSet.last().getKey());
        
        treeSet = s.byThreeConditions(set, 
                "0 - 9.99", "8 - 9.999", "Action", false, tagMap);
        assertEquals(60, (int) treeSet.first().getKey());
        assertEquals(10, (int) treeSet.last().getKey());
        
        treeSet = s.byThreeConditions(set, 
                "-5 - 4.99", "8 - 9.999", "Action", true, tagMap);
        assertEquals(5, treeSet.size());
        
        treeSet = s.byThreeConditions(set, "", "", "", true, tagMap);
        assertTrue(treeSet.isEmpty());
    }

    @Test
    public void testByCompany() {
        TreeMap<String, TreeSet<Entry<Integer, Game>>> map = 
                s.byCompany(originCompMap, fullGamesMap);
        assertEquals("Big Fish Games", map.firstKey());
        assertEquals(453390, (int) map.firstEntry().getValue().last().getKey());
        assertEquals(596270, (int) map.firstEntry().getValue().first().getKey());
    }
}
