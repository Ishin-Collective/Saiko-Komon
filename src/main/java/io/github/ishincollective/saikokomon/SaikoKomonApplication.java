package io.github.ishincollective.saikokomon;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.EventDispatcher;
import discord4j.core.event.ReplayingEventDispatcher;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.shard.GatewayBootstrap;
import discord4j.gateway.GatewayOptions;
import discord4j.gateway.intent.IntentSet;
import io.github.cdimascio.dotenv.Dotenv;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class SaikoKomonApplication {

    private final Vendor vendor;
    private final DiscordClient client;

    private static class Vendor {
        private final String token;

        public Vendor(Dotenv dotenv) {
            this.token = dotenv.get("BOT.TOKEN");
        }
    }

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        new SaikoKomonApplication(new Vendor(dotenv)).start().block();
    }

    public SaikoKomonApplication(Vendor vendor) {
        this.vendor = vendor;
        this.client = DiscordClientBuilder.create(vendor.token).build();
    }

    public Mono<Void> start() {
        GatewayBootstrap<GatewayOptions> gateway = this.client.gateway();
        System.out.println("TOKEN is " + this.vendor.token);
        return Mono.empty().then(gateway.login()).then();
    }

}
