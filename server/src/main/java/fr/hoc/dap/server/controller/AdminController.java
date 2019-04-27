/**
 * //TODO VA by Djer |JavaDoc| Devrait être sur la JavaDoc de la classe.
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
    //TODO VA by Djer |JavaDoc| Faux (n'instanciate pas du tout, au "mieux" réserve de la mémoire pour) et inutile (le Type est autpatiquement extrait par JavaDoc). "The CredentialService" est suffisant (les curieux pourront allez lire la documentation de la lcasse "CredentialService").
    /** instanciates a service of CredentialService type. */
    @Autowired
    private CredentialService cs;

    //TODO VA by Djer |JavaDoc| Potentiellement "faux", le "localHost:8080" est coffigurable, donc cela peut changer.
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
        DataStore<StoredCredential> users = cs.listUser();

        Map<String, StoredCredential> userMaps = new HashMap<>();
        Set<String> allKeys = users.keySet();

        for (String aKey : allKeys) {
            StoredCredential value = users.get(aKey);
            //TODO VA by Djer |POO| Attention provoque un NullPointerException si un "ExpirationTimeMilliseconds" est null pour un des "users"
            //TODO VA by Djer |POO| En général on évite de modifier les valeurs d'un Objet dont est est pas "maitre" (le risque ici c'est qu'une autre méthode utulitaire (de Google API) manipule cette données et du coups fasse n'importe quoi). Une des solutuons est decréer votre porpre classe (qui pourrai hérité d e StoredCredential", ou de traiter le calcul d'affichage dans la vue)
            //TODO VA by Djer |POO| Plante si pour UN des crrédential le "expiration time" est null (je vous propose une correction)
            //value.setExpirationTimeMilliseconds(value.getExpirationTimeMilliseconds() / 1000 / 60 / 60 / 24); // En jour
            if (null != value.getExpirationTimeMilliseconds()) {
              //TODO VA by Djer |POO| Au lieu du commentaire "en jour" cré une constante "MS_TO_DAY" qui contient la vlaeur 1000 * 60 * 60 * 24, et utilise cette constante ici
                value.setExpirationTimeMilliseconds(value.getExpirationTimeMilliseconds() / 1000 / 60 / 60 / 24); // En jour
            }
            userMaps.put(aKey, value);
        }

        model.addAttribute("informations", userMaps);
        return "admin";
    }
}
