package fr.hoc.dap.server.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

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
 * @author moi. Classe autorisant l'accees.
 */
class GoogleService {

    /**
     * . json factory instanciation
     */
    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /** . */
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
     * @throws IOException If the credentials.json file cannot be found.
     */

    protected Credential getCredentials(final NetHttpTransport httptransport, final String userKey) throws IOException {
        // final int port = 8888;
        scopes.add(GmailScopes.GMAIL_READONLY);
        scopes.add(PeopleServiceScopes.CONTACTS_READONLY);
        scopes.add(GmailScopes.GMAIL_LABELS);
        scopes.add(CalendarScopes.CALENDAR_READONLY);

        // Load client secrets.
        File in = new java.io.File(configuration.getCreditFilePath());
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httptransport, JSON_FACTORY,
                clientSecrets, scopes)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(configuration.getTokenFolder())))
                        .setAccessType("offline").build();
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(port).build();
//        return new AuthorizationCodeInstalledApp(flow, receiver).authorize(userKey);
        return flow.loadCredential(userKey);
    }

    /**
     * @return .
     * @throws GeneralSecurityException .
     * @throws IOException              .
     */
    public GoogleAuthorizationCodeFlow getFlow() throws GeneralSecurityException, IOException {
        scopes.add(GmailScopes.GMAIL_READONLY);
        scopes.add(PeopleServiceScopes.CONTACTS_READONLY);
        scopes.add(GmailScopes.GMAIL_LABELS);
        scopes.add(CalendarScopes.CALENDAR_READONLY);
        final NetHttpTransport httptransport = GoogleNetHttpTransport.newTrustedTransport();
        File in = new java.io.File(configuration.getCreditFilePath());
        Reader targetReader = new FileReader(in);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, targetReader);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httptransport, JSON_FACTORY,
                clientSecrets, scopes)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(configuration.getTokenFolder())))
                        .setAccessType("offline").build();
        return flow;
    }

    /**
     * @param config .
     */
    public void setConfig(final Config config) {
        this.configuration = config;
    }

    /** @return . */
    public Config getConfig() {
        return this.configuration;
    }

}
