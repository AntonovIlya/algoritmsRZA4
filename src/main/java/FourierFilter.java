public class FourierFilter {
    private CurrentData cd;
    public CurrentF currentF = new CurrentF();
    private Logic logic = new Logic(currentF);
    private int step = 80;
    private double[][] phABC = new double[6][step]; //arrays current selections
    private short count = 0;

    public FourierFilter(CurrentData cd) {
        this.cd = cd;
    }

    public void processing() {
        for (int i = 0; i < 6; i++) {
            phABC[i][count] = cd.phABC[i];
        }
        fourier50(phABC);
        logic.log();
        if (++count >= step) count = 0;
    }

    //Discrete Fourier transform 1st harmonic
    private void fourier50(double[][] mas) {
        double[] Fx = new double[6];
        double[] Fy = new double[6];
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < step; i++) {
                Fx[j] += 0.025 * Math.sin(0.025 * i * Math.PI) * mas[j][i];
                Fy[j] += 0.025 * Math.cos(0.025 * i * Math.PI) * mas[j][i];

            }
            double F = Math.sqrt((Math.pow(Fx[j], 2) + Math.pow(Fy[j], 2)) * 0.5); // module
            Charts.addAnalogData(j, 1, F);

        }
        currentF.setpolarFormZeroSequence(polarForm(Fx, Fy));
    }

    private double[][] polarForm(double[] Fx, double[] Fy) {
        double[][] F = new double[2][6];
        // Convert from Exponential to Polar form (Uab, Ubc, Uca, Iab, Ibc, Ica)
        for (int j = 0; j < 6; j++) {
            F[0][j] = Math.toDegrees(Math.atan2(Fy[j], Fx[j])); // argument
            F[1][j] = Math.sqrt((Math.pow(Fx[j], 2) + Math.pow(Fy[j], 2)) * 0.5); // module
        }
        return zeroSequence(F);
    }

    // addition three currents in polar form
    private double[][] zeroSequence(double[][] F) {
        double[][] solve = new double[2][2];
        double[] Fx = {0, 0};
        double[] Fy = {0, 0};
        // voltage
        for (int i = 0; i < 3; i++) {
            Fx[0] += Math.cos(Math.toRadians(F[0][i])) * F[1][i];
            Fy[0] += Math.sin(Math.toRadians(F[0][i])) * F[1][i];
        }
        solve[0][0] = Math.toDegrees(Math.atan2(Fy[0], Fx[0])); // argument
        solve[1][0] = Math.sqrt(Math.pow(Fx[0], 2) + Math.pow(Fy[0], 2)); // module
        // current
        for (int i = 3; i < 6; i++) {
            Fx[1] += Math.cos(Math.toRadians(F[0][i])) * F[1][i];
            Fy[1] += Math.sin(Math.toRadians(F[0][i])) * F[1][i];
        }
        solve[0][1] = Math.toDegrees(Math.atan2(Fy[1], Fx[1])); // argument
        solve[1][1] = Math.sqrt(Math.pow(Fx[1], 2) + Math.pow(Fy[1], 2)); // module
        if (Logic.count2 == 4) Charts.addAnalogData(6, 0, solve[1][1]);
        return solve;
    }

}
