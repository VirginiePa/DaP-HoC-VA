package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;

/**
 * Gère les accès aux mails Google.
 * @author Virginie et Armand.
 */
@Service
public final class GMailService extends GoogleService {
    /** . */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * //TODO VA by Djer |JavaDoc| Il manque la description (de la méthode) : première ligne de la JavaDoc
     * @return the gmail service.
     * @param userKey : compte Google.
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     * @throws IOException if credentials aren't valid or file not found.
     */
    public Gmail buildService(final String userKey) throws GeneralSecurityException, IOException {
        final NetHttpTransport httptransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Gmail.Builder(httptransport, JSON_FACTORY, getCredentials(httptransport, userKey))
                .setApplicationName(getConfig().getAppName()).build();
    }

    /**
     * //TODO VA by Djer |JavaDoc| Il manque la description (de la méthode) : première ligne de la JavaDoc
     * @param service googleservice.
     * @param userId  ID du compte Google.
     * @param labelId ID du label du mail.
     * @throws IOException if credentials aren't good.
     * @return label as string
     */
    public Integer retrieveLabel(final Gmail service, final String userId, final String labelId) throws IOException {
        Label label = service.users().labels().get(userId, labelId).execute();
        LOG.info("Label " + label.getName() + " retrieved.");
        LOG.info(label.getMessagesUnread());
        return label.getMessagesUnread();
    }

    /**
     * //TODO VA by Djer |JavaDoc| Il manque la description (de la méthode) : première ligne de la JavaDoc
     * @return the number of unread emails
     * @param userKey : compte Google.
     * @throws IOException if credentials aren't valid or file not found.
     * @throws NumberFormatException    : the application has attempted to a numeric types and not to a string.
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     */
    public Integer getNumberUnreadEmails(final String userKey)
            throws IOException, NumberFormatException, GeneralSecurityException {
        return retrieveLabel(buildService(userKey), "me", "UNREAD");
    }

}
