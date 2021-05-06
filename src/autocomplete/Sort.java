import java.util.*;
import java.util.Map.Entry;

public class Sort implements ISort {

    public Sort() {

    }

    @Override
    public Map<String, Integer> byGameNumber(Map<String, Company> map, boolean order, Tuple interval) {
        List<Entry<String, Company>> list = new LinkedList<>(map.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Company>>() {
            @Override
            public int compare(Entry<String, Company> o1, Entry<String, Company> o2) {
                Company o1C = (Company) o1.getValue();
                Company o2C = (Company) o2.getValue();

                if (!order) {
                    if (o1C.getGameNumber() < o2C.getGameNumber()) {
                        return 1;
                    }
                    if (o1C.getGameNumber() > o2C.getGameNumber()) {
                        return -1;
                    }
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    if (o1C.getGameNumber() > o2C.getGameNumber()) {
                        return 1;
                    }
                    if (o1C.getGameNumber() < o2C.getGameNumber()) {
                        return -1;
                    }
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Entry<String, Company> entry : list) {
            int low = (int) interval.getLeft();
            int high = (int) interval.getRight();
            if (entry.getValue().getGameNumber() >= low && entry.getValue().getGameNumber() <= high) {
                sortedMap.put(entry.getKey(), entry.getValue().getGameNumber());
            }
        }

        return sortedMap;

    }

    @Override
    public Map<String, Double> byAvgRating(Map<String, Company> map, boolean order, Tuple interval) {
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
            Double low = (double) interval.getLeft();
            Double high = (double) interval.getRight();
            if (entry.getValue().getAverageRating() >= low && entry.getValue().getAverageRating() <= high) {
                sortedMap.put(entry.getKey(), entry.getValue().getAverageRating());
            }

        }

        return sortedMap;
    }

    @Override
    public Map<String, Double> byPrice(Map<Integer, Game> map, boolean order, Tuple interval) {
        List<Entry<Integer, Game>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, new Comparator<Entry<Integer, Game>>() {
            @Override
            public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
                Game o1G = (Game) o1.getValue();
                Game o2G = (Game) o2.getValue();

                if (!order) {
                    if (o1G.getPrice() < o2G.getPrice()) {
                        return 1;
                    }
                    if (o1G.getPrice() > o2G.getPrice()) {
                        return -1;
                    }
                    return o1G.getName().compareTo(o2G.getName());
                } else {
                    if (o1G.getPrice() > o2G.getPrice()) {
                        return 1;
                    }
                    if (o1G.getPrice() < o2G.getPrice()) {
                        return -1;
                    }
                    return o1G.getName().compareTo(o2G.getName());
                }
            }
        });

        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Entry<Integer, Game> entry : list) {
            Double low = (double) interval.getLeft();
            Double high = (double) interval.getRight();
            if (entry.getValue().getPrice() >= low && entry.getValue().getPrice() <= high) {
                sortedMap.put(entry.getValue().getName(), entry.getValue().getPrice());
            }

        }

