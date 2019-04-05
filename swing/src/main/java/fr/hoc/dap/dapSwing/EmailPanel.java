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

public class EmailPanel extends JPanel implements ActionListener
{
    /**
     */
    private static final long serialVersionUID = -8512702153132277439L;
    /** . */
    private JLabel nbunread = new JLabel(Launcher.getData("http://localhost:8080/email/nbunread?userKey=bob"));

    /** . */
    public EmailPanel() throws Exception {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        nbunread.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        // this.setAlignmentX(Component.CENTER_ALIGNMENT);
        // this.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.setBackground(new Color(102, 204, 204));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(Box.createRigidArea(new Dimension(210, 230)));
        this.add(nbunread);// FIXME recupére le procain // evennement

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            nbunread.setText(Launcher.getData("http://localhost:8080/email/nbunread?userKey=" + BoutonPanel.toSend));// + BoutonPanel.toSend));
        } catch (Exception e1) {
            nbunread.setText("Error");
        }
    }

    public void setNbUnread(String unread) {
        this.nbunread.setText(unread);
    }

}
