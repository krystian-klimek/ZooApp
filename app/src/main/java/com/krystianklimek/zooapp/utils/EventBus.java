package com.krystianklimek.zooapp.utils;

import com.squareup.otto.Bus;

/**
 * Created by: Krystian Klimek
 * Date: 24.05.2016.
 */

public class EventBus extends Bus {
    private static final EventBus BUS = new EventBus();

    public static Bus getInstance() {
        return BUS;
    }

    private EventBus() {}
}
