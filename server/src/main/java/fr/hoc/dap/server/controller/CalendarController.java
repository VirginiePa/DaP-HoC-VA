/**
 * //TODO VA by Djer |JavaDoc| Devrait être sur la JavaDoc de la classe.
 * @author Virginie et Armand.
 */
package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.service.CalendarService;

/**
 * @author Virginie et Armand
 * //TODO VA by Djer |JavaDoc| La "description" DOIT ête la première ligne de la JavaDoc
 * Classe REST controller : elle traite les requêtes via http et va renvoyer les
 * prochins évènements en json via la méthode nextEvents.
 */
@RestController
public class CalendarController {
    /** Acces to Calendar API. */
    @Autowired
    private CalendarService calendar;

    /**
     * //TODO VA by Djer |JavaDoc| Il manque la description (de la méthode) : première ligne de la JavaDoc
     * @return les prochains évènements du userKey.
     * @param userKey : compte Google à renseigner dans l'URL.
     * @param eventNb : nombre d'évènement par défaut = 1.
     * @throws GeneralSecurityException Constructs a GeneralSecurityException with the specified detail
     * message.
     * @throws IOException if credentials aren't valid or file not found.
     * @throws NumberFormatException    : the application has attempted to a numeric
     *                                  types and not to a string.
     */
    @RequestMapping("/event/next")
    public List<String> nextEvents(@RequestParam(name = "eventNb", defaultValue = "1") final Integer eventNb,
            @RequestParam(name = "userKey") final String userKey)
            throws NumberFormatException, IOException, GeneralSecurityException {
        return calendar.getNextEvents(eventNb, userKey);
    }
}
