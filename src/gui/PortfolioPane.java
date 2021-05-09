package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constant.Constants;
import game.Game;
import game.Sort;
import helper.LoaderHelper;

public abstract class PortfolioPane extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    protected Set<Game> games;
    protected Sort sort;
    protected JPanel panel;
    protected JLabel[] labels;
    
    public PortfolioPane() {
        Map<Integer, Game> gameMap = LoaderHelper.readGames(Constants.GAME_FILE);
        sort = new Sort();
        TreeMap<Integer, TreeSet<Entry<Integer, Game>>> map = sort.byTotalRatings(gameMap);
        games = sort.byLeastTotalRating(map, 200);
        
        setIconifiable(true);
        setClosable(true);
        setBounds(50, 50, 1000, 500);
        
        // set button
        Box topBox = Box.createHorizontalBox();
        topBox.add(Box.createHorizontalGlue());
        JButton jButton = new JButton("click here to change a group of recommendation");
        jButton.addActionListener(new ClickActionListener());
        jButton.setFont(new Font("Verdana", Font.BOLD, 15));
        topBox.add(jButton);
        topBox.add(Box.createHorizontalGlue());
        getContentPane().add(topBox, BorderLayout.NORTH);
        
        initialize();
        
    }
    
    /**
     * Initialize the panel and game labels
     */
    public void initialize() {
        panel = new JPanel(); // show 1 random companies' games
        labels = new JLabel[4]; // each company show 5 random games
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
        }
        
        // arrange all components
        Box box = Box.createHorizontalBox(); // container for game posts
        box.add(Box.createHorizontalStrut(10));
        for (int j = 0; j < labels.length; j++) {
            box.add(labels[j]);
            box.add(Box.createHorizontalStrut(10));
        }
        panel.add(box);
        getContentPane().add(panel, BorderLayout.CENTER);
    }
    
    /**
     * Update each game post
     */
    public abstract void updatePosts();
    
    /*****************************Action Listeners*****************************/
    class ClickActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            updatePosts();
        }
        
    }
}
