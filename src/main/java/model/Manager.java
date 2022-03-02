package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * Define Manager class.
 */
@Entity
@Table(name = "manager", schema = "MOODJA")
public class Manager implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;
    private boolean delete;
    private Administrator adminByAdminId;
    private Collection<Document> documents;

    public Manager() {

    }

    public Manager(String firstName, String lastName, String email, String mobile, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.delete = false;
    }

    // The get/set function of variable ID
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // The get/set function of variable first name
    @Basic
    @Column(name = "firstname", nullable = false, length = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //The get/set function of variable last name
    @Basic
    @Column(name = "lastname", nullable = false, length = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // The get/set function of variable email
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // The get/set function of variable mobile
    @Basic
    @Column(name = "mobile", nullable = false, length = 15)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // The get/set function of variable password
    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // The decision function of delete
    @Basic
    @Column(name = "deleted", nullable = false)
    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    /**
     * Override equals function
     * Check the equation of 2 Manager classes through all variables above.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manager manager = (Manager) o;

        if (!Objects.equals(id, manager.id)) return false;
        if (delete != manager.delete) return false;
        if (!Objects.equals(firstName, manager.firstName)) return false;
        if (!Objects.equals(lastName, manager.lastName)) return false;
        if (!Objects.equals(password, manager.password)) return false;
        if (!Objects.equals(email, manager.email)) return false;
        if (!Objects.equals(mobile, manager.mobile)) return false;

        return true;
        //return id == manager.id && delete == manager.delete && Objects.equals(firstName, manager.firstName) && Objects.equals(lastName, manager.lastName) && Objects.equals(email, manager.email) && Objects.equals(mobile, manager.mobile) && Objects.equals(password, manager.password) && Objects.equals(adminByAdminId, manager.adminByAdminId);
    }

    /**
     * Override hashCode function
     * @return
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (delete ? 1:0);

        return result;
        //return Objects.hash(id, firstName, lastName, email, mobile, password, delete, adminByAdminId);
    }

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id", nullable = false)
    public Administrator getAdminByAdminId() {
        return adminByAdminId;
    }

    public void setAdminByAdminId(Administrator adminByAdminId) {
        this.adminByAdminId = adminByAdminId;
    }


    /**
     * The get/set function of Collection documents.
     * @return
     */
    @OneToMany(mappedBy = "manager")
    public Collection<Document> getDoc() {
        return documents;
    }

    public void setDoc(Collection<Document> documents) {
        this.documents = documents;
    }

    /**
     * Override toString function
     * Return the String in the following format.
     * @return
     */
    @Override
    public String toString() {
        return "Manager: " +
                "No. " + id +
                ", First Name: '" + firstName + '\'' +
                ", Last Name: " + lastName + '\'' +
                ", Email: " + email + '\'' +
                ", Phone Number: " + mobile + '.';
    }
}
