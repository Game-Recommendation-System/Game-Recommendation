package game;

import java.sql.Date;

public class Game {

    /**
     * Identifier of a certain game
     */
    private int id;
    
    /**
     * Name of the game
     */
    private String name;
    
    /**
     * The company that published that game
     */
    private String company;
    
    /**
     * Price of the game
     */
    private double price;
    
    /**
     * Release date of that game
     */
    private Date releaseDate;
    
    /**
     * The number of ratings for that game
     */
    private int totalNumberOfRatings;
    
    /**
     * Rating for that game
     */
    private double rating;
    
    /**
     * The website that contains the game's image
     */
    private String header;
    
    /**
     * Description of that game
     */
    private String description;

    /**
     * Full constructer for Game object
     * @param id
     * @param name
     * @param company
     * @param price
     * @param releaseDate
     * @param totalNumberOfRatings
     * @param rating
     * @param header
     * @param description
     */
    public Game(int id, String name, String company, double price, Date releaseDate,
            int totalNumberOfRatings, double rating, String header, String description) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.price = price;
        this.releaseDate = releaseDate;
        this.totalNumberOfRatings = totalNumberOfRatings;
        this.rating = rating;
        this.header = header;
        this.description = description;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the releaseDate
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @return the totalNumberOfRatings
     */
    public int getTotalNumberOfRatings() {
        return totalNumberOfRatings;
    }

    /**
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    
    
}
