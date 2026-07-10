package api_dadata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressLocation {
    private String value;
    private AddressData data;

    public AddressLocation() {
    }

    public String getValue() {
        return value;
    }

    public AddressData getData() {
        return data;
    }
}
