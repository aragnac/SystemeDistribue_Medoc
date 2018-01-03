/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientRemote;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas
 */
@Entity
@Table(name = "demande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demande.findAll", query = "SELECT d FROM Demande d")
    , @NamedQuery(name = "Demande.findById", query = "SELECT d FROM Demande d WHERE d.id = :id")
    , @NamedQuery(name = "Demande.findByRefPatient", query = "SELECT d FROM Demande d WHERE d.refPatient = :refPatient")
    , @NamedQuery(name = "Demande.findByRefMedecin", query = "SELECT d FROM Demande d WHERE d.refMedecin = :refMedecin")
    , @NamedQuery(name = "Demande.findByDateHeureDemande", query = "SELECT d FROM Demande d WHERE d.dateHeureDemande = :dateHeureDemande")
    , @NamedQuery(name = "Demande.findByTraitee", query = "SELECT d FROM Demande d WHERE d.traitee = :traitee")
    , @NamedQuery(name = "Demande.findByUrgent", query = "SELECT d FROM Demande d WHERE d.urgent = :urgent")})
public class Demande implements Serializable {

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
    @Column(name = "RefMedecin")
    private int refMedecin;
    @Column(name = "DateHeureDemande")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHeureDemande;
    @Column(name = "Traitee")
    private Boolean traitee;
    @Column(name = "Urgent")
    private Boolean urgent;

    public Demande() {
    }

    public Demande(Integer id) {
        this.id = id;
    }

    public Demande(Integer id, int refPatient, int refMedecin) {
        this.id = id;
        this.refPatient = refPatient;
        this.refMedecin = refMedecin;
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

    public int getRefMedecin() {
        return refMedecin;
    }

    public void setRefMedecin(int refMedecin) {
        this.refMedecin = refMedecin;
    }

    public Date getDateHeureDemande() {
        return dateHeureDemande;
    }

    public void setDateHeureDemande(Date dateHeureDemande) {
        this.dateHeureDemande = dateHeureDemande;
    }

    public Boolean getTraitee() {
        return traitee;
    }

    public void setTraitee(Boolean traitee) {
        this.traitee = traitee;
    }

    public Boolean getUrgent() {
        return urgent;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
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
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "num :" + this.id + ", " + this.dateHeureDemande.toString() + ", Urgent : " + this.urgent ;
    }
    
}
