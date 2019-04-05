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
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/**
 * Pour se connecter au service users et récupérer les mails non lus.
 */
@Service
public final class GMailService extends GoogleService {
    /** . */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * @return the gmail service.
     * @param userKey : compte Google.
     * @throws GeneralSecurityException : if security related class problem.
     * @throws IOException              : if credentials problem.
     */
    public Gmail getService(final String userKey) throws GeneralSecurityException, IOException {
        final NetHttpTransport httptransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Gmail.Builder(httptransport, JSON_FACTORY, getCredentials(httptransport, userKey))
                .setApplicationName(getConfig().getAppName()).build();
    }

    /**
     * @param service googleservice.
     * @param userId  ID du compte Google.
     * @param labelId ID du label du mail.
     * @throws IOException if credentials aren't good.
     * @return label as string
     */
    public Integer getLabel(final Gmail service, final String userId, final String labelId) throws IOException {
        Label label = service.users().labels().get(userId, labelId).execute();

        LOG.info("Label " + label.getName() + " retrieved.");
        LOG.info(label.getMessagesUnread());

        return label.getMessagesUnread();
    }

    /**
     * @return the number of unread emails
     * @param userKey : compte Google.
     * @throws IOException              : if credentials aren't good.
     * @throws NumberFormatException    : the application has attempted to a numeric types and not to a string.
     * @throws GeneralSecurityException : if security related class problem.
     */
    public Integer getNumberUnreadEmails(final String userKey)
            throws IOException, NumberFormatException, GeneralSecurityException {
        return getLabel(getService(userKey), "me", "UNREAD");
    }

    /**
     * @param service   googleservice.
     * @param userId    ID du compte Google.
     * @param messageId messageId.
     * @throws IOException if credentials aren't valid.
     * @return theMessage.
     */
    public Message getMessage(final Gmail service, final String userId, final String messageId) throws IOException {
        Message message = service.users().messages().get(userId, messageId).execute();

        System.out.println(StringUtils
                .newStringUtf8(Base64.decodeBase64(message.getPayload().getParts().get(0).getBody().getData())));
        return message;
    }

    /**
     * @param service googleservice.
     * @param userId  userId.
     * @param query   query.
     * @throws IOException if credentials aren't valid.
     * @return theMessage.
     */
    public List<Message> listMessagesMatchingQuery(final Gmail service, final String userId, final String query)
            throws IOException {
        ListMessagesResponse response = service.users().messages().list(userId).setQ(query).execute();

        List<Message> messages = new ArrayList<Message>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = service.users().messages().list(userId).setQ(query).setPageToken(pageToken).execute();
            } else {
                break;
            }
        }

        for (Message message : messages) {
            getMessage(service, userId, message.getId());
        }
        return messages;
    }

}
