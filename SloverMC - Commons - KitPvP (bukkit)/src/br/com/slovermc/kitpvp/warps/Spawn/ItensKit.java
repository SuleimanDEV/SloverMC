package br.com.slovermc.kitpvp.warps.Spawn;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ItensKit {

	public static final ItemStack PvP() {
		final ItemStack i = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bPvP");
		ik.setLore(Arrays.asList(new String[] { "§fKit sem habilidades, porem você recebe uma",
				"§fespada com afiada 1 para vantagens de dano!"}));
		ik.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Anchor() {
		final ItemStack i = new ItemStack(Material.ANVIL, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bAnchor");
		ik.setLore(Arrays.asList(new String[] { "§fNão dê nem leve knockback no oponente",
				"§fe impossibite-o de fugir de você!" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Ajnin() {
		final ItemStack i = new ItemStack(Material.NETHER_STAR, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bAjnin");
		ik.setLore(Arrays.asList(new String[] { "§fAo hitar um jogador, agache (shift) e",
				"§fele será teletransportado para você!"}));
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack Gladiator() {
		final ItemStack i = new ItemStack(Material.IRON_FENCE, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bGladiator");
		ik.setLore(Arrays.asList(new String[] { "§fPuxe um inimigo para 1v1 onde",
				"§fterão uma batalha sem serem interronpidos!" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Ninja() {
		final ItemStack i = new ItemStack(Material.EMERALD, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bNinja");
		ik.setLore(Arrays.asList(new String[] { "§fAo hitar um jogador, agache (shift) e",
				"§fvocê será teletransportado para ele!" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Boxer() {
		final ItemStack i = new ItemStack(Material.IRON_SWORD, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bBoxer");
		ik.setLore(Arrays.asList(new String[] { "§fDe 1.5 coraçoes a mais de dano e",
				"§freceba 1.5 corações a menos de dano!" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Fisherman() {
		final ItemStack i = new ItemStack(Material.FISHING_ROD, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bFisherman");
		ik.setLore(Arrays.asList(new String[] { "§fAo fisgar um inimigo com sua vara",
				"§fao puxar ele será trazido até você!" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack AntiStomper() {
		final ItemStack i = new ItemStack(Material.DIAMOND_HELMET, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bAntiStomper");
		ik.setLore(Arrays.asList(new String[] { "§fLeve dano reduzido para stompers",
				"§fcomo se estivesse sempre de shift (agachado)" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Stomper() {
		final ItemStack i = new ItemStack(Material.DIAMOND_BOOTS, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bStomper");
		ik.setLore(Arrays.asList(new String[] { "§fAo pular ou cair de um lugar alto",
				"§fesmague todos que estiverem em baixo!" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Hulk() {
		final ItemStack i = new ItemStack(Material.SADDLE, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bHulk");
		ik.setLore(Arrays.asList(new String[] { "§fAo cliquar com botao direito em alguém",
				"§fcarregue-o em cima de sua cabeça" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Kangaroo() {
		final ItemStack i = new ItemStack(Material.FIREWORK, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bKangaroo");
		ik.setLore(Arrays.asList(new String[] { "§fCom seu foguete, pule alto e",
				"§fmova-se mais rápido pelo mapa!" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Fireman() {
		final ItemStack i = new ItemStack(Material.FIRE, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bFireman");
		ik.setLore(Arrays.asList(new String[] { "§fNão leve nenhum tipo de dano para",
				"§felementos como fogo ou lava!" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Magma() {
		final ItemStack i = new ItemStack(Material.LAVA_BUCKET, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bMagma");
		ik.setLore(Arrays.asList(new String[] { "§fAo hitar um jogador tenha 40% de chance de",
				"§fele pegar fogo! Nao leve dano para fogo ou lava, porem",
				"§fao ficar na agua, leve 3 coraçoes de dano por segundo!" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Monk() {
		final ItemStack i = new ItemStack(Material.BLAZE_ROD, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bMonk");
		ik.setLore(Arrays.asList(new String[] { "§fAo cliquar em alguem com sua vara magica",
				"§fbagunçe o inventario dele!" }));
		i.setItemMeta(ik);
		return i;
	}

	public static final ItemStack Minato() {
		final ItemStack i = new ItemStack(Material.ARROW, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bMinato");
		ik.setLore(Arrays.asList(new String[] { "§fAo lançar sua kunai com um selo",
				"§fteleporte-se rapidamente para onde ela encostar!" }));
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack Phantom() {
		final ItemStack i = new ItemStack(Material.FEATHER, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bPhantom");
		ik.setLore(Arrays.asList(new String[] { "§fGanhe o poder de Voar",
				"§fpor 7 segundos!" }));
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack Switcher() {
		final ItemStack i = new ItemStack(Material.SNOW_BALL, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bSwitcher");
		ik.setLore(Arrays.asList(new String[] { "§fLançe sua bola em alguem",
				"§7e troque de lugar com ela!" }));
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack Thor() {
		final ItemStack i = new ItemStack(Material.GOLD_AXE, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bThor");
		ik.setLore(Arrays.asList(new String[] { "§fClique com seu machado em para uma",
				"§fdireção, e faça cair um relâmpago dos céus ali!" }));
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack Timelord() {
		final ItemStack i = new ItemStack(Material.WATCH, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bTimelord");
		ik.setLore(Arrays.asList(new String[] { "§fParalise todos jogadores a 6",
				"§fblocos de distância de você por 7 segundos!" }));
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack Viking() {
		final ItemStack i = new ItemStack(Material.DIAMOND_AXE, 1, (byte) 0);
		final ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§bViking");
		ik.setLore(Arrays.asList(new String[] { "§fDe mais dano com seu machado",
				"§fde diamante mais afiado que uma espada!" }));
		i.setItemMeta(ik);
		return i;
	}
}
