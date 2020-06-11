package me.sta.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {

    public static String homeHandler(String username, String passwd, BlockException blockException) {
        return "线程池：  " + Thread.currentThread().getName() + " homeHandler";
    }
}
