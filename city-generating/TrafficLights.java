package CityGenerator;

public class TrafficLights {
    private enum StareSemafor { Green, YellowGreen, Red, YellowRed};
    private StareSemafor stare;
    private Integer timeMax,timer,poz;

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
    public void decrementTime(){
        if(timer>0)
        {
            timer-=1;
        }else{
            timer=timeMax;
            stare=StareSemafor[poz];
            poz=(poz+1)%4;
        }

    }

}
