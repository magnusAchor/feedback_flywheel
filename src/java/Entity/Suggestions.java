/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author macintosh
 */
@Entity
@Table(name = "suggestions")
@NamedQueries({
    @NamedQuery(name = "Suggestions.findAll", query = "SELECT s FROM Suggestions s"),
    @NamedQuery(name = "Suggestions.findById", query = "SELECT s FROM Suggestions s WHERE s.id = :id"),
    @NamedQuery(name = "Suggestions.findByTitle", query = "SELECT s FROM Suggestions s WHERE s.title = :title"),
    @NamedQuery(name = "Suggestions.findByAttachment", query = "SELECT s FROM Suggestions s WHERE s.attachment = :attachment"),
    @NamedQuery(name = "Suggestions.findByCreatedAt", query = "SELECT s FROM Suggestions s WHERE s.createdAt = :createdAt")})
public class Suggestions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] attachment;


    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @JoinColumn(name = "website_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Websites websiteId;

    public Suggestions() {
    }

    public Suggestions(Integer id) {
        this.id = id;
    }

    public Suggestions(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getAttachment() {
        return attachment;
    }

     public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Websites getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Websites websiteId) {
        this.websiteId = websiteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suggestions)) {
            return false;
        }
        Suggestions other = (Suggestions) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Entity.Suggestions[ id=" + id + " ]";
    }

    public void setWebsite(Websites website) {
    this.websiteId = website;
}

    public void setAttachmentType(String contentType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
