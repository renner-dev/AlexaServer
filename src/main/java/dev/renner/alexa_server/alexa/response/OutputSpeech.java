package dev.renner.alexa_server.alexa.response;

/**
 * Created by renne on 27.06.2017.
 */
public class OutputSpeech {

    public OutputSpeechType type;
    public String text;
    public String ssml;

    public OutputSpeech() {
        this.type = OutputSpeechType.PlainText;
        this.text = "";
        this.ssml = "";
    }
}
