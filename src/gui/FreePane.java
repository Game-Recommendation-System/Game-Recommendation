package gui;

import java.util.Random;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import game.Game;

public class FreePane extends PortfolioPane {

    private static final long serialVersionUID = 1L;

    private TreeSet<Entry<Integer, Game>> set;
    
    public FreePane() {
        super();
        setTitle("Free Games");        
        
        // get game set
        set = sort.byPrice(games).firstEntry().getValue();
        updatePosts();
        Border etched = BorderFactory.createEtchedBorder();
        Border border = BorderFactory.createTitledBorder(etched, "Free");
        panel.setBorder(border);
    }



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
