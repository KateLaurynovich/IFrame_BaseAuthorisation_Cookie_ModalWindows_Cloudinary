package taskPages.iFrame.pageObject.editMenu;

public enum EditMenuCategory {
    BOLD(3),
    ITALIC(5);

    private int title;

    EditMenuCategory(int title) {
        this.title = title;
    }

    public int getTitle() {
        return title;
    }
}
