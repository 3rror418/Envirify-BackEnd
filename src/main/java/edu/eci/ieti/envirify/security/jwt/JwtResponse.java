package edu.eci.ieti.envirify.security.jwt;

/**
 * Class that represents a jwt response after logging into the app
 */
public class JwtResponse {

    private String jwt;
    private String id;
    private String username;
    private String email;

    /**
     * Constructor of the JwtResponse class
     * @param jwt authentication token
     * @param id  id of the user that made the request
     * @param username username of the user that made the request
     * @param email email of the user that made the request
     */
    public JwtResponse(String jwt, String id, String username, String email) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.email = email;
    }

    /**
     * Returns the authentication token
     * @return the authentication token
     */
    public String getJwt() {
        return jwt;
    }

    /**
     * sets the authentication token
     * @param jwt the authentication token
     */
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Returns the id of the user that made the request
     * @return the id of the user that made the request
     */
    public String getId() {
        return id;
    }


    /**
     * Sets the id of the user that made the request
     * @param id the id of the user that made the request
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the username of the user that made the request
     * @return the username of the user that made the request
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user that made the request
     * @param username the username of the user that made the request
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the email of the user that made the request
     * @return the email of the user that made the request
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user that made the request
     * @param email the email of the user that made the request
     */
    public void setEmail(String email) {
        this.email = email;
    }
}

