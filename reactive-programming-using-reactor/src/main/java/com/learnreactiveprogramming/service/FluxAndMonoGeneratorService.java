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
//basic map method
//    public Flux<String> namesFlux_map(){
//        return Flux.fromIterable(List.of("alan","ben","chloe"))
//                .map(String::toUpperCase)
//                //.map(s->s.toUpperCase)
//                .log();
//    }

    //Dynamic usage
    public Flux<String> namesFlux_map(int stringLength){
        return Flux.fromIterable(List.of("alan","ben","chloe"))
                .map(String::toUpperCase)
                //.map(s->s.toUpperCase)
                .filter(s->s.length()>stringLength)
                .map(s->s.length()+"-"+s)
                .log();
    }

    public Flux<String> namesFlux_flatmap(int stringLength){
        return Flux.fromIterable(List.of("alan","ben","chloe"))
                .map(String::toUpperCase)
                //.map(s->s.toUpperCase)
                .filter(s->s.length()>stringLength)
                //A,L,A,N,C,H,O,L,E
                .flatMap(s->splitString(s))
                .log();
    }

    public Flux<String> namesFlux_flatmap_async(int stringLength){
        return Flux.fromIterable(List.of("alan","ben","chloe"))
                .map(String::toUpperCase)
                //.map(s->s.toUpperCase)
                .filter(s->s.length()>stringLength)
                //A,L,A,N,C,H,O,L,E
                .flatMap(s->splitString_withDealay(s))
                .log();
    }

    //ALAN-> A,L,A,N
    public Flux<String> splitString(String name){
        var charArray=name.split("");
        return Flux.fromArray(charArray);
    }

    public Flux<String> splitString_withDealay(String name){
        var charArray=name.split("");
        var delay=new Random().nextInt(1000);
        return Flux.fromArray(charArray).delayElements(Duration.ofMillis(delay));
    }

    public Flux<String> namesFlux_immutability(){
        var namesFlux= Flux.fromIterable(List.of("alan","ben","chloe"));
        namesFlux.map(String::toUpperCase);
        return namesFlux;
    }

    public static void main(String[] args) {

        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService.namesFlux().subscribe((name) -> {
            System.out.println("Name is : " + name);
        });

        fluxAndMonoGeneratorService.namesMono().subscribe(name->{System.out.println("Mono name is: "+name);});

    }
}
