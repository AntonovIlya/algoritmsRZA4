public class OutputData {

    public void process(boolean[] flags) {
        if (Logic.count2 == 4) {
            for (int i = 0; i < flags.length; i++) {
                if (flags[i]) {
                    Charts2.addAnalogData((i + 3), 0, 1);
                } else {
                    Charts2.addAnalogData((i + 3), 0, 0);
                }
            }
        }
    }
}
