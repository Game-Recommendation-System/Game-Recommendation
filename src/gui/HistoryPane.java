package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import constant.Constants;
import game.Game;
import game.Sort;
import helper.LoaderHelper;

public class HistoryPane extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    
    private JTable table;
    private DefaultTableModel defaultTableModel;
    
    private Map<Integer, Game> gameMap;
    private Set<Game> games;
    private Sort sort;

    public HistoryPane() {
        sort = new Sort();
        gameMap = LoaderHelper.readGames(Constants.GAME_FILE);
        TreeMap<Integer, TreeSet<Entry<Integer, Game>>> map2 = sort.byTotalRatings(gameMap);
        games = sort.byLeastTotalRating(map2, Constants.LEASTTOTALRATINGS);
        setIconifiable(true);
        setClosable(true);
        setTitle("History Exhibition");
        getContentPane().setLayout(new GridBagLayout());
        setBounds(200, 100, 600, 500);
        
        // JLabel
        JLabel label = new JLabel("Popular Game of Each Year");
        label.setForeground(Color.MAGENTA);
        label.setBackground(Color.ORANGE);
        label.setFont(new Font("Verdana", Font.BOLD, 18));
        
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(label, gridBagConstraints);
        
        // JTable
        final JScrollPane scrollPane = new JScrollPane();
        final GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.weighty = 1.0;
        gridBagConstraints1.anchor = GridBagConstraints.SOUTH;
        gridBagConstraints1.insets = new Insets(1, 1, 1, 1);
        gridBagConstraints1.fill = GridBagConstraints.BOTH;
        gridBagConstraints1.gridwidth = 30;
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 100;
        getContentPane().add(scrollPane, gridBagConstraints1);       
        
        table = new JTable();
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        defaultTableModel = (DefaultTableModel) table.getModel();
        String[] head = new String[] {"Release Year", "Name", "Company", 
            "Price", "Rating", "Description"};
        defaultTableModel.setColumnIdentifiers(head);
        table.getColumnModel().getColumn(5).setPreferredWidth(2000);
        scrollPane.setViewportView(table);
        
        // show table
        TreeMap<Integer, TreeSet<Entry<Integer, Game>>> treeMap = sort.
                byReleaseYear(games);
        updateTable(treeMap);
    }
    
    /**
     * Update the information of JTable
     * @param set Result filtered by condtional search
     */
    private void updateTable(TreeMap<Integer, TreeSet<Entry<Integer, Game>>> map) {
        int rowCount = defaultTableModel.getRowCount();
        
        // remove previous information
        for (int i = 0; i < rowCount; i++) {
            defaultTableModel.removeRow(0);
        }
        
        for (Map.Entry<Integer, TreeSet<Entry<Integer, Game>>> entry : 
            map.entrySet()) {
            int year = entry.getKey();
            TreeSet<Entry<Integer, Game>> set = entry.getValue();
            Game game = set.first().getValue();
            Object[] objects = new Object[] {year, game.getName(), game.getCompany(), 
                    game.getPrice(), game.getRating(), game.getDescription()};
            defaultTableModel.addRow(objects);
        }
    }

}
