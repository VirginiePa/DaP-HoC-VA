/**
 * 
 */
package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.hoc.dap.server.service.CredentialService;

/**
 * @author house
 *
 */
@Controller
public class AdminController {
    @Autowired
    private CredentialService cs;

    /**
     * @param args
     * @throws IOException 
     * @throws GeneralSecurityException 
     */
    @RequestMapping("/admin")
    public String displayAdmin(ModelMap model) throws Exception, IOException {
        DataStore<StoredCredential> users = cs.listUser();

        Map<String, StoredCredential> userMaps = new HashMap<>();
        Set<String> allKeys = users.keySet();

        for (String aKey : allKeys) {
            StoredCredential value = users.get(aKey);
            value.setExpirationTimeMilliseconds(value.getExpirationTimeMilliseconds() / 1000 / 60 / 60 / 24); // En
                                                                                                              // jours
            userMaps.put(aKey, value);
        }

        model.addAttribute("informations", userMaps);
        return "admin";
    }

    /**
     * @param args
     * @throws IOException
     * @throws GeneralSecurityException
     */
    @RequestMapping("/admin2")
    public String essai(ModelMap model) throws Exception, IOException {
        Map<Integer, String> usersInformations = new HashMap<Integer, String>();

        String[] tempUsersInformations = String.valueOf(cs.listUser()).split("\\}");

        for (int i = 0; i < tempUsersInformations.length; i++)
            usersInformations.put(i, tempUsersInformations[i]);

        List<String> ids = new ArrayList<String>();
        List<String> accesToken = new ArrayList<String>();
        List<String> refreshToken = new ArrayList<String>();
        List<String> expirationTime = new ArrayList<String>();

        for (Map.Entry<Integer, String> entry : usersInformations.entrySet()) {

            entry.setValue(entry.getValue().replaceAll(",", ""));
            entry.setValue(entry.getValue().replaceAll("\\{", ""));

            ids.add(entry.getValue().substring(0, entry.getValue().indexOf("=")).trim());
            accesToken.add(entry.getValue().substring(entry.getValue().indexOf("accessToken="),
                    entry.getValue().indexOf("accessToken=") + 143));
            refreshToken.add(entry.getValue().substring(entry.getValue().indexOf("refreshToken="),
                    entry.getValue().indexOf("refreshToken=") + 79));
            expirationTime.add(entry.getValue().substring(entry.getValue().indexOf("expirationTimeMilliseconds="),
                    entry.getValue().indexOf("expirationTimeMilliseconds=") + 40));

        }

        Map<String, List<String>> test = new HashMap<String, List<String>>();
        test.put("id", ids);
        test.put("at", accesToken);
        test.put("rt", refreshToken);
        test.put("et", expirationTime);

        model.addAttribute("informations", test);

        return "admin";
    }

}
