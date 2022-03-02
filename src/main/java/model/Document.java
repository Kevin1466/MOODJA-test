package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "document", schema = "MOODJA")
public class Document implements Serializable {

    public static final String SUBMITTED = "Submitted";
    public static final String ACCEPTED = "Accepted";
    public static final String REFUSED = "Refused";
    public static final String REPLIED = "Replied";
    public static final String PROCESSING = "Processing";

    private Integer id;
    private String status;
    private Date submitDate;
    private Date viewDate;
    private String content;
    private Employee employee;
    private Manager manager;
    private String title;
    private String comment;
    private Collection<Attachment> attachmentsById;
    private Double number;
    private Double summary;

    private Integer draftId;
    /**
     * Constructor of Document class.
     */
    public Document() {

    }

    /**
     * Constructor of Document class.
     * @param title
     * @param content
     * @param submitDate
     * @param status
     * @param employee
     * @param summary
     * @param number
     */
    public Document(String title, String content, Date submitDate, String status, Employee employee, Double summary, Double number) {
        this.title = title;
        this.content = content;
        this.submitDate = submitDate;
        this.status = status;
        this.employee = employee;
        this.summary = summary;
        this.number = number;
    }

    /**
     * The get/set function of variable ID.
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The get/set function of variable status.
     * @return
     */
    @Basic
    @Column(name = "status", nullable = true)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
     * The get/set function of variable submit date.
     * @return
     */
    @Basic
    @Column(name = "submit_date", nullable = true)
    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    /**
     * The get/set function of variable view date.
     * @return
     */
    @Basic
    @Column(name = "view_date", nullable = true)
    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    /**
     * The get/set function of variable comment.
     * @return
     */
    @Basic
    @Column(name = "comment", nullable = true, length = 500)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * The function to add comment.
     * @param comment
     */
    public void addComment(String comment) {
        if (this.comment == null) this.comment = "";
        this.comment += comment;
    }

    /**
     * The get/set function of variable employee.
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * The get/set function of variable manager.
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
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
     * Override equals function.
     * Judge the equation of 2 Document classes through the variables above.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (!Objects.equals(id, document.id)) return false;
        if (!Objects.equals(status, document.status)) return false;
        if (!Objects.equals(submitDate, document.submitDate)) return false;
        if (!Objects.equals(viewDate, document.viewDate)) return false;
        if (!Objects.equals(content, document.content)) return false;

        return true;
        //return Objects.equals(id, document.id) && Objects.equals(status, document.status) && Objects.equals(submitDate, document.submitDate) && Objects.equals(viewDate, document.viewDate) && Objects.equals(content, document.content) && Objects.equals(employee, document.employee) && Objects.equals(manager, document.manager) && Objects.equals(title, document.title) && Objects.equals(comment, document.comment) && Objects.equals(attachmentsById, document.attachmentsById) && Objects.equals(number, document.number) && Objects.equals(summary, document.summary);
    }

    /**
     * Override hashCode function.
     * @return
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (submitDate != null ? submitDate.hashCode() : 0);
        result = 31 * result + (viewDate != null ? viewDate.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);

        return result;
        //return Objects.hash(id, status, submitDate, viewDate, content, employee, manager, title, comment, attachmentsById, number, summary);
    }

    /**
     * Override toString function.
     * Return the string in the following format.
     * @return
     */
    @Override
    public String toString() {
        return "ID: " + this.getId() + ". Title: " + this.getTitle() + ". Employee: " + employee.getFirstName() + ", " + employee.getLastName() + ", " + employee.getId();
    }

    /**
     * Separate submitDateString by space and pick the first string.
     * @return
     */
    public String submitDateString() {
        return submitDate.toString().split(" ")[0];
    }

    /**
     * Separate viewDateString by space and pick the first string.
     * @return
     */
    public String viewDateString() {
        return viewDate.toString().split(" ")[0];
    }

    /**
     * The get/set function of variable attachmentById.
     * @return
     */
    @OneToMany(mappedBy = "document")
    public Collection<Attachment> getAttachmentsById() {
        return attachmentsById;
    }

    public void setAttachmentsById(Collection<Attachment> attachmentsById) {
        this.attachmentsById = attachmentsById;
    }

    /*
    public Integer getDraftId() {
        return draftId;
    }

    public void setDraftId(Integer draftId) {
        this.draftId = draftId;
    }

     */
}
