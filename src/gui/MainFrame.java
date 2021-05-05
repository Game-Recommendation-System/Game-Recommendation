package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;


public class MainFrame {

    private JPanel sysManagePanel;
    private JDesktopPane desktopPane;
    private JFrame frame;
    private JLabel backLabel;
    private JMenuBar menuBar = new JMenuBar();
    
    // menu
    private JMenu mainMenu = new JMenu("Main(M)");
    private JMenu searchMenu = new JMenu("Search(S)");
    private JMenu portfolio = new JMenu("Portfolio(P)");
    private JMenu history = new JMenu("History(H)");
    private JMenu company = new JMenu("Company(C)");
    
    // main menu item
    private JMenuItem mainItem = new JMenuItem("Back To Main");
    
    // search menu item
    private JMenuItem generalSearchItem = new JMenuItem("General Search");
    private JMenuItem conditionalSearchItem = new JMenuItem("Conditional Search");
    
    // portfolio item
    private JMenuItem newlyReleasedItem = new JMenuItem("Newly Released");
    private JMenuItem freeItem = new JMenuItem("Free");
    private JMenuItem topRatingItem = new JMenuItem("Top Rating");
    
    // history item
    private JMenuItem historyItem = new JMenuItem("History");
    
    // company item
    private JMenuItem companyItem = new JMenuItem("Company");
    
    // create the map of frames
    private Map<String, JInternalFrame> ifs = new HashMap<>();
    
    
    public MainFrame() {
        frame = new JFrame("Game Recommendation System");
        frame.getContentPane().setBackground(new Color(105, 165, 205));
        frame.addComponentListener(new FrameListener());
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setBounds(20, 20, 1120, 710);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backLabel = new JLabel(); // background label
        backLabel.setVerticalAlignment(SwingConstants.TOP);
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateBackgroundImage();
        desktopPane = new JDesktopPane();
        desktopPane.add(backLabel, Integer.MIN_VALUE);
        frame.getContentPane().add(desktopPane);
        initMenu();
    }
    
    /**
     * Init the menu bar
     */
    public void initMenu() {
        
        // add action listener to every menu item
        mainItem.addActionListener(new openFrameAction("MainPane"));
        mainMenu.add(mainItem);
        menuBar.add(mainMenu);
        
        generalSearchItem.addActionListener(new openFrameAction(
                "GeneralSearchPane"));
        conditionalSearchItem.addActionListener(new openFrameAction(
                "ConditionalSearchPane"));
        searchMenu.add(generalSearchItem);
        searchMenu.add(conditionalSearchItem);
        menuBar.add(searchMenu);
        
        newlyReleasedItem.addActionListener(new openFrameAction(
                "NewlyReleased"));
        topRatingItem.addActionListener(new openFrameAction(
                "TopRating"));
        freeItem.addActionListener(new openFrameAction("FreePane"));
        portfolio.add(topRatingItem);
        portfolio.add(newlyReleasedItem);
        portfolio.add(freeItem);
        menuBar.add(portfolio);
        
        historyItem.addActionListener(new openFrameAction(
                "HistoryPane"));
        history.add(historyItem);
        menuBar.add(history);
        
        companyItem.addActionListener(new openFrameAction(
                "CompanyPane"));
        company.add(companyItem);
        menuBar.add(company);
        
        frame.add(menuBar, "North");
        frame.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        new MainFrame();
    }
    
    
    /*************************Internal Classes*************************/
    /**
     * Frame Listener
     * @author yifan
     *
     */
    private final class FrameListener extends ComponentAdapter {
        public void componentResized(final ComponentEvent e) {
            updateBackgroundImage();
        }
    }
    
    
    /**
     * main JMenu item listener
     * @author yifan
     *
     */
    protected final class openFrameAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        private String frameName = null;
        private openFrameAction(String frameName) {
            this.frameName = frameName;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JInternalFrame jFrame = getIFrame(frameName);
            jFrame.addInternalFrameListener(new InternalFrameAdapter() {
                public void internalFrameClosed(InternalFrameEvent e) {
                    ifs.remove(frameName);
                }
            });
            if (jFrame.getDesktopPane() == null) {
                desktopPane.add(jFrame);
                jFrame.setVisible(true);
            }
            
            try {
                jFrame.setSelected(true);
            } catch (PropertyVetoException e1) {
                e1.printStackTrace();
            }
        }
        
    }
    
    /************************Helper Functions*************************/
    /**
     * Update the background Image
     */
    private void updateBackgroundImage() {
        if (backLabel != null) {
            int backw = frame.getWidth();
            int backh = frame.getHeight();
            backLabel.setSize(backw, backh);
            backLabel.setIcon(new ImageIcon("welcome.jpg"));
        }
    }
    
    // get the unique internal JFrame
    private JInternalFrame getIFrame(String frameName) {
        JInternalFrame jf = null;
        if (!ifs.containsKey(frameName)) {
            try {
                Class fClass = Class.forName("internal." + frameName);
                Constructor constructor = fClass.getConstructor(null);
                jf = (JInternalFrame) constructor.newInstance(null);
                ifs.put(frameName, jf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            jf = ifs.get(frameName);
        }
        return jf;
    }
    
}
