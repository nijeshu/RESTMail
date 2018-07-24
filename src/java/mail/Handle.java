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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "handle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Handle.findAll", query = "SELECT h FROM Handle h"),
    @NamedQuery(name = "Handle.findById", query = "SELECT h FROM Handle h WHERE h.id = :id"),
    @NamedQuery(name = "Handle.findByMessage", query = "SELECT h FROM Handle h WHERE h.message = :message"),
    @NamedQuery(name = "Handle.findByReceiver", query = "SELECT h FROM Handle h WHERE h.receiver = :receiver"),
    @NamedQuery(name = "Handle.findBySender", query = "SELECT h FROM Handle h WHERE h.sender = :sender"),
    @NamedQuery(name = "Handle.findBySubject", query = "SELECT h FROM Handle h WHERE h.subject = :subject")})
public class Handle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Column(name = "message")
    private Integer message;
    @Column(name = "receiver")
    private Integer receiver;
    @Size(max = 45)
    @Column(name = "sender")
    private String sender;
    @Size(max = 256)
    @Column(name = "subject")
    private String subject;

    public Handle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
}
