package dev.renner.alexa_server;

import com.google.gson.Gson;
import dev.renner.alexa_server.alexa.AlexaRequest;
import dev.renner.alexa_server.alexa.AlexaResponse;
import dev.renner.alexa_server.alexa.response.Intent;
import dev.renner.alexa_server.alexa.response.OutputSpeechType;
import javafx.util.Pair;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by renne on 27.06.2017.
 */
public class ResponseBuilder {

    private static Map<String, Pair<Class<?>, Intent>> intentMapping = new HashMap<>();

    public static void setup() {
        for (Class<?> c : IntentHelper.annotated) {
            if (c.isAnnotationPresent(Intent.class)) {
                Intent intent = c.getAnnotation(Intent.class);
                intentMapping.put(intent.name(), new Pair<>(c, intent));
            }
        }
    }

    public static String getBasicResponse() {

        AlexaResponse testResponse = new AlexaResponse();
        testResponse.response.outputSpeech.type = OutputSpeechType.PlainText;
        testResponse.response.outputSpeech.text = "Hallo";
        testResponse.response.outputSpeech.ssml = null;
        testResponse.response.reprompt = null;
        testResponse.response.directives = null;

        Gson gson = new Gson();

        String result = gson.toJson(testResponse);
        System.out.println(result);


        return result;
    }

    public static String getResponse(String request) {

        System.out.println(request);
        Gson gson = new Gson();
        AlexaRequest alexaRequest = gson.fromJson(request, AlexaRequest.class);
        System.out.println(alexaRequest.toString());

        AlexaResponse alexaResponse = null;
        Pair<Class<?>, Intent> intentClassPair = intentMapping.get(alexaRequest.request.intent.name.trim());
        Intent intent = intentClassPair.getValue();
        if (intent != null) {
            System.out.println("Intent asked for: " + alexaRequest.request.intent.name + " | Current intent: " + intent.name());
            if (alexaRequest.request.intent.name.trim().equals(intent.name().trim())) {
                try {
                    Object basicIntent = intentClassPair.getKey().newInstance();
                    Method method = intentClassPair.getKey().getMethod(intent.executionMethod(), AlexaRequest.class);
                    alexaResponse = (AlexaResponse) method.invoke(basicIntent, alexaRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        if (alexaResponse == null) {
            alexaResponse.response.outputSpeech.type = OutputSpeechType.PlainText;
            alexaResponse.response.outputSpeech.text = "Tut mir leid, aber du warst du dumm einen richtigen befehel zu benutzen!";
            alexaResponse.response.outputSpeech.ssml = null;
            alexaResponse.response.reprompt = null;
            alexaResponse.response.directives = null;
        }

        String result = gson.toJson(alexaResponse);
        System.out.println(result);
        return result;
    }

}
