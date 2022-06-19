package com.rest.Models;

import java.util.List;

public class NavigationDTO {
    // Класс, хранящий пару (строка и её уровень вложенности)

    private String line;
    private int level;
    private List<NavigationDTO> navigation = null;

    public void setNavigation(List<NavigationDTO> navigation) {
        this.navigation = navigation;
    }

    public NavigationDTO() {
    }

    /*
    public NavigationDTO(String line, int level, List<NavigationDTO> navigation) {
        this.line = line;
        this.level = level;
        this.navigation = navigation;
    }
    */

    public void setLine(String line) {
        this.line = line;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public NavigationDTO(String line, int level) {
        this.line = line;
        this.level = level;
    }

    public String getLine() {
        return line;
    }

    public List<NavigationDTO> getNavigation() {
        return navigation;
    }

    public int getLevel() {
        return level;
    }
}
