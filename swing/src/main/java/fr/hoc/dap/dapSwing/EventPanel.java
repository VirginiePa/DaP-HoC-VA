package fr.hoc.dap.dapSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** classe pour construire la partie événements. */

public class EventPanel extends JPanel implements ActionListener {
    private JLabel nextEvent = new JLabel("Loading Event");
    /** serialVersionUID. */
    private static final long serialVersionUID = 4260503208492809966L;

    /** Appel de la partie texte des événements. */
    private JLabel eventLabel = new JLabel("Next event");

    /** Appel des données pour le prochain événements. */

    /** constructeur de la partie événements. 
     * @throws Exception */
    public EventPanel() throws Exception {
        if (Launcher.getData("http://localhost:8080/event/next?userKey=bob").length() < 10)
            nextEvent.setText("NO EVENT");
        else
            nextEvent.setText(Launcher.getData("http://localhost:8080/event/next?userKey=bob").replace("[\"", "")
                    .replace("\"]", "").split(",")[0].substring(12,
                            Launcher.getData("http://localhost:8080/event/next?userKey=bob").split(",")[0].length()
                                    - 50));
        nextEvent.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0, 204, 153));
        eventLabel.setAlignmentX(RIGHT_ALIGNMENT);
        nextEvent.setAlignmentX(RIGHT_ALIGNMENT);
        // this.setPreferredSize(new Dimension(160, 80));
        this.add(new JLabel(" "));
        this.add(eventLabel);
        this.add(nextEvent);
        this.add(new JLabel(" "));
    }

    /** connexion via le protocole http.
     * @param desiredUrl url demandée par les méthodes Gmail et Event.
     * @return content  = retourne le nb mails non lus et le prochain événement.
     * @throws Exception = plus tard.
     * */
    public String getData(final String desiredUrl) throws Exception {
        URL url = new URL(desiredUrl);
        // Nous nous connectons au site en question
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

        urlConn.setRequestMethod("GET");

        String content = "";
        String line = null;
        // Nous récupérons un flux en retour de connexion
        BufferedReader buf = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        // Nous stockons les informations retournées dans une chaîne de caractères
        while ((line = buf.readLine()) != null) {
            content += line;
        }
        return content;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (Launcher.getData("http://localhost:8080/event/next?userKey=" + BoutonPanel.toSend).length() < 10) {
                nextEvent.setText("NO EVENT");
            } else {
                nextEvent.setText(Launcher.getData("http://localhost:8080/event/next?userKey=" + BoutonPanel.toSend)
                        .replace("[\"", "")
                        .replace("\"]", "")
                        .substring(12,
                                Launcher.getData("http://localhost:8080/event/next?userKey=" + BoutonPanel.toSend)
                                        .indexOf(',') - 50));
            }
        } catch (Exception e1) {
            nextEvent.setText("ERROR");
        }
    }
}
