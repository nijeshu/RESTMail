/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

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
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "recipient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipient.findAll", query = "SELECT r FROM Recipient r"),
    @NamedQuery(name = "Recipient.findByIdrecipient", query = "SELECT r FROM Recipient r WHERE r.idrecipient = :idrecipient"),
    @NamedQuery(name = "Recipient.findByMessage", query = "SELECT r FROM Recipient r WHERE r.message = :message"),
    @NamedQuery(name = "Recipient.findByRecipient", query = "SELECT r FROM Recipient r WHERE r.recipient = :recipient")})
public class Recipient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = true)
    @Column(name = "idrecipient")
    private Integer idrecipient;
    @Column(name = "message")
    private Integer message;
    @Column(name = "recipient")
    private Integer recipient;

    public Recipient() {
    }

    public Recipient(Integer idrecipient) {
        this.idrecipient = idrecipient;
    }

    public Integer getIdrecipient() {
        return idrecipient;
    }

    public void setIdrecipient(Integer idrecipient) {
        this.idrecipient = idrecipient;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getRecipient() {
        return recipient;
    }

    public void setRecipient(Integer recipient) {
        this.recipient = recipient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrecipient != null ? idrecipient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipient)) {
            return false;
        }
        Recipient other = (Recipient) object;
        if ((this.idrecipient == null && other.idrecipient != null) || (this.idrecipient != null && !this.idrecipient.equals(other.idrecipient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mail.Recipient[ idrecipient=" + idrecipient + " ]";
    }
    
}
