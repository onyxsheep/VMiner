import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.TilePath;

import java.util.Random;

public class Mine extends Task<ClientContext> {
    private final static int[] copperRockIDs = {11960, 11961, 11962};
    private int copperOre = 436;
    public Random randomInt = new Random();
    public int randomDelay = randomInt.nextInt(1000) + 420;
    private final Tile[] tileToMine = new Tile[] {
            new Tile(3254, 3423, 0),
            new Tile(3261, 3431, 0),
            new Tile(3271, 3428, 0),
            new Tile(3281, 3426, 0),
            new Tile(3288, 3418, 0),
            new Tile(3291, 3408, 0),
            new Tile(3294, 3398, 0),
            new Tile(3294, 3388, 0),
            new Tile(3293, 3378, 0),
            new Tile(3285, 3371, 0)
    };
    TilePath pathToMine = new TilePath(ctx, tileToMine);

    public Mine(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
               return ctx.backpack.select().count() < 28 &&
               ctx.players.local().animation() == -1;
    }


    @Override
    public void execute() {
        GameObject copperRocks = ctx.objects.id(copperRockIDs).nearest().poll();

        if (copperRocks.inViewport()) {
            copperRocks.interact("Mine");
            try { Thread.sleep(randomDelay); }
            catch (InterruptedException e){ System.out.println("failed. Exception: " + e); }
            System.out.println("Mining Copper Rocks.");
        }  else if(!copperRocks.inViewport()) {
            pathToMine.traverse();
            ctx.movement.step(copperRocks);
            System.out.println("Pathing to Copper Rocks.");
            try { Thread.sleep(randomDelay); }
            catch (InterruptedException e){ System.out.println("failed. Exception: " + e); }
        }



    }
}