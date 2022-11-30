package br.com.slovermc.ycommons.api.lenth;

import java.util.concurrent.TimeUnit;

public class Lenth {

	public static String convertLenth(Long log) {
		String time = "";
		long totalLenth = log;
		long timeLefting = totalLenth - System.currentTimeMillis();
		long seconds = timeLefting / 1000L;
		time = convertLenth(time, seconds);
		return time;
	}

	public static String convertLenth(String restingTime, long lenth) {

		int days = (int) TimeUnit.SECONDS.toDays(lenth);
		long hours = TimeUnit.SECONDS.toHours(lenth) - days * 24;
		long minutes = TimeUnit.SECONDS.toMinutes(lenth) - TimeUnit.SECONDS.toHours(lenth) * 60L;
		long seconds = TimeUnit.SECONDS.toSeconds(lenth) - TimeUnit.SECONDS.toMinutes(lenth) * 60L;

		String totalDay = days + "d ";

		String totalHours = hours + "h ";

		String totalMinutes = minutes + "m ";

		String totalSeconds = seconds + "s ";

		if (days == 0) {
			totalDay = "";
		}
		if (hours == 0L) {
			totalHours = "";
		}
		if (minutes == 0L) {
			totalMinutes = "";
		}
		if (seconds == 0L) {
			totalSeconds = "";
		}
		restingTime = totalDay + totalHours + totalMinutes + totalSeconds;
		restingTime = restingTime.trim();
		if (restingTime.equals("")) {
			restingTime = "0s";
		}
		return restingTime;
	}
}
