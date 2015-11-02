package EventHandler;

import com.squareup.otto.Bus;

/**
 * Created by rob2cool on 9/8/15.
 */
public final class BUS {
    private static final Bus bus= new Bus();
    public static Bus  getInstance(){

        return bus;
    }
    private BUS(){

    }

}
