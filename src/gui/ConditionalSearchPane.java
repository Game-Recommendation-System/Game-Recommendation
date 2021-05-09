package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import constant.Constants;
import game.Game;
import game.Sort;
import helper.LoaderHelper;

public class ConditionalSearchPane extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    
    private JButton queryButton;
    private JComboBox<String> priceBox;
    private JComboBox<String> ratingBox;
    private JComboBox<String> tagBox;
    private JCheckBox reverseBox;
    private JTable table;
    private DefaultTableModel defaultTableModel;
    
    private Map<String, Set<String>> tagMap;
    private Map<Integer, Game> gameMap;
    private Set<Game> games;
    private TreeSet<Entry<Integer, Game>> treeSet;
    private Sort sort;
    
    public ConditionalSearchPane() {
        sort = new Sort();
        tagMap = LoaderHelper.readTags(Constants.TAG_FILE);
        gameMap = LoaderHelper.readGames(Constants.GAME_FILE);
        TreeMap<Integer, TreeSet<Entry<Integer, Game>>> map2 = sort.byTotalRatings(gameMap);
        games = sort.byLeastTotalRating(map2, Constants.LEASTTOTALRATINGS);
        treeSet = new TreeSet<>();
        setIconifiable(true);
        setClosable(true);
        setTitle("Conditional Search");
        getContentPane().setLayout(new GridBagLayout());
        setBounds(100, 100, 1000, 500);
        
        // price box
        setupComponent(new JLabel("  price: "), 0, 0, 1, 1, false);
        priceBox = new JComboBox<>();
        priceBox.addItem("");
        for (double i = 0.0; i < 100.0; i += 10.0) {
            double high = (int) ((i + 9.99) * 100);
            high /= 100.0;
            priceBox.addItem(i + " - " + high);
        }
        priceBox.addItem("100+");     
        setupComponent(priceBox, 1, 0, 1, 30, true);
        
        // rating box
        setupComponent(new JLabel("  rating: "), 4, 0, 1, 1, false);
        ratingBox = new JComboBox<>();
        ratingBox.addItem("");
        for (double i = 0; i < 10; i += 2) {
            double high = (int) ((i + 1.999) * 1000);
            high /= 1000.0;
            ratingBox.addItem(i + " - " + high);
        }
        ratingBox.addItem("10");   
        setupComponent(ratingBox, 5, 0, 1, 30, true);
        
        // tag box
        setupComponent(new JLabel("  tag: "), 8, 0, 1, 1, false);
        tagBox = new JComboBox<>();
        tagBox.addItem("");
        for (String tag : tagMap.keySet()) {
            tagBox.addItem(tag);
        }
        tagBox.setEditor(new MyEditor());
        setupComponent(tagBox, 9, 0, 1, 30, true);
        
        // search button
        queryButton = new JButton("search");
        queryButton.addActionListener(new QueryActionListener());
        setupComponent(queryButton, 10, 0, 1, 1, false);
        
        // reverse order check box
        reverseBox = new JCheckBox("reverse order");
        setupComponent(reverseBox, 1, 2, 1, 1, false);
        
        // JTable
        final JScrollPane scrollPane = new JScrollPane();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = 20;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(scrollPane, gridBagConstraints);       
        
        table = new JTable();
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        defaultTableModel = (DefaultTableModel) table.getModel();
        String[] head = new String[] {"Name", "Company", "Platform", "Price", 
            "Release Year", "Rating", "Description"};
        defaultTableModel.setColumnIdentifiers(head);
        table.getColumnModel().getColumn(6).setPreferredWidth(2000);
        scrollPane.setViewportView(table);
    }
    
    /**
     * Locate component and add it to container
     * @param component The component to add
     * @param gridx
     * @param gridy
     * @param gridwidth
     * @param ipadx
     * @param fill
     */
    private void setupComponent(JComponent component, int gridx, int gridy, 
            int gridwidth, int ipadx, boolean fill) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        if (gridwidth > 1) {
            gridBagConstraints.gridwidth = gridwidth;
        }
        if (ipadx > 0) {
            gridBagConstraints.ipadx = ipadx;
        }
        gridBagConstraints.insets = new Insets(5, 1, 3, 1);
        if (fill) {
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        }
        getContentPane().add(component, gridBagConstraints);
    }
    
    /**
     * Update the information of JTable
     * @param set Result filtered by condtional search
     */
    private void updateTable(TreeSet<Entry<Integer, Game>> set) {
        int rowCount = defaultTableModel.getRowCount();
        
        // remove previous information
        for (int i = 0; i < rowCount; i++) {
            defaultTableModel.removeRow(0);
        }
        
        for (Map.Entry<Integer, Game> entry : set) {
            Game game = entry.getValue();
            String[] platforms = game.getPlatforms();
            StringBuilder sBuilder = new StringBuilder();
            for (int i = 0; i < platforms.length; i++) {
                sBuilder.append(platforms[i]);
                if (i == platforms.length - 1) {
                    break;
                }
                sBuilder.append(", ");
            }
            Object[] objects = new Object[] {game.getName(), game.getCompany(), 
                    sBuilder.toString(), game.getPrice(), game.getReleaseYear(), 
                    game.getRating(), game.getDescription()};
            defaultTableModel.addRow(objects);
        }
    }
    
    
    /*****************************Action Listeners*****************************/
    class QueryActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String priceString = (String) priceBox.getSelectedItem();          
            String ratingString = (String) ratingBox.getSelectedItem();
            String tag = (String) tagBox.getSelectedItem();
            boolean order = !reverseBox.isSelected();
            treeSet = sort.byThreeConditions(games, priceString, ratingString, 
                    tag, order, tagMap);
            updateTable(treeSet);
        }
        
    }

}
