package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

/**
 * Pour se connecter au service Credential Service et en extraire les informations.
 * @author Virginie et Armand.
 *  */
@Service
public class CredentialService extends GoogleService {
    /** . */
    private static final Logger LOG = LogManager.getLogger();
     
    /**
    * Returns the data stored by google.
    * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
    * message.
    * @throws IOException if credentials aren't valid or file not found.
    * @return Store Credential.
    */
    public DataStore<StoredCredential> retrieveUserList() throws GeneralSecurityException, IOException {
        LOG.info("Retrieving list of All Google credentials : " + getFlow().getCredentialDataStore());
        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore();
        return datas;
    }
}
