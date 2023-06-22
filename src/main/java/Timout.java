public class Timout {
    public int[] counts = {0, 0, 0, 0, 0};
    public int[] timeouts = {20, 100, 300, 500, 700};
    public boolean[] flags = new boolean[5];

    public void timers(){
        //run timer first step
        if (flags[0] && ++counts[0] > (timeouts[0] * 4)) {
            flags[0] = false;
            counts[0] = 0;
        }
        //run timer second step
        if (flags[1] && ++counts[1] > (timeouts[1] * 4)) {
            flags[1] = false;
            counts[1] = 0;
        }

        //run timer third step
        if (flags[2] && ++counts[2] > (timeouts[2] * 4)) {
            flags[2] = false;
            counts[2] = 0;
        }

        //run timer fourth step
        if (flags[3] && ++counts[3] > (timeouts[3] * 4)) {
            flags[3] = false;
            counts[3] = 0;
        }

        //run timer fifth step
        if (flags[4] && ++counts[4] > (timeouts[4] * 4)) {
            flags[4] = false;
            counts[4] = 0;
        }
    }
}
