import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static JFrame f;

    public static boolean saveLastSMS = true;

    public static JTextArea mainDisplay;
    public static JTextField tfIDSMS;

    public static JTextField tfUser;
    public static JTextField tfRecipient;


    public static JTextArea textSMS1;                                   //Текстовое поле для ввода первой части сообщения
    public static JTextArea textSMS2;                                   //Текстовое поле для ввода средней части сообщения
    public static JTextArea textSMS3;                                   //Текстовое поле для ввода заключительной части сообщения

    public static int intTfIdSMS;

    public static ArrayList<BoxSMS> boxSMS = new ArrayList<BoxSMS>(Collections.singleton(new BoxSMS(0,
            "", "", "", "")));

    public static ArrayList<BoxSMS> genBoxSMS = new ArrayList<BoxSMS>(Collections.singleton(new BoxSMS(0,
            "", "", "", "")));

    public static void main(String[] args) {
        /*
        Описание:

            Данное приложение является развлекательным и именуется как "Испорченный mail".
            Суть программы заключается в том, чтобы зафиксировать поздравления от пользователя нескольким получателям,
                после чего перемешать между собой фрагментами, и вывести пользователю.

         */
        int locationY = 20;                 //Базовое расположение окна ChatFrame
        int sizeFrame = 800;                //Ширина окна

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int locationX = (size.width - sizeFrame) / 2;


        // Создаём форму для нашей программы
        //JFrame f = new JFrame("Программа для генерации юмористических поздравлений \"Испорченный MAIL\"");
        f = new JFrame("Программа \"Испорченный MAIL\"");
        f.setBounds(locationX, locationY, sizeFrame, 700);                  //Пока размеры основного указаны без учёта размера экрана.

        f.setLocation(locationX, locationY);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setLayout(new BorderLayout());

        MenuListener listener = new MenuListener(f);



        JMenuBar mb = new JMenuBar();                                                      //Создание панели Меню
        JMenu main = new JMenu("Меню");                                                 //Создание пункта меню
        JMenuItem miHelp = new JMenuItem("Справка");                                  //Создание подпункта Справка
        JMenuItem miAboutProgram = new JMenuItem("О программе");                      //Создание подпункта О программе
        JMenuItem miExit = new JMenuItem("Выход");                                    //Создание подпункта Выход
        main.add(miHelp);
        main.add(miAboutProgram);
        main.addSeparator();                                                               //Добавление линии(разделителя)
        main.add(miExit);
        mb.add(main);

        //Фронтальная панель    ------------------------------------------------
        //JPanel front = new JPanel(new BorderLayout());
        int textSMS_rows = 6;
        int textSMS_columns = 63;

        JPanel front = new JPanel(new GridBagLayout());

        mainDisplay = new JTextArea("Дисплей. Здесь, если Вы дождётесь, будут выводиться сгенерированные сообщения и " +
                "справочная информация.", 8, textSMS_columns);                                                      //Основной дисплей приложения
        mainDisplay.setLineWrap(true);
        mainDisplay.setEditable(false);                                                                                 //Запрет редактирования дисплея


        textSMS1 = new JTextArea("Введите начало сообщения, вместо имени получателя используйте латинскую N ...",
                textSMS_rows, textSMS_columns);                                                                         //Поле сообщения 1
        textSMS1.setLineWrap(true);
        textSMS2 = new JTextArea("Введите середину сообщения...", textSMS_rows, textSMS_columns);                              //Поле сообщения 2
        textSMS2.setLineWrap(true);
        textSMS3 = new JTextArea("Введите окончание сообщения...", textSMS_rows, textSMS_columns);                                      //Поле сообщения 3
        textSMS3.setLineWrap(true);

        //Панель отправителя
        JPanel panUser = new JPanel();
        //JLabel labUser = new JLabel("Отправитель:");
        JLabel labUser = new JLabel("Отправитель:");
         tfUser = new JTextField(15);

        panUser.add(labUser);
        panUser.add(tfUser);


        //Панель индекса
        JPanel panId = new JPanel();
        JButton bLeft = new JButton("<<<");                                                                         //Стрелка влево
        tfIDSMS = new JTextField("0", 2);
        JButton bRight = new JButton(">>>");                                                                        //Стрелка вправо

        tfIDSMS.setText("1");

        //Настройка полей индекса
        tfIDSMS.setHorizontalAlignment(JTextField.CENTER);

        //Добавление элементов на панель Id
        panId.add(bLeft);
        panId.add(tfIDSMS);
        panId.add(bRight);

        //Панель получателя
        JPanel panRecipient = new JPanel();
//        JLabel labRecipient = new JLabel("Получатель:");
        JLabel labRecipient = new JLabel("Жертва:");
        tfRecipient = new JTextField(15);

        panRecipient.add(labRecipient);
        panRecipient.add(tfRecipient);

        JScrollPane jspMainDisplay =  new JScrollPane(mainDisplay);
        JScrollPane jspTextSMS1 =  new JScrollPane(textSMS1);
        JScrollPane jspTextSMS2 =  new JScrollPane(textSMS2);
        JScrollPane jspTextSMS3 =  new JScrollPane(textSMS3);

        GridBagConstraints c = new GridBagConstraints();            //Настроить
        c.gridwidth = 3;
        c.insets = new Insets(0, 0, 10, 0);

        //Добавление дисплея
        c.gridx = 0;
        c.gridy = 0;
        front.add(jspMainDisplay, c);

        //Добавление Панели с отправителем
        c.gridwidth = 1;
        c.gridy = 1;
        front.add(panUser, c);

        //Добавление панели с индексом
        //c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        front.add(panId, c);

        //Добавление Панели с получателем
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 2;
        front.add(panRecipient, c);

        //Первое поле СМС
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        front.add(jspTextSMS1, c);

        //Второе поле СМС
        c.gridy = 3;
        front.add(jspTextSMS2, c);

        //Третье поле СМС
        c.gridy = 4;
        front.add(jspTextSMS3, c);


        //Подвал    ------------------------------------------------------------
        JPanel bottom = new JPanel();

        JButton bStart = new JButton("Закаламбурировать");
        JButton bClear = new JButton("Очистить");
        JButton bSave = new JButton("Запомнить");
        JButton bSet = new JButton("Редактировать");

        bottom.add(bStart);
        bottom.add(bClear);
        bottom.add(bSave);
        bottom.add(bSet);

        //Добавление обработчиков на кнопки

//        JButton bLeft = new JButton("<<<");                                                                         //Стрелка влево
//        tfIDSMS = new JTextField("0", 2);
//        JButton bRight = new JButton(">>>");




        bLeft.addActionListener(e -> {

            try {
                intTfIdSMS = Integer.parseInt(tfIDSMS.getText());
                if (intTfIdSMS > 0 && intTfIdSMS <= boxSMS.size()) {
                    intTfIdSMS--;
                    tfIDSMS.setText(Integer.toString(intTfIdSMS));

                    if (intTfIdSMS < genBoxSMS.size()) {                                  //Поправить
                        mainDisplay.setText(
                        tfRecipient.getText() +
                        genBoxSMS.get(intTfIdSMS).getGenTextFields()[0] + "" +
                        genBoxSMS.get(intTfIdSMS).getGenTextFields()[1] + "" +
                        genBoxSMS.get(intTfIdSMS).getGenTextFields()[2] + "" +
                            "\n\n                                                                          " + tfUser.getText()
                        );
                    }

                    //Выводим на экран сообщения по индексу
                    tfRecipient.setText(boxSMS.get(intTfIdSMS).getRecipient());
                    textSMS1.setText(boxSMS.get(intTfIdSMS).getOriginTextFields()[0]);
                    textSMS2.setText(boxSMS.get(intTfIdSMS).getOriginTextFields()[1]);
                    textSMS3.setText(boxSMS.get(intTfIdSMS).getOriginTextFields()[2]);

                } else if (intTfIdSMS < 0 || intTfIdSMS > Main.boxSMS.size()) {
                    JOptionPane.showMessageDialog(
                            f,
                            "Индекс может быть только целым числом, от 0 до 18\n" +
                                    "И не может превышать количество доступных сообщений.\n" +
                                    "Если исправите, то может повезёт раз ;)",
                            "Информация",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Индекс может быть только целым числом, от 0 до 18\n" +
                            "И не может превышать количество доступных сообщений");
                }
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(bLeft.this, "Что-то пошло не так", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(f, "Что-то пошло не так", "Предупреждение",
                        JOptionPane.WARNING_MESSAGE);
                System.out.println("Что-то пошло не так");
            }
        });


        bRight.addActionListener(e -> {

            try {
                int intTfIdSMS = Integer.parseInt(tfIDSMS.getText());

                if (intTfIdSMS >= 0 && intTfIdSMS < boxSMS.size()) {

                    //mainDisplay.setEditable(true);

                    System.out.println("intTfIdSMS = " + intTfIdSMS + " boxSMS.size() = " + boxSMS.size());

                    if (intTfIdSMS < 18) {
                        intTfIdSMS++;
                        tfIDSMS.setText(Integer.toString(intTfIdSMS));

                        System.out.println("boxSMS.indexOf(intTfIdSMS) == " + boxSMS.indexOf(intTfIdSMS));       //Для проверки индекса

                        //tfIDSMS.setText("1");
                        //System.out.println(genBoxSMS.get(1).getGenerateText());

                        if (intTfIdSMS < genBoxSMS.size()){
                            mainDisplay.setText(
                                tfRecipient.getText() +
                                genBoxSMS.get(intTfIdSMS).getGenTextFields()[0] + "" +
                                genBoxSMS.get(intTfIdSMS).getGenTextFields()[1] + "" +
                                genBoxSMS.get(intTfIdSMS).getGenTextFields()[2] + "" +
                                        "\n\n                                                                          " + tfUser.getText()
                            );
                        }

                        //Выводим на экран сообщения по индексу
                        if (intTfIdSMS < boxSMS.size()) {
                            //mainDisplay
                            tfRecipient.setText(boxSMS.get(intTfIdSMS).getRecipient());
                            textSMS1.setText(boxSMS.get(intTfIdSMS).getOriginTextFields()[0]);
                            textSMS2.setText(boxSMS.get(intTfIdSMS).getOriginTextFields()[1]);
                            textSMS3.setText(boxSMS.get(intTfIdSMS).getOriginTextFields()[2]);
                        } else if(intTfIdSMS == 18) {
                            //if (intTfIdSMS == boxSMS.size())
                        } else {
                            tfRecipient.setText("");
                            textSMS1.setText("");
                            textSMS2.setText("");
                            textSMS3.setText("");
                        }
                    } else if(intTfIdSMS == 18 && intTfIdSMS < boxSMS.size()) {

                        if (intTfIdSMS < genBoxSMS.size()){
                            mainDisplay.setText(
                                tfRecipient.getText() +
                                genBoxSMS.get(intTfIdSMS).getGenTextFields()[0] + "" +
                                genBoxSMS.get(intTfIdSMS).getGenTextFields()[1] + "" +
                                genBoxSMS.get(intTfIdSMS).getGenTextFields()[2] + "" +
                                "\n\n                                                                          " + tfUser.getText()
                            );
                        }

                        tfRecipient.setText(boxSMS.get(intTfIdSMS).getRecipient());
                        textSMS1.setText(boxSMS.get(intTfIdSMS).getOriginTextFields()[0]);
                        textSMS2.setText(boxSMS.get(intTfIdSMS).getOriginTextFields()[1]);
                        textSMS3.setText(boxSMS.get(intTfIdSMS).getOriginTextFields()[2]);
                    }

                } else if (intTfIdSMS < 0 || intTfIdSMS > boxSMS.size()) {
                    JOptionPane.showMessageDialog(
                            f,
                            "Индекс может быть только целым числом, от 0 до 18\n" +
                                    "И не может превышать количество доступных сообщений.\n" +
                                    "Если исправите, то может повезёт раз ;)",
                            "Информация",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Индекс может быть только целым числом, от 0 до 18\n" +
                            "И не может превышать количество доступных сообщений");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Что-то пошло не так", "Предупреждение",
                        JOptionPane.WARNING_MESSAGE);
                System.out.println("Что-то пошло не так");
                System.out.println(ex);
            }
        });


        bClear.addActionListener(e -> {
            textSMS1.setText("");
            textSMS2.setText("");
            textSMS3.setText("");
        });

        bSave.addActionListener(e -> {



            try {
//                System.out.println(1);
                int intTfIdSMS = Integer.parseInt(tfIDSMS.getText());
//                System.out.println(2);
                if (intTfIdSMS > 0 && intTfIdSMS < 18 && intTfIdSMS <= boxSMS.size()) {
                    if (intTfIdSMS == boxSMS.size()) {
                        BoxSMS sms = new BoxSMS(
                                intTfIdSMS,
                                tfRecipient.getText(),
                                textSMS1.getText(),
                                textSMS2.getText(),
                                textSMS3.getText()
                                );
    //                    System.out.println(3);
                        boxSMS.add(sms);
    //                    System.out.println(4);
                        tfRecipient.setText("");
                        textSMS1.setText("");
                        textSMS2.setText("");
                        textSMS3.setText("");
                        intTfIdSMS++;
                        tfIDSMS.setText(Integer.toString(intTfIdSMS));
                        System.out.println("Сохранено");
                        mainDisplay.setEditable(true);
                    } else {
                        boxSMS.get(intTfIdSMS).setRecipient(tfRecipient.getText());
                        boxSMS.get(intTfIdSMS).setOriginTextFields(
                                textSMS1.getText(),
                                textSMS2.getText(),
                                textSMS3.getText()
                        );
                        System.out.println("Отредактировано");
                        mainDisplay.setEditable(true);                  //Переместить
                    }
                } else if (intTfIdSMS == 18 && intTfIdSMS == boxSMS.size()) {
                    if (saveLastSMS) {
                        BoxSMS sms = new BoxSMS(
                            intTfIdSMS,
                            tfRecipient.getText(),
                            textSMS1.getText(),
                            textSMS2.getText(),
                            textSMS3.getText()
                        );
                        boxSMS.add(sms);

                        JOptionPane.showMessageDialog(
                                f,
                                "Сохранено последнее поздравление! :)\n" +
                                        "В данной версии это максимум :(",
                                "Информация",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Сохранён последний элемент");
                        saveLastSMS = false;
                    } else {
                        boxSMS.get(intTfIdSMS).setRecipient(tfRecipient.getText());
                        boxSMS.get(intTfIdSMS).setOriginTextFields(
                                textSMS1.getText(),
                                textSMS2.getText(),
                                textSMS3.getText()
                        );
                        System.out.println("Отредактировано");
                    }
//              } else if (intTfIdSMS < 0 || intTfIdSMS > 18) {
                } else if (intTfIdSMS == 18 && intTfIdSMS < boxSMS.size()) {
                    System.out.println("Отредактировано последнее сообщение");
                } else if (intTfIdSMS <= 0 || intTfIdSMS > 18) {
                    JOptionPane.showMessageDialog(
                            f,
                            "Запоминать записи можно только с номером от 1 до 18\n" +
                                    "Если исправите, то может повезёт раз ;)",
                            "Информация",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Индекс может быть только целым числом, от 1 до 18");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Что-то пошло не так", "Предупреждение",
                        JOptionPane.WARNING_MESSAGE);
                System.out.println("Что-то пошло не так");
            }
        });

        bStart.addActionListener(e -> {

            start();

            try {
                System.out.println(boxSMS.size());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Что-то пошло не так", "Предупреждение",
                        JOptionPane.WARNING_MESSAGE);
                System.out.println("Что-то пошло не так");
            }
        });


        //Обработчик индекса

        tfIDSMS.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("Индекс изменён");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("Индекс удалён");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("Событие 3");
            }
        });




        f.setJMenuBar(mb);

        miHelp.addActionListener(listener);
        miAboutProgram.addActionListener(listener);
        miExit.addActionListener(listener);

        f.add(front, BorderLayout.CENTER);
        f.add(bottom, BorderLayout.SOUTH);

        f.setVisible(true);

    }

    public static void start() {

        for (int i = 1; i < boxSMS.size(); i++) {
            BoxSMS genSMS = new BoxSMS(
                    i,
                    boxSMS.get(i).getRecipient(),
                    boxSMS.get(i).getOriginTextFields()[0],
                    boxSMS.get(i).getOriginTextFields()[0],
                    boxSMS.get(i).getOriginTextFields()[0]
            );
            genBoxSMS.add(genSMS);
        }
            System.out.println("genBoxSMS = " + genBoxSMS.size());

        for (int i = 1; i < boxSMS.size(); i++) {
            int rnd1 = (int) (Math.random() * (boxSMS.size() - 1)) + 1;
            int rnd2 = (int) (Math.random() * (boxSMS.size() - 1)) + 1;
            int rnd3 = (int) (Math.random() * (boxSMS.size() - 1)) + 1;
            System.out.println(rnd1 + " " + rnd2 + " " + rnd3);
            genBoxSMS.get(i).setGenTextFields(
                    boxSMS.get(rnd1).getOriginTextFields()[0],
                    boxSMS.get(rnd2).getOriginTextFields()[1],
                    boxSMS.get(rnd3).getOriginTextFields()[2]
            );


        }
        //System.out.println("genBoxSMS = " + genBoxSMS.size());
        tfIDSMS.setText("1");
        System.out.println(genBoxSMS.get(1).getGenerateText());
        mainDisplay.setText(
                tfRecipient.getText() +
                genBoxSMS.get(1).getGenTextFields()[0] + "" +
                genBoxSMS.get(1).getGenTextFields()[1] + "" +
                genBoxSMS.get(1).getGenTextFields()[2]+ "" +
                        "\n\n                                                         " + tfUser.getText()
        );

    }

}
