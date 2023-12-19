import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public abstract class Pop {

    /**
     * At the last pop a window to show massage. 最后用一个窗口显示信息。
     * @param excels To transmit the count. 用于传递处理过的数量。
     */
    protected static void popWin(File[] excels){
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
    /**
     * Pop a window to show status. 最后用一个窗口显示信息。
     */
    protected static JFrame popStatus(){
        JFrame jFrame = new JFrame("Info");
        JLabel jLabel = new JLabel("...Unifying format...", JLabel.CENTER);
        JPanel jPanel = new JPanel();
        jPanel.add(jLabel);

        jFrame.getContentPane().add(jPanel);
        jFrame.setSize(300, 70);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jFrame;
    }

    /**
     * At the first pop a window to show massage. 用一个窗口显示信息。
     */
    protected static void popExcelIsRunning(){
        JFrame jFrame = new JFrame("Info");
        JLabel jLabel = new JLabel("Close all excel files plz.", JLabel.CENTER);
        JPanel jPanel = new JPanel();
        jPanel.add(jLabel);

        jFrame.getContentPane().add(jPanel);
        jFrame.setSize(300, 70);
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


