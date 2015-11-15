import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* copper ore = 11960 - 11962
*  tin ore = 11957 - 11959 */
@Script.Manifest(name = "VEMiner", description = "Mines & Banks in Varrock-East")
public class VEMiner extends PollingScript<ClientContext> {
    private List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start () {
        System.out.println("SUCCESFULLY STARTED.");
        taskList.addAll(Arrays.asList(new Mine(ctx), new Bank(ctx)));
    }


    @Override
    public void poll() {
        for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
            }
        }

    }

    @Override
    public void stop() {
        System.out.println("STOPPING SCRIPT.");
    }


}
