import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.TilePath;

import java.util.Random;

public class Bank extends Task<ClientContext>{
    private final static int bankBoothID = 782;
    GameObject bankBooth = ctx.objects.id(bankBoothID).nearest().poll();
    private final Tile[] tileToBank = new Tile[] {
            new Tile(3285, 3365, 0),
            new Tile(3291, 3373, 0),
            new Tile(3293, 3383, 0),
            new Tile(3291, 3393, 0),
            new Tile(3289, 3403, 0),
            new Tile(3291, 3413, 0),
            new Tile(3289, 3423, 0),
            new Tile(3282, 3431, 0),
            new Tile(3273, 3426, 0),
            new Tile(3263, 3429, 0),
            new Tile(3253, 3428, 0),
            new Tile(3255, 3419, 0)
    };

    TilePath pathToBank = new TilePath(ctx, tileToBank);

    public Random randomInt = new Random();
    public int randomDelay = randomInt.nextInt(500) + 420;


    public Bank(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28;

    }
    @Override
    public void execute() {

        if (bankBooth.inViewport()) {
            ctx.bank.open();
            ctx.bank.depositInventory();
            ctx.bank.close();
            try { Thread.sleep(randomDelay + 1000); }
            catch (InterruptedException e){ System.out.println("failed. Exception: " + e); }
            System.out.println("Banking Copper Rocks.");
        } else {

          pathToBank.traverse();
          try { Thread.sleep(randomDelay + 132); }
          catch (InterruptedException e){ System.out.println("failed. Exception: " + e); }
          System.out.println("Going to Bank.");

        }



    }
}
