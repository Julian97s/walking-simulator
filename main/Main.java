package main;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Simulation game = new Simulation(10,10);
        game.addActor();
        while(true){
            Thread.sleep(1000);
            game.step();
            System.out.println(game);
        }
    }
}
