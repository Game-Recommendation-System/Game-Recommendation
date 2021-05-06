public interface ICompany extends Comparable<ICompany>{

    /**
     * @return the name
     */
    public String getName();

    /**
     * @return the gameNumber
     */
    public int getGameNumber();

    /**
     * @return the averageRating
     */
    public double getAverageRating();
    
    public void setName(String name);

    public void setGameNumber(int gameNumber);

    public void setAverageRating(double averageRating);
    
    /**
     * compare company names lexicographically
     * @return -1, 1, or 0
     */
    public int compareTo(ICompany that);

    
    
}
