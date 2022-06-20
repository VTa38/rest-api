package com.rest.model;

import java.util.ArrayList;
import java.util.List;


public class NavigationDTO {
    // Класс, хранящий строку, её уровень вложенности и вложенную в него структуру

    // Ссылка на вышестоящую структуру
    private NavigationDTO parent = null;
    private String line;
    private int level;

    private List<NavigationDTO> navigation = new ArrayList<>();

    public NavigationDTO() {
    }

    // default getter на вышестоящую структуру. Служит только для того чтобы анализировать в моделях
    NavigationDTO getParent() {
        return parent;
    }

    public void setParent(NavigationDTO parent) {
        this.parent = parent;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public List<NavigationDTO> getNavigation() {
        return navigation;
    }

    public void setNavigation(NavigationDTO navigation) {
        this.navigation.add(navigation);
    }

}
