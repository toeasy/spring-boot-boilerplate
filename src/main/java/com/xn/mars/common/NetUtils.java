package com.xn.mars.common;

import java.net.*;
import java.util.Enumeration;

/**
 * author:by Liang.qinjie
 * on 2017-03-04 15:44
 */
public class NetUtils {

    public static String getLocalHostName() {
        String hostName;
        try {
            /**返回本地主机。*/
            InetAddress addr = InetAddress.getLocalHost();
            /**获取此 IP 地址的主机名。*/
            hostName = addr.getHostName();
        } catch (Exception ex) {
            hostName = "";
        }

        return hostName;
    }

    public static String[] getAllLocalHostIP() {

        String[] ret = null;
        try {
            /**获得主机名*/
            String hostName = getLocalHostName();
            if (hostName.length() > 0) {
                /**在给定主机名的情况下，根据系统上配置的名称服务返回其 IP 地址所组成的数组。*/
                InetAddress[] addrs = InetAddress.getAllByName(hostName);
                if (addrs.length > 0) {
                    ret = new String[addrs.length];
                    for (int i = 0; i < addrs.length; i++) {
                        /**.getHostAddress()   返回 IP 地址字符串（以文本表现形式）。*/
                        ret[i] = addrs[i].getHostAddress();
                    }
                }
            }

        } catch (Exception ex) {
            ret = null;
        }

        return ret;
    }

    public static String getIp()   {
        // 获得本机的所有网络接口
        Enumeration<NetworkInterface> nifs = null;
        try {
            nifs = NetworkInterface.getNetworkInterfaces();
            while (nifs.hasMoreElements()) {
                NetworkInterface nif = nifs.nextElement();

                // 获得与该网络接口绑定的 IP 地址，一般只有一个
                Enumeration<InetAddress> addresses = nif.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    if (addr instanceof Inet4Address) { // 只关心 IPv4 地址
//                        System.out.println("网卡接口名称：" + nif.getName());
//                        System.out.println("网卡接口地址：" + addr.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }


        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            return ip;
        }
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        System.out.println("args = " + InetAddress.getLocalHost().getHostAddress());
    }

}
