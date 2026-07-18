package api_dadata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FioSuggestion {
    private String value;
    private FioData data;

    public String getValue() {
        return value;
    }

    public FioData getData() {
        return data;
    }
}