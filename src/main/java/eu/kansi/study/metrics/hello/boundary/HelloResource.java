package eu.kansi.study.metrics.hello.boundary;

import eu.kansi.study.metrics.hello.boundary.interceptor.RandomDelay;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Properties;

@Path("greetings")
public class HelloResource {

    @GET
    @RandomDelay
    @Timed
    @Timeout(2000)
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Timeout"),
                    @APIResponse(
                            responseCode = "200",
                            description = "Says hello",
                            content = @Content(mediaType = "text/plain")) })
    @Operation(
            summary = "Says hello",
            description = "Says hello with random response time")
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
