package dev.renner.alexa_server;

import com.google.gson.Gson;
import dev.renner.alexa_server.alexa.AlexaRequest;
import dev.renner.alexa_server.alexa.AlexaResponse;
import dev.renner.alexa_server.alexa.response.Intent;
import dev.renner.alexa_server.alexa.response.OutputSpeechType;
import dev.renner.alexa_server.intents.BasicIntent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by renne on 27.06.2017.
 */
public class ResponseBuilder {

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

        Gson gson = new Gson();
        AlexaResponse alexaResponse = gson.fromJson(ResponseBuilder.getBasicResponse(), AlexaResponse.class);
        alexaResponse.response.outputSpeech.type = OutputSpeechType.PlainText;
        alexaResponse.response.outputSpeech.text = "Hallo";
        alexaResponse.response.outputSpeech.ssml = null;
        alexaResponse.response.reprompt = null;
        alexaResponse.response.directives = null;

        System.out.println(request);
        AlexaRequest alexaRequest = gson.fromJson(request, AlexaRequest.class);
        System.out.println(alexaRequest.toString());

        for (Class<?> c : IntentHelper.annotated) {
            if (c.isAnnotationPresent(Intent.class)) {
                Intent intent = c.getAnnotation(Intent.class);
                System.out.println("Intent asked for: " + alexaRequest.request.intent.name + " | Current intent: " + intent.name());
                if (alexaRequest.request.intent.name.trim().equals(intent.name().trim())) {
                    try {
                        BasicIntent basicIntent = (BasicIntent) c.newInstance();
                        Method method = c.getMethod(intent.executionMethod(), AlexaRequest.class);
                        alexaResponse = (AlexaResponse) method.invoke(basicIntent, alexaRequest);

                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        String result = gson.toJson(alexaResponse);
        System.out.println(result);
        return result;
    }

}
