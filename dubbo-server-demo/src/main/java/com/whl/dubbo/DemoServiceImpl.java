package com.whl.dubbo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DemoServiceImpl implements DemoService {

	public String sayHello(String name) {
		try {
			InetAddress address = InetAddress.getLocalHost();
			String ip = address.getHostAddress();
			return "Hello " + name + ", from " + ip;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "Hello " + name;
	}

}