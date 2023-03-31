package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

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

    @Test
    void namesFlux_concatmap(){
        int stringLength=3;
        var namesFlux =fluxAndMonoGeneratorService.namesFlux_concatmap(stringLength);

        StepVerifier.create(namesFlux)
                .expectNext("A","L","A","N","C","H","L","O","E")
//                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void namesMono_map_filter() {
        int stringLength = 3;
        var stringMono = fluxAndMonoGeneratorService.namesMono_map_filter(stringLength);

        StepVerifier.create(stringMono)
                .expectNext("ALAN")
                .verifyComplete();

    }

    @Test
    void namesMono_flatmap() {
        int stringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.namesMono_flatmap(stringLength).log();
        StepVerifier.create(namesFlux)
                .expectNext(List.of("A","L","A","N"))
                .verifyComplete();
    }

    @Test
    void namesMono_flatmapMany() {
        int stringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.namesMono_flatmapMany(stringLength).log();
        StepVerifier.create(namesFlux)
                .expectNext("A","L","A","N")
                .verifyComplete();
    }

    @Test
    void namesFlux_transform(){
        int stringLength=3;
        var namesFlux =fluxAndMonoGeneratorService.namesFlux_transform(stringLength);

        StepVerifier.create(namesFlux)
                .expectNext("A","L","A","N","C","H","L","O","E")
                .verifyComplete();
    }

    @Test
    void namesFlux_transform_1(){
        int stringLength=6;
        var namesFlux =fluxAndMonoGeneratorService.namesFlux_transform(stringLength);

        StepVerifier.create(namesFlux)
//                .expectNext("A","L","A","N","C","H","L","O","E")
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void namesFlux_transform_switchIfEmpty() {
        int stringLength = 6;
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_transform_switchIfEmpty(stringLength).log();
        StepVerifier.create(namesFlux)
                .expectNext("D", "E", "F", "A", "U", "L", "T")
                //.expectNextCount(5)
                .verifyComplete();

    }
}