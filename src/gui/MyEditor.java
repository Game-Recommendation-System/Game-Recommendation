package gui;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class MyEditor extends BasicComboBoxEditor {
    JScrollPane scroller = new JScrollPane();

    public MyEditor(){
        super();
        scroller.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
