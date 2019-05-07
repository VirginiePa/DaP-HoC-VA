
package fr.hoc.dap.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.data.DapUser;
import fr.hoc.dap.server.data.DapUserRepository;

/**
 * Permet de faire des tests des entités JPA.
 * @author Virginie et Armand
 *
 */
@RestController
public class TestJpaController {
    @Autowired
    private DapUserRepository dapUserRepo;

    /**
     * Crée un nouvel utilisateur DaP avec un userKey.
     * Exemple d'appel : /test/createDapUser?loginName=Bibi&userKey=BibiPerso
     * @param loginName Dap Login Name
     * @param userKey Dap User Key
     * @return savedUser
     */
    @RequestMapping("test/createDapUser")
    public DapUser createDapUser(final String loginName, final String userKey) {
        DapUser monUser = new DapUser();
        monUser.setLoginName(loginName);
        monUser.setUserKey(userKey);
        //TODO save the user
        DapUser savedUser = dapUserRepo.save(monUser);
        return savedUser;
    }

    //    /**
    //     * Crée un nouvel utilisateur DaP avec un userKey.
    //     */
    //    @RequestMapping("/user/add/{userKey}")
    //    public RedirectView redirectWithUsingRedirectView(@PathVariable final String userKey,
    //            @RequestParam final String loginName) {
    //        DapUser monUser = new DapUser();
    //        monUser.setLoginName(loginName);
    //        monUser.setUserKey(userKey);
    //        dapUserRepo.save(monUser);
    //        return new RedirectView("/account/add/" + userKey);
    //    }
    //    

    /**
     * Crée un nouvel utilisateur DaP avec un userKey.
     * Exemple d'appel : /test/createDapUser?loginName=Bibi&userKey=BibiPerso
     * @param loginName Dap Login Name
     * @param userKey Dap User Key
     * @return savedUser
    */
    @RequestMapping("/test/loadDapUser")
    public DapUser loadDapUser(@RequestParam String userKey) {
        DapUser loadUser = new DapUser();
        loadUser = dapUserRepo.findByUserKey(userKey);
        System.out.println(loadUser);
        return loadUser;
    }

}
