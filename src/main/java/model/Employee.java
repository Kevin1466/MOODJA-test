package model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * Define Employee class
 */
@Entity
@Proxy(lazy = false)
@Table(name = "employee", schema = "MOODJA")
public class Employee implements Serializable {
    private Integer id;
    private String password;
    private String email;
    private String mobile;
    private String department;
    private String firstName;
    private String lastName;
    private String position;
    private Date dob;
    private String gender;
    private String nationality;
    private String startYear;
    private String bankInfo;
    private Set<Document> documents;
    private Draft draft;

    // The get/set function of variable draft.
    @OneToOne
    @JoinColumn(name = "draft_id", referencedColumnName = "id")
    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
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
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "mobile", nullable = true, length = 15)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // The get/set function of variable department
    @Basic
    @Column(name = "department", nullable = true, length = 10)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // The get/set function of variable first name
    @Basic
    @Column(name = "firstname", nullable = true, length = 10)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // The get/set function of variable last name
    @Basic
    @Column(name = "lastname", nullable = true, length = 10)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    // The get/set function of variable position
    @Basic
    @Column(name = "position", nullable = true, length = 10)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // The get/set function of variable gender
    @Basic
    @Column(name = "gender", nullable = true, length = 5)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // The get/set function of variable nationality
    @Basic
    @Column(name = "nationality", nullable = true, length = 20)
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    // The get/set function of variable date of birth
    @Basic
    @Column(name = "dob", nullable = true)
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    // The get/set function of variable of bank info
    @Basic
    @Column(name = "bankInfo", nullable = true, length = 100)
    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    // The get/set function of variable start year
    @Basic
    @Column(name = "startYear", nullable = true, length = 4)
    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!Objects.equals(id, employee.id)) return false;
        if (!Objects.equals(password, employee.password)) return false;
        if (!Objects.equals(email, employee.email)) return false;
        if (!Objects.equals(mobile, employee.email)) return false;
        if (!Objects.equals(department, employee.department)) return false;
        if (!Objects.equals(firstName, employee.firstName)) return false;
        if (!Objects.equals(lastName, employee.lastName)) return false;
        if (!Objects.equals(position, employee.position)) return false;
        if (!Objects.equals(dob, employee.dob)) return false;
        if (!Objects.equals(gender, employee.gender)) return false;
        if (!Objects.equals(nationality, employee.nationality)) return false;
        if (!Objects.equals(startYear, employee.startYear)) return false;
        if (!Objects.equals(bankInfo, employee.bankInfo)) return false;

        return true;
        //return Objects.equals(id, employee.id) && Objects.equals(password, employee.password) && Objects.equals(email, employee.email) && Objects.equals(mobile, employee.mobile) && Objects.equals(department, employee.department) && Objects.equals(firstName, employee.firstName) && Objects.equals(LastName, employee.LastName) && Objects.equals(position, employee.position) && Objects.equals(dob, employee.dob) && Objects.equals(gender, employee.gender) && Objects.equals(nationality, employee.nationality) && Objects.equals(startYear, employee.startYear) && Objects.equals(bankInfo, employee.bankInfo);
    }

    /**
     * The get/set function of application set.
     * @return
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    /**
     * Override hashCode function
     * @return
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (startYear != null ? startYear.hashCode() : 0);
        result = 31 * result + (bankInfo != null ? bankInfo.hashCode() : 0);

        return result;

        //return Objects.hash(id, password, email, mobile, department, firstName, lastName, position, dob, gender, nationality, startYear, bankInfo);
    }

    /**
     * Override toString function
     * Return String in the following format.
     * @return
     */
    @Override
    public String toString() {
        return "Employee: " +
                "ID: " + id +
                ", Password: " + password + '\'' +
                ", Email: " + email +
                ", Mobile: " + mobile + '\'' +
                ", Department: " + department + '\'' +
                ", First Name: " + firstName + '\'' +
                ", Last Name: " + lastName + '\'' +
                ", Position: " + position + '\'' +
                ", Date of Birth: " + dob +
                ", Gender: " + gender + '\'' +
                ", Nationality: " + nationality + '\'' +
                ", Start Year: " + startYear + '\'' +
                ", Bank Info: " + bankInfo + '\'' +
                '.';
    }
}
