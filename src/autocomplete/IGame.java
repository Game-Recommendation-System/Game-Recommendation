public interface IGame {

    /**
     * @return the id
     */
    public int getId();

    /**
     * @return the name
     */
    public String getName();

    /**
     * @return the company
     */
    public String getCompany();
    
    /**
     * @return the platforms
     */
    public String[] getPlatforms();

    /**
     * @return the price
     */
    public double getPrice();
    /**
     * @return the releaseDate
     */
    public int getReleaseYear();

    /**
     * @return the totalNumberOfRatings
     */
    public int getTotalNumberOfRatings();

    /**
     * @return the rating
     */
    public double getRating();

    /**
     * @return the header
     */
    public String getHeader();

    /**
     * @return the description
     */
    public String getDescription();
    
    public void setId(int id);

    public void setName(String name);

    public void setCompany(String company);

    public void setPrice(double price);

    public void setReleaseYear(Integer releaseYear);
    
    public void setTotalNumberOfRatings(int totalNumberOfRatings);

    public void setRating(double rating);

    public void setHeader(String header);

    public void setPlatforms(String[] platforms);

    public void setDescription(String description);
    
    /**
     * compare game names lexicographically
     * @return -1, 1, or 0
     */
    public int compareTo(IGame that);
    
}
