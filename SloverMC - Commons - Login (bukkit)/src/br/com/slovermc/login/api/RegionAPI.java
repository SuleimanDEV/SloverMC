package br.com.slovermc.login.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public final class RegionAPI {

	@SuppressWarnings("unused")
	public static final String getCountry(final InetAddress ip) throws Exception {
		URL url = new URL("http://ip-api.com/json/" + ip.getHostAddress());
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder pageString = new StringBuilder();
		String connected;
		while ((connected = stream.readLine()) != null)
			pageString.append(connected);
		stream.close();
		String location = pageString.toString().split("\"country\":\"")[1].split("\",")[0];
		return pageString.toString().split("\"country\":\"")[1].split("\",")[0];
	}

	@SuppressWarnings("unused")
	public static final String getCity(final InetAddress ip) throws Exception {
		URL url = new URL("http://ip-api.com/json/" + ip.getHostAddress());
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder pageString = new StringBuilder();
		String connected;
		while ((connected = stream.readLine()) != null)
			pageString.append(connected);
		stream.close();
		String location = pageString.toString().split("\"city\":\"")[1].split("\",")[0];
		return pageString.toString().split("\"city\":\"")[1].split("\",")[0];
	}

	@SuppressWarnings("unused")
	public static final String getRegionID(final InetAddress ip) throws Exception {
		URL url = new URL("http://ip-api.com/json/" + ip.getHostAddress());
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder pageString = new StringBuilder();
		String connected;
		while ((connected = stream.readLine()) != null)
			pageString.append(connected);
		stream.close();
		String location = pageString.toString().split("\"region\":\"")[1].split("\",")[0];
		return pageString.toString().split("\"region\":\"")[1].split("\",")[0];
	}

	@SuppressWarnings("unused")
	public static final String getRegionName(final InetAddress ip) throws Exception {
		URL url = new URL("http://ip-api.com/json/" + ip.getHostAddress());
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder pageString = new StringBuilder();
		String connected;
		while ((connected = stream.readLine()) != null)
			pageString.append(connected);
		stream.close();
		String location = pageString.toString().split("\"regionName\":\"")[1].split("\",")[0];
		return pageString.toString().split("\"regionName\":\"")[1].split("\",")[0];
	}

	@SuppressWarnings("unused")
	public static final String getIP(final InetAddress ip) throws Exception {
		URL url = new URL("http://ip-api.com/json/" + ip.getHostAddress());
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder pageString = new StringBuilder();
		String connected;
		while ((connected = stream.readLine()) != null)
			pageString.append(connected);
		stream.close();
		String location = pageString.toString().split("\"query\":\"")[1].split("\",")[0];
		return pageString.toString().split("\"query\":\"")[1].split("\",")[0];
	}
}