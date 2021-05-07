// java Program to create multiple internal frames

import javax.swing.*;
import java.awt.*;

class Solution extends JFrame {

    // frame
    static JFrame f;

    // label to display text
    static JLabel l, l1;

    // main class
    public static void main(String[] args)
    {
        // create a new frame
        f = new JFrame("frame");

        // set layout of frame
        f.setLayout(new FlowLayout());

        // create a internal frame
        JInternalFrame in = new SearchGUI(7);



//        // create a Button
//        JButton b = new JButton("button");
//
//
//        // create a label to display text
//        l = new JLabel("This is a JInternal Frame no 1 ");
//

//        // create a panel
//        JPanel p = new JPanel();
//
//
//        // add label and button to panel
//        p.add(l);
//        p.add(b);


        // set visibility internal frame
        in.setVisible(true);


//        // add panel to internal frame
//        in.add(p);
//        in1.add(p1);

        // add internal frame to frame
        f.add(in);
//        f.add(in1);

        // set the size of frame
        f.setSize(1000, 1000);

        f.show();
    }
}

