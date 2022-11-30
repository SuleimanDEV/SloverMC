package br.com.slovermc.kitpvp.region;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URL;

public final class RegionAPI {

	public static final String Pais(final InetSocketAddress ip) throws Exception {
		URL url = new URL("http://ip-api.com/json/" + ip.getHostString());
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder pageString = new StringBuilder();
		String connected;
		while ((connected = stream.readLine()) != null)
			pageString.append(connected);
		stream.close();
		return pageString.toString().split("\"country\":\"")[1].split("\",")[0];
	}

	public static final String Cidade(final InetSocketAddress ip) throws Exception {
		URL url = new URL("http://ip-api.com/json/" + ip.getHostString());
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder pageString = new StringBuilder();
		String connected;
		while ((connected = stream.readLine()) != null)
			pageString.append(connected);
		stream.close();
		return pageString.toString().split("\"city\":\"")[1].split("\",")[0];
	}

	public static final String IdEstado(final InetSocketAddress ip) throws Exception {
		URL url = new URL("http://ip-api.com/json/" + ip.getHostString());
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder pageString = new StringBuilder();
		String connected;
		while ((connected = stream.readLine()) != null)
			pageString.append(connected);
		stream.close();
		return pageString.toString().split("\"region\":\"")[1].split("\",")[0];
	}

	public static final String Estado(final InetSocketAddress ip) throws Exception {
		URL url = new URL("http://ip-api.com/json/" + ip.getHostString());
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder pageString = new StringBuilder();
		String connected;
		while ((connected = stream.readLine()) != null)
			pageString.append(connected);
		stream.close();
		return pageString.toString().split("\"regionName\":\"")[1].split("\",")[0];
	}

	public static final String Ip(final InetSocketAddress ip) throws Exception {
		URL url = new URL("http://ip-api.com/json/" + ip.getHostString());
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder pageString = new StringBuilder();
		String connected;
		while ((connected = stream.readLine()) != null)
			pageString.append(connected);
		stream.close();
		return pageString.toString().split("\"query\":\"")[1].split("\",")[0];
	}
}