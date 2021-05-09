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


public class GameRecommendation {

    private JDesktopPane desktopPane;
    private JFrame frame;
    private JLabel backLabel;
    private JMenuBar menuBar = new JMenuBar();
    
    // menu
    private JMenu searchMenu = new JMenu("Search(S)");
    private JMenu portfolio = new JMenu("Portfolio(P)");
    private JMenu history = new JMenu("History(H)");
    private JMenu company = new JMenu("Company(C)");
    
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
    
    
    public GameRecommendation() {
        frame = new JFrame("Game Recommendation System");
        frame.getContentPane().setBackground(new Color(105, 165, 205));
        frame.addComponentListener(new FrameListener());
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setBounds(20, 20, 1120, 750);
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
        generalSearchItem.addActionListener(new OpenFrameAction(
                "GeneralSearchPane"));
        conditionalSearchItem.addActionListener(new OpenFrameAction(
                "ConditionalSearchPane"));
        searchMenu.add(generalSearchItem);
        searchMenu.add(conditionalSearchItem);
        menuBar.add(searchMenu);
        
        newlyReleasedItem.addActionListener(new OpenFrameAction(
                "NewlyReleasedPane"));
        topRatingItem.addActionListener(new OpenFrameAction(
                "TopRatingPane"));
        freeItem.addActionListener(new OpenFrameAction("FreePane"));
        portfolio.add(topRatingItem);
        portfolio.add(newlyReleasedItem);
        portfolio.add(freeItem);
        menuBar.add(portfolio);
        
        historyItem.addActionListener(new OpenFrameAction(
                "HistoryPane"));
        history.add(historyItem);
        menuBar.add(history);
        
        companyItem.addActionListener(new OpenFrameAction(
                "CompanyPane"));
        company.add(companyItem);
        menuBar.add(company);
        
        frame.add(menuBar, "North");
        frame.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        new GameRecommendation();
    }
    
    
    /*************************Internal Classes*************************/
    /**
     * Frame Listener
     * @author Fan Yi
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
    protected final class OpenFrameAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        private String frameName = null;
        private OpenFrameAction(String frameName) {
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

    /**
     * get the unique internal JFrame
     * @param frameName
     * @return the specific internal JFrame
     */
    private JInternalFrame getIFrame(String frameName) {
        JInternalFrame jf = null;
        if (!ifs.containsKey(frameName)) {
            try {
                Class<?> fClass = Class.forName("gui." + frameName);
                Constructor<?> constructor = fClass.getConstructor(null);
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
