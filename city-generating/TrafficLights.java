package CityGenerator;

public class TrafficLights {
    private enum StareSemafor {
        Green, YellowGreen, Red, YellowRed
    };

    private StareSemafor stare;
    private Integer timeMax, timer, poz;

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

    public StareSemafor getColor() {
        return stare;
    }

    public void setColor(StareSemafor color) {
        stare = color;
    }

    public Integer getTime() {
        return time;
    }

    public void setMaxTime(Integer time) {
        this.timeMax = time;
    }

    public void decrementTime() {
        if (timer > 0) {
            timer -= 1;
        } else {
            timer = timeMax;
            stare = StareSemafor[poz];
            poz = (poz + 1) % 4;
        }

    }
}
