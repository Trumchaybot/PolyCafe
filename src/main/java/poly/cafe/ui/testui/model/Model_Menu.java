package poly.cafe.ui.testui.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Menu {

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Model_Menu(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public Model_Menu() {
    }

    private String icon;
    private String name;
    private MenuType type;

    public Icon toIcon() {
    if (icon == null || icon.trim().isEmpty()) {
        System.err.println("⚠️ Icon name is null or empty for menu: " + name);
        return null;
    }
    java.net.URL url = getClass().getResource("/icons/" + icon + ".png");
    if (url == null) {
        System.err.println("/icons/" + icon + ".png");
        return null;
    }
    return new ImageIcon(url);
}


    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
}
