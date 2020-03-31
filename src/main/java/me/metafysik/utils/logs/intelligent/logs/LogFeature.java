package me.metafysik.utils.logs.intelligent.logs;

/**
 * @author metafysik
 */
public class LogFeature {

    /**
     * Write time elapsed attribute.
     */
    public static final int TIME_ELAPSED = 1;

    /**
     * Write input parameters attribute.
     * <p>
     * The default serialization type is json.
     */
    public static final int INPUT_PARAMETERS = 1 << 1;

    /**
     * Write output parameter attribute.
     * <p>
     * The default serialization type is json.
     */
    public static final int OUTPUT_PARAMETER = 1 << 2;

    /**
     * Write throwable stacktrace attribute.
     */
    public static final int THROWABLE_STACK_TRACE = 1 << 3;

    /**
     * Write all attribute.
     */
    public static final int GLOBAL_FULL_LOG = TIME_ELAPSED | INPUT_PARAMETERS | OUTPUT_PARAMETER | THROWABLE_STACK_TRACE;

    /**
     * Write all attribute without input parameters.
     */
    public static final int GLOBAL_FULL_LOG_WITHOUT_INPUT_PARAMETER = GLOBAL_FULL_LOG - INPUT_PARAMETERS;

    /**
     * Write all attribute without output parameter.
     */
    public static final int GLOBAL_FULL_LOG_WITHOUT_OUTPUT_PARAMETER = GLOBAL_FULL_LOG - OUTPUT_PARAMETER;
}