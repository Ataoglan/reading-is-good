package com.getir.readingisgood.application.utils.context;

public class ContextManager {
    private static final ThreadLocal<Context> contexts = new ThreadLocal<Context>();

    public static void set(Context ctx) {
        contexts.set(ctx);
    }

    public static Context get() {
        return contexts.get();
    }

    public static void clear() {
        contexts.remove();
    }

}