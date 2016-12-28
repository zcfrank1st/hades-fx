package com.chaos.fx.hades.util;

import com.chaos.fx.hades.model.ConfigContent;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by zcfrank1st on 28/12/2016.
 */
public class Api {
    private static final String API_SERVER = Conf.conf.getString("hades.apiserver");
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static Response exists (String privateKey, String userName, String projectName, String projectPath) throws IOException {
        Request existsRequest = new Request.Builder()
                .url(API_SERVER + "config/" + projectName)
                .addHeader("token", privateKey)
                .addHeader("projectpath", projectPath)
                .addHeader("username", userName)
                .build();

        return client.newCall(existsRequest).execute();
    }

    public static Response init (String privateKey, String userName, String projectName, String projectPath) throws IOException {
        Request initRequest = new Request.Builder()
                .url(API_SERVER + "config/skeleton")
                .addHeader("token", privateKey)
                .addHeader("projectpath", projectPath)
                .addHeader("username", userName)
                .post(RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), projectName))
                .build();

        return client.newCall(initRequest).execute();
    }


    public static Response addOne (String privateKey, String userName, ConfigContent configContent, String projectPath) throws IOException {
        Request addOneRequest = new Request.Builder()
                .url(API_SERVER + "config/")
                .addHeader("token", privateKey)
                .addHeader("projectpath", projectPath)
                .addHeader("username", userName)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(configContent)))
                .build();

        return client.newCall(addOneRequest).execute();
    }

    public static Response deleteAll (String privateKey, String userName, String projectName, String projectPath) throws IOException {
        Request deleteAllRequest = new Request.Builder()
                .url(API_SERVER + "config/skeleton/" + projectName)
                .addHeader("token", privateKey)
                .addHeader("projectpath", projectPath)
                .addHeader("username", userName)
                .delete()
                .build();

        return client.newCall(deleteAllRequest).execute();
    }

    public static Response getAllThroughProfile (String privateKey, String userName, String projectName, Integer env, String projectPath) throws IOException {
        Request getAllRequest = new Request.Builder()
                .url(API_SERVER + "config?project=" + projectName + "&env=" + env)
                .addHeader("token", privateKey)
                .addHeader("projectpath", projectPath)
                .addHeader("username", userName)
                .build();

        return client.newCall(getAllRequest).execute();
    }

    public static Response deleteOne (String privateKey, String userName, String projectName, Integer env, String key, String projectPath) throws IOException {
        Request deleteOneRequest = new Request.Builder()
                .url(API_SERVER + "config/" + projectName + "/" + env + "/" + key + "/")
                .addHeader("token", privateKey)
                .addHeader("projectpath", projectPath)
                .addHeader("username", userName)
                .delete()
                .build();

        return client.newCall(deleteOneRequest).execute();
    }
}
