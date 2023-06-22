public class RNMoff {
    public CurrentF cf;
    public double[] S = new double[2];

    public RNMoff(CurrentF cf) {
        this.cf = cf;
    }

    public boolean rnmOf() {
        double phiMax = cf.polarFormZeroSequence[0][0] - 65 + 180;
        double angleMin = phiMax - 90;
        double angleMax = phiMax + 90;
        S[0] = cf.polarFormZeroSequence[0][0] + cf.polarFormZeroSequence[0][1]; // argument
        double i0min = 0.0258;
        return (S[0] > angleMin && S[0] < angleMax) && (cf.polarFormZeroSequence[1][1] > i0min);
    }
}
