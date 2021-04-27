package game;

public class Company {
    
    /**
     * Name of that company
     */
    private String name;
    
    /**
     * Number of games published by that company
     */
    private int gameNumber;
    
    /**
     * Average rating of all games published by that company
     */
    private double averageRating;

    public Company(String name, int gameNumber, double averageRating) {
        this.name = name;
        this.gameNumber = gameNumber;
        this.averageRating = averageRating;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the gameNumber
     */
    public int getGameNumber() {
        return gameNumber;
    }

    /**
     * @return the averageRating
     */
    public double getAverageRating() {
        return averageRating;
    }
    
}
