package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService=new FluxAndMonoGeneratorService();

    @Test
    void namesFlux(){
        var namesFlux =fluxAndMonoGeneratorService.namesFlux();
    }
}