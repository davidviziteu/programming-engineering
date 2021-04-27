package CityGenerating;

public class TrafficLights extends Thread{
    public enum StareSemafor { Green, YellowGreen, Red, YellowRed;

        static
        public final StareSemafor[] values=values();
        public StareSemafor prev() {
            return values[(ordinal() - 1  + values.length) % values.length];
        }

        public StareSemafor next() {
            return values[(ordinal() + 1) % values.length];
        }};
    private StareSemafor stare;
    private Integer timeMax,timer,poz;

    TrafficLights(Integer timeMax, int st, int poz) {
        this.timeMax = timeMax;
        this.timer = timeMax;
        if (st == 1) {
            this.stare = StareSemafor.Green;
        }
        if (st == 0) {
            this.stare = StareSemafor.Red;
        }
        this.poz = poz;
    }

    public StareSemafor getStare() {
        return stare;
    }

    public void setStare(StareSemafor stare) {
        this.stare = stare;
    }

    public Integer getTimeMax() {
        return timeMax;
    }

    public void setTimeMax(Integer timeMax) {
        this.timeMax = timeMax;
    }

    public Integer getPoz() {
        return poz;
    }

    public void setPoz(Integer poz) {
        this.poz = poz;
    }

    public StareSemafor getColor() {
        return stare;
    }

    public void setColor(StareSemafor color) {
        stare = color;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public void setMaxTime(Integer time) {
        this.timeMax = time;
    }

    public Integer getMaxTime(){
        return timeMax;
    }

    @Override
    public String toString() {
        return "TrafficLights{" +
                "stare=" + stare +
                ", timeMax=" + timeMax +
                ", timer=" + timer +
                ", poz=" + poz +
                '}';
    }


}
