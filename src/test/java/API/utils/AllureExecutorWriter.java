package API.utils;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class AllureExecutorWriter {
    public static void createExecutorFile() {

        String repo = System.getenv("GITHUB_REPOSITORY");
        String runId = System.getenv("GITHUB_RUN_ID");
        String runNumber = System.getenv("GITHUB_RUN_NUMBER");
        String serverUrl = System.getenv("GITHUB_SERVER_URL");
        String buildStatus = System.getenv("BUILD_STATUS"); // берём из GITHUB_ENV - maven.yml

        JSONObject executor = new JSONObject();
        executor.put("name", "GitHub Actions");
        executor.put("type", "github");
        executor.put("url", serverUrl + "/" + repo + "/actions");
        executor.put("buildOrder", Integer.parseInt(runNumber));
        executor.put("buildName", "Build #" + runNumber);
        executor.put("buildUrl", serverUrl + "/" + repo + "/actions/runs/" + runId);
        executor.put("reportUrl", "https://atalantak.github.io/Final_Certification_v2/");
        executor.put("buildStatus", buildStatus);

        try (FileWriter file = new FileWriter("target/allure-results/executor.json")) {
            file.write(executor.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
