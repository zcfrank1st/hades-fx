package com.chaos.fx.hades.store.strategy;

import com.chaos.fx.hades.model.InfoMeta;

/**
 * Created by zcfrank1st on 29/12/2016.
 */
public interface StoreStrategy {

    InfoMeta readStore() throws Exception;

    void writeStore(String userName, String privateKey, String projectName, String projectPath) throws Exception;
}
