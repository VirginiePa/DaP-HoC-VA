package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.hoc.dap.server.service.CredentialService;

/**
 * Affichage du store credential et gestion des user.
 * @author Virginie et Armand.
 */
@Controller
public class AdminController {
    /**.*/
    private static final Logger LOG = LogManager.getLogger();
    /** instanciates a service of CredentialService type. */
    @Autowired
    private CredentialService cs;

    private static final double MSTODAY = 86400000;

    //TODO VA by Djer |JavaDoc| Il ne faut pas documenter l'annotation, mais la méthode qui est en dessous (que vous avez deja partiellement fait)
    /** for mapping web request : localhost:8080/admin.
     * //TODO VA by Djer |JavaDoc| Non ! Return une Vue. Ce que produit cette vue peut changer, sans pour autant que l'on doivent changer cette JavaDoc
     * @return le store credential sous forme de tableau. */
    @RequestMapping("/admin")
    /**
     *
    * @param model : .
    * @return le store credential .
    * @throws IOException if credentials aren't valid or file not found.
    * @throws Exception Constructs a GeneralSecurityException with the specified detail message.
    */
    public String displayAdmin(final ModelMap model) throws Exception, IOException {
        DataStore<StoredCredential> users = cs.retrieveUserList();

        Map<String, StoredCredential> userMaps = new HashMap<>();
        Set<String> allKeys = users.keySet();

        for (String aKey : allKeys) {
            try {
            StoredCredential value = users.get(aKey);
                if (null != value.getExpirationTimeMilliseconds()) {
                    value.setExpirationTimeMilliseconds((long) (value.getExpirationTimeMilliseconds() / MSTODAY));
                }
                userMaps.put(aKey, value);
            } catch (Exception e) {
                LOG.error("Error while getting credentials :" + e);
            }

            //value.setExpirationTimeMilliseconds(value.getExpirationTimeMilliseconds() / 1000 / 60 / 60 / 24); // En jour


        }

        model.addAttribute("informations", userMaps);
        return "admin";
    }
}
