package game;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public interface ISort {
    
    /**
     * sort by average of ratings
     * @return a sorted map with key being the company name and value being average ratings
     */
    public Map<String, Double> byAvgRating(Map<String, Company> map, boolean order, 
            Tuple<Double, Double> interval);
    
    /**
     * sort the average of price
     * @return a sorted map with key being the game name and value being the price
     */
    public TreeMap<Double, TreeSet<Entry<Integer, Game>>> byPrice(Set<Game> games);
    
    /**
     * compare the release year
     * @return a sorted map with key being the game name and value being the released year
     */
    public Map<String, Integer> byReleaseYear(Map<Integer, Game> map, boolean order,
            Tuple<Integer, Integer> interval);
    
    /**
     * sort the total ratings
     * @return a sorted map with key being the game name
     * and the value being the total number of ratings
     */
    public TreeMap<Integer, TreeSet<Entry<Integer, Game>>> byTotalRatings(
            Map<Integer, Game> map);
    
    /**
     * Return all games that has total ratings greater than or equal to total ratings
     * @param map Game map sorted by total number of ratings
     * @return game set
     */
    public Set<Game> byLeastTotalRating(TreeMap<Integer, 
            TreeSet<Entry<Integer, Game>>> map, int totalRatings);
    
    /**
     * sort the ratings
     * @param games Game set after filtering by least total ratings
     * @return a sorted map with key being the game's rating
     * and the value being the total number of ratings
     */
    public TreeMap<Double, TreeSet<Entry<Integer, Game>>> byRating(Set<Game> games);
    
    /**
     * sort by company
     * @return a sorted map with key being the game name
     * and the value being the selected company sort method
     */
    public List<Game> byCompany(Map<Integer, Game> gameMap, boolean order, String company);
    
    /**
     * search by tag
     * @param games Game set after filtering by least total ratings
     * @param tagMap tag map of tag-game names pair
     * @param tag A specific tag
     * @return
     */
    public Set<Game> searchByTag(Set<Game> games, Map<String, Set<String>> tagMap, 
            String tag);
    
    /**
     * Search all results by conditional search.
     * @param games
     * @param priceString
     * @param ratingString
     * @param tag
     * @param order
     * @param tagMap
     * @return
     */
    public TreeSet<Entry<Integer, Game>> byThreeConditions(Set<Game> games, 
            String priceString, String ratingString, String tag, boolean order, 
            Map<String, Set<String>> tagMap);
    
    
}
