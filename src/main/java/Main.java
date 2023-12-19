
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.*;

public class Main {

    static java.util.List<File> excels = new ArrayList<File>();

    public static void main(String[] args) {

        // Return the main method if excel.exe is running . 如果excel.exe在运行则退出。
        if(isExcelRunning()){
            Pop.popExcelIsRunning();
            return;
        }
        // Pop a window to show status. 创造一个窗口来显示状态。
        JFrame status = Pop.popStatus();

        // Get all Excel files in this local folder(recursion). 获取当前目录下所有的Excel文件(迭代)
        File currentDirectory = new File(".");
        SearchExcelPath(currentDirectory);

        File[] excelFiles =  excels.toArray(new File[0]);

        // If there are not excelFiles reported it. 如果没有excel文件则报告。
        if (excelFiles != null && excelFiles.length > 0) {
            for (File excel : excelFiles) {
                manipulateExcel(excel);
            }
        }

        // At the end report. 最后汇报。
        status.dispose();
        Pop.popWin(excelFiles);
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
                try (FileOutputStream fos = new FileOutputStream(excel.getAbsolutePath())) {
                    workbook.write(fos);
                }
            }

            System.out.println("----------Completed: " + excel.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check whether excel is running. 检查是否有Excel正在运行。
     * @return whether excel is running.
     */
    private static boolean isExcelRunning(){
        try {
            // Check whether there is excel threads in system. 检察是否有Excel的进程。
            Process process = Runtime.getRuntime().exec("tasklist /FI \"IMAGENAME eq EXCEL.EXE \"");

            // Read cmd.  读取命令输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Read all lines. 读取所有行。
            while ((line = reader.readLine()) != null) {
                if (line.contains("EXCEL.EXE")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Search Excel files by recursion. 使用遍历的方式获得文件夹下所有excel。
     * @param localFile A local path. 一个当前路径。
     */
    private static void SearchExcelPath(File localFile) {
        File[] files = localFile.listFiles();

        // Ref: https://support.microsoft.com/en-us/office/file-formats-that-are-supported-in-excel-0943ff2c-6014-4e8d-aaea-b83d51d46247
        for (File f : files) {
            if (f.isFile()){
                String name = f.toString();
                if(name.endsWith(".xlsx")
                    || name.endsWith(".xlsm")
                    || name.endsWith(".xlsb")
                    || name.endsWith(".xltx")
                    || name.endsWith(".xltm")
                    || name.endsWith(".xlt")
                    // name.endsWith(".xml") Apache Package is not compatible.
                    || name.endsWith(".xlam")
                    || name.endsWith(".xla")
                    || name.endsWith(".xlw")
                    || name.endsWith(".xlr")
                    || name.endsWith(".xls")){
                        excels.add(f);
                    }
            }
            else if (f.isDirectory()) {
                SearchExcelPath(f);
            }
        }
    }
}
