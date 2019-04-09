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
 * Classe REST controller : elle traite les requêtes via http et va renvoyer le
 * nombre de mails non lus en json via la méthode Index.
 */
@RestController
public class MailController {
    /** instanciates a service of GMailService type. */
    @Autowired
    private GMailService gmail;

    /**
     * @return le nombre de mail non lus du userKey.
     * @param userKey : compte Google à renseigner dans l'URL.
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
