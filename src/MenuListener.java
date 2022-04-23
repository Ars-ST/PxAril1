import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener {

    JFrame frame;

    public MenuListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        String c = e.getActionCommand();
        switch (c) {
            case "Выход":
                System.exit(0);
            case "Справка":
                Main.mainDisplay.setText(Info.TEXT_HELP);
                //Main.mainDisplay.insert(Info.TEXT_HELP, 0);         //Добавляет текст в поле не затирая
                Main.mainDisplay.setEditable(false);
                Main.tfIDSMS.setText("0");
                break;
            case "О программе":

                Main.mainDisplay.setText(Info.TEXT_ABOUT_PROGRAM);
                //Main.mainDisplay.insert(Info.TEXT_ABOUT_PROGRAM, 0);
                Main.mainDisplay.setEditable(false);
                Main.tfIDSMS.setText("0");
                break;
        }
    }


}
