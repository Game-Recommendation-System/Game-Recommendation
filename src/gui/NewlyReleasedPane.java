package gui;

import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import game.Game;

public class NewlyReleasedPane extends PortfolioPane {
    
    private static final long serialVersionUID = 1L;

    private TreeSet<Entry<Integer, Game>> set;
    
    public NewlyReleasedPane() {
        super();
        setTitle("Newly Released Games");        
        
        // get game set
        set = sort.byReleaseYear(games).lastEntry().getValue();    
        updatePosts();
        Border etched = BorderFactory.createEtchedBorder();
        Border border = BorderFactory.createTitledBorder(etched, "Newly Released");
        panel.setBorder(border);
    }

    /**
     * update posts by selecting some games in the most recent year
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
