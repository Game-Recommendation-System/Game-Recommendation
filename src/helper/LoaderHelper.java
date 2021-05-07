package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import game.Company;
import game.Game;

public class LoaderHelper {
    
    /**
     * Helper function for reading in all data from .csv file
     * and configure game map
     * @param filename The filename of the data source
     * @return a hashmap of all games
     */
    public static Map<Integer, Game> readGames(String filename) {
        Map<Integer, Game> games = new HashMap<>();

        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] contents = line.split(",");
                
                // get description
                StringBuilder description = new StringBuilder();
                for (int i = 9; i < contents.length; i++) {
                    description.append(contents[i]);
                }
                
                // platforms
                String[] platforms = contents[3].split(";");
                
                // get release year
                int releaseYear;
                DateTimeFormatter formatter = DateTimeFormatter.
                        ofPattern("yyyy/M/d", Locale.ENGLISH);
                try {
                    LocalDate date = LocalDate.parse(contents[5], formatter);
                    releaseYear = date.getYear();
                } catch (DateTimeParseException e) {
                    continue;
                }
                
                Game game = new Game(Integer.parseInt(contents[0]), contents[1], contents[2], 
                        platforms, Double.parseDouble(contents[4]), releaseYear, 
                        Integer.parseInt(contents[6]), Double.parseDouble(contents[7]), 
                        contents[8], description.toString());
                games.put(game.getId(), game); // store in map
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return games;
    }
    
    /**
     * Helper function for reading in all data from .csv file
     * and configure company map
     * @param filename The filename of the data source
     * @return a hashmap of all companies
     */
    public static Map<String, Company> readCompany(String filename) {
        Map<String, Company> companys = new HashMap<>();
        
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] contents = line.split(",");
                
                int length = contents.length;
                
                // get company name
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < length - 2; i++) {
                    stringBuilder.append(contents[i]);
                }
                
                Company company = new Company(stringBuilder.toString(), 
                        Integer.parseInt(contents[length - 2]), 
                        Double.parseDouble(contents[length - 1]));
                companys.put(company.getName(), company);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return companys;
    }
    
    
    public static Map<String, Set<String>> readTags(String filename) {
        Map<String, Set<String>> tagNamePairs = new TreeMap<>(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] contents = line.split(",");
                String tag = contents[0];
                String name = contents[1];
                if (tagNamePairs.containsKey(tag)) {
                    tagNamePairs.get(tag).add(name);
                } else {
                    Set<String> names = new HashSet<>();
                    names.add(name);
                    tagNamePairs.put(tag, names);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tagNamePairs;
    }
    
}
