package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Define Administrator class.
 */
@Entity
@Table(name = "admin", schema = "MOODJA")
public class Administrator implements Serializable {
    private Integer id;
    private String username;
    private String password;

    /**
     * The constructor of Administrator class
     */
    public void setId(int id) {
        this.id = id;
    }

    // The get/set function of variable ID
    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // The get/set function of variable password
    @Basic
    @Column(name = "password", nullable = false, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // The get/set function of variable username
    @Basic
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Override equals function
     * Judge the equation of 2 Administrators classes through ID, username and password.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Administrator administrator = (Administrator) o;

        return  Objects.equals(id, administrator.id) &&
                Objects.equals(username, administrator.username) &&
                Objects.equals(password, administrator.password);
        //return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);

        return result;
        //return Objects.hash(id, username, password);
    }
}
