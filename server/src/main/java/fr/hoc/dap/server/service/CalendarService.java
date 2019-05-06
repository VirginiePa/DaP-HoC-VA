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

/**
 * Pour se connecter au service Calendar et récupérer les évènements.
 * @author Virginie et Armand.
 */
@Service
public final class CalendarService extends GoogleService {
    /** Logger. */
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
    public Calendar buildService(final String userKey) throws GeneralSecurityException, IOException {
        final NetHttpTransport httptransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Calendar.Builder(httptransport, JSON_FACTORY, getCredentials(httptransport, userKey))
                .setApplicationName(getConfig().getAppName()).build();
    }

    /**
     *  //TODO VA by Djer |JavaDoc| Cette description ne semble pas correspondre à la méthode
     * Creates an authorized Credential object.
     *
     * @param userKey : compte DaP.
     * @return the next 10 events from the primary calendar.
     * @param maxResults : nombre d'évènements max, ici = 10.
     * @throws IOException if credentials aren't valid or file not found.
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     */
    public List<String> getNextEvents(final String userKey)
            throws IOException, GeneralSecurityException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = buildService(userKey).events().list("primary").setMaxResults(NBMAX).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();

        List<String> eventsInfo = new ArrayList<String>();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            LOG.info("items list is empty");
        } else {
            for (Event eventIndexAnalysed : items) {
                DateTime start = eventIndexAnalysed.getStart().getDateTime();
                if (start == null) {
                    start = eventIndexAnalysed.getStart().getDate();
                }
                eventsInfo.add("event name : " + eventIndexAnalysed.getSummary() + " event date:  " + start
                        + " event status: " + eventIndexAnalysed.getStatus() + "Event attendees :->"
                        + eventIndexAnalysed.getAttendees());
                LOG.info("Following items were retrieved : " + "event name : " + eventIndexAnalysed.getSummary()
                        + " event date:  " + start + " event status: " + eventIndexAnalysed.getStatus()
                        + "Event attendees :->" + eventIndexAnalysed.getAttendees());
            }
        }
        return eventsInfo;
    }
}
