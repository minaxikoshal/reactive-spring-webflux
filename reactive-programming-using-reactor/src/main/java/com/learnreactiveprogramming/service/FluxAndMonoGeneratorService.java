package com.learnreactiveprogramming.service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
public class FluxAndMonoGeneratorService {

    //publishers
    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("alan","ben","chloe")).log();

    }

    public Mono<String> namesMono() {
        return Mono.just("alan");

    }
    public static void main(String[] args) {

        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService.namesFlux().subscribe((name) -> {
            System.out.println("Name is : " + name);
        });

        fluxAndMonoGeneratorService.namesMono().subscribe(name->{System.out.println("Mono name is: "+name);});

    }
}
