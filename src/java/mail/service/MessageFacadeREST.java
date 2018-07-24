package mail.service;

import java.util.Calendar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import mail.Message;


@Stateless
@Path("message")
public class MessageFacadeREST extends AbstractFacade<Message> {
    @PersistenceContext(unitName = "RESTMailPU")
    private EntityManager em;

    public MessageFacadeREST() {
        super(Message.class);
    }

    @POST
    @Consumes({"application/json"})
    public String createMessage(Message entity) {
        entity.setSent(Calendar.getInstance().getTime());
        super.create(entity);
        em.flush();
        return entity.getIdmessage().toString();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Message find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
