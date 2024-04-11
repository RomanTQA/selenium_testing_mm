package CoreSettings;

public class TestData {
    private static String testName = "Ivan Fedorovich Kruzenshtern";
    private static String testEmail = "seven@rounds.test.ctb";
    private static String testPhone = "9656885574";
    private static String testCity = "Москва";
    private static String testAddress = "Красная площадь, 1";

    private static String testPassword = "B123456b";



    private static String stringFakerFlag = "FakeTest";     //это для будущих тестов, чтобы искать по флагу сгенерированное

    private static int typeDelay = 20;    //для human-like инпута - задержка между вбиванием символов
    private static int updElemDelay = 250;   //тех пауза (мс) для апдейта состояния элемента движком страницы.



    private static int waitForElem = 10000;   //тех интервал (мс) для ожидания элемента на странице

    public static int getWaitForElem() {
        return waitForElem;
    }
    public static void setWaitForElem(int waitForElem) {
        TestData.waitForElem = waitForElem;
    }



    public static int getUpdElemDelay() {
        return updElemDelay;
    }

    public static void setUpdElemDelay(int updElemDelay) {
        TestData.updElemDelay = updElemDelay;
    }



    public static int getTypeDelay() {
        return typeDelay;
    }

    public void setTypeDelay(int typeDelay) {
        TestData.typeDelay = typeDelay;
    }




    public static String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        TestData.testName = testName;
    }

    public static String getTestEmail() {
        return testEmail;
    }

    public void setTestEmail(String testEmail) {
        TestData.testEmail = testEmail;
    }

    public static String getTestPhone() {
        return testPhone;
    }

    public void setTestPhone(String testPhone) {
        TestData.testPhone = testPhone;
    }

    public static String getTestCity() {
        return testCity;
    }

    public void setTestCity(String testCity) {
        TestData.testCity = testCity;
    }

    public static String getTestAddress() {
        return testAddress;
    }

    public void setTestAddress(String testAddress) {
        TestData.testAddress = testAddress;
    }

    public static String getTestPassword() {
        return testPassword;
    }

    public void setTestPassword(String testPassword) {
        TestData.testPassword = testPassword;
    }

    public static String getStringFakerFlag() {
        return stringFakerFlag;
    }

    public static void setStringFakerFlag(String stringFakerFlag) {
        TestData.stringFakerFlag = stringFakerFlag;
    }


}
