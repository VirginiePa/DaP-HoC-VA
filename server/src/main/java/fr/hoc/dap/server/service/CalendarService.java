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
    public Calendar getService(final String userKey) throws GeneralSecurityException, IOException {
        final NetHttpTransport httptransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Calendar.Builder(httptransport, JSON_FACTORY, getCredentials(httptransport, userKey))
                .setApplicationName(getConfig().getAppName()).build();
    }

    /**
     * Creates an authorized Credential object.
     *
     * @param userKey : compte Google.
     * @return the next 10 events from the primary calendar.
     * @param maxResults : nombre d'évènements max, ici = 10.
     * @throws IOException if credentials aren't valid or file not found.
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     */
    public List<String> getNextEvents(final Integer maxResults, final String userKey)
            throws IOException, GeneralSecurityException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = getService(userKey).events().list("primary").setMaxResults(NBMAX).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();

        List<String> test = new ArrayList<String>();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            LOG.error("items list is empty");
        } else {
            LOG.info("upcoming events found");
            int a = 0;
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                test.add(a, "event name : " + event.getSummary() + " event date:  " + start + " event status: "
                        + event.getStatus() + "Event attendees :->" + event.getAttendees());
                System.out.println(test);
                a++;
            }
        }
        return test;
    }

}
