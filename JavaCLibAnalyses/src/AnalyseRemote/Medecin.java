/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyseRemote;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas
 */
@Entity
@Table(name = "medecin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medecin.findAll", query = "SELECT m FROM Medecin m")
    , @NamedQuery(name = "Medecin.findById", query = "SELECT m FROM Medecin m WHERE m.id = :id")
    , @NamedQuery(name = "Medecin.findByNom", query = "SELECT m FROM Medecin m WHERE m.nom = :nom")
    , @NamedQuery(name = "Medecin.findByPrenom", query = "SELECT m FROM Medecin m WHERE m.prenom = :prenom")
    , @NamedQuery(name = "Medecin.findByLogin", query = "SELECT m FROM Medecin m WHERE m.login = :login")})
public class Medecin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "login")
    private String login;

    public Medecin() {
    }

    public Medecin(Integer id) {
        this.id = id;
    }

    public Medecin(Integer id, String nom, String prenom, String login) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        if (!(object instanceof Medecin)) {
            return false;
        }
        Medecin other = (Medecin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnalyseRemote.Medecin[ id=" + id + " ]";
    }
    
}
