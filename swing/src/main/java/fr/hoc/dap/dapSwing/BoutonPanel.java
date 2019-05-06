package fr.hoc.dap.dapSwing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author house
 */
public class BoutonPanel extends JButton {
    /***/
    private static final Logger LOG = LogManager.getLogger();
    /** serialVersionUID. */
    private static final long serialVersionUID = 5397584956099407202L;

    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnOption = new JButton("Options");
    private JButton btnAccount = new JButton("Add Account");
    //TODO VA by Djer |POO| Pas top du tout pour "échanger" des données. Une alternative mieux serait de créer un OBJET pour stcoker cette valeur durant l'éxécution. Une classe "DataStorage" en Singleton serait une bonne base
    public static String toSend = "bob";

    private JTextField addAccountTextField = new JTextField();

    public BoutonPanel() throws Exception {
        /* BIDOUILLES */
        btnOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] users = { "bob", "sob", "rob" };
                try {
                    String userChange = String.valueOf(JOptionPane.showInputDialog(null, "Choose user...",
                            "Choix de l'utilisateur", JOptionPane.DEFAULT_OPTION, null, users, users[0]));
                    if (userChange.isEmpty()) {
                        ;
                    } else {
                        toSend = userChange;
                    }
                } catch (Exception e1) {
                    LOG.error("Error caught while changing user : " + e1);
                }
            }
        });
        /* BIDOUILLES */
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(200, 100, 200));

        this.add(btnRefresh);
        this.add(btnOption);
        this.add(btnAccount);
        this.add(addAccountTextField);
        this.add(Box.createVerticalStrut(400));
    }

    public void registrerRefresh(ActionListener listner) {
        btnRefresh.addActionListener(listner);
    }

    public void registrerOptions(ActionListener listner) {
        btnOption.addActionListener(listner);
    }
}
