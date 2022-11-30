package br.com.slovermc.kitpvp.api;

import org.bukkit.entity.Player;

public final class TextConverter {
	public static String convert(String text) {
		if ((text == null) || (text.length() == 0)) {
			return "\"\"";
		}
		int len = text.length();
		StringBuilder sb = new StringBuilder(len + 4);

		sb.append('"');
		for (int i = 0; i < len; i++) {
			char c = text.charAt(i);
			switch (c) {
			case '"':
			case '\\':
				sb.append('\\');
				sb.append(c);
				break;
			case '/':
				sb.append('\\');
				sb.append(c);
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\r':
				sb.append("\\r");
				break;
			default:
				if (c < ' ') {
					String t = "000" + Integer.toHexString(c);
					sb.append("\\u" + t.substring(t.length() - 4));
				} else {
					sb.append(c);
				}
				break;
			}
		}
		sb.append('"');
		return sb.toString();
	}

	public static String setPlayerName(Player player, String text) {
		return text.replaceAll("(?i)\\{PLAYER\\}", player.getName());
	}
}
