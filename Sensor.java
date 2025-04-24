public abstract class Sensor{
    private double alcance;

    Sensor(double alcance){
        this.alcance = alcance;
    }

    double getAlcance(){
        return this.alcance;
    }

    public abstract void monitorar();
}