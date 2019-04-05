package fr.hoc.dap.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.hoc.dap.server.service.Config;

/**
 * Application permettant de récupérer les e-mails non lus et le dernier
 * évènement pour différents users Google. Possibilité d'ajouter un nouvel user.
 * Point d'entrée de l'application.
 *
 * @author house
 */
@SpringBootApplication
public class Application {
    /** . */
    // private static final Logger LOG = LogManager.getLogger();

    /**
     * Méthode principale. Lancement de Spring.
     * @param args arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * @return la configuration par défaut.
     */
    @Bean
    public Config createConf() {
        Config conf = new Config();
        return conf;
    }
}
