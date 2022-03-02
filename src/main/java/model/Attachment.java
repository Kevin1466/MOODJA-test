package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Define Attachment class.
 */
@Entity
@Table(name = "attachment", schema = "MOODJA")
public class Attachment implements Serializable {

    private Integer id;
    private Date uploadDate;
    private String path;
    private String name;

    //@ManyToOne
    //@JoinColumn(name = "document_id")
    private Document document;

    //@ManyToOne
    //@JoinColumn(name = "draft_id")
    private Draft draft;

    public Attachment(String uploadPath, Document document, Draft draft, Date date, String name) {
    }

    @ManyToOne
    @JoinColumn(name = "draft_id")
    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }

    @ManyToOne
    @JoinColumn(name = "document_id")
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     * The constructor of attachment.
     */
    public Attachment() {

    }

    public Attachment(String path, Date uploadDate, String name) {
        this.path = path;
        this.uploadDate = uploadDate;
        this.name = name;
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
     * The get/set function of variable upload date.
     * @return
     */
    @Basic
    @Column(name = "upload_date", nullable = true)
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * The get/set function of variable path.
     * @return
     */
    @Basic
    @Column(name = "path", nullable = true)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * The get/set function of variable name.
     * @return
     */
    @Basic
    @Column(name = "name", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Override equals function.
     * Judge the equation of 2 attachment classes through the variable above.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attachment that = (Attachment) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(uploadDate, that.uploadDate)) return false;
        if (!Objects.equals(path, that.path)) return false;

        return true;
        //return Objects.equals(id, that.id) && Objects.equals(uploadDate, that.uploadDate) && Objects.equals(path, that.path) && Objects.equals(name, that.name);
    }

    /**
     * Override hashCode function.
     * @return
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);

        return result;
        //return Objects.hash(id, uploadDate, path, name);
    }
}
