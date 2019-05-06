package fr.hoc.dap.server.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.people.v1.PeopleServiceScopes;

/**
 *  Classe autorisant l'accès.
 *  
 * @author Virginie et Armand.
 */
class GoogleService {
    /** Instanciates the logger. */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * json factory instanciation.
     */
    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Injecte une instance du bean créé dans Application .
     * */
    @Autowired
    private Config configuration;

    /**
     * Global instance of the scopes required by this quickstart. If modifying these scopes, delete your previously
     * saved tokens/ folder.
     */
    private static ArrayList<String> scopes = new ArrayList<String>();

    /**
     * Creates an authorized Credential object.
     *
     * @param httptransport The network HTTP Transport.
     * @param userKey       .
     * @return An authorized Credential object.
     * @throws IOException if credentials aren't valid or file not found.
     * @throws GeneralSecurityException 
     */
    public Credential getCredentials(final NetHttpTransport httptransport, final String userKey)
            throws IOException, GeneralSecurityException {
        return this.getFlow().loadCredential(userKey);
    }

    /**
     * //TODO VA by Djer |JavaDoc| Il manque la description (de la méthode) : première ligne de la JavaDoc
     * @return a flow
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     * @throws IOException if credentials aren't valid or file not found.
     */
    public GoogleAuthorizationCodeFlow getFlow() throws GeneralSecurityException, IOException {
        scopes.add(GmailScopes.GMAIL_READONLY);
        scopes.add(PeopleServiceScopes.CONTACTS_READONLY);
        scopes.add(GmailScopes.GMAIL_LABELS);
        scopes.add(CalendarScopes.CALENDAR_READONLY);
        final NetHttpTransport httptransport = GoogleNetHttpTransport.newTrustedTransport();
        File in = new java.io.File(configuration.getCreditFilePath());

        if (in.length() == 0) {

            LOG.error("Failed to initialize file input.Check if configuration autowired is null. Config get file path : " + configuration.getCreditFilePath());
        }

        Reader targetReader = new FileReader(in);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, targetReader);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httptransport, JSON_FACTORY,
                clientSecrets, scopes)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(configuration.getTokenFolder())))
                        .setAccessType("offline").build();

        if (flow == null) {
            LOG.error("Error initializing flow");
        }
        return flow;
    }

    /**
     * @param config sets a config .
     */
    public void setConfig(final Config config) {
        this.configuration = config;
    }

    /**
     * @return a config .
     */
    public Config getConfig() {
        return this.configuration;
    }
}
