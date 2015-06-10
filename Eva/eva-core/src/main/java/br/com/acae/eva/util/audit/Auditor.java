/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.util.audit;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 *
 * @author vitor
 */
@Audited
@Interceptor
public class Auditor implements Serializable {
    private static final Logger logger = Logger.getLogger("Auditor");
    @Inject private Event<Exception> event;
    
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        final Method method = ctx.getMethod();
        final Class<?> target = method.getDeclaringClass();
        final String operation = target.getSimpleName() + "." + method.getName();
        logger.log(Level.FINE, "{0} start", operation);
        
        final DateTime before = new DateTime();
        try {
            return ctx.proceed();
        } catch (Exception e) {
            event.fire(e);
            throw e;
        } finally {
            final DateTime after = new DateTime();
            final Period diff = new Period(before, after, PeriodType.millis());
            final String millis = String.valueOf(diff.getMillis());
            final String[] args = new String[]{operation, millis};
            logger.log(Level.FINE, "{0} executed in {1}ms", args);
        }
    }
}