        return sortedMap;
    }

    @Override
    public Map<String, Integer> byReleaseYear(Map<Integer, Game> map, boolean order, Tuple interval) {
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
            int low = (int) interval.getLeft();
            int high = (int) interval.getRight();
            if (entry.getValue().getReleaseYear() >= low && entry.getValue().getReleaseYear() <= high) {
                sortedMap.put(entry.getValue().getName(), entry.getValue().getReleaseYear());
            }

        }

        return sortedMap;
    }

    @Override
    public Map<String, Integer> byTotalRatings(Map<Integer, Game> map, boolean order, Tuple interval) {
        List<Entry<Integer, Game>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, new Comparator<Entry<Integer, Game>>() {
            @Override
            public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
                Game o1G = (Game) o1.getValue();
                Game o2G = (Game) o2.getValue();

                if (!order) {
                    if (o1G.getTotalNumberOfRatings() < o2G.getTotalNumberOfRatings()) {
                        return 1;
                    }
                    if (o1G.getTotalNumberOfRatings() > o2G.getTotalNumberOfRatings()) {
                        return -1;
                    }
                    return o1G.getName().compareTo(o2G.getName());
                } else {
                    if (o1G.getTotalNumberOfRatings() > o2G.getTotalNumberOfRatings()) {
                        return 1;
                    }
                    if (o1G.getTotalNumberOfRatings() < o2G.getTotalNumberOfRatings()) {
                        return -1;
                    }
                    return o1G.getName().compareTo(o2G.getName());
                }
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Entry<Integer, Game> entry : list) {
            int low = (int) interval.getLeft();
            int high = (int) interval.getRight();
            if (entry.getValue().getTotalNumberOfRatings() >= low
                    && entry.getValue().getTotalNumberOfRatings() <= high) {
                sortedMap.put(entry.getValue().getName(), entry.getValue().getTotalNumberOfRatings());
            }

        }

        return sortedMap;
    }

    @Override
    public Map<String, Double> byRatings(Map<Integer, Game> map, boolean order, Tuple interval) {
        List<Entry<Integer, Game>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, new Comparator<Entry<Integer, Game>>() {
            @Override
            public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
                Game o1G = (Game) o1.getValue();
                Game o2G = (Game) o2.getValue();

                if (!order) {
                    if (o1G.getRating() < o2G.getRating()) {
                        return 1;
                    }
                    if (o1G.getRating() > o2G.getRating()) {
                        return -1;
                    }
                    return o1G.getName().compareTo(o2G.getName());
                } else {
                    if (o1G.getRating() > o2G.getRating()) {
                        return 1;
                    }
                    if (o1G.getRating() < o2G.getRating()) {
                        return -1;
                    }
                    return o1G.getName().compareTo(o2G.getName());
                }
            }
        });

        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Entry<Integer, Game> entry : list) {
            Double low = (double) interval.getLeft();
            Double high = (double) interval.getRight();
            if (entry.getValue().getRating() >= low && entry.getValue().getRating() <= high) {
                sortedMap.put(entry.getValue().getName(), entry.getValue().getRating());
            }

        }

        return sortedMap;
    }

