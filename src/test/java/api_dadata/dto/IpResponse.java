package api_dadata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpResponse {
    private AddressLocation location;

    public IpResponse() {
    }

    public AddressLocation getLocation() {
        return location;
    }
}
