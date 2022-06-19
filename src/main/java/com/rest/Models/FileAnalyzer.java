package com.rest.Models;

import com.rest.exception.IllegalStructureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileAnalyzer {
    // Класс, который анализирует и преобразует информацию из файла

    private List<NavigationDTO> navigationData = new ArrayList<>();
    private int initGrowing = 0;
    private List<String> dataForAnalyze;

    public void startProcessing(List<String> data) {
        dataForAnalyze = data;
        NavigationDTO navigationDTO = new NavigationDTO();
        NavigationDTO prevNavigationDTO = null;
        for (int i = 0; i < dataForAnalyze.size(); i++) {
            String check = dataForAnalyze.get(i);

            if (check.startsWith("#")){
                if (i==0){
                    throw new IllegalStructureException("First element can't be inner");
                }
                prevNavigationDTO.setNavigation(fileProcessing(i, 1));
                i += initGrowing;

            } else {
                boolean moreThanOneLine = false;

                while (check.endsWith("CRLF")) {
                    //Флаг, проверка входа в цикл
                    moreThanOneLine = true;
                    String temp = dataForAnalyze.get(++i);
                    if (temp.startsWith("#")) {

                        throw new IllegalStructureException("Part of line can't be inner");
                    }
                    check = check.substring(0, check.length() - 4);
                    check = check + temp;
                }

                while (check.endsWith("LF") || check.endsWith("CR")) {
                    //Флаг, проверка входа в цикл
                    moreThanOneLine = true;
                    String temp = dataForAnalyze.get(++i);
                    if (temp.startsWith("#")) {
                        throw new IllegalStructureException("Part of line can't be inner");
                    }
                    check = check.substring(0, check.length() - 2);
                    check = check + temp;
                }
                if (moreThanOneLine){
                    i++;
                }

                navigationDTO.setLine(check);
                navigationDTO.setLevel(0);
                navigationData.add(navigationDTO);
                prevNavigationDTO = navigationDTO;
                navigationDTO = new NavigationDTO();
            }
        }
    }

    private List<NavigationDTO> fileProcessing(int nextLine, int level) throws IllegalArgumentException {
        List<NavigationDTO> subNavigationData = new ArrayList<>();
        NavigationDTO navigationDTO = new NavigationDTO();
        NavigationDTO prevNavigationDTO = null;
        for (int i = nextLine; i < dataForAnalyze.size(); i++) {
            initGrowing++;
            String check = dataForAnalyze.get(i);

            if (check.startsWith("##")){
                throw new IllegalStructureException("Element can't be inner without title element");
            }

            if (check.startsWith("#")){
                check = check.substring(1);
                dataForAnalyze.add(i, check);
                prevNavigationDTO.setNavigation(fileProcessing(i, ++level));
            } else {
                boolean moreThanOneLine = false;

                while (check.endsWith("CRLF")) {

                    //Флаг, проверка входа в цикл
                    moreThanOneLine = true;

                    i+=2;
                    String temp = dataForAnalyze.get(i);
                    if (temp.startsWith("#")) {
                        System.out.println(check);
                        System.out.println(temp);
                        throw new IllegalStructureException("Part of line can't be inner");
                    }
                    check = check.substring(0, check.length() - 4);
                    check = check + temp;
                }

                while (check.endsWith("LF") || check.endsWith("CR")) {
                    i++;
                    //Флаг, проверка входа в цикл
                    moreThanOneLine = true;
                    String temp = dataForAnalyze.get(i);
                    if (temp.startsWith("#")) {
                        throw new IllegalStructureException("Part of line can't be inner");
                    }
                    check = check.substring(0, check.length() - 2);
                    check = check + temp;
                }

                if (moreThanOneLine){
                    i++;
                }

                navigationDTO.setLine(check);
                navigationDTO.setLevel(level);
                subNavigationData.add(navigationDTO);
                prevNavigationDTO = navigationDTO;
                navigationDTO = new NavigationDTO();
            }

        }
        return subNavigationData;
    }

    public List<NavigationDTO> getData() {
        return navigationData;
    }

    public void clearList(){
        navigationData = new ArrayList<>();
    }
}
