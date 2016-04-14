package tasks;

import SudokuHandling.HandleWeb;
import data.Variables;
import framework.Task;

/**
 * Created by Sphiinx on 4/13/2016.
 */
public class GetWebMatrix extends Task {


    @Override
    public void execute() {
        if (!HandleWeb.isGameOpen("//*[@id=\"game\"]"))
            return;

        HandleWeb.resetGame("//*[@id=\"site-navigation\"]/div[2]/ul/li/ul/li[5]/a");
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (!HandleWeb.getElementXPath(HandleWeb.driver, "//*[@id=\"table\"]/tbody/tr[" + i + "]/td[" + j + "]/div").getText().contains(" ")) {
                    Variables.matrix[i - 1][j - 1] = Integer.parseInt(HandleWeb.getElementXPath(HandleWeb.driver, "//*[@id=\"table\"]/tbody/tr[" + i + "]/td[" + j + "]/div").getText());
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Getting Matrix...";
    }

    @Override
    public boolean validate() {
        int cells = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Variables.matrix[i][j] == 0) {
                    cells++;
                }
            }
        }
        return cells == 81;
    }
}

