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
@Table(name = "analyses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analyses.findAll", query = "SELECT a FROM Analyses a")
    , @NamedQuery(name = "Analyses.findById", query = "SELECT a FROM Analyses a WHERE a.id = :id")
    , @NamedQuery(name = "Analyses.findByRefPatient", query = "SELECT a FROM Analyses a WHERE a.refPatient = :refPatient")
    , @NamedQuery(name = "Analyses.findByItem", query = "SELECT a FROM Analyses a WHERE a.item = :item")
    , @NamedQuery(name = "Analyses.findByValeur", query = "SELECT a FROM Analyses a WHERE a.valeur = :valeur")})
public class Analyses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RefPatient")
    private int refPatient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "item")
    private String item;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "valeur")
    private String valeur;

    public Analyses() {
    }

    public Analyses(Integer id) {
        this.id = id;
    }

    public Analyses(Integer id, int refPatient, String item, String valeur) {
        this.id = id;
        this.refPatient = refPatient;
        this.item = item;
        this.valeur = valeur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRefPatient() {
        return refPatient;
    }

    public void setRefPatient(int refPatient) {
        this.refPatient = refPatient;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
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
        if (!(object instanceof Analyses)) {
            return false;
        }
        Analyses other = (Analyses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnalyseRemote.Analyses[ id=" + id + " ]";
    }
    
}
