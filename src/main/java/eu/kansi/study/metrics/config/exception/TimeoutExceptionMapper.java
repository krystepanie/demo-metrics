package eu.kansi.study.metrics.config.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.concurrent.TimeoutException;

@Provider
public class TimeoutExceptionMapper implements ExceptionMapper<TimeoutException> {

    @Override
    public Response toResponse(TimeoutException exception) {
        return Response.status(Response.Status.GATEWAY_TIMEOUT).build();
    }

}
