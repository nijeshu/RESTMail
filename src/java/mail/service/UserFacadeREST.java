/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import mail.User;


@Stateless
@Path("user")
public class UserFacadeREST extends AbstractFacade<User> {
    @PersistenceContext(unitName = "RESTMailPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Consumes({"application/json"})
    public String createUser(User entity) {
    
        User other = null;
        try {
            other = em.createNamedQuery("User.findByName", User.class).setParameter("name", entity.getName()).getSingleResult();
        } catch (Exception ex) {
           
        }
        if(other != null)
            return "0";
        
        super.create(entity);
        em.flush();
        return entity.getIduser().toString();
    }

    @GET
    @Produces({"text/plain"})
    public String checkUser(@QueryParam("user") String user, @QueryParam("password") String password) {
        User u = null;
        try {
            u = em.createNamedQuery("User.findByName", User.class).setParameter("name", user).getSingleResult();
        } catch (Exception ex) {
            return "0";
        }
        if (u.getPassword().equals(password)) {
            return u.getIduser().toString();
        } else {
            return "0";
        }
    }

    @GET
    @Path("id")
    @Produces({"text/plain"})
    public String findId(@QueryParam("user") String user) {
        User u = null;
        try {
            u = em.createNamedQuery("User.findByName", User.class).setParameter("name", user).getSingleResult();
        } catch (Exception ex) {
            return "0";
        }
        return u.getIduser().toString();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
