package io.github.ishincollective.saikokomon;

import io.github.cdimascio.dotenv.Dotenv;

public class SaikoKomonApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        System.out.println(dotenv.get("TOKEN"));
    }

}
