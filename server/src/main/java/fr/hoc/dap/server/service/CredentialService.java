/**
 * 
 */
package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

/**
 * @author house
 *
 */
@Service
public class CredentialService extends GoogleService {
    GoogleService gs = new GoogleService();

    public DataStore<StoredCredential> listUser() throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore();

        return datas;
    }


}
