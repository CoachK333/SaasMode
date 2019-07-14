package com.boot.data.java_8;

import org.junit.Test;

public class TestDefaultMethod {

    interface A1 {
        default void hehe() {
            System.out.println("hehe,A1");
        }

        static void cnm(){

        };
    }

    interface A2 {
        void hehe();
    }

    class A3 {
        public void hehe() {
            System.out.println("hehe,A3");
        }
    }

    class A /*extends A3*/ implements A1, A2 {

        @Override
        public void hehe() {

        }
    }

    @Test
    public void test1() {
        A1 a = new A();
        a.hehe();
    }
}
