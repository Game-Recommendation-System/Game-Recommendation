package game;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.AbstractMap.SimpleEntry;

import constant.Constants;

public class Sort implements ISort {

    @Override
    public Map<String, Double> byAvgRating(Map<String, Company> map, boolean order, 
            Tuple<Double, Double> interval) {
        List<Entry<String, Company>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Company>>() {
            @Override
            public int compare(Entry<String, Company> o1, Entry<String, Company> o2) {
                Company o1C = (Company) o1.getValue();
                Company o2C = (Company) o2.getValue();

                if (!order) {
                    if (o1C.getAverageRating() < o2C.getAverageRating()) {
                        return 1;
                    }
                    if (o1C.getAverageRating() > o2C.getAverageRating()) {
                        return -1;
                    }
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    if (o1C.getAverageRating() > o2C.getAverageRating()) {
                        return 1;
                    }
                    if (o1C.getAverageRating() < o2C.getAverageRating()) {
                        return -1;
                    }
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });

        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Entry<String, Company> entry : list) {
            Double low = interval.getLeft();
            Double high = interval.getRight();
            if (entry.getValue().getAverageRating() >= low 
                    && entry.getValue().getAverageRating() <= high) {
                sortedMap.put(entry.getKey(), entry.getValue().getAverageRating());
            }

        }

        return sortedMap;
    }

    @Override
    public TreeMap<Double, TreeSet<Entry<Integer, Game>>> byPrice(Set<Game> games) {
        TreeMap<Double, TreeSet<Entry<Integer, Game>>> treeMap = new TreeMap<>();
        for (Game game : games) {
            double price = game.getPrice();
            if (!treeMap.containsKey(price)) {
                TreeSet<Entry<Integer, Game>> set = new TreeSet<>(
                        new Comparator<Entry<Integer, Game>>() {

                            @Override
                            public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
                                Game game1 = o1.getValue();
                                Game game2 = o2.getValue();                        
                                return game1.getName().compareTo(game2.getName());
                            }
                        });
                set.add(new AbstractMap.SimpleEntry<Integer, Game>(game.getId(), game));
                treeMap.put(price, set);
            } else {
                treeMap.get(price).add(
                        new AbstractMap.SimpleEntry<Integer, Game>(game.getId(), game));
            }
        }
        
