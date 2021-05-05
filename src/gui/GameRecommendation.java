package gui;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import game.Company;
import game.Game;

public class GameRecommendation {
    
    private Map<Integer, Game> games;
    private Map<String, Company> companys;
    
    public GameRecommendation() {
        readGames("Game.csv");
        readCompany("Company.csv");
    }
    
    /**
     * Helper function for reading in all data from .csv file
     * and configure game map
     * @param filename The filename of the data source
     */
    private void readGames(String filename) {
        games = new HashMap<>();

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
    }
    
    /**
     * Helper function for reading in all data from .csv file
     * and configure company map
     * @param filename The filename of the data source
     */
    private void readCompany(String filename) {
        companys = new HashMap<>();
        
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
        System.out.println(companys.size());
        
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        new GameRecommendation();
    }

}
