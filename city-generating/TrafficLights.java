package city-generating;

public class TrafficLights {
    private enum StareSemafor { Green, YellowGreen, Red, YellowRed};
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

    // Decrements a light time, implemented with threads
    // so the lights can me changed from different threads.
    public synchronized void decrementTime(){
        System.out.println("We are decrementing");
        if(timer>0)
        {
            timer-=1;
        }else{
            timer=timeMax;
            stare=StareSemafor.values()[poz];
            poz=(poz+1)%4;
        }
        System.out.println("The new value is " + timer);
    }

    @Override
    public void run(){
        decrementTime();
    }

}
