package actors;

import main.*;

public class Tortoise extends Actor {

    public Tortoise(int initial_x, int initial_y){
        super(initial_x, initial_y);
        this.max_speed = 1;
    }

    @Override
    public void step(MovementVisitor visitor){
        visitor.move(this);  
    }

    @Override
    public String toString(){
        String rep = " T ";
        return  rep;
    }

    
}
