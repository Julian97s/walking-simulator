package actors;

import main.MovementVisitor;

public class Rabbit extends Actor{

    private int energy_level;

    public Rabbit(int initial_x, int initial_y){
        super(initial_x, initial_y);
        this.max_speed = 2;
        this.energy_level = 3;
    }

    @Override
    public void step(MovementVisitor visitor){
        visitor.move(this); // this passes the actual rabbit?

    }

    public boolean burnEnergy(){
        if (this.energy_level <= 0) {
            this.energy_level = 3;
            return false;
        } else {
            this.energy_level--;
            return true;
        }
    }

    public boolean is_rabbit(Actor a){
        if (a.getClass().equals(this.getClass())){
            return true;
        }
        return false;
    }
    

    @Override
    public String toString(){
        String rep = " R ";
        return rep;
    }
}
