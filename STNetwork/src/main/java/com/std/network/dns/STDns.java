package com.std.network.dns;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import okhttp3.Dns;

/**
 * Description:
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-27.
 */
public class STDns implements Dns {
    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        return null;
    }
}
