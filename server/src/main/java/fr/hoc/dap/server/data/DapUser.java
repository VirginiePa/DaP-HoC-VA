package fr.hoc.dap.server.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Contient tous les attributs pour gérer un utilisateur DaP.
 * @author Virginie et Armand
 */
@Entity
public class DapUser {

    /** Dap User Id. */
    @Id
    @GeneratedValue
    private Long idDap;
    /** Dap userKey (to store in Google credential). */
    @Column(nullable = false, unique = true)
    private String userKey;
    /** Dap User loginName. */
    private String loginName;

    /**
     * @return the id
     */
    public Long getId() {
        return idDap;
    }

    /**
     * @param newId the id to set
     */
    public void setId(final Long newId) {
        this.idDap = newId;
    }

    /**
     * @return the userKey
     */
    public String getUserKey() {
        return userKey;
    }

    /**
     * @param newUserKey the userKey to set
     */
    public void setUserKey(final String newUserKey) {
        this.userKey = newUserKey;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param newLoginName the loginName to set
     */
    public void setLoginName(final String newLoginName) {
        this.loginName = newLoginName;
    }

}
