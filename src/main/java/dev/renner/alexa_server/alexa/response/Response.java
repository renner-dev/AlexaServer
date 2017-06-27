package dev.renner.alexa_server.alexa.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renne on 27.06.2017.
 */
public class Response {

    public OutputSpeech outputSpeech;
    public Card card;
    public Reprompt reprompt;
    public List<Directive> directives;
    public boolean shouldEndSession;

    public Response() {
        this.shouldEndSession = true;
        this.outputSpeech = new OutputSpeech();
        this.card = new Card();
        this.card = null;
        this.reprompt = new Reprompt();
        this.directives = new ArrayList<>();
    }

}
