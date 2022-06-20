package com.rest.model;

import com.rest.exception.IllegalStructureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileAnalyzer {
    // Класс с основной логикой, который анализирует и преобразует информацию из файла

    private List<NavigationDTO> list = new ArrayList<>();

    public List<NavigationDTO> startProcessing(List<String> data) {
        List<NavigationDTO> navigationData = new ArrayList<>();

        NavigationDTO navigationDTO = new NavigationDTO();
        NavigationDTO prevNavigationDTO = null;

        for (int i = 0; i < data.size(); i++) {
            String check = data.get(i);


            while (check.endsWith("CRLF")) {
                String temp = data.get(++i);
                if (temp.startsWith("#")) {
                    throw new IllegalStructureException("Part of line can't be inner");
                }
                check = check.substring(0, check.length() - 4);
                check = check + temp;
            }

            while (check.endsWith("LF") || check.endsWith("CR")) {
                String temp = data.get(++i);
                if (temp.startsWith("#")) {
                    throw new IllegalStructureException("Part of line can't be inner");
                }
                check = check.substring(0, check.length() - 2);
                check = check + temp;
            }

            int currentLevel = checkLevel(check);
            navigationDTO.setLevel(currentLevel);

            if (i == 0 && currentLevel > 0)
                throw new IllegalStructureException("First element can't be inner");
            else if (prevNavigationDTO != null && currentLevel > prevNavigationDTO.getLevel() + 1)
                throw new IllegalStructureException("Wrong nesting transition");


            if (currentLevel == 0) {
                navigationDTO.setParent(null);
                navigationDTO.setLine(check);
                navigationData.add(navigationDTO);
            } else {
                check = check.substring(currentLevel);
                while (currentLevel <= prevNavigationDTO.getLevel()) {

                    prevNavigationDTO = prevNavigationDTO.getParent();
                }
                navigationDTO.setParent(prevNavigationDTO);
                prevNavigationDTO.setNavigation(navigationDTO);
            }
            navigationDTO.setLine(check);

            prevNavigationDTO = navigationDTO;
            navigationDTO = new NavigationDTO();
        }
        list = navigationData;
        //TODO класс не должен хранить данные
        return navigationData;
    }

    private int checkLevel(String line) {
        int level = 0;
        while (line.startsWith("#")) {
            line = line.substring(1);
            level++;
        }
        return level;
    }

    public List<NavigationDTO> getData() {
        return list;
    }
}
