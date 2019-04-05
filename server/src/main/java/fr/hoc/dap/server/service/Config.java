package fr.hoc.dap.server.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** * @author house * */
public class Config {
    /** . */
    private static final Logger LOG = LogManager.getLogger();
    /** * Fichier de permissions acceptées. */
    private static final String TOKEN_DIR = System.getProperty("user.home") + "\\dap\\tokens";
    /** * Fichier de config api. */
    private static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + "\\dap\\credentials.json";
    /** * Nom de l'application. */
    private static final String APPLICATION_NAME = "HoC DaP";
    /**
     * @param tokenFolderName if
     * @param credFilePath    if
     * @param appName         if
     */

    private String tokenFolder;
    /** . */
    private String credFilePath;
    /** . */
    private String appName;

    /** . */
    public Config() {
        tokenFolder = TOKEN_DIR;
        credFilePath = CREDENTIALS_FILE_PATH;
        appName = APPLICATION_NAME;
    }

    /**
     * @param tf  trkl
     * @param cfp trkl
     * @param an  trkl
     */
    Config(final String tf, final String cfp, final String an) {
        tokenFolder = tf;
        credFilePath = cfp;
        appName = an;
    }

    /**
     * @return tokenFolderName
     */
    public String getTokenFolder() {
        LOG.info("TokenFolder returned");
        return tokenFolder;
    }

    /**
     * @return tokenFolderName
     */
    public static String getoAuth2CallbackUrl() {
        return "/oAuth2Callback";
    }

    /**
     * @param newName d
     */
    public void setTokenFolderName(final String newName) {
        LOG.info("TokenFolder changed");
        this.tokenFolder = newName;
    }

    /**
     * @return tokenFolderName
     */
    public String getAppName() {
        LOG.info("AppName returned");
        return appName;
    }

    /**
     * @param newName d
     */
    public void setAppName(final String newName) {
        LOG.info("AppName changed");
        this.appName = newName;
    }

    /**
     * @return tokenFolderName
     */
    public String getCreditFilePath() {
        LOG.info("CreditFilePath returned");
        return credFilePath;
    }

    /**
     * @param newName d
     */
    public void setCreditFilePath(final String newName) {
        LOG.info("CreditFilePath changed");
        this.credFilePath = newName;
    }

}
