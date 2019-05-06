package fr.hoc.dap.dapSwing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** classe pour construire la partie événements. */
public class EventPanel extends JPanel implements ActionListener {
    /** Instanciates the logger. */
    private static final Logger LOG = LogManager.getLogger();
    /** serialVersionUID. */
    private static final long serialVersionUID = 4260503208492809966L;

    /**.*/
    private JLabel nextEvent = new JLabel("Loading Event");

    /** Appel de la partie texte des événements. */
    private JLabel eventLabel = new JLabel("Next event");

    /** constructeur de la partie événements. 
     * @throws Exception */
    public EventPanel() throws Exception {
        this.checkEvent();
        nextEvent.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0, 204, 153));
        this.setPreferredSize(new Dimension(800, 80));
        this.setBorder((BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        eventLabel.setAlignmentX(RIGHT_ALIGNMENT);
        nextEvent.setAlignmentX(RIGHT_ALIGNMENT);
        //TODO VA by Djer |Swing| Evitez d'utiliser des composants "utiles" juste pour fair de "l'espace" !
        this.add(eventLabel);
        this.add(nextEvent);
    }

    /** connexion via le protocole http.
     * @param desiredUrl url demandée par les méthodes Gmail et Event.
     * @return content  = retourne le nb mails non lus et le prochain événement.
     * @throws Exception = plus tard.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.checkEvent();
        } catch (Exception e1) {
            LOG.info("No next event found.");
            nextEvent.setText("ERROR");
        }
    }

    public void checkEvent() throws MalformedURLException, ProtocolException, IOException {
        if (Launcher.getData("http://localhost:8080/event/next?userKey=" + BoutonPanel.toSend).length() < 10){
            nextEvent.setText("NO EVENT");
        } else{
            nextEvent.setText(Launcher.getData("http://localhost:8080/event/next?userKey=" + BoutonPanel.toSend)
                    .replace("[\"", "").replace("\"]", "").substring(12,
                            Launcher.getData("http://localhost:8080/event/next?userKey=" + BoutonPanel.toSend)
                                    .indexOf(',') - 50));
        }
    }

}
