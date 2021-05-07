package autocomplete;

import java.util.*;

import game.*;
import helper.LoaderHelper;

public class Autocomplete2 implements IAutocomplete {

    private Node root;
    private int limitDisplay;
    private List<ITerm> suggestion;
    private Map<Integer, Game> gameMap;
    public Autocomplete2() {
        setRoot(new Node("", 0));
    }

    /**
     * Adds a new word with its associated weight to the Trie
     *
     * @param word   the word to be added to the Trie
     * @param weight the weight of the word
     */
    @Override
    public void addWord(String word, long weight) {
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) {
                return;
            }
        }
        if (weight < 0) {
            return;
        }
        Node temp = getRoot();
        word = word.toLowerCase();
        String rest = word;
        while (rest.length() != 0) {
            int pos = rest.charAt(0) - 'a';
            if (!temp.getReferences().containsKey(pos)) {
                // add a new node
                rest = rest.substring(1);
                Node addedNode = new Node();
                temp.getReferences().put(pos,addedNode);
                temp.setPrefixes(temp.getPrefixes() + 1);
            } else {
                // if it is filled
                rest = rest.substring(1);
                temp.setPrefixes(temp.getPrefixes() + 1);
            }
            // move to next level
            temp = temp.getReferences().get(pos);
        }
        // at the end of the word
        temp.setWords(1);
        temp.setPrefixes(1);
        temp.setTerm(new Term("", weight));
    }

    /**
     * Initializes the Trie
     *
     * @param filename the file to read all the autocomplete data from each line
     *                 contains a word and its weight This method will call the
     *                 addWord method
     * @param k        the maximum number of suggestions that should be displayed
     * @return the root of the Trie You might find the readLine() method in
     *         BufferedReader useful in this situation as it will allow you to read
     *         a file one line at a time.
     */
    @Override
    public Node buildTrie(int k) {
        setLimitDisplay(k);
        gameMap = LoaderHelper.readGames("Game.csv");
        // create a new Trie
        setRoot(new Node("", 0));
        for (Map.Entry<Integer, Game> game: gameMap.entrySet()){
            String gameName = game.getValue().getName();
            addWord(gameName,0);
        }
        return getRoot();
    }

    /**
     * @return k the the maximum number of suggestions that should be displayed
     */
    @Override
    public int numberSuggestions() {
        return limitDisplay;
    }

    public List<ITerm> getSuggestionArray() {
        return suggestion;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setLimitDisplay(int limitDisplay) {
        this.limitDisplay = limitDisplay;
    }

    public void setSuggestion(List<ITerm> suggestion) {
        this.suggestion = suggestion;
    }

    /**
     * @param prefix
     * @return the root of the subTrie corresponding to the last character of the
     *         prefix.
     */
    @Override
    public Node getSubTrie(String prefix) {

        Node temp = getRoot();

        for (int i = 0; i < prefix.length(); i++) {
            if (!Character.isLetter(prefix.charAt(i))) {
                return null;
            }
        }

        prefix = prefix.toLowerCase();

        int i = 0;
        while (i < prefix.length()) {
            int pos = prefix.charAt(i) - 'a';
//            if (temp.getReferences()[pos] != null) {
            if (temp.getReferences().containsKey(pos)) {
//                temp = temp.getReferences()[pos];
                temp = temp.getReferences().get(pos);
                i++;
            } else {
                temp = null;
                break;
            }
        }
        return temp;
    }

    /**
     * @param prefix
     * @return the number of words that start with prefix.
     */
    @Override
    public int countPrefixes(String prefix) {
        if (getSubTrie(prefix) != null) {
            Node commonNode = getSubTrie(prefix);
            return commonNode.getPrefixes();
        }
        return 0;
    }

    /**
     * This method should not throw an exception
     *
     * @param prefix
     * @return a List containing all the ITerm objects with query starting with
     *         prefix. Return an empty list if there are no ITerm object starting
     *         with prefix.
     */
    @Override
    public List<ITerm> getSuggestions(String prefix) {
        setSuggestion(new ArrayList<>());

        if (getSubTrie(prefix) == null) {
            return getSuggestionArray();
        }
        for (int i = 0; i < prefix.length(); i++) {
            if (!Character.isLetter(prefix.charAt(i))) {
                return getSuggestionArray();
            }
        }
        prefix = prefix.toLowerCase();
        Node commonNode = getSubTrie(prefix);
        dfs(commonNode, prefix);
        Collections.sort(getSuggestionArray());
        return getSuggestionArray();
    }

    private void dfs(Node node, String path) {
        if (node.getTerm() != null && node.getWords() == 1) {
            Term newTerm = new Term(path, node.getTerm().getWeight());
            getSuggestionArray().add(newTerm);
            return;
        }
        for (Map.Entry<Integer, Node> curr: node.getReferences().entrySet()){
            int target = 'a' + curr.getKey();
            char chr = (char) target;
            dfs(node.getReferences().get(curr.getKey()), path+chr);
        }
        return;
    }

}
