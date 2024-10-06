package main;
import actors.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private MovementVisitor visitor;
    private Position upperBound;
    private Position lowerBound;
    private List<Actor> actors;

    public Simulation(int width, int length){
        this.lowerBound = new Position(0,0);
        this.upperBound = new Position(width,length);
        actors = new ArrayList<>();
        this.visitor = new MovementVisitor(this.lowerBound, this.upperBound);
        this.addPrices();
        this.upperBound = new Position(width, length);
        this.lowerBound = new Position(0,0);
        
    }

    public void addActor(){
        Tortoise new_tortoise = new Tortoise(1,1);
        Rabbit new_rabbit = new Rabbit(2, 2);
        this.actors.add(new_tortoise);
        this.actors.add(new_rabbit);
    }

    //  Updates the simulation by one frame
    public void step(){
        if (this.visitor.getPricesCount() == 0){
            this.addPrices();
        }
        for(Actor actor : this.actors){
            actor.step(this.visitor); 
        }
    }

    @Override
    public String toString(){
        
        String rabbitScore = "rabbit score: "; 
        String tortoiseScore = "tortoise score: "; 
        String[][] output = new String[this.upperBound.getPositionX()][this.upperBound.getPositionY()];
        for(Actor actor: this.actors){
             output[actor.getPositionX()][actor.getPositionY()] = actor.toString();
             if (actor instanceof Rabbit){
                rabbitScore += actor.getScore();
             } else if (actor instanceof Tortoise){
                tortoiseScore += actor.getScore();
             }
             
        }
        String result = "";
        for (int i=0; i<=this.upperBound.getPositionX()-1;i++){
            for (int j=0; j<=this.upperBound.getPositionY()-1; j++){
                if(output[i][j]==null){
                    result = result + " - ";
                } else {
                    result = result + output[i][j];
                }
            }
            result = result + "\n";
        }
        // to display the scores of the tortoise and the rabbit, should i create a helper function that allows me to check for their clarr?
        //something like a is_rabbit or is_tortoise
        result += "\n" + rabbitScore + "\n" + tortoiseScore;
        return result;
    }

    private void addPrices(){
        if ( this.visitor.getPricesCount() == 0 ) {
            for (int i=0;i<3;i++){
                this.actors.add(this.visitor.addPrize());
            }
        }
    }

}
