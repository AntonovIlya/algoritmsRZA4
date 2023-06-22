public class Main {
    private static InputData inputData = new InputData();

    public static void main(String[] args) {
        System.out.println(Time.getCurrentHour());
        createCharts();
        inputData.readFile();
        System.out.println("Время работы программы: " + Time.getCurrentHour() + " мс");
    }

    private static void createCharts() {
        Charts.createAnalogChart("UA", 0);
        Charts.addSeries("UA, кV", 0, 0);
        Charts.addSeries("UAModule, kV", 0, 1);

        Charts.createAnalogChart("UB", 1);
        Charts.addSeries("UB, kV", 1, 0);
        Charts.addSeries("UBModule, kV", 1, 1);

        Charts.createAnalogChart("UC", 2);
        Charts.addSeries("UC, kV", 2, 0);
        Charts.addSeries("UCModule, kV", 2, 1);

        Charts.createAnalogChart("IA", 3);
        Charts.addSeries("IA, kA", 3, 0);
        Charts.addSeries("IAModule, kA", 3, 1);

        Charts.createAnalogChart("IB", 4);
        Charts.addSeries("IB, kA", 4, 0);
        Charts.addSeries("IBModule, kA", 4, 1);

        Charts.createAnalogChart("IC", 5);
        Charts.addSeries("IC, kA", 5, 0);
        Charts.addSeries("ICModule, kA", 5, 1);

        Charts.createAnalogChart("I0", 6);
        Charts.addSeries("I0, кA", 6, 1);
        Charts.addSeries("I(I), кA", 6, 2);
        Charts.addSeries("I(II), кA", 6, 3);
        Charts.addSeries("I(III), кA", 6, 4);
        Charts.addSeries("I(IV), кA", 6, 5);
        Charts.addSeries("I(V), кA", 6, 6);

        Charts2.createAnalogChart("S0", 0);
        Charts2.addSeries("S0, кВА", 0, 0);

        Charts2.createAnalogChart("S0, angle", 1);
        Charts2.addSeries("Angle, град", 1, 0);

        Charts2.createAnalogChart("ОНМ", 2);
        Charts2.addSeries("true, false",2, 0);

        Charts2.createAnalogChart("Trip_1", 3);
        Charts2.addSeries("trip", 3, 0);

        Charts2.createAnalogChart("Trip_2", 4);
        Charts2.addSeries("trip", 4, 0);

        Charts2.createAnalogChart("Trip_3", 5);
        Charts2.addSeries("trip", 5, 0);

        Charts2.createAnalogChart("Trip_4", 6);
        Charts2.addSeries("trip", 6, 0);

        Charts2.createAnalogChart("Trip_5", 7);
        Charts2.addSeries("trip", 7, 0);

    }

}
