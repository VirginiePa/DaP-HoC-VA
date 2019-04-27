/**
 * @author Virginie et Armand.
 *
 */
package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.service.GMailService;

/**
 * //TODO VA by Djer |JavaDoc| Evite de parler des méthodes dans la JavaDoc de la classe, Si tu ajoutes des méhodes il faudra la modifier. "Classe communiquant les informatiosn sur les e-mails" serait suffisant
 * Classe REST controller : elle traite les requêtes via http et va renvoyer le
 * nombre de mails non lus en json via la méthode Index.
 */
@RestController
public class MailController {
    //TODO VA by Djer |JavaDoc| Faux (n'instanciate pas du tout, au "mieux" réserve de la mémoire pour) et inutile (le Type est autpatiquement extrait par JavaDoc). "The CredentialService" est suffisant (les curieux pourront allez lire la documentation de la lcasse "CredentialService").
    /** instanciates a service of GMailService type. */
    @Autowired
    private GMailService gmail;

    /**
     * //TODO VA by Djer |JavaDoc| Il manque la description (de la méthode) : première ligne de la JavaDoc
     * @return le nombre de mail non lus du userKey.
     * @param userKey : compte Google à renseigner dans l'URL. //TODO VA by Djer |JavaDoc| C'est le compte **DaP** qu'il faut indiquer, pas celui de "Google"
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     * @throws IOException if credentials aren't valid or file not found.
     * @throws NumberFormatException    : the application has attempted to a numeric
     *                                  types and not to a string.
     */
    @RequestMapping("/email/nbunread")
    public String index(@RequestParam(name = "userKey") final String userKey)
            throws NumberFormatException, IOException, GeneralSecurityException {
        return "Number of unread emails : " + gmail.getNumberUnreadEmails(userKey);
    }
}
