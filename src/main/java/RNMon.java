public class RNMon {
    public CurrentF cf;
    public double[] S = new double[2];

    public RNMon(CurrentF cf) {
        this.cf = cf;
    }

    public boolean rnmOn() {
        double phiMax = cf.polarFormZeroSequence[0][0] - 65;
        double angleMin = phiMax - 90;
        double angleMax = phiMax + 90;
        S[0] = cf.polarFormZeroSequence[0][0] + cf.polarFormZeroSequence[0][1]; // argument
        S[1] = cf.polarFormZeroSequence[1][0] * cf.polarFormZeroSequence[1][1]; // module
        if (Logic.count2 == 4) {
            Charts2.addAnalogData(0, 0, S[1] * 1000);
            Charts2.addAnalogData(1, 0, S[0]);
        }
        double i0min = 0.0258;
        return (S[0] > angleMin && S[0] < angleMax) && (cf.polarFormZeroSequence[1][1] > i0min);
    }
}
