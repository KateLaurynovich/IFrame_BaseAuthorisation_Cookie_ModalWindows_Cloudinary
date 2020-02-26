package taskPages.modalWindows;

public enum  ButtonCategory {
    ALERT("jsAlert()"),
    CONFIRM("jsConfirm()"),
    PROMPT("jsPrompt()");

    private String title;

    ButtonCategory(String  title) {
        this.title = title;
    }

    public String  getTitle() {
        return title;
    }
}
