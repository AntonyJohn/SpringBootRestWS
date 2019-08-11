/**
 * 
 */
package com.antony.SpringBootRestWS.login.valueobject;

import java.io.Serializable;

/**
 * @author Antony John
 *
 */
public class UsersVO implements Serializable {	
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
    private String password;    
    private short enabled;
    private int id;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getEnabled() {
        return enabled;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
