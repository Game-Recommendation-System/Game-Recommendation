package game;

import java.util.Arrays;

public class Game implements IGame{

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
    private int releaseYear;
    
    
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
     * Platforms
     */
    private String[] platforms;
    
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
    public Game(int id, String name, String company, String[] platforms, double price, int releaseYear,
            int totalNumberOfRatings, double rating, String header, String description) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.platforms = platforms;
        this.price = price;
        this.releaseYear = releaseYear;
        this.totalNumberOfRatings = totalNumberOfRatings;
        this.rating = rating;
        this.header = header;
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCompany() {
        return company;
    }
    
    @Override
    public String[] getPlatforms() {
        return platforms;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getReleaseYear() {
        return releaseYear;
    }


    @Override
    public int getTotalNumberOfRatings() {
        return totalNumberOfRatings;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public void setTotalNumberOfRatings(int totalNumberOfRatings) {
        this.totalNumberOfRatings = totalNumberOfRatings;
    }

    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(IGame that) {
        Game gameThat = (Game) that;
        return this.getName().compareTo(gameThat.getName());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Game [id=" + id + ", name=" + name + ", company=" + company + ", price=" + price + ", releaseYear="
                + releaseYear + ", totalNumberOfRatings=" + totalNumberOfRatings + ", rating=" + rating + ", header="
                + header + ", platforms=" + Arrays.toString(platforms) + ", description=" + description + "]";
    }
    
    
    
}
