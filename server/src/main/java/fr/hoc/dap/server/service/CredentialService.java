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
    public DataStore<StoredCredential> listUser() throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore();
        return datas;
    }

    //    public String deleteAccount(@PathVariable final String userId) throws GeneralSecurityException {
    //
    //        String response;
    //     
    //                // redirect to the authorization flow
    //        final AuthorizationCodeRequestUrl authorizationUrl = getFlow().AuthorizationUrl();
    //
    //                // store userId in session for CallBack Access
    //
    //        response = authorizationUrl.remove("userId", userId);
    //
    //        return response;
    //    }
}



