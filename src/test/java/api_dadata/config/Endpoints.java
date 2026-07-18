package api_dadata.config;

public enum Endpoints {
    SUGGEST_ADDRESS("/suggest/address"),
    IP_LOCATE("/iplocate/address"),
    SUGGEST_FIO("/suggest/fio");

    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}