package dev.renner.alexa_server.alexa.response;

/**
 * Created by renne on 27.06.2017.
 */
public class Directive {

    public DirectiveType type;
    public String playBehavior;
    public AudioItem audioItem;

    public Directive() {
        this.type = null;
        this.playBehavior = "";
    }


}
