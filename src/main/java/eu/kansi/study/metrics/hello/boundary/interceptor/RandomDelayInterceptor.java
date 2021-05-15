package eu.kansi.study.metrics.hello.boundary.interceptor;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.concurrent.ThreadLocalRandom;

@Interceptor
@RandomDelay
public class RandomDelayInterceptor {

    @Inject
    public RandomDelayInterceptor(Logger logger) {
        this.logger = logger;
    }

    private final Logger logger;

    @AroundInvoke
    Object aroundInvoke(InvocationContext invocationContext) throws Exception {
        var delayValue = getDelayValue();
        logger.info("delaying response by {} ms", delayValue);
        Thread.sleep(getDelayValue());

        return invocationContext.proceed();
    }

    long getDelayValue() {
        return ThreadLocalRandom.current().nextLong(3000);
    }

}
