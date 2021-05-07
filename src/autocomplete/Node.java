package autocomplete;

import java.util.HashMap;

/**
 * ==== Attributes ==== - words: number of words - term: the ITerm object -
 * prefixes: number of prefixes - references: Array of references to
 * next/children Nodes
 * 
 * ==== Constructor ==== Node(String word, long weight)
 * 
 * @author Hanye Wang
 */
public class Node {
    private Term term;
    private int words;
    private int prefixes;
    protected HashMap<Integer, Node> references;

    // CONSTRUCTOR
    public Node(String word, long weight) {
        if (word == null || weight < 0) {
            throw new IllegalArgumentException("invalid node");
        }
        term = new Term(word, weight);
        references = new HashMap<>();
        words = 0;
        prefixes = 0;
    }

    public Node() {
        term = null;
        references = new HashMap<>();
        words = 0;
        prefixes = 0;
    }

    // getter and setter
    protected Node getNode() {
        return this;
    }

    protected Term getTerm() {
        return term;
    }

    protected void setTerm(Term term) {
        this.term = term;
    }

    protected int getWords() {
        return words;
    }

    protected void setWords(int words) {
        this.words = words;
    }

    protected int getPrefixes() {
        return prefixes;
    }

    protected void setPrefixes(int prefixes) {
        this.prefixes = prefixes;
    }

    protected HashMap<Integer, Node> getReferences() {
        return references;
    }

    protected void setReferences(HashMap<Integer, Node> references) {
        this.references = references;
    }

}
