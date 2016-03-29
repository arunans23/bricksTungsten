
/**
 * Description	:Game launcher application with settings
 * Copyright	:Copyright (c) 2014
 * Company		:Embla Software Innovations (Pvt) Ltd
 * Created on	:2014.09.01
 *
 * @author :sugirjan,laval, sivakar, arunan, shandimal 
 * @version :1.0
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class GameLauncher {

    /**
     * Main method
     *
     * @param args : no arguments
     */
    JButton j;
    static boolean isKey;

    public static void main(String[] args) {

        // Game width and height
        Dimension gameDimension = new Dimension(400, 600);
        // Create a window
        JFrame frame = new JFrame();
        Panel panel = new Panel();
        JButton j = new JButton();
        panel.setLayout(new BorderLayout());
        JLabel jlabel = new JLabel("Start : Space bar ");
        jlabel.setFont(new Font("Verdana", 10, 10));
        jlabel.setSize(25, 25);
        jlabel.setLocation(10, 580);

        // Create our game 
        //while(isKey){
        Game g = new Game(gameDimension);
        g.setBounds(0, 0, gameDimension.width, gameDimension.height);
        // Add to the frame
        panel.add(g);
        panel.add(jlabel, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.NORTH);
        // Setting for the frame
        // Set default behavior to close on exit
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Stop resizing
//        frame.setResizable(false);
        // Set title
        frame.setTitle("Bricks");
        // Pack the content
        frame.pack();
        // Center the frame
        frame.setLocationRelativeTo(null);
        // Show the frame
        frame.setVisible(true);

        //Add listener
        frame.addKeyListener(g);
        // Start the game
        g.Start();
       // keyTypeds(KeyEvent ev)
    }//}

    private void jActionPerformed(java.awt.event.ActionEvent evt) {
        //JOptionPane.showMessageDialog(null, "Completed Analysis!","Alert", 1);
        j.setEnabled(false);
        //// jTextArea2.setEditable(false);
        // jTextArea3.setEditable(false);
        GameLauncher.main(new String[]{"arg1", "arg2"});
    }

//    public void keyTypeds(KeyEvent ev) {
//        op
//                
//                ]<MLK<LLKif (Character.isSpaceChar(ev.getKeyChar())) {
//        GameLauncher.main(new String[]{});
//
//            isKey = true;
//        }
    
}
