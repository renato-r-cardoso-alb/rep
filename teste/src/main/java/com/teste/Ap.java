package com.teste;

import com.demo.App;

/**
 * Hello world!
 */
public final class Ap {
    private Ap() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(new App().teste());
    }
}