//    @Override
//    public Map<String, Object> byCompany(Map<String, Company> compMap, Map<Integer, Game> gameMap, boolean order,
//            Tuple interval, boolean num) {
//        List<Entry<Integer, Game>> list = new LinkedList<>(gameMap.entrySet());
//        if (num) {
//            Collections.sort(list, new Comparator<Entry<Integer, Game>>() {
//                @Override
//                public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
//                    Game o1G = (Game) o1.getValue();
//                    Game o2G = (Game) o2.getValue();
//
//                    if (!order) {
//                        if (compMap.containsKey(o1G.getCompany()) && compMap.containsKey(o2G.getCompany())) {
//                            if (compMap.get(o1G.getCompany()).getGameNumber() < compMap.get(o2G.getCompany())
//                                    .getGameNumber()) {
//                                return 1;
//                            }
//                            if (compMap.get(o1G.getCompany()).getGameNumber() > compMap.get(o2G.getCompany())
//                                    .getGameNumber()) {
//                                return -1;
//                            }
//                        }
//                        
//                        if (!compMap.containsKey(o1G.getCompany()) && compMap.containsKey(o2G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//                        
//                        if (!compMap.containsKey(o2G.getCompany()) && compMap.containsKey(o1G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//                        
//                        if (!compMap.containsKey(o2G.getCompany()) && !compMap.containsKey(o1G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//                        
//
//                        // if company not in dataset, just sort by name
//                        return o1G.getName().compareTo(o2G.getName());
//                    } else {
//                        if (compMap.containsKey(o1G.getCompany()) && compMap.containsKey(o2G.getCompany())) {
//                            if (compMap.get(o1G.getCompany()).getGameNumber() > compMap.get(o2G.getCompany())
//                                    .getGameNumber()) {
//                                return 1;
//                            }
//                            if (compMap.get(o1G.getCompany()).getGameNumber() < compMap.get(o2G.getCompany())
//                                    .getGameNumber()) {
//                                return -1;
//                            }
//                            
//                        }
//                        
//                        if (!compMap.containsKey(o1G.getCompany()) && compMap.containsKey(o2G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//                        
//                        if (!compMap.containsKey(o2G.getCompany()) && compMap.containsKey(o1G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//                        
//                        if (!compMap.containsKey(o2G.getCompany()) && !compMap.containsKey(o1G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//                        
//                        return o1G.getName().compareTo(o2G.getName());
//                    }
//                }
//            });
//
//        } else {
//            Collections.sort(list, new Comparator<Entry<Integer, Game>>() {
//                @Override
//                public int compare(Entry<Integer, Game> o1, Entry<Integer, Game> o2) {
//                    Game o1G = (Game) o1.getValue();
//                    Game o2G = (Game) o2.getValue();
//
//                    if (!order) {
//                        if (compMap.containsKey(o1G.getCompany()) && compMap.containsKey(o2G.getCompany())) {
//                            if (compMap.get(o1G.getCompany()).getAverageRating() < compMap.get(o2G.getCompany())
//                                    .getAverageRating()) {
//                                return 1;
//                            }
//                            if (compMap.get(o1G.getCompany()).getAverageRating() > compMap.get(o2G.getCompany())
//                                    .getAverageRating()) {
//                                return -1;
//                            }
//                        }
//                        
//                        if (!compMap.containsKey(o1G.getCompany()) && compMap.containsKey(o2G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//                        
//                        if (!compMap.containsKey(o2G.getCompany()) && compMap.containsKey(o1G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//                        
//                        if (!compMap.containsKey(o2G.getCompany()) && !compMap.containsKey(o1G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//
//                        return o1G.getName().compareTo(o2G.getName());
//                    } else {
//                        if (compMap.containsKey(o1G.getCompany()) && compMap.containsKey(o2G.getCompany())) {
//                            if (compMap.get(o1G.getCompany()).getAverageRating() > compMap.get(o2G.getCompany())
//                                    .getAverageRating()) {
//                                return 1;
//                            }
//                            if (compMap.get(o1G.getCompany()).getAverageRating() < compMap.get(o2G.getCompany())
//                                    .getAverageRating()) {
//                                return -1;
//                            }
//                        }
//                        
//                        if (!compMap.containsKey(o1G.getCompany()) && compMap.containsKey(o2G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//                        
//                        if (!compMap.containsKey(o2G.getCompany()) && compMap.containsKey(o1G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//                        
//                        if (!compMap.containsKey(o2G.getCompany()) && !compMap.containsKey(o1G.getCompany())) {
//                            return o1G.getName().compareTo(o2G.getName());
//                        }
//                        
//                        return o1G.getName().compareTo(o2G.getName());
//                    }
//                }
//            });
//        }
//        Map<String, Object> sortedMap = new LinkedHashMap<>();
//        for (Entry<Integer, Game> entry : list) {
//            if (num) {
//                Integer low = (int) interval.getLeft();
//                Integer high = (int) interval.getRight();
//                if (compMap.get(entry.getValue()).getGameNumber() >= low
//                        && compMap.get(entry.getValue()).getGameNumber() <= high) {
//                    sortedMap.put(entry.getValue().getName(), entry.getValue().getCompany());
//                }
//            } else {
//                Double low = (double) interval.getLeft();
//                Double high = (double) interval.getRight();
//                if (compMap.get(entry.getValue()).getAverageRating() >= low
//                        && compMap.get(entry.getValue()).getAverageRating() <= high) {
//                    sortedMap.put(entry.getValue().getName(), entry.getValue().getCompany());
//                }
//            }
//
//        }
//
//        return sortedMap;
//    }

}
