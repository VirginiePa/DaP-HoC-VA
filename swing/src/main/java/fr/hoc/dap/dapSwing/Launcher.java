package fr.hoc.dap.dapSwing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
     * @param desiredUrl : connexion. //TODO VA by Djer |JavaDoc| "URL pour récupérer les données" serait mieu
     * @return informations de l'Url. //TODO VA by Djer |JavaDoc| "Contenu (body) de la réponse à la requete" serait mieu
     * @throws Exception si erreurs.
     */
    //TODO VA by Djer |Gestion Exception| Eviter de "throws" Exception qui est un peu trop global. Il est possible d'être plus spécifique (Eclipse peu vous aider)
    public static String getData(final String desiredUrl) throws Exception {
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
