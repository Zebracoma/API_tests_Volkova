package api_dadata.config;

public class TestData {
    public static final String VALID_IP = ConfigProvider.get("test.ip");
    public static final String VALID_SEARCH_QUERY = ConfigProvider.get("test.search.query");
    public static final String EXPECTED_CITY = ConfigProvider.get("expected.city");
    public static final String EXPECTED_STREET = ConfigProvider.get("expected.street");

    // Заодно вытащим сюда базовый URL, чтобы не хранить его в тестах
    public static final String BASE_URL = ConfigProvider.get("base.url");
}