package actors;

import java.util.Random;
import main.Position;

public class Rabbit extends Actor{

    private int energy_level;

    public Rabbit(int initial_x, int initial_y){
        super(initial_x, initial_y);
        this.max_speed = 2;
        this.energy_level = 3;
    }

    @Override
    public void step(Position lower_bound, Position upper_bound){
        Random r = new Random();
        int random_x = 0;
        int random_y = 0;
        boolean straight = false;
        if (this.energy_level > 0){            
            while (!straight) { 
                random_x = r.nextInt(-this.max_speed, this.max_speed+1);
                random_y = r.nextInt(-this.max_speed, this.max_speed+1);
                if (random_x==0 && random_y!=0){
                    straight = true;
                } else if(random_x!=0 && random_y==0){
                    straight = true;
                }
            }
            
            this.current_position =this.current_position.moveBy(random_x, random_y);
            this.current_position = this.current_position.fitBoundries(lower_bound.getPositionX(), lower_bound.getPositionY(), upper_bound.getPositionX(), upper_bound.getPositionY());   
            this.energy_level -= 1;
        }else {
            this.energy_level = 3;
        }
    }

    
    

    @Override
    public String toString(){
        String rep = " R ";
        return rep;
    }
}
