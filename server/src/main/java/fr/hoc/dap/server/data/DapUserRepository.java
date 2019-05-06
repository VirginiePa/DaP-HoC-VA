package fr.hoc.dap.server.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Gérer les accès au DaP user aux bases de données.
 * @author Virginie et Armand
 *
 */
public interface DapUserRepository extends CrudRepository<DapUser, Long> {
    DapUser findByUserKey(String userKey);
}
