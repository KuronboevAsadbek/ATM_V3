package uz.atm_v_3.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import uz.atm_v_3.config.network.NetworkDataService;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientInfoService {

    private final NetworkDataService networkDataService;

    private static final Logger LOG = LoggerFactory.getLogger(ClientInfoService.class);

    public void getLogger(HttpServletRequest httpServletRequest){
        LOG.info(networkDataService.getRemoteUserInfo(httpServletRequest));
        LOG.info(networkDataService.getClientIPv4Address(httpServletRequest));
    }
}
