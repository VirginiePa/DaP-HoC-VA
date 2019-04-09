/**
 * @author Virginie et Armand.
 *
 */
package fr.hoc.dap.server.controller;


import java.io.IOException;
import java.util.HashMap;
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
 * Affichage du store credential et gestion des user.
 */
@Controller
public class AdminController {
    /** instanciates a service of CredentialService type. */
    @Autowired
    private CredentialService cs;

    /** for mapping web request : localhost:8080/admin.
     * @return le store credential sous forme de tableau. */
    @RequestMapping("/admin")
    /**
    * @param model : .
    * @return le store credential .
    * @throws IOException if credentials aren't valid or file not found.
    * @throws Exception Constructs a GeneralSecurityException with the specified detail message.
    *
    */
    public String displayAdmin(final ModelMap model) throws Exception, IOException {
        DataStore<StoredCredential> users = cs.listUser();

        Map<String, StoredCredential> userMaps = new HashMap<>();
        Set<String> allKeys = users.keySet();

        for (String aKey : allKeys) {
            StoredCredential value = users.get(aKey);
            value.setExpirationTimeMilliseconds(value.getExpirationTimeMilliseconds() / 1000 / 60 / 60 / 24); // En jour
            userMaps.put(aKey, value);
        }

        model.addAttribute("informations", userMaps);
        return "admin";
    }

}
