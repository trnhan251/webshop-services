package com.gca.catalog.services.implementation;

import com.gca.catalog.services.DelayService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DelayServiceImpl implements DelayService {

    private Logger logger = LoggerFactory.getLogger(DelayServiceImpl.class);

    @Override
    public void delay(Optional<Long> milliseconds) {
        if (!milliseconds.isPresent()) return;
        long millis = milliseconds.get();
        if (millis <= 0) return;

        try {
            logger.info("Delaying execution for " + millis + "ms");
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            logger.error("Delay interrupted", e);
        }
    }
}
