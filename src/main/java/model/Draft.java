package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * Define draft class.
 */
@Entity
@Table(name = "draft", schema = "MOODJA")
public class Draft implements Serializable {

    private Integer id;
    private Integer employeeId;
    private String content;
    private Date lastEdit;
    private Employee employee;
    private String title;
    private Double number;
    private Double summary;
    private Collection<Attachment> attachments;

    /**
     * The constructor of Draft class.
     */
    public Draft() {

    }

    /**
     * The constructor of Draft class.
     * @param title
     * @param content
     * @param employee
     * @param number
     * @param summary
     */
    public Draft(String title, String content, Employee employee, Double number, Double summary) {
        this.title = title;
        this.content = content;
        this.employee = employee;
        this.lastEdit = new Date();
        this.number = number;
        this.summary = summary;
    }

    /**
     * The get/set function of variable ID.
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * The get/set function of variable employee.
     * @return
     */
    @OneToOne(mappedBy = "draft")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * The get/set function of variable last edit.
     * @return
     */
    @Basic
    @Column(name = "last_edit", nullable = true)
    public Date getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(Date lastEdit) {
        this.lastEdit = lastEdit;
    }

    /**
     * The get/set function of variable content.
     * @return
     */
    @Basic
    @Column(name = "content", nullable = true, length = 500)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * The get/set function of variable title.
     * @return
     */
    @Basic
    @Column(name = "title", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The get/set function of variable number.
     * @return
     */
    @Basic
    @Column(name = "number", nullable = true, precision = 0)
    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    /**
     * The get/set function of variable summary.
     * @return
     */
    @Basic
    @Column(name = "summary", nullable = true, precision = 0)
    public Double getSummary() {
        return summary;
    }

    public void setSummary(Double summary) {
        this.summary = summary;
    }

    /**
     * The get/set function of variable attachments.
     * @return
     */
    @OneToMany(mappedBy = "draft")
    public Collection<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<Attachment> attachments) {
        this.attachments = attachments;
    }

    /**
     * Override equals function.
     * Judge the equation of 2 Draft classes through all the variables above.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Draft draft = (Draft) o;

        if (!Objects.equals(id, draft.id)) return false;
        if (!Objects.equals(content, draft.content)) return false;
        if (!Objects.equals(employeeId, draft.employeeId)) return false;
        if (!Objects.equals(lastEdit, draft.lastEdit)) return false;

        return true;
        //return Objects.equals(id, draft.id) && Objects.equals(employeeId, draft.employeeId) && Objects.equals(content, draft.content) && Objects.equals(lastEdit, draft.lastEdit) && Objects.equals(employee, draft.employee) && Objects.equals(title, draft.title) && Objects.equals(number, draft.number) && Objects.equals(summary, draft.summary) && Objects.equals(attachments, draft.attachments);
    }

    /**
     * Override hashCode function.
     * @return
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (lastEdit != null ? lastEdit.hashCode() : 0);
        return result;
        //return Objects.hash(id, employeeId, content, lastEdit, employee, title, number, summary, attachments);
    }

    /**
     * Override toString function.
     * Return the string in the following format.
     * @return
     */
    @Override
    public String toString() {
        return "ID: " + id + "; Title: " + title + "; Date: " + lastEdit.toString() + "; Employee: " + employee.toString();
    }
}
