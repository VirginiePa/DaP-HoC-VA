package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

/**
 * @author Virginie et Armand.
 *
 * Returns the data stored by google. */

@Service
public class CredentialService extends GoogleService {
     /**
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     * @throws IOException if credentials aren't valid or file not found.
     * @return Store Credential.
     */
    public DataStore<StoredCredential> listUser() throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore();
        return datas;
    }
}



