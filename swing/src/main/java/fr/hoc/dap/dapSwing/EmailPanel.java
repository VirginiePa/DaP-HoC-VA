package fr.hoc.dap.dapSwing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
*
* @author house
*/
public class EmailPanel extends JPanel implements ActionListener {
    /** serialVersionUID. */
    private static final long serialVersionUID = -8512702153132277439L;
    /** . */
    private static final Logger LOG = LogManager.getLogger();
    /** . */
    private JLabel nbunread = new JLabel();

    /** . */
    public EmailPanel() throws Exception {
        nbunread.setText(Launcher.getData("http://localhost:8080/email/nbunread?userKey=bob"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        nbunread.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        this.setBackground(new Color(102, 204, 204));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(Box.createRigidArea(new Dimension(210, 230)));
        this.add(nbunread);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            nbunread.setText(Launcher.getData("http://localhost:8080/email/nbunread?userKey=" + BoutonPanel.toSend));// + BoutonPanel.toSend));
        } catch (Exception e1) {
            LOG.error("Error while loading number of emails : " + e1);
            nbunread.setText("Error");
        }
    }
    
    /**.*/
    public void updateNbUnread(String unread) {
        this.nbunread.setText(unread);
    }
}

