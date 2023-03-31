package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService=new FluxAndMonoGeneratorService();

    @Test
    void namesFlux(){
        var namesFlux =fluxAndMonoGeneratorService.namesFlux();

        StepVerifier.create(namesFlux)
//                .expectNext("alan","ben","chloe")
//                .expectNextCount(3)
                .expectNext("alan")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void namesFlux_map(){
        //Dynamic map use string length
        int stringLength=3;
        var namesFlux= fluxAndMonoGeneratorService.namesFlux_map(stringLength);

        StepVerifier.create(namesFlux)
//                .expectNext("ALAN","BEN","CHLOE")
                .expectNext("4-ALAN","5-CHLOE")
                .verifyComplete();
    }

    @Test
    void namesFlux_immutability(){
        var namesFlux= fluxAndMonoGeneratorService.namesFlux_immutability();

        StepVerifier.create(namesFlux)
                .expectNext("alan","ben","chloe")
                .verifyComplete();
    }

    @Test
    void namesFlux_flatmap(){
        int stringLength=3;
        var namesFlux =fluxAndMonoGeneratorService.namesFlux_flatmap(stringLength);

        StepVerifier.create(namesFlux)
                .expectNext("A","L","A","N","C","H","L","O","E")
                .verifyComplete();
    }

    @Test
    void namesFlux_flatmap_async(){
        int stringLength=3;
        var namesFlux =fluxAndMonoGeneratorService.namesFlux_flatmap_async(stringLength);

        StepVerifier.create(namesFlux)
//                .expectNext("A","L","A","N","C","H","L","O","E")
                .expectNextCount(9)
                .verifyComplete();
    }
}