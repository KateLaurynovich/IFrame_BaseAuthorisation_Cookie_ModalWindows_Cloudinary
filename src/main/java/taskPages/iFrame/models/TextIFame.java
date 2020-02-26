package taskPages.iFrame.models;

public class TextIFame {
    private String text;

    public TextIFame(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "TextIFame{" +
                "text='" + text + '\'' +
                '}';
    }
}
