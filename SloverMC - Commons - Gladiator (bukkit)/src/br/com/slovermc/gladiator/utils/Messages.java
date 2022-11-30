package br.com.slovermc.gladiator.utils;

public class Messages {

	/* Pricipal Methods */

	public static String getPrefix() {
		return null;
	}

	public static String getPrefixOut() {
		return "";
	}

	/* Error Methods ( General ) */

	public static String getWithoutPermissionMessage() {
		return "§c§lERRO §fVocê não tem permissão para executar este comando.";
	}

	public static String getWrongArgumentMessage(String correctUsage) {
		return "§c§lERRO§f Utilize §e" + correctUsage + "§f.";
	}

	public static String getPlayerOfflineMessage(String offlineName) {
		return "§c§lERRO§f Este jogador não está online..";
	}

	public static String getCommandOnlyForPlayersMessage() {
		return "§c§lERRO §fEste comando só pode ser executado por jogadores.";
	}
}