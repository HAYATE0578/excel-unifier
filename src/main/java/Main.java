import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Get all excel files in this local folder. 获取当前目录下所有的Excel文件
        File currentDirectory = new File(".");
        File[] excels = currentDirectory.listFiles((dir, name) -> name.endsWith(".xlsx") || name.endsWith(".xls"));

        // If there are not excels reported it. 如果没有excel文件则报告。
        if (excels != null && excels.length > 0) {
            for (File excel : excels) {
                manipulateExcel(excel);
            }
        } else {
            System.out.println("No Excel files found in the current directory. 当前文件夹中并无excel文件。");
        }

        System.out.println("----------Regulated: "+excels.length);
        popWin(excels);
    }

    /**
     * Set Excel files: 设置全部Excel文件：
     * 1. to be focused on A1 锁定在A1格子
     * 2. the zoom is 100% 缩放为100%
     * 3. the first sheet is active 第一张表格被显示
     * @param excel
     */
    private static void manipulateExcel(File excel) {
        try (FileInputStream fis = new FileInputStream(excel);
             Workbook workbook = new XSSFWorkbook(fis)) {

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);

                // Focus on A1. 锁定到A1。
                sheet.createFreezePane(0, 1, 0, 1);

                // Set the zoom to be 100%. 调整缩放为100%。
                sheet.setZoom(100);

                // Activate the first sheet. 活性化第一张表格。
                workbook.setActiveSheet(0);

                // Save the Excel. 保存修改后的Excel文件
                try (FileOutputStream fos = new FileOutputStream(excel.getName())) {
                    workbook.write(fos);
                }
            }

            System.out.println("----------Completed: " + excel.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * At the last pop a window to show massage. 最后用一个窗口显示信息。
     * @param excels To transmit the count. 用于传递处理过的数量。
     */
    private static void popWin(File[] excels){
        JFrame jFrame = new JFrame("Info");
        JLabel jLabel = new JLabel("Regulated Excels count: "+excels.length, JLabel.CENTER);
        JLabel jLabel2 = new JLabel("This window will close in 3s... ",JLabel.CENTER);

        JPanel jPanel = new JPanel(new GridLayout(2,1));
        jPanel.add(jLabel);
        jPanel.add(jLabel2);

        jFrame.getContentPane().add(jPanel);
        jFrame.setSize(300, 100);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timer.setRepeats(false);
        timer.start();
    }
}
