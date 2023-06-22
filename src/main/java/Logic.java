public class Logic {
    public CurrentF currentF;
    private Timout timout = new Timout();
    private OutputData od = new OutputData();
    private boolean[] steps = new boolean[5];
    private double[] ust = {1.95, 1.43, 0.78, 0.216, 0.0258};
    private boolean flag;
    private boolean[] flags = new boolean[5];
    public static  int count2 = 0;

    public Logic(CurrentF cF) {
        this.currentF = cF;
    }

    public void log() {
        if (++ count2 > 5) count2 = 0;
        if (count2 == 4) createUst();

        RNMon rnMon = new RNMon(currentF);
        RNMoff rnMoff = new RNMoff(currentF);
        flag = rnMon.rnmOn() && !rnMoff.rnmOf();

        tripSteps();
        stepsLogic();
        timout.timers();

        od.process(flags);
    }

    private void tripSteps() {
        // Проверка превышения уставки ступенями
        for (int i = 0; i < 5; i++) {
            steps[i] = currentF.polarFormZeroSequence[1][1] > ust[i];
        }
    }


    private void stepsLogic() {
        for (int i = 0; i < 5; i++) {
            if (i < 3) {
                if (steps[i] && !flags[i]) {
                    //System.out.println("Сработала " + i + " ступень");
                    // Проверка ОНМ
                    if (flag) {
                        //System.out.println("Запущена " + i + " ступень");
                        timout.flags[i] = true;
                    }
                    if (timout.counts[i] == (timout.timeouts[i] * 4)) {
                        System.out.println("Ступень " + (i + 1) + " сработала");
                        flags[i] = true;

                    }
                } else {
                    timout.flags[i] = false;
                    timout.counts[i] = 0;
                }
            } else {
                if (steps[i] && !flags[i]) {
                    //System.out.println("Сработала " + i + " ступень");
                    //Пуск выдержки времени ступени
                    timout.flags[i] = true;
                    if (timout.counts[i] == (timout.timeouts[i] * 4)) {
                        System.out.println("Ступень " + (i + 1) + " сработала");
                        flags[i] = true;

                    }
                } else {
                    timout.flags[i] = false;
                    timout.counts[i] = 0;
                }
            }
        }
    }

    private void createUst() {
        Charts.addAnalogData(6, 1, ust[0]);
        Charts.addAnalogData(6, 2, ust[1]);
        Charts.addAnalogData(6, 3, ust[2]);
        Charts.addAnalogData(6, 4, ust[3]);
        Charts.addAnalogData(6, 5, ust[4]);
        if (flag) {
            Charts2.addAnalogData(2, 0, 1);
        } else {
            Charts2.addAnalogData(2, 0, 0);
        }
    }

}

