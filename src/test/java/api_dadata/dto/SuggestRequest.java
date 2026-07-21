package api_dadata.dto;

public class SuggestRequest {
    private final String query;

    public SuggestRequest(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
