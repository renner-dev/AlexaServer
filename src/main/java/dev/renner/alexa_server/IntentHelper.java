package dev.renner.alexa_server;

import dev.renner.alexa_server.alexa.response.Intent;
import org.reflections.Reflections;

import java.util.Set;

/**
 * Created by renne on 27.06.2017.
 */
public class IntentHelper {

    public static Set<Class<?>> annotated;

    public static void setup() {
        Reflections reflections = new Reflections();
        annotated = reflections.getTypesAnnotatedWith(Intent.class);

        System.out.println("Found " + annotated.size() + " intents!");
    }
}
