package fr.hoc.dap.dapSwing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BoutonPanel extends JButton
{
    /**
     * 
     */
    private static final long serialVersionUID = 5397584956099407202L;
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnOption = new JButton("Options");
    private JButton btnAccount = new JButton("Add Account");
    public static Boolean refresh = false;
    public static String toSend = "bob";

    private JTextField textField = new JTextField();

    public BoutonPanel() throws Exception {
        /* BIDOUILLES */
        btnOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] users = { "bob", "sob", "rob" };
                String userChange = String.valueOf(JOptionPane.showInputDialog(null, "Choose user...",
                        "Choix de l'utilisateur", JOptionPane.DEFAULT_OPTION, null, users, users[0]));
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