package de.bschueller.quarkus.client;

import de.bschueller.quarkus.contact.Contact;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * https://quarkus.io/guides/rest-client
 */
@Path("/api/v3/contacts")
@RegisterRestClient
public interface ContactClient {


    @GET
    @Path("/{id}")
    @Produces("application/json")
    Uni< Contact > getContact( @PathParam Long id);


}
