package com.chaos.fx.hades.store;

import com.chaos.fx.hades.store.strategy.FileStoreStrategy;
import com.chaos.fx.hades.store.strategy.H2StoreStrategy;
import com.chaos.fx.hades.store.strategy.StoreStrategy;
import com.chaos.fx.hades.util.Conf;

import java.sql.SQLException;

/**
 * Created by zcfrank1st on 29/12/2016.
 */
public class StrategyFactory {
    private static final String STRATEGY_ENGINE = Conf.conf.getString("hades.datastoreengine");

    public static StoreStrategy buildStrategy () throws SQLException, ClassNotFoundException {
        if ("h2".equals(STRATEGY_ENGINE)) {
            return new H2StoreStrategy();
        }

        // file store engine default
        return new FileStoreStrategy();
    }
}
