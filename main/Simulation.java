package main;
import actors.Actor;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private int max_width;
    private int max_height;
    List<Actor> actors;

    public Simulation(int width, int length){
        this.max_width = width;
        this.max_height = length;
        actors = new ArrayList<>();
    }

    public void addActor(){
        Actor new_actor = new Actor(1,1);
        this.actors.add(new_actor);
    }

    public void step(){
        // indtsnciste lower snd upper bound snd pass yhodr as parameters
        Position lower_bound = new Position(0,0);
        Position upper_bound = new Position(9,9);
        for(Actor actor : this.actors){
            actor.step(lower_bound, upper_bound); // got lost here, im not understanding the boundries.
        }
    }

    @Override
    public String toString(){
        String[][] output = new String[this.max_width][this.max_height];
        for(Actor actor: this.actors){
             output[actor.getPositionX()][actor.getPositionY()] = actor.toString(); 
        }
        String result = "";
        for (int i=0; i<this.max_width-1;i++){
            for (int j=0; j<this.max_height-1; j++){
                if(output[i][j]==null){
                    result = result + "-";
                } else {
                    result = result + output[i][j];
                }
            }
            result = result + "\n";
        }
        return result;
    }

}
