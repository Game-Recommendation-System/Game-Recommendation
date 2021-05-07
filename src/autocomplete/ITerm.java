package autocomplete;

import java.util.Comparator;

/**
 * @author ericfouh
 */
public interface ITerm extends Comparable<ITerm> {

    /**
     * Compares the two terms in descending order by weight.
     * 
     * @return comparator Object
     */
    public static Comparator<ITerm> byReverseWeightOrder() {
        Comparator<ITerm> reverseWeight = (i1,
            i2) -> (int) (((Term) i2).getWeight() - ((Term) i1).getWeight());
        return reverseWeight;
    }

    /**
     * Compares the two terms in lexicographic order but using only the first r
     * characters of each query.
     * 
     * @param r
     * @return comparator Object
     */
    public static Comparator<ITerm> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException("r is negative");
        }
        Comparator<ITerm> prefix = (i1, i2) -> {
            for (int i = 0; i < r && ((Term) i1).getString().length() > i
                && ((Term) i2).getString().length() > i; i++) {
                if (((Term) i1).getString().charAt(i) != ((Term) i2).getString().charAt(i)) {
                    return ((Term) i1).getString().charAt(i) - ((Term) i2).getString().charAt(i);
                }
            }
            return 0;
        };
        return prefix;
    }

    // Compares the two terms in lexicographic order by query.
    @Override
    public int compareTo(ITerm that);

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    @Override
    public String toString();

}
