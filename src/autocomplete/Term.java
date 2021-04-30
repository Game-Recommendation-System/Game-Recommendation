
public class Term implements ITerm {

    private String string;
    private long weight;

    // constructor
    public Term(String query, long weight) {
        if (query == null || weight < 0) {
            throw new IllegalArgumentException("invalid");
        }
//        if (!query.equals("")) {
//            for (int i = 0; i < query.length(); i++) {
//                if (query.charAt(i) > 'z' || query.charAt(i) < 'A'
//                        || (query.charAt(i) < 'a' && query.charAt(i) > 'Z'))  {
//                    throw new IllegalArgumentException("invalid");
//                }
//            }
//        }
//        query = query.toLowerCase();
        setString(query);
        setWeight(weight);
    }

    @Override
    public int compareTo(ITerm that) {
        return getString().compareTo(((Term) that).getString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ITerm) {
            return ((Term) obj).getWeight() == getWeight()
                && ((Term) obj).getString().equals(getString());
        }
        return false;
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getWeight());
        sb.append("\t");
        sb.append(getString());
        return sb.toString();
    }

    public String getString() {
        return string;
    }

    public long getWeight() {
        return weight;
    }

    public void setString(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Query is null");
        }
        this.string = string;
    }

    public void setWeight(long weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight is negative");
        }
        this.weight = weight;
    }

    public String getTerm() {
        return getString();
    }

    public void setTerm(String string) {
        setString(string);
    }

}
