package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import game.Company;
import game.Game;
import game.Read;
import game.Sort;
import game.Tuple;

public class SortTest {
    
    Read r = new Read();
    Sort s = new Sort();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testByGameNumber() {
        HashMap<String, Company> companyMap = r.getCompanyMap();
        Tuple t = new Tuple(100,155);
        Map<String, Integer> map = s.byGameNumber(companyMap, false, t);
        assertEquals(map.size(), 2);
        assertEquals((int) map.get("Big Fish Games"), 155);
    }
    
    @Test
    public void testByAvgRating() {
        HashMap<String, Company> companyMap = r.getCompanyMap();
        Tuple t = new Tuple(0.0,8.8);
        Map<String, Double> map = s.byAvgRating(companyMap, true, t);
        assertEquals(map.size(), 8812);
        assertEquals((double) map.get("Boncho Games"), 8.8, 0.01);
    }
    
    @Test
    public void testByPrice() {
        HashMap<Integer, Game> gameMap = r.getGameMap();
        game.Tuple t = new game.Tuple(3.99, 5.99);
        Map<String, Double> map = s.byPrice(gameMap, true, t);
        assertEquals(map.size(), 5381);
        assertEquals((double) map.get("Team Fortress Classic"), 3.99, 0.01);
    }
    
    @Test
    public void testByReleaseYear() {
        HashMap<Integer, Game> gameMap = r.getGameMap();
        game.Tuple t = new game.Tuple(2001, 2004);
        Map<String, Integer> map = s.byReleaseYear(gameMap, false, t);
        assertEquals(map.size(), 13);
        assertEquals((int) map.get("Day of Defeat"), 2003);
    }
    
    @Test
    public void testTotalRatings() {
        HashMap<Integer, Game> gameMap = r.getGameMap();
        game.Tuple t = new game.Tuple(57, 1785);
        Map<String, Integer> map = s.byTotalRatings(gameMap, false, t);
        assertEquals(map.size(), 7525);
        assertEquals((int) map.get("A Dance of Fire and Ice"), 1570);
    }
    
    @Test
    public void testRatings() {
        HashMap<Integer, Game> gameMap = r.getGameMap();
        game.Tuple t = new game.Tuple(9.01, 9.48);
        Map<String, Double> map = s.byRatings(gameMap, false, t);
        assertEquals(map.size(), 1794);
        assertEquals((double) map.get("BLARP!"), 9.48, 0.01);
    }
    
//    @Test
//    public void testByCompany() {
//        HashMap<String, Company> companyMap = r.getCompanyMap();
//        HashMap<Integer, Game> gameMap = r.getGameMap();
//        game.Tuple t = new game.Tuple(2, 4);
//        Map<String, Object> map = s.byCompany(companyMap, gameMap, false, t, true);
//        System.out.println(map);
////        assertEquals(map.size(), 1794);
////        assertEquals((double) map.get("BLARP!"), 9.48, 0.01);
//    }


}
