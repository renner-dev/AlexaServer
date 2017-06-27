package dev.renner.alexa_server.alexa;

import dev.renner.alexa_server.alexa.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by renne on 27.06.2017.
 */
public class AlexaResponse {

    public String version;
    public Map<String, String> sessionAttributes;
    public Response response;

    public AlexaResponse() {
        this.version = "1.0";
        this.sessionAttributes = new HashMap<>();
        this.response = new Response();
    }

}
