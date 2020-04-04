package br.com.ferracini.utils;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author lferracini
 * @project = docker-validation
 * @since <pre>03/04/2020</pre>
 */
public class DockerUtils {

    public static void startDocker() {
        try {
            Runtime.getRuntime().exec("cmd /k cd scripts  & start start-docker-compose.bat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopDocker() {
        try {
            Runtime.getRuntime().exec("cmd /k cd scripts  & start stop-docker-compose.bat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void start() throws IOException, InterruptedException {

        boolean flag = false;
        startDocker();

        String f = "output-run.log";

        Calendar cal = Calendar.getInstance();//2:44 15th second
        cal.add(Calendar.SECOND, 45);//2:44   45seconds
        long stopnow = cal.getTimeInMillis();
        Thread.sleep(3000);

        while (System.currentTimeMillis() < stopnow) {
            if (flag) {
                break;
            }

            var reader = new BufferedReader(new FileReader(f));
            var currentLine = reader.readLine();
            while (currentLine != null && !flag) {

                if (currentLine.contains("registered to the hub and ready to use")) {
                    System.out.println("found my text");
                    flag = true;//14th seconds
                    break;
                }

                currentLine = reader.readLine();
            }
            reader.close();

        }

        Assert.assertTrue(flag);
        Thread.sleep(15000);
    }

    public static void stop() throws IOException, InterruptedException {

        boolean flag = false;

        stopDocker();

        String f = "output-run.log";

        Calendar cal = Calendar.getInstance();//2:44 15th second
        cal.add(Calendar.SECOND, 45);//2:44   45seconds
        long stopnow = cal.getTimeInMillis();
        Thread.sleep(3000);

        while (System.currentTimeMillis() < stopnow) {
            if (flag) {
                break;
            }

            BufferedReader reader = new BufferedReader(new FileReader(f));
            String currentLine = reader.readLine();
            while (currentLine != null && !flag) {

                if (currentLine.contains("selenium-hub exited")) {
                    System.out.println("found my text");
                    flag = true;//14th seconds
                    break;
                }

                currentLine = reader.readLine();
            }
            reader.close();
        }
        Assert.assertTrue(flag);
    }

    public static void main(String[] args) throws InterruptedException {
        startDocker();
        Thread.sleep(5000);
        stopDocker();
    }
}
