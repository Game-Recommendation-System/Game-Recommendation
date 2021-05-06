import java.util.Map;

public interface ISort {

    /**
     * sort number of games
     * @return a sorted map with key being the company name and value being number of games
     */
    Map<String, Integer> byGameNumber(Map<String, Company> map, boolean order, Tuple interval);
    
    /**
     * sort the average of ratings
     * @return a sorted map with key being the company name and value being average ratings
     */
    public Map<String, Double> byAvgRating(Map<String, Company> map, boolean order, Tuple interval);
    
    /**
     * sort the average of price
     * @return a sorted map with key being the game name and value being the price
     */
    public Map<String, Double> byPrice(Map<Integer, Game> map, boolean order, Tuple interval);
    
    /**
     * compare the release year
     * @return a sorted map with key being the game name and value being the released year
     */
    public Map<String, Integer> byReleaseYear(Map<Integer, Game> map, boolean order, Tuple interval);
    
    /**
     * sort the total ratings
     * @return a sorted map with key being the game name
     * and the value being the total number of ratings
     */
    public Map<String, Integer> byTotalRatings(Map<Integer, Game> map, boolean order, Tuple interval);
    
    /**
     * sort the ratings
     * @return a sorted map with key being the game name
     * and the value being the total number of ratings
     */
    public Map<String, Double> byRatings(Map<Integer, Game> map, boolean order, Tuple interval);
    
//    /**
//     * sort by company
//     * @return a sorted map with key being the game name
//     * and the value being the selected company sort method
//     */
//    public Map<String, Object> byCompany(Map<String, Company> compMap, Map<Integer, Game> gameMap,
//            boolean order, Tuple interval, boolean num);

    
    
    
}
