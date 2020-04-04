package br.com.ferracini.utils;

import java.io.IOException;

/**
 * @author lferracini
 * @project = docker-validation
 * @since <pre>03/04/2020</pre>
 */
public class DockerUtils {

    public static void startDocker(){
        try {
            Runtime.getRuntime().exec("cmd /k cd scripts  & start start-docker-compose.bat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopDocker(){
        try {
            Runtime.getRuntime().exec("cmd /k cd scripts  & start stop-docker-compose.bat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        startDocker();
        Thread.sleep(5000);
        stopDocker();
    }
}
