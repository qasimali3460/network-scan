package gg;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class PortScanner {



	public ArrayList<Integer> getOpenPorts(String ipAddr) throws InterruptedException, ExecutionException {
		final ExecutorService es = Executors.newFixedThreadPool(20);
		final int timeout  = 5;
		final List<Future<ScanResult>> futures = new ArrayList<>();
		ArrayList<Integer> ports = new ArrayList<Integer>();
		for(int port = 1;port <=1000;port++) {
			if(portIsOpen(es, ipAddr, port, timeout).get().isOpen()) {
				ports.add(port);
			}
			futures.add(portIsOpen(es, ipAddr, port, timeout));
		}
		es.awaitTermination(200L, TimeUnit.MILLISECONDS);
		return ports;
	}
	
	public static Future<ScanResult> portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout) {
		return es.submit(new Callable<ScanResult>() {

			public ScanResult call() throws Exception {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return new ScanResult(port, true);
				} catch(Exception ex) {
					return new ScanResult(port, false);	
				}
			}
			
		});
	}
	

	public static class ScanResult {
		private int port;
		private boolean isOpen;
		
		public ScanResult(int port, boolean isOpen) {
			super();
			this.port = port;
			this.isOpen = isOpen;
		}
		
		public void setPort(int port) {
			this.port = port;
		}
		
		public int getPort() {
			return port;
		}
		
		public boolean isOpen() {
			return isOpen;
		}
		
		public void setOpen(boolean isOpen) {
			this.isOpen = isOpen;
		}
	}

}



