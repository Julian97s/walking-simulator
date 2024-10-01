package actors;

import java.util.Random;
import main.*;

public class Tortoise extends Actor {

    public Tortoise(int initial_x, int initial_y){
        super(initial_x, initial_y);
        this.max_speed = 1;
    }

    @Override
    public void step(Position lower_bound, Position upper_bound){
        Random r = new Random();
        int random_x=0;
        int random_y=0;
        while (random_x == 0 && random_y == 0){
            random_x = r.nextInt(-this.max_speed, this.max_speed+1);
            random_y = r.nextInt(-this.max_speed, this.max_speed+1);
        } 
        this.current_position =this.current_position.moveBy(random_x, random_y);
        this.current_position = this.current_position.fitBoundries(lower_bound.getPositionX(), lower_bound.getPositionY(), upper_bound.getPositionX(), upper_bound.getPositionY());   
    }

    @Override
    public String toString(){
        String rep = " T ";
        return  rep;
    }

    
}
