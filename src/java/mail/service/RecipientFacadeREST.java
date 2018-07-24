package mail.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import mail.Recipient;


@Stateless
@Path("recipient")
public class RecipientFacadeREST extends AbstractFacade<Recipient> {
    @PersistenceContext(unitName = "RESTMailPU")
    private EntityManager em;

    public RecipientFacadeREST() {
        super(Recipient.class);
    }

    @POST
    @Consumes({"application/json"})
    public String createRecipient(Recipient entity) {
        super.create(entity);
        em.flush();
        return entity.getIdrecipient().toString();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        Recipient r = super.find(id);
        Integer mId = r.getMessage();
        List<Recipient> allRs = em.createNamedQuery("Recipient.findByMessage", Recipient.class).setParameter("message", mId).getResultList();
        if(allRs.size() == 1) {
            mail.Message message = em.find(mail.Message.class, mId);
            em.remove(message);
        }
        super.remove(super.find(id));
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
