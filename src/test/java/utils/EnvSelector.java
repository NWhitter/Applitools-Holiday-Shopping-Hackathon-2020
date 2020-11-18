package utils;

import exceptions.InvalidEnvironmentException;

public class EnvSelector {
    private static final String baseUrl = "https://demo.applitools.com";

    public static String getEnvironment(String env) {
        return switch (env.toUpperCase()) {
            case "DEV" -> baseUrl + "/tlcHackathonDev.html";
            case "V1" -> baseUrl + "/tlcHackathonMasterV1.html";
            case "V2" -> baseUrl + "/tlcHackathonMasterV2.html";
            default -> throw new InvalidEnvironmentException("Invalid environment", env);
        };
    }
}
