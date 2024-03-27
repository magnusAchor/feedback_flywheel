/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author macintosh
 */
@Entity
@Table(name = "websites")
@NamedQueries({
    @NamedQuery(name = "Websites.findAll", query = "SELECT w FROM Websites w"),
    @NamedQuery(name = "Websites.findById", query = "SELECT w FROM Websites w WHERE w.id = :id"),
    @NamedQuery(name = "Websites.findByUrl", query = "SELECT w FROM Websites w WHERE w.url = :url"),
    @NamedQuery(name = "Websites.findByName", query = "SELECT w FROM Websites w WHERE w.name = :name")})
public class Websites implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "websiteId")
    private Collection<Suggestions> suggestionsCollection;

    public Websites() {
    }

    public Websites(Integer id) {
        this.id = id;
    }

    public Websites(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Suggestions> getSuggestionsCollection() {
        return suggestionsCollection;
    }

    public void setSuggestionsCollection(Collection<Suggestions> suggestionsCollection) {
        this.suggestionsCollection = suggestionsCollection;
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
        if (!(object instanceof Websites)) {
            return false;
        }
        Websites other = (Websites) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Entity.Websites[ id=" + id + " ]";
    }
   
}
