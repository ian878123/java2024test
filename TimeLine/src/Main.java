import javax.swing.*;
import java.lang.runtime.SwitchBootstraps;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI frame = new GUI();
                frame.setVisible(true);
            }
        });
    }
}