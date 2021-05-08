package gui;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;

import constant.Constants;
import game.Company;
import game.Game;
import game.Sort;
import helper.LoaderHelper;

public class TestComboScroll {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Scroll inside Combo");
        JLabel label = new JLabel();
        Map<Integer, Game> map = LoaderHelper.readGames(Constants.GAME_FILE);
        String text = map.get(20).getPost();
        label.setText(text);
        frame.add(label);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);        
//        Map<Integer, Game> gameMap = LoaderHelper.readGames(Constants.GAME_FILE);
//        Map<String, Company> compMap = LoaderHelper.readCompany(Constants.COMPANY_FILE);
//        
//        // read in sorted map on company
//        TreeMap<String, TreeSet<Entry<Integer, Game>>> map = 
//                new Sort().byCompany(compMap, gameMap).entrySet().stream()
//                .limit(20)
//                .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);;
//        for (String companyString : map.keySet()) {
//            System.out.println(map.get(companyString).size());
//        }
    }
}
