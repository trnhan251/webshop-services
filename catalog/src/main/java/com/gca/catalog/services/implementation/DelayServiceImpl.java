package com.gca.catalog.services.implementation;

import com.gca.catalog.services.DelayService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DelayServiceImpl implements DelayService {

    private Logger logger = LoggerFactory.getLogger(DelayServiceImpl.class);

    @Override
    public void delay(Long milliseconds) {
        if (milliseconds <= 0) return;

        try {
            logger.info("Delaying execution for " + milliseconds + "ms");
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            logger.error("Delay interrupted", e);
        }
    }
}
