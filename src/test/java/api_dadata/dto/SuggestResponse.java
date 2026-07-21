package api_dadata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuggestResponse {
    private List<AddressLocation> suggestions;

    public SuggestResponse() {
    }

    public List<AddressLocation> getSuggestions() {
        return suggestions;
    }
}
