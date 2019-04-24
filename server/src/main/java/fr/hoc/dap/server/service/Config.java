/**
 * //TODO VA by Djer |JavaDoc| Devrait être sur la JavaDoc de la classe.
 * @author Virginie et Armand.
 */
package fr.hoc.dap.server.service;

/**
 * Pour modifier la configuration. */
public class Config {

    /** Fichier de permissions acceptées. */
    private static final String TOKEN_DIR = System.getProperty("user.home") + "\\dap\\tokens";
    /** Fichier de config api. */
    private static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + "\\dap\\credentials.json";
    /** Nom de l'application. */
    private static final String APPLICATION_NAME = "HoC DaP";

    /** token folder name attribute. */
    private String tokenFolder;
    /** credentials file path attribute. */
    private String credFilePath;
    /** application name attribute. */
    private String appName;

    /** default constructor for config. */
    public Config() {
        tokenFolder = TOKEN_DIR;
        credFilePath = CREDENTIALS_FILE_PATH;
        appName = APPLICATION_NAME;
    }

    /**
     *  //TODO VA by Djer |JavaDoc| Il manque la description (du constructeur) : première ligne de la JavaDoc
     * @param tf  token folder
     * @param cfp credentials file path
     * @param an  application name
     * constructor for config
     */
    Config(final String tf, final String cfp, final String an) {
        tokenFolder = tf;
        credFilePath = cfp;
        appName = an;
    }

    /**
     * @return token folder name
     */
    public String getTokenFolder() {
        return tokenFolder;
    }

    /**
     * @return the oauth2 callback
     */
    public static String getoAuth2CallbackUrl() {
        return "/oAuth2Callback";
    }

    /**
     * @param newName token folder new name
     */
    public void setTokenFolderName(final String newName) {
        this.tokenFolder = newName;
    }

    /**
     * @return application name
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param newName new application name
     */
    public void setAppName(final String newName) {
        this.appName = newName;
    }

    /**
     * @return credential file path
     */
    public String getCreditFilePath() {
        return credFilePath;
    }

    /**
     * @param newName new credential file path
     */
    public void setCreditFilePath(final String newName) {
        this.credFilePath = newName;
    }
}
