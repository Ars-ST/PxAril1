public class BoxSMS {

    private static String user;                                         //Пользователь, он же отправитель

    private int indexSMS;                                               //индекс сообщения
    private String recipient;                                           //Получатель
    private String GenerateText;                                        //Сгенерированный тест
    private String[] GenTextFields = new String[3];                     //Ячейки для сгенерированного текста
    private String[] OriginTextFields = {"", "", ""};                   //Ячейки для оригинального текста

    public BoxSMS(int indexSMS, String recipient, String sms1, String sms2, String sms3) {
        this.indexSMS = indexSMS;
        this.recipient = recipient;
        OriginTextFields[0] = sms1;
        OriginTextFields[1] = sms2;
        OriginTextFields[2] = sms3;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        BoxSMS.user = user;
    }

    public int getIndexSMS() {
        return indexSMS;
    }

    public void setIndexSMS(int indexSMS) {
        this.indexSMS = indexSMS;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getGenerateText() {
        return GenerateText;
    }

    public void setGenerateText(String generateText) {
        GenerateText = generateText;
    }

    public String[] getGenTextFields() {
        return GenTextFields;
    }

    public void setGenTextFields(String str1, String str2, String str3) {
        GenTextFields[0] = str1;
        GenTextFields[1] = str2;
        GenTextFields[2] = str3;
    }

    public String[] getOriginTextFields() {
        return OriginTextFields;
    }

    public void setOriginTextFields(String str1, String str2, String str3) {
//        OriginTextFields = originTextFields;
        OriginTextFields[0] = str1;
        OriginTextFields[1] = str2;
        OriginTextFields[2] = str3;

    }
}
