package org.havis.weatherinfo.service.iplocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IPAddressLocationResolver {
    private final static Logger logger = LoggerFactory.getLogger(IPAddressLocationResolver.class);

    private static final String LOCATION_INFO_URI = "http://ipinfo.io/{ipAddress}/json?token={ipInfoServiceToken}";

    @Value("#{environment['IPINFO_SERVICE_TOKEN']}")
    private String ipInfoServiceToken;

    @Resource
    private MyExternalIpService myExternalIpService;

    public LocationInfoDTO resolveFrom(HttpServletRequest request) {
        return new RestTemplate()
                .<LocationInfoDTO>getForEntity(
                        LOCATION_INFO_URI,
                        LocationInfoDTO.class,
                        createUriVariables(request))
                .getBody();
    }

    private Map<String, String> createUriVariables(HttpServletRequest request) {
        String ipAddress = getIpFromRequest(request);
        if (isLocalhost(ipAddress)) {
            ipAddress = myExternalIpService.getExternalIpAddress();
        }

        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("ipInfoServiceToken", ipInfoServiceToken);
        uriVariables.put("ipAddress", ipAddress);
        return uriVariables;
    }

    private String getIpFromRequest(HttpServletRequest request) {
        return Optional
                .ofNullable(request.getHeader("X-FORWARDED-FOR"))
                .map(s -> s.contains(",") ? s.split(",")[0] : s)
                .orElse(request.getRemoteAddr());
    }

    private boolean isLocalhost(String ipAddress) {
        try {
            final InetAddress inetAddress = InetAddress.getByName(ipAddress);
            final boolean isLocalhost = inetAddress.isLoopbackAddress()
                    || inetAddress.isLinkLocalAddress()
                    || inetAddress.isSiteLocalAddress();
            logger.debug("IP address {} {} localhost or local", ipAddress, isLocalhost ? "is" : "is not");
            return isLocalhost;
        } catch (UnknownHostException e) {
            logger.error("Failed to determine if this is localhost", e);
        }
        return false;
    }
}
