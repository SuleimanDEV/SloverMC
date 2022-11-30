package br.com.slovermc.kitpvp.utils;

public final class TimeConverter {
	
	public static String ConvertTime(int i) {
		int minutos = (int) i / 60;
		int segundos = i - minutos * 60;
		if (i >= 60 && segundos >= 10) {
			return minutos + ":" + segundos;
		} else if (i >= 60 && segundos < 10) {
			return minutos + ":0" + segundos;
		} else if (i < 60 && i >= 10) {
			return "0:" + i;
		} else {
			return "0:0" + i;
		}
	}
}