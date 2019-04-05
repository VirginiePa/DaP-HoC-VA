/**
 * 
 */
package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.hoc.dap.server.service.CredentialService;

/**
 * @author house
 *
 */
@Controller
public class AdminController {
    CredentialService cs = new CredentialService();

    /**
     * @param args
     * @throws IOException 
     * @throws GeneralSecurityException 
     */
    @RequestMapping("/admin")
    public String essai(ModelMap model) throws Exception, IOException {
        model.addAttribute("informations", cs.listUser());
        return "admin";
    }

}
