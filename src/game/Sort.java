package game;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import constant.Constants;

public class Sort implements ISort {

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
    public TreeMap<Integer, TreeSet<Entry<Integer, Game>>> byReleaseYear(Set<Game> games) {
        TreeMap<Integer, TreeSet<Entry<Integer, Game>>> treeMap = new TreeMap<>();
        
        for (Game game : games) {
            int year = game.getReleaseYear();
            if (!treeMap.containsKey(year)) {
                TreeSet<Entry<Integer, Game>> set = new TreeSet<>(
                        new Comparator<Entry<Integer, Game>>() {

                            @Override
                            public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
                                Game game1 = o1.getValue(), game2 = o2.getValue();
                                double rating1 = game1.getRating(), rating2 = game2.getRating();
                                if (rating1 > rating2) {
                                    return -1;
                                } else if (rating1 < rating2) {
                                    return 1;
                                } else {
                                    return game1.getName().compareTo(game2.getName());
                                }
                            }
                            
                        });
                set.add(new AbstractMap.SimpleEntry<Integer, Game>(game.getId(), game));
                treeMap.put(year, set);
            } else {
                treeMap.get(year).add(
                        new AbstractMap.SimpleEntry<Integer, Game>(game.getId(), game));
            }
        }
        
        return treeMap;
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

    @Override
    public TreeMap<String, TreeSet<Entry<Integer, Game>>> byCompany(Map<String, Company> companyMap,
            Map<Integer, Game> gameMap) {
        TreeMap<String, TreeSet<Entry<Integer, Game>>> map = new TreeMap<>(
                new Comparator<String>() {

                    @Override
                    public int compare(String o1, String o2) {
                        return companyMap.get(o1).compareTo(companyMap.get(o2));
                    }

                });
        
        for (Game game : gameMap.values()) {
            String company = game.getCompany();
            if (!companyMap.containsKey(company)) {
                continue;
            }
            if (!map.containsKey(company)) {
                TreeSet<Entry<Integer, Game>> set = new TreeSet<>(
                        new Comparator<Entry<Integer, Game>>() {

                            @Override
                            public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
                                Game game1 = o1.getValue(), game2 = o2.getValue();
                                int numOfRatings1 = game1.getTotalNumberOfRatings();
                                int numOfRatings2 = game2.getTotalNumberOfRatings();
                                double rating1 = game1.getRating(), rating2 = game2.getRating();
                                double ratingDiff = rating2 - rating1;
                                if (numOfRatings1 >= 20 && numOfRatings2 >= 20 || 
                                        numOfRatings1 < 20 && numOfRatings2 < 20) {
                                    if (ratingDiff > 0) {
                                        return 1;
                                    } else if (ratingDiff < 0) {
                                        return -1;
                                    } else {
                                        if (numOfRatings1 != numOfRatings2) {
                                            return numOfRatings2 - numOfRatings1;
                                        }
                                        return game1.getName().compareTo(game2.getName());
                                    }
                                }
                                if (numOfRatings1 >= 20) {
                                    return -1;
                                } else {
                                    return 1;
                                }
                            }
                        });
                set.add(new AbstractMap.SimpleEntry<Integer, Game>(game.getId(), game));
                map.put(company, set);
            } else {
                map.get(company).add(
                        new AbstractMap.SimpleEntry<Integer, Game>(game.getId(), game));
            }
        }
        return map;
    }
    
    
    
    
}
