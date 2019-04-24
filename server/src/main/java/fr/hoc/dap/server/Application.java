/**
 * //TODO VA by Djer |JavaDoc| Ce bloque de commentaire n'est pas de la JavaDoc "officiel". LE @Author est en général préiciser sur la CLasse. Ce bloque de commentaire sert pour donner des informations sur le fichier (en général la License)
 * @author Virginie et Armand.
 *
 */
package fr.hoc.dap.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.hoc.dap.server.service.Config;

/**
 * //TODO VA by Djer |JavaDoc| Essayez de documenter ce que "c'est", pas ce que ca fait". LE "ce que ca fait hange" plus souvent que le "ce que c'est". EX : bonne "Armand est un étudiant à Housse of Code" ; Mauvais : "Armand peut lire ecrire, il apprend a lire et écreir du Java, du HTML, du PHP; Il peut s'assoire, se lever, marchern se nourire, ..."
 * Application permettant de récupérer les e-mails non lus et le dernier
 * évènement pour différents users Google. Possibilité d'ajouter un nouvel usere
 * Point d'entrée de l'application.
 */
@SpringBootApplication
public class Application {
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
