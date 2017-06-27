package dev.renner.alexa_server.alexa.response;

/**
 * Created by renne on 27.06.2017.
 */
public class Card {

    private CardType type;
    private String title;
    private String content;
    private String text;
    private CardImage image;

    public Card() {
        this.type = CardType.Simple;
        this.title = "";
        this.content = "";
        this.text = "";
        this.image = new CardImage();
    }

}
