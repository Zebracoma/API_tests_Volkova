package api_dadata.dto;

public class SuggestRequest {
    private String query;

    public SuggestRequest(String query) {
        this.query = query;
    }

    public String getQuery() { return query; }
}