        return treeMap;
    }

    @Override
    public Map<String, Integer> byReleaseYear(Map<Integer, Game> map, boolean order, 
            Tuple<Integer, Integer> interval) {
        List<Entry<Integer, Game>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, new Comparator<Entry<Integer, Game>>() {
            @Override
            public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
                Game o1G = (Game) o1.getValue();
                Game o2G = (Game) o2.getValue();

                if (!order) {
                    if (o1G.getReleaseYear() < o2G.getReleaseYear()) {
                        return 1;
                    }
                    if (o1G.getReleaseYear() > o2G.getReleaseYear()) {
                        return -1;
                    }
                    return o1G.getName().compareTo(o2G.getName());
                } else {
                    if (o1G.getReleaseYear() > o2G.getReleaseYear()) {
                        return 1;
                    }
                    if (o1G.getReleaseYear() < o2G.getReleaseYear()) {
                        return -1;
                    }
                    return o1G.getName().compareTo(o2G.getName());
                }
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Entry<Integer, Game> entry : list) {
            int low = interval.getLeft();
            int high = interval.getRight();
            if (entry.getValue().getReleaseYear() >= low 
                    && entry.getValue().getReleaseYear() <= high) {
                sortedMap.put(entry.getValue().getName(), entry.getValue().getReleaseYear());
            }

        }

        return sortedMap;
    }

    @Override
    public TreeMap<Integer, TreeSet<Entry<Integer, Game>>> byTotalRatings(
            Map<Integer, Game> map) {
        TreeMap<Integer, TreeSet<Entry<Integer, Game>>> treeMap = new TreeMap<>();
        for (Map.Entry<Integer, Game> entry : map.entrySet()) {
            Game game = entry.getValue();
            int totalRatings = game.getTotalNumberOfRatings();
            if (!treeMap.containsKey(totalRatings)) {
                TreeSet<Entry<Integer, Game>> set = new TreeSet<>(
                        new Comparator<Entry<Integer, Game>>() {

                            @Override
                            public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
                                Game game1 = o1.getValue();
                                Game game2 = o2.getValue();                        
                                return game1.getName().compareTo(game2.getName());
                            }
                        });
                set.add(new AbstractMap.SimpleEntry<Integer, Game>(game.getId(), game));
                treeMap.put(totalRatings, set);
            } else {
                treeMap.get(game.getTotalNumberOfRatings()).add(
                        new AbstractMap.SimpleEntry<Integer, Game>(game.getId(), game));
            }
        }
        
        return treeMap;
    }
    
    @Override
    public Set<Game> byLeastTotalRating(
            TreeMap<Integer, TreeSet<Entry<Integer, Game>>> map, int totalRatings) {
        Set<Game> set = new HashSet<>();
        SortedMap<Integer, TreeSet<Entry<Integer, Game>>> subMap = map.subMap(totalRatings, 
                        Constants.INFINITE);
        for (Map.Entry<Integer, TreeSet<Entry<Integer, Game>>> entry : subMap.entrySet()) {
            TreeSet<Entry<Integer, Game>> treeSet = entry.getValue();
            for (Map.Entry<Integer, Game> innerEntry : treeSet) {
                set.add(innerEntry.getValue());
            }
        }
        return set;
    }

    @Override
    public TreeMap<Double, TreeSet<Entry<Integer, Game>>> byRating(Set<Game> games) {
        TreeMap<Double, TreeSet<Entry<Integer, Game>>> treeMap = new TreeMap<>(
                new Comparator<Double>() {

                    @Override
                    public int compare(Double o1, Double o2) {
                        double result = o2 - o1;
                        if (result < 0) {
                            return -1;
                        } else if (result == 0) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                }
                );
        for (Game game : games) {
            double rating = game.getRating();
            if (!treeMap.containsKey(rating)) {
                TreeSet<Entry<Integer, Game>> set = new TreeSet<>(
                        new Comparator<Entry<Integer, Game>>() {

                            @Override
                            public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
                                Game game1 = o1.getValue();
                                Game game2 = o2.getValue();                        
                                return game1.getName().compareTo(game2.getName());
                            }
                        });
                set.add(new AbstractMap.SimpleEntry<Integer, Game>(game.getId(), game));
                treeMap.put(rating, set);
            } else {
                treeMap.get(rating).add(
                        new AbstractMap.SimpleEntry<Integer, Game>(game.getId(), game));
            }
        }
        
        return treeMap;
    }

    @Override
    public List<Game> byCompany(Map<Integer, Game> gameMap, boolean order, String company) {
        List<Entry<Integer, Game>> list = new LinkedList<>();
        for (Map.Entry<Integer, Game> entry : gameMap.entrySet()) {
            if (entry.getValue().getCompany().equals(company)) {
                list.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()));
            }
        }
        Collections.sort(list, new Comparator<Entry<Integer, Game>>() {

            @Override
            public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
                if (!order) {
                    return o2.getKey() - o1.getKey();
                }
                return o1.getKey() - o2.getKey();
            }
        });
        List<Game> games = new LinkedList<>();
        for (Map.Entry<Integer, Game> entry : list) {
            games.add(entry.getValue());
        }
        return games;
    }

    @Override
    public Set<Game> searchByTag(Set<Game> games, Map<String, Set<String>> tagMap, 
            String tag) {
        Set<Game> set = new HashSet<>();
        Set<String> names = tagMap.get(tag);
        for (Game game : games) {
            if (names.contains(game.getName())) {
                set.add(game);
            }
        }
        return set;
    }

    @Override
    public TreeSet<Entry<Integer, Game>> byThreeConditions(Set<Game> games, 
            String priceString, String ratingString, String tag, boolean order, 
            Map<String, Set<String>> tagMap) {
        if ("".equals(priceString) && "".equals(ratingString) && "".equals(tag)) {
            return new TreeSet<>();
        }
        Set<Game> set = new HashSet<>(games);
        
        // filter by tag
        if (!"".equals(tag)) {
            set = searchByTag(games, tagMap, tag);
        }
        
        // filter by price
        if (!"".equals(priceString)) {
            double price = Double.parseDouble(priceString.split(Constants.SPLIT_DOUBLE)[0]);
            TreeMap<Double, TreeSet<Entry<Integer, Game>>> map = byPrice(set);
            set = new HashSet<>();
            SortedMap<Double, TreeSet<Entry<Integer, Game>>> sortedMap;
            if (price < 100) {
                sortedMap = map.subMap(price, price + 10);
            } else {
                sortedMap = map.subMap(price, (double) Constants.INFINITE);
            }
            for (TreeSet<Entry<Integer, Game>> treeSet : sortedMap.values()) {
                for (Map.Entry<Integer, Game> entry : treeSet) {
                    set.add(entry.getValue());
                }
            }     
        }
        
        // filter by rating
        if (!"".equals(ratingString)) {
            double rating = Double.parseDouble(ratingString.split(Constants.SPLIT_DOUBLE)[0]);
            TreeMap<Double, TreeSet<Entry<Integer, Game>>> map = byRating(set);
            set = new HashSet<>();
            SortedMap<Double, TreeSet<Entry<Integer, Game>>> sortedMap;
            if (rating < 8) {
                sortedMap = map.subMap(rating + 2, rating);
            } else {
                sortedMap = map.subMap(10.1, rating);
            }
            for (TreeSet<Entry<Integer, Game>> treeSet : sortedMap.values()) {
                for (Map.Entry<Integer, Game> entry : treeSet) {
                    set.add(entry.getValue());
                }
            }
        }
        TreeSet<Entry<Integer, Game>> result = new TreeSet<>(
                new Comparator<Entry<Integer, Game>>() {

                    @Override
                    public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
                        Game game1 = o1.getValue();
                        Game game2 = o2.getValue();
                        double rating1 = game1.getRating();
                        double rating2 = game2.getRating();
                        if (rating1 == rating2) {
                            double price1 = game1.getPrice();
                            double price2 = game2.getPrice();
                            if (price1 == price2) {
                                if (order) {
                                    return game1.getName().compareTo(game2.getName());
                                }
                                return game2.getName().compareTo(game1.getName());
                            }
                            if (order) {
                                return price1 > price2 ? 1 : -1;
                            }
                            return price1 > price2 ? -1 : 1;
                        }
                        if (order) {
                            return rating1 > rating2 ? -1 : 1;
                        }
                        return rating1 > rating2 ? 1 : -1;
                    }
        });
        for (Game game : set) {
            result.add(new AbstractMap.SimpleEntry<Integer, Game>(game.getId(), game));
        }
        return result;
    }
    
    /**
     * a helper function that returns the game map with key being the company name
     * @param gameMap
     * @return a map with key being the company name and value being the game set for that company
     */
    private Map<String, TreeSet<Entry<Integer, Game>>> compGameMap(Map<Integer, Game> gameMap){
        Map<String, TreeSet<Entry<Integer, Game>>> map = new HashMap();
        for (Map.Entry<Integer,Game> e : gameMap.entrySet()) {
            String compName = e.getValue().getCompany();
            if (!map.containsKey(compName)) {
                TreeSet<Entry<Integer, Game>> t = new TreeSet(new PairComparator());
                t.add(new AbstractMap.SimpleEntry<Integer, Game>(e.getKey(), e.getValue()));
                map.put(compName, t);
            } else {
                TreeSet<Entry<Integer, Game>> t= map.get(compName);
                t.add(new AbstractMap.SimpleEntry<Integer, Game>(e.getKey(), e.getValue()));
                map.put(compName, t);
            }
        }
        
        return map;
         
    }
    
    /**
     * the comparator to compare games
     */
    static class PairComparator implements Comparator<SimpleEntry<Integer, Game>> {
        @Override
        public int compare(SimpleEntry<Integer, Game> o1, SimpleEntry<Integer, Game> o2) {
            int key1 = o1.getKey();
            int key2 = o2.getKey();
            return key1 - key2;
        }
    }
    
    @Override
    public LinkedHashMap<String, TreeSet<Entry<Integer, Game>>> byCompanyGame(
            Map<Integer, Game> gameMap, Map<String, Company> compMap) {
        
        LinkedHashMap<String, TreeSet<Entry<Integer, Game>>> t = new LinkedHashMap<>();
        
        List<Entry<String, Company>> list = new LinkedList<>(compMap.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Company>>() {
            @Override
            public int compare(Entry<String, Company> o1, Entry<String, Company> o2) {
                if (o1.getValue().getGameNumber() < o2.getValue().getGameNumber()) {
                    return 1;
                }
                if (o1.getValue().getGameNumber() > o2.getValue().getGameNumber()) {
                    return -1;
                }
                else {
                    if (o1.getValue().getAverageRating() < o2.getValue().getAverageRating()) {
                        return 1;
                    }
                    if (o1.getValue().getAverageRating() > o2.getValue().getAverageRating()) {
                        return -1;
                    } else {
                        return o1.getKey().compareTo(o2.getKey());
                    }
                }
            }
        });

        for (Entry<String, Company> entry : list) {
            String compName = entry.getKey();
            if (this.compGameMap(gameMap).containsKey(compName)) {
                TreeSet<Entry<Integer, Game>> gameSet = this.compGameMap(gameMap).get(compName);
                t.put(compName, gameSet);
            } else {
                TreeSet<Entry<Integer, Game>> gameSet = new TreeSet();
                t.put(compName, gameSet);
            }      
            
        }

        return t;
    }
    
    
    
    
}
