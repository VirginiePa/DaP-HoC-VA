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

/**
*
* @author house
*/
public class EmailPanel extends JPanel implements ActionListener {
    /** serialVersionUID. */
    private static final long serialVersionUID = -8512702153132277439L;
    /** . */
    //TODO VA by Djer |POO| Utulise le camelCase : "nbUnread" ou "nbUnreadLbl"
    //TODO VA by Djer |POO| Evite de fairedes action "complexe" à l'itnitialisation d'un attribut, si une exception se produit, tu ne pourras PAS la gérer ici.
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
        //TODO VA by Djer |POO| Le fixe-me ci-dessous ne semble pas/plus valide
        this.add(nbunread);// FIXME recupére le procain // evennement
        
      //TODO VA by Djer |POO| Appeler la (future) méthode "updateNbUnread("bob")" ici plutot qu'a l'initialisation du de l'attribut "nbunread" 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            nbunread.setText(Launcher.getData("http://localhost:8080/email/nbunread?userKey=" + BoutonPanel.toSend));// + BoutonPanel.toSend));
        } catch (Exception e1) {
          //TODO VA by Djer |POO| Contextualise à minima ton message (c'est pour l'utilisateur donc, pas trop de détails) : "Error while loading number of emails"
          //TODO VA by Djer |Log4J| Une petite LOG ? (contextualisée et avec du détail)
            nbunread.setText("Error");
        }
    }
    
    //TODO VA by Djer |POO| Une méthode privé "updateNbUnread(String userkey)" serait pratique et pourrais être appelé parl e "actionperformed" ET le constructeur

    //TODO VA by Djer |POO| Cette méthode n'est plus utilisée. Cette clase sait gèrer sa mise à jour toutes seul il ne semble pas/plus utile d'exposer cette méthode
    public void setNbUnread(String unread) {
        this.nbunread.setText(unread);
    }
}
