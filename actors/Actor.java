package actors;
import java.util.Random;
import main.Position;

public class Actor {
    protected Position current_position;
    protected int max_speed;

    public Actor(int initial_x, int initial_y){
        current_position = new Position(initial_x,initial_y);
        max_speed = 1;
    }

    public int getPositionX(){
        return current_position.getPositionX();
    }
    
    public int getPositionY(){
        return current_position.getPrositonY();
    }

    public void step(Position lower_bound, Position upper_bound){

        // i didnt understand the instruction It must be given a lower bound and upper bound Position for its step.
        Random r = new Random();
        int random_x = r.nextInt(-this.max_speed, this.max_speed+1);
        int random_y = r.nextInt(-this.max_speed, this.max_speed+1);
        this.current_position =this.current_position.moveBy(random_x, random_y);
        this.current_position = this.current_position.fitBoundries(lower_bound.getPositionX(), lower_bound.getPrositonY(), upper_bound.getPositionX(), upper_bound.getPrositonY());
    }
    

    @Override
    public String toString(){
        String rep = " * ";
        return rep;
    }
}
