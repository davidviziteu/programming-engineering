
public class TrafficLights {
    private enum StareSemafor { Green, YellowGreen, YellowRed, Red};
    private StareSemafor stare;
    private Integer time;

    public StareSemafor getColor() {
        return stare;
    }

    public void setColor(StareSemafor color) {
        stare = color;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

}

