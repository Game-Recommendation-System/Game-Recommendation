package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.Map.Entry;

public class Read {
    HashMap<String, Company> companyMap = new HashMap<String, Company>();
    HashMap<Integer, Game> gameMap = new HashMap<Integer, Game>();
//    HashMap<String, TreeSet<Entry<Integer, Game>>> compGameMap = new HashMap<>();
    
    public HashMap<String, Company> getCompanyMap() {
        return companyMap;
    }

    public void setCompanyMap(HashMap<String, Company> companyMap) {
        this.companyMap = companyMap;
    }

    public HashMap<Integer, Game> getGameMap() {
        return gameMap;
    }

    public void setGameMap(HashMap<Integer, Game> gameMap) {
        this.gameMap = gameMap;
    }
    
    
    public Read() {
        this.CompanyRead();
        this.GameRead();
//        this.CompGameRead();
    }

    public void CompanyRead() {
        String file = "Company.csv";
        try {  
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            String line = null;  
            while((line=reader.readLine())!=null){  
                String item[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String name = item[0].strip().trim();
                int gameNumber = Integer.parseInt(item[1]);
                double averageRating = Double.parseDouble(item[2]);
                Company c = new Company(name, gameNumber, averageRating);
                companyMap.put(name, c);
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
    }
    
    public void GameRead() {
        String file = "Game.csv";
        try {  
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            String line = null;  
            while((line=reader.readLine())!=null){  
                String item[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                int gameId = Integer.parseInt(item[0]);
                String name = item[1];
                String company = item[2];
                String[] platforms = item[3].split(";");
                double price = Double.parseDouble(item[4]);
                DateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
                Date releaseDate = format.parse(item[5]);
                int releaseYear = releaseDate.getYear() + 1900;
                int totalNumberOfRatings = Integer.parseInt(item[6]);
                double rating = Double.parseDouble(item[7]);
                String header = item[8];
                String description = item[9];
                Game g = new Game(gameId, name, company, platforms, price, releaseYear, 
                        totalNumberOfRatings, rating, header, description);
                gameMap.put(gameId, g);
                
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    
//    public void CompGameRead() {
//        String file = "Game.csv";
//        try {  
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            reader.readLine();
//            String line = null;  
//            while((line=reader.readLine())!=null){  
//                String item[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//                int gameId = Integer.parseInt(item[0]);
//                String name = item[1];
//                String company = item[2];
//                String[] platforms = item[3].split(";");
//                double price = Double.parseDouble(item[4]);
//                DateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
//                Date releaseDate = format.parse(item[5]);
//                int releaseYear = releaseDate.getYear() + 1900;
//                int totalNumberOfRatings = Integer.parseInt(item[6]);
//                double rating = Double.parseDouble(item[7]);
//                String header = item[8];
//                String description = item[9];
//                Game g = new Game(gameId, name, company, platforms, price, releaseYear, 
//                        totalNumberOfRatings, rating, header, description);
//                if (!compGameMap.containsKey(company)) {
//                    TreeSet<Entry<Integer, Game>> t = new TreeSet(new PairComparator());
//                    t.add(new AbstractMap.SimpleEntry<Integer, Game>(gameId, g));
//                    compGameMap.put(company, t);
//                } else {
//                    TreeSet<Entry<Integer, Game>> t= compGameMap.get(company);
//                    t.add(new AbstractMap.SimpleEntry<Integer, Game>(gameId, g));
//                    compGameMap.put(company, t);
//                }
//                
//            }  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        
//    }
//    
//    static class PairComparator implements Comparator<SimpleEntry<Integer, Game>> {
//        @Override
//        public int compare(SimpleEntry<Integer, Game> o1, SimpleEntry<Integer, Game> o2) {
//            int key1 = o1.getKey();
//            int key2 = o2.getKey();
//            return key1 - key2;
//        }
//    }
//    
//    
//    
//    public static void main(String[] args) {
//        Read r = new Read();
//        System.out.println(r.compGameMap);
//    }

}
