package api_dadata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FioResponse {
    private List<FioSuggestion> suggestions;

    public List<FioSuggestion> getSuggestions() {
        return suggestions;
    }
}