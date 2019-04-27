package fr.hoc.dap.dapSwing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author house
 */
public class BoutonPanel extends JButton {
    /** serialVersionUID. */
    private static final long serialVersionUID = 5397584956099407202L;

    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnOption = new JButton("Options");
    private JButton btnAccount = new JButton("Add Account");
    //TODO VA by Djer |POO| Pourquoi public ? ("privé" par defaut, en plus cette attribut ne sert pas/plus dans le reste du code
    public static Boolean refresh = false;
    //TODO VA by Djer |POO| Pas top du tout pour "échanger" des données. Une alternative mieux serait de créer un OBJET pour stcoker cette valeur durant l'éxécution. Une classe "DataStorage" en Singleton serait une bonne base
    public static String toSend = "bob";

    //TODO VA by Djer |POO| Nom pas top
    private JTextField textField = new JTextField();

    public BoutonPanel() throws Exception {
        /* BIDOUILLES */
        btnOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] users = { "bob", "sob", "rob" };
                //TODO VA by Djer |Swing| Utiliser une Liste est "sympa" mais il faut trouver un moyen de gérer cette Liste .... Un simpel zoen de sasie est moins "top" mais fonctionne (en attendant de trouver mieux)
                String userChange = String.valueOf(JOptionPane.showInputDialog(null, "Choose user...",
                        "Choix de l'utilisateur", JOptionPane.DEFAULT_OPTION, null, users, users[0]));

                //TODO VA by Djer |POO| Attention "userChange" peut être NULL (si l'utilsateur choisi "cancel") ce qui provoquera un NPE ici.

                //TODO VA by Djer |POO| Cet algo peut etre simplifié en : toSend = userChange
                if (userChange.isEmpty()) {
                    ;
                } else if (userChange.equals("sob")) {
                    toSend = "sob";
                } else if (userChange.equals("rob")) {
                    toSend = "rob";
                } else if (userChange.equals("bob")) {
                    toSend = "bob";
                }
            }
        });
        /* BIDOUILLES */
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(200, 100, 200));

        this.add(btnRefresh);
        this.add(btnOption);
        this.add(btnAccount);
        this.add(textField);
        this.add(Box.createVerticalStrut(400));
    }

    public void registrerRefresh(ActionListener listner) {
        btnRefresh.addActionListener(listner);
    }

    public void registrerOptions(ActionListener listner) {
        btnOption.addActionListener(listner);
    }
}
