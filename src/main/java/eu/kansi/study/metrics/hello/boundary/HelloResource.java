package eu.kansi.study.metrics.hello.boundary;

import eu.kansi.study.metrics.hello.boundary.interceptor.RandomDelay;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("greetings")
@Traced
public class HelloResource {

    @GET
    @RandomDelay
    @Timed(name = "hello")
    @Timeout(2000)
    public Response hello() {
        return Response.ok("hello").build();
    }

    @POST
    @Path("{message}")
    @RandomDelay
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(@PathParam("message") String message) {
        return Response.created(
                UriBuilder.fromResource(HelloResource.class)
                        .path("say/{message}")
                        .build(message))
                .entity(message)
                .build();
    }

    @GET
    @Path("say/{message}")
    @RandomDelay
    @Produces({MediaType.TEXT_PLAIN})
    public Response say(@PathParam("message") String message) {
        return Response.ok(message).build();
    }

}
