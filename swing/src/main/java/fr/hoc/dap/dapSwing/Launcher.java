package fr.hoc.dap.dapSwing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * 
 * @author house
 */
public class Launcher {
    /** App Entry Point.
     * @param args arguments.
     * @throws Exception = voir plus tard.
     */
    public static void main(final String[] args) throws Exception {
        new Fenetre();
    }

    /**
     * 
     * @param desiredUrl : URL pour récupérer les données.
     * @return Contenu (body) de la réponse à la requete.
     * @throws MalformedURLException .
     * @throws ProtocolException si erreurs.
     * @throws IOException si erreurs.
     */
    public static String getData(final String desiredUrl) throws MalformedURLException, ProtocolException, IOException {
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
}
