package gg;

import java.util.ArrayList;

public class Data {
	private String ipAddress;
	private ArrayList<Integer> ports;
	
	public Data(String ipAddress, ArrayList<Integer> ports) {
		this.ipAddress = ipAddress;
		this.ports = ports;
	}
	
	public String getIp() {
		return this.ipAddress;
	}
	
	public ArrayList<Integer> getPorts() {
		return this.ports;
	}
}
