package com.chaos.fx.hades.store.strategy;

import com.chaos.fx.hades.model.InfoMeta;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by zcfrank1st on 29/12/2016.
 */
public class FileStoreStrategy implements StoreStrategy {
    private static final String STORE_FILE = System.getProperty("user.dir") + "/hadesfx.yml";

    @Override
    public InfoMeta readStore() throws IOException {
        if (!Files.exists(Paths.get(STORE_FILE))) {
            return null;
        }

        Yaml yaml = new Yaml();
        return yaml.loadAs(Files.newInputStream(Paths.get(STORE_FILE)), InfoMeta.class);
    }

    @Override
    public void writeStore(String userName, String privateKey, String projectName, String projectPath) throws IOException {
        Yaml yaml = new Yaml();
        InfoMeta meta = new InfoMeta();
        meta.setUserName(userName);
        meta.setPrivateKey(privateKey);
        meta.setProjectName(projectName);
        meta.setProjectPath(projectPath);
        yaml.dump(meta, new FileWriter(new File(STORE_FILE)));
    }
}
