package org.havis.weatherinfo.service.iplocation;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class MyExternalIpService {

    private static final String IPIFY_URL = "https://api.ipify.org?format=json";

    String getExternalIpAddress() {
        return new RestTemplate()
                .getForEntity(IPIFY_URL, IpAddressDTO.class)
                .getBody()
                .getIp();
    }
}
