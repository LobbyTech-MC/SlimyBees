package cz.martinbrom.slimybees;

import javax.annotation.Nonnull;

/**
 * This is the place where all items from SlimyBees are registered.
 */
public class ItemSetup {

    private static boolean initialized = false;

    // prevent instantiation
    private ItemSetup() {
    }

    public static void setup(@Nonnull SlimyBees plugin, @Nonnull Categories categories) {
        if (initialized) {
            throw new UnsupportedOperationException("SlimyBees Items can only be registered once!");
        }

        initialized = true;

    }

}
