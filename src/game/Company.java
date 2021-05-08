package game;

public class Company implements ICompany {
    
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getGameNumber() {
        return gameNumber;
    }

    @Override
    public double getAverageRating() {
        return averageRating;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    @Override
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public int compareTo(ICompany that) {
        Company companyThat = (Company) that;
        int gameNumber = companyThat.getGameNumber();
        if (this.gameNumber == gameNumber) {
            double average = companyThat.getAverageRating();
            if (averageRating == average) {
                return name.compareTo(companyThat.getName());
            }
            return averageRating > average ? -1 : 1;
        }
        return gameNumber - this.gameNumber;
    }
    
}
