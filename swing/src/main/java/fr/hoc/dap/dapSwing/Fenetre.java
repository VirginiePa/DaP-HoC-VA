
package fr.hoc.dap.dapSwing;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * @author house
 *
 */
public class Fenetre extends JFrame {
    /** .*/
    private static final long serialVersionUID = 8629900868945535881L;
    private BoutonPanel menu = new BoutonPanel();
    private EmailPanel emailPanel = new EmailPanel();
    private EventPanel eventPanel = new EventPanel();

    /**
     * @throws Exception = voir plus tard.
     */
    public Fenetre() throws Exception {

        this.setTitle("DaP");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        // On définit le layout à utiliser sur le content pane
        this.setLayout(new BorderLayout());
        // Au centre
        this.getContentPane().add(emailPanel, BorderLayout.CENTER);
        // Au sud
        this.getContentPane().add(eventPanel, BorderLayout.SOUTH);
        // À l'ouest
        this.getContentPane().add(menu, BorderLayout.WEST);

        this.setVisible(true);

        menu.registrerRefresh(emailPanel);
        menu.registrerRefresh(eventPanel);

    }

}
