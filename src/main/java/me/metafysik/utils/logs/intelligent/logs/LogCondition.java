package me.metafysik.utils.logs.intelligent.logs;

/**
 * @author metafysik
 */
public enum LogCondition {

    /**
     * Will be ignored.
     */
    Ignore,

    /**
     * A record will always be logged.
     */
    Any,

    /**
     * When the method executes successfully without throwing any exception, a record will be logged.
     */
    OnlyInSuccess,

    /**
     * When an exception is thrown during the execution of this method, a record will be logged.
     */
    OnlyInFailure
}