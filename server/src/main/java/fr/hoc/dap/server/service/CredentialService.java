package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

/**
 * @author Virginie et Armand.
 *
 * //TODO VA by Djer |JavaDoc| Une classe en "retourne" rien, elle sert de "gabarit" pour créer des objets. Se sont les méthodes qui "retourne" quelque-chose
 * Returns the data stored by google. */
@Service
public class CredentialService extends GoogleService {
     /**
      * //TODO VA by Djer |JavaDoc| Il manque la description (de la méthode) : première ligne de la JavaDoc
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     * @throws IOException if credentials aren't valid or file not found.
     * @return Store Credential.
     */
    //TODO VA by Djer |POO| En gnénéral les nom de méthode contienne un verbe : "retrieveUserList" ou "extractUserList" serait mieux
    public DataStore<StoredCredential> listUser() throws GeneralSecurityException, IOException {
      //TODO VA by Djer |Log4J| Une petite Log (en Info) : "Retrieving list of All Google credentials"
        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore();
        return datas;
    }
}
