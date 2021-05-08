package gui;

import java.util.TreeMap;
import java.util.TreeSet;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import constant.Constants;
import game.Company;
import game.Game;
import game.Sort;
import helper.LoaderHelper;

public class CompanyPane extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel panel;
    private JLabel[] gameJLabels;
    
    private TreeMap<String, TreeSet<Entry<Integer, Game>>> map;

    public CompanyPane() {
        Map<Integer, Game> gameMap = LoaderHelper.readGames(Constants.GAME_FILE);
        Map<String, Company> compMap = LoaderHelper.readCompany(Constants.COMPANY_FILE);
        
        // read in first 10 entries of the sorted map on company
        map = new Sort().byCompany(compMap, gameMap).entrySet()
              .stream()
              .limit(10)
              .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
        
        // Configure the basic properties for Company Pane
        setIconifiable(true);
        setClosable(true);
        setTitle("Recommend by Popular Companies");
        setBounds(50, 50, 1000, 500);
        getContentPane().setLayout(new BorderLayout());
        Box topBox = Box.createHorizontalBox();
        topBox.add(Box.createHorizontalGlue());
        JButton jButton = new JButton("click here to change recommendation");
        jButton.addActionListener(new ClickActionListener());
        jButton.setFont(new Font("Verdana", Font.BOLD, 15));
        topBox.add(jButton);
        topBox.add(Box.createHorizontalGlue());
        getContentPane().add(topBox, BorderLayout.NORTH);
        
        // initialization
        panel = new JPanel(); // show 1 random companies' games
        gameJLabels = new JLabel[4]; // each company show 5 random games
        for (int i = 0; i < gameJLabels.length; i++) {
            gameJLabels[i] = new JLabel();
        }
        updateCompanies();
        
        // arrange all components
        Box box = Box.createHorizontalBox(); // container for game posts
        box.add(Box.createHorizontalGlue());
        for (int j = 0; j < gameJLabels.length; j++) {
            box.add(gameJLabels[j]);
            box.add(Box.createHorizontalStrut(10));
        }
        panel.add(box);
        getContentPane().add(panel, BorderLayout.CENTER);
    }
    
    /**
     * Update the recommendation when pressing F5
     */
    private void updateCompanies() {
        Random random = new Random();
        Object[] keys = map.keySet().toArray();
        int index = random.nextInt(map.size());
        String company = (String) keys[index];
        Border etched = BorderFactory.createEtchedBorder();
        Border border = BorderFactory.createTitledBorder(etched, company);
        panel.setBorder(border);
        
        // update each JLabel of game post
        TreeSet<Entry<Integer, Game>> set = map.get(company);
        Object[] values = set.toArray();
        for (int j = 0; j < gameJLabels.length; j++) {
            index = random.nextInt(set.size());
            Game game = ((Map.Entry<Integer, Game>) values[j]).getValue();
            gameJLabels[j].setText(game.getPost());
        }
    }
    
    /*****************************Action Listeners*****************************/
    class ClickActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            updateCompanies();
        }
        
    }


}
