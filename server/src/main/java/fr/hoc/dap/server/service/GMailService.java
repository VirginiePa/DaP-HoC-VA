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
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/**
 * //TODO VA by Djer |JavaDoc| Evite dedécrire ce que ca fait, mais plutot se que c'est : "Gère les accès aux mails Google" serait suffisant.
 * Pour se connecter au service users et récupérer les mails non lus.
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
    //TODO VA by Djer |POO| "buildService" serait mieux
    public Gmail getService(final String userKey) throws GeneralSecurityException, IOException {
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
    //TODO VA by Djer |POO| "retrieveLabels" serait mieu
    public Integer getLabel(final Gmail service, final String userId, final String labelId) throws IOException {
        //TODO VA by Djer |Log4J| Une petite Log (en Info)
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
        return getLabel(getService(userKey), "me", "UNREAD");
    }

    /**
     * //TODO VA by Djer |JavaDoc| Il manque la description (de la méthode) : première ligne de la JavaDoc
     * @param service   googleservice.
     * @param userId    ID du compte Google.
     * @param messageId messageId.
     * @throws IOException if credentials aren't valid or file not found.
     * @return theMessage.
     */
    //TODO VA by Djer |POO| Devrait être privé (utile que dans cette classe)
    //TODO VA by Djer |POO| "retreiveMessage" serait mieu
    public Message getMessage(final Gmail service, final String userId, final String messageId) throws IOException {
        //TODO VA by Djer |Log4J| Une petite Log (en Info)
        Message message = service.users().messages().get(userId, messageId).execute();

        //TODO VA by Djer |POO| Pas de SysOut sur un serveur ! Utilise une LOG (en debug a priori ici) si nécéssaire
        System.out.println(StringUtils
                .newStringUtf8(Base64.decodeBase64(message.getPayload().getParts().get(0).getBody().getData())));
        //TODO VA by Djer |POO| Devrait renvoyer me "message" décodé tel que fait dans le "SysOut" au dessus ? 
        return message;
    }

    /**
     * //TODO VA by Djer |JavaDoc| Il manque la description (de la méthode) : première ligne de la JavaDoc
     * @param service googleservice.
     * @param userId  userId.
     * @param query   query.
     * @throws IOException if credentials aren't valid or file not found.
     * @return theMessage.
     */
    public List<Message> listMessagesMatchingQuery(final Gmail service, final String userId, final String query)
            throws IOException {
        //TODO VA by Djer |Log4J| Une petite Log (en Info)
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
            //TODO VA by Djer |POO| Cette méthode sert a "extraire le message" si tu ne récupère/exmploite pas la valeur de retour, ce n'est pas (plus ?) utile d'appeler cette méthode
            getMessage(service, userId, message.getId());
        }
        return messages;
    }
}
