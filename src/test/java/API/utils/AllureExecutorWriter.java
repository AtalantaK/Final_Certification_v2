package API.utils;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class AllureExecutorWriter {
    public static void createExecutorFile() {
        JSONObject executor = new JSONObject();
        executor.put("name", "GitHub Actions");
        executor.put("type", "github");
        executor.put("url", "https://github.com/AtalantaK/Final_Certification_v2/actions");
//        executor.put("buildOrder", 101);
//        executor.put("buildName", "CI Build #101");
//        executor.put("buildUrl", "https://github.com/AtalantaK/Final_Certification_v2/actions/runs/101");
        executor.put("reportUrl", "https://atalantak.github.io/Final_Certification_v2/");
        executor.put("buildStatus", "SUCCESS");

        try (FileWriter file = new FileWriter("target/allure-results/executor.json")) {
            file.write(executor.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
