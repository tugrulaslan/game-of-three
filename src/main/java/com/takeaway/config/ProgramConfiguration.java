package com.takeaway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class ProgramConfiguration {
    private Integer divisionNumber = 3;
    private Integer winningNumber = 1;

    public Integer getDivisionNumber() {
        return divisionNumber;
    }

    public void setDivisionNumber(Integer divisionNumber) {
        this.divisionNumber = divisionNumber;
    }

    public Integer getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(Integer winningNumber) {
        this.winningNumber = winningNumber;
    }
}
