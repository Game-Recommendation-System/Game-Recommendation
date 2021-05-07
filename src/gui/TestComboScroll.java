package gui;

import java.awt.Dimension;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import helper.LoaderHelper;

public class TestComboScroll {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Scroll inside Combo");
        JComboBox<String> combobox = new JComboBox<>();
        
        Map<String, Set<String>> tagMap = LoaderHelper.readTags("GameTag.csv");

        combobox.addItem("");
        for (String tag : tagMap.keySet()) {
            combobox.addItem(tag);
        }

        combobox.setEditor(new MyEditor());

        combobox.setPreferredSize(new Dimension(100, 25));
        frame.add(combobox);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
}
