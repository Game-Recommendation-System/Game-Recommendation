package gui;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import game.Game;

public class TopRatingPane extends PortfolioPane {

    private static final long serialVersionUID = 1L;
    
    private Set<Entry<Integer, Game>> set;


    public TopRatingPane() {
        super();
        setTitle("Top Rating Games");
        
        // construct game set for post
        SortedMap<Double, TreeSet<Entry<Integer, Game>>> map = 
                sort.byRating(games).headMap(9.8);
        set = new HashSet<Entry<Integer, Game>>();
        for (TreeSet<Entry<Integer, Game>> treeSet : map.values()) {
            for (Entry<Integer, Game> entry : treeSet) {
                set.add(new AbstractMap.SimpleEntry<Integer, Game>(
                        entry.getKey(), entry.getValue()));
            }
        }
        
        updatePosts();
        Border etched = BorderFactory.createEtchedBorder();
        Border border = BorderFactory.createTitledBorder(etched, "Top Rating");
        panel.setBorder(border);
        
    }

    /**
     * update post by selecting some games with high rating(>=9.8) randomly
     */
    @Override
    public void updatePosts() {
        Random random = new Random();
        Object[] objects = set.toArray();
        int size = set.size();
        boolean[] flags = new boolean[size];
        for (int i = 0; i < labels.length; i++) {
            int index = random.nextInt(size);
            while (flags[index]) {
                index = random.nextInt(size);
            }
            flags[index] = true;
            Game game = ((Entry<Integer, Game>) objects[index]).getValue();
            labels[i].setText(game.getPost());
        }
    }

}
