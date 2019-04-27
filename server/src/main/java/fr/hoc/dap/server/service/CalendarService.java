//TODO VA by Djer |JavaDoc| Devrait être sur classe
/**
 * @author Virginie et Armand.
 *
 */
package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

/** Pour se connecter au service Calendar et récupérer les évènements. */
@Service
public final class CalendarService extends GoogleService {
    //TODO VA by Djer |JavaDoc| "Logger" serait suffisant
    /** instanciates the log manager. */
    private static final Logger LOG = LogManager.getLogger();

    /** declare the max number of events as a constant. */
    private static final Integer NBMAX = 10;

    /**
     * Build a new authorized API client service.
     *
     * @return a service.
     * @param userKey : compte Google.
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     * @throws IOException if credentials aren't valid or file not found.
     */
    //TODO VA by Djer |POO| "buildService" serait mieux
    public Calendar getService(final String userKey) throws GeneralSecurityException, IOException {
        final NetHttpTransport httptransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Calendar.Builder(httptransport, JSON_FACTORY, getCredentials(httptransport, userKey))
                .setApplicationName(getConfig().getAppName()).build();
    }

    /**
     *  //TODO VA by Djer |JavaDoc| Cette description ne semble pas correspondre à la méthode
     * Creates an authorized Credential object.
     *
     * @param userKey : compte Google. //TODO VA by Djer |JavaDoc| "compte DaP"
     * @return the next 10 events from the primary calendar.
     * @param maxResults : nombre d'évènements max, ici = 10.
     * @throws IOException if credentials aren't valid or file not found.
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     */
    //TODO VA by Djer |POO| Si tu porposes a l'appelant de definir le nbMax, il faut l'utiliser dans ton code !
    public List<String> getNextEvents(final Integer maxResults, final String userKey)
            throws IOException, GeneralSecurityException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = getService(userKey).events().list("primary").setMaxResults(NBMAX).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();

        //TODO VA by Djer |POO| Pas top comme nom de varaible, "result" ou "eventsInfo" serait mieux
        List<String> test = new ArrayList<String>();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            //TODO VA by Djer |Log4J| Ca n'est pas vraiment uen "error", l'utilisateur a le droit de ne pas avoir d'évènnements à venir. Le "level" Info serait plus adapté
            LOG.error("items list is empty");
        } else {
            LOG.info("upcoming events found");
            //TODO VA by Djer |POO| en général on utilise "i" poour un compte. "eventindexAnalysed" serait aussi pas mal
            int a = 0;
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
              //TODO VA by Djer |POO| Il existe une méthdoe "add" sans avoir besoi nde préciser l'index (qui ajoute à la fin). On utilise cette version lorsqu'on veux insérer "au milieu" (ou à un autre endroit précis)
                test.add(a, "event name : " + event.getSummary() + " event date:  " + start + " event status: "
                        + event.getStatus() + "Event attendees :->" + event.getAttendees());
                //TODO VA by Djer |POO| Pas de Sysout sur un serveur !!! Utilises une log si nécéssaire
                System.out.println(test);
                a++;
            }
        }

        //TODO VA by Djer |Log4J| Il serait plsu "claire dnas ta log" d'affiche la valeur que tu vas erenvoyer ici (en "info" à priori) plutot que dans ta boucle.

        return test;
    }
}
