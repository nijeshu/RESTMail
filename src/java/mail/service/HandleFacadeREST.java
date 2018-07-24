package mail.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import mail.Handle;


@Stateless
@Path("handle")
public class HandleFacadeREST extends AbstractFacade<Handle> {
    @PersistenceContext(unitName = "RESTMailPU")
    private EntityManager em;

    public HandleFacadeREST() {
        super(Handle.class);
    }

    @GET
    @Produces({"application/json"})
    public List<Handle> findAll(@QueryParam("receiver") int receiver) {
        return em.createNamedQuery("Handle.findByReceiver", Handle.class).setParameter("receiver", new Integer(receiver)).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
