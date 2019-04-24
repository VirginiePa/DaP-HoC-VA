//TODO VA by Djer |JavaDoc| Devrait être sur classe
/**
 * @author Virginie et Armand.
 *
 */
package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.Person;

/** . */
public final class PeoplService extends GoogleService {

    /**
     * //TODO VA by Djer |JavaDoc| Il manque la description (de la méthode) : première ligne de la JavaDoc
     * @return a serice
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     * @throws IOException If the credentials.json file cannot be found.
     */
    //TODO VA by Djer |POO| "buildService" serait mieux
    public PeopleService getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httptransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleService gs = new GoogleService();
        return new PeopleService.Builder(httptransport, JSON_FACTORY, getCredentials(httptransport, "me"))
                .setApplicationName(gs.getConfig().getAppName()).build();
    }

    /**
     * //TODO VA by Djer |JavaDoc| Cette description ne semble pas correspondre à la méthode
     * Creates an authorized Credential object.
     *
     * @throws IOException              If the credentials.json file cannot be found.
     * @throws GeneralSecurityException If can't connect.
     */
    public void getPeople() throws IOException, GeneralSecurityException {
        //TODO VA by Djer |Log4J| Une petite log (en Info) ?

        //TODO VA by Djer |POO| Ce commentaire est devenu FAUX !
        // Request 10 connections.
        ListConnectionsResponse response = getService().people().connections().list("people/me").setPageSize(1)
                .setPersonFields("names,emailAddresses").execute();

        // Print display name of connections if available.
        List<Person> connections = response.getConnections();

        if (connections != null && connections.size() > 0) {
            for (Person person : connections) {
                List<Name> names = person.getNames();
                if (names != null && names.size() > 0) {
                    //TODO VA by Djer |POO| Pas de SysOut sur un serveur
                    System.out.println("Name: " + person.getNames().get(0).getDisplayName());
                } else {
                    //TODO VA by Djer |POO| Pas de SysOut sur un serveur
                    System.out.println("No names available for connection.");
                }
            }
        } else {
            //TODO VA by Djer |POO| Pas de SysOut sur un serveur
            System.out.println("No connections found.");
        }
    }
}
