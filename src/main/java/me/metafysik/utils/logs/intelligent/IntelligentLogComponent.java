package me.metafysik.utils.logs.intelligent;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import me.metafysik.utils.logs.intelligent.entities.LogEntry;
import me.metafysik.utils.logs.intelligent.logs.LogCondition;
import me.metafysik.utils.logs.intelligent.logs.LogFeature;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author metafysik
 */
@Aspect
public class IntelligentLogComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger("IntelligentLog");

    private static final String NOT_RECORD_PARAMS = "IGNORED";
    private static final Object[] NOT_RECORD_PARAMS_ARRAY = new Object[]{NOT_RECORD_PARAMS};

    @Around("@annotation(IntelligentLog)")
    public Object proceed(ProceedingJoinPoint call) throws Throwable {
        final MethodSignature signature = (MethodSignature) call.getSignature();
        final Method method = signature.getMethod();
        final IntelligentLog annotation = method.getAnnotation(IntelligentLog.class);
        final int feature = annotation.feature();

        final boolean writeFailure = annotation.condition().equals(LogCondition.Any) || annotation.condition().equals(LogCondition.OnlyInFailure);
        final boolean writeSuccess = annotation.condition().equals(LogCondition.Any) || annotation.condition().equals(LogCondition.OnlyInSuccess);

        if (!writeFailure && !writeSuccess) {
            return call.proceed();
        }

        final boolean writeTimeElapsed = (feature & LogFeature.TIME_ELAPSED) == LogFeature.TIME_ELAPSED;
        final boolean writeInputParams = (feature & LogFeature.INPUT_PARAMETERS) == LogFeature.INPUT_PARAMETERS;
        final boolean writeOutputParam = (feature & LogFeature.OUTPUT_PARAMETER) == LogFeature.OUTPUT_PARAMETER;
        final boolean writeStackTrace = (feature & LogFeature.THROWABLE_STACK_TRACE) == LogFeature.THROWABLE_STACK_TRACE;

        final Stopwatch stopwatch = writeTimeElapsed ? Stopwatch.createStarted() : null;

        boolean isThrown = false;
        Throwable throwable = null;
        Object value = null;
        long now = System.currentTimeMillis();
        try {
            return value = call.proceed();
        } catch (Throwable t) {
            isThrown = true;
            throwable = t;
            throw t;
        } finally {
            if (stopwatch != null) {
                stopwatch.stop();
            }

            LogEntry logEntry = new LogEntry();
            logEntry.setModule(annotation.module());
            logEntry.setCategory(Strings.isNullOrEmpty(annotation.category()) ? method.getDeclaringClass().getSimpleName() : annotation.category());
            logEntry.setSubCategory(Strings.isNullOrEmpty(annotation.subCategory()) ? method.getName() : annotation.subCategory());
            logEntry.setSuccess(!isThrown);
            logEntry.setMethodName(method.getName());
            logEntry.setInputParameters(writeInputParams ? call.getArgs() : NOT_RECORD_PARAMS_ARRAY);
            logEntry.setOutputParameter(writeOutputParam ? value : NOT_RECORD_PARAMS);
            logEntry.setNow(now);
            logEntry.setTimeElapsed(stopwatch != null ? stopwatch.elapsed(TimeUnit.MILLISECONDS) : null);
            logEntry.setClassName(call.getTarget().getClass().getSimpleName());

            if (!isThrown) {
                if (writeSuccess) {
                    LOGGER.info("[{}][{}][{}]{}", logEntry.getModule(), logEntry.getCategory(), logEntry.getSubCategory(), JSON.toJSONString(logEntry));
                }
            } else {
                if (writeFailure) {
                    logEntry.setThrowableMessage(throwable.getMessage());

                    boolean isNotIllegalArgument = !(throwable instanceof IllegalArgumentException);
                    if (writeStackTrace && isNotIllegalArgument) {
                        logEntry.setThrowableStacktrace(ExceptionUtils.getStackTrace(throwable));
                    }

                    if (isNotIllegalArgument) {
                        LOGGER.warn("[{}][{}][{}]{}", logEntry.getModule(), logEntry.getCategory(), logEntry.getSubCategory(), JSON.toJSONString(logEntry));
                    } else {
                        LOGGER.error("[{}][{}][{}]{}", logEntry.getModule(), logEntry.getCategory(), logEntry.getSubCategory(), JSON.toJSONString(logEntry), throwable);
                    }
                }
            }
        }
    }
}