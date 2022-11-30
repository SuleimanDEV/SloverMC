package br.com.slovermc.kitpvp.utils;

public final class BattleStrings {
	
	public static final String BattlePlayerIsNotOnline = "§c§lERRO§f Este player não está online.";
	public static final String NoPermissionToCommand = "§c§lERRO§f Você não possui permissão para executar este comando.";
	public static final String CommandOnlyInGame = "§c§lERRO§f Comando disponivel apenas in-game!";
	public static final String NoPermissionToKit = "§3§lKIT§f Você não tem este kit!!";
	
	public static final String getBattlePlayerIsNotOnlineMessage() {
		return BattlePlayerIsNotOnline;
	}
	public static final String getNoPermissionToCommandMessage() {
		return NoPermissionToCommand;
	}
	public static final String getCommandOnlyInGameMessage() {
		return CommandOnlyInGame;
	}
}