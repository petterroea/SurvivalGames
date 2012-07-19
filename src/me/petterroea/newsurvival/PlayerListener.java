package me.petterroea.newsurvival;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
	Survival games;
	public PlayerListener(Survival games)
	{
		this.games = games;
	}
	@EventHandler
	public void easteregg(PlayerChatEvent event)
	{
		if(event.getMessage().contains("get in a hole")||event.getMessage().contains("find a hole")||event.getMessage().contains("its so big"))
		{
			event.getPlayer().sendMessage(ChatColor.BLUE+"THAT'S WHAT SHE SAID^^");
		}
		else if(event.getMessage().contains("gay"))
		{
			event.getPlayer().sendMessage(ChatColor.BLUE+"No, you're gay!");
		}
		else if(event.getMessage().contains("fuck"))
		{
			event.getPlayer().sendMessage(ChatColor.BLUE+"Eww! Get a room!");
		}
		else if(event.getMessage().contains("the cake is a lie"))
		{ 
			event.getPlayer().sendMessage("            ,:/+/-  ");
			event.getPlayer().sendMessage("            /M/              .,-=;//;-");
			event.getPlayer().sendMessage("       .:/= ;MH/,    ,=/+%$XH@MM#@:");
			event.getPlayer().sendMessage("      -$##@+$###@H@MMM#######H:.    -/H#");
			event.getPlayer().sendMessage(" .,H@H@ X######@ -H#####@+-     -+H###@X");
			event.getPlayer().sendMessage("  .,@##H;      +XM##M/,     =%@###@X;-");
			event.getPlayer().sendMessage("X%-  :M##########$.    .:%M###@%:   ");
			event.getPlayer().sendMessage("M##H,   +H@@@$/-.  ,;$M###@%,          -");
			event.getPlayer().sendMessage("M####M=,,---,.-%%H####M$:          ,+@##");
			event.getPlayer().sendMessage("@##################@/.         :%H##@$-");
			event.getPlayer().sendMessage("M###############H,         ;HM##M$=  ");
			event.getPlayer().sendMessage("#################.    .=$M##M$=       ");
			event.getPlayer().sendMessage("################H..;XM##M$=          .:+");
			event.getPlayer().sendMessage("M###################@%=           =+@MH%");
			event.getPlayer().sendMessage("@################M/.          =+H#X%=");
			event.getPlayer().sendMessage("=+M##############M,       -/X#X+;.");
			event.getPlayer().sendMessage("  .;XM##########H=    ,/X#H+:,");
			event.getPlayer().sendMessage("     .=+HM######M+/+HM@+=.");
			event.getPlayer().sendMessage("         ,:/%XM####H/.");
			event.getPlayer().sendMessage("              ,.:=-.");
		}
		else if(event.getMessage().contains("your mother is a lie"))
		{
			event.getPlayer().sendMessage("no u");
		}
		else if(event.getMessage().contains("WHO DA FUQ MADE THIS PLUGIN"))
		{
			event.getPlayer().sendMessage("Petterroea, some contributions by I_am_not_funny");
		}
	}
	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		event.setDeathMessage(ChatColor.RED + "The tribute " + event.getEntity().getName() + " died!");
		if(!games.dead.contains(event.getEntity().getName()))	
		{
			games.dead.add(event.getEntity().getName());
			event.getEntity().setGameMode(GameMode.CREATIVE);
			for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
			{
				if(!games.getServer().getOnlinePlayers()[i].getName().equals(event.getEntity().getName()))
				{
					games.getServer().getOnlinePlayers()[i].hidePlayer(event.getEntity());
				}
			}
		}
		event.getEntity().sendMessage(ChatColor.RED + "You died! You are now a spectator...");
		int num = 0;
		String lastname = "ANON";
		for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
		{
			if(!games.dead.contains(games.getServer().getOnlinePlayers()[i].getName()))
			{
				num++;
				lastname = games.getServer().getOnlinePlayers()[i].getName();
			}
		}
		if(num==1)
		{
			games.getServer().broadcastMessage(lastname + " has won the game! Restarting in 30 sec");
			games.getServer().getScheduler().scheduleSyncDelayedTask(games, new Runnable() {

				   public void run() {
					   games.getServer().broadcastMessage("Shutting down...");
					   games.getServer().shutdown();
				   }
				}, (long)20*30);
		}
	}
	@EventHandler
	public void playerInteract(EntityDamageByEntityEvent event)
	{
		if(event.getDamager() instanceof Player)
		{
			Player player = (Player)event.getDamager();
			if(games.dead.contains(player.getName()))
			{
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void interact(PlayerInteractEvent event)
	{
		if(games.dead.contains(event.getPlayer().getName()))
		{
			if(event.getAction()==Action.RIGHT_CLICK_BLOCK)
			{
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void playerOpenInv(InventoryClickEvent event)
	{
		if(games.dead.contains(event.getWhoClicked().getName()))
		{
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void damageEvent(EntityDamageEvent event)
	{
		if(event.getEntity() instanceof Player && !games.canDamage)
		{
			event.setCancelled(true);
			Player player = (Player) event.getEntity();
			if(player.getHealth() <= 0)
			{
				//games.getServer().broadcastMessage("Death");
			}
		}
		else if(event.getEntity() instanceof Player)
		{
			Player player = (Player)event.getEntity();
			if(games.dead.contains(player.getName()))
			{
				event.setCancelled(true);
			}
		}
		//games.getServer().broadcastMessage("Attack");
	}
	@EventHandler
	public void playerLogin(PlayerJoinEvent event)
	{
		event.setJoinMessage("");
			event.setJoinMessage(event.getPlayer().getName() + " has joined!");
			event.getPlayer().sendMessage(ChatColor.RED+"Type /start to start a game if a game is not already running");
			event.getPlayer().sendMessage(ChatColor.RED+"You can't kill other players within the first 2 minutes of the game");
			event.getPlayer().sendMessage(ChatColor.RED+"Please find a pod IMMEDIATELY");
		if(games.started)
		{
			if(!games.dead.contains(event.getPlayer().getName()))
			{
				games.dead.add(event.getPlayer().getName());
			}
			event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), -1573, 71, -643));
			event.getPlayer().setGameMode(GameMode.CREATIVE);
			for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
			{
				if(!games.getServer().getOnlinePlayers()[i].getName().equals(event.getPlayer().getName()))
				{
					games.getServer().getOnlinePlayers()[i].hidePlayer(event.getPlayer());
				}
			}
			for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
			{
				if(!games.getServer().getOnlinePlayers()[i].getName().equals(event.getPlayer().getName()) && games.dead.contains(games.getServer().getOnlinePlayers()[i].getName()))
				{
					event.getPlayer().hidePlayer(games.getServer().getOnlinePlayers()[i]);
				}
			}
		}
	}
//	@EventHandler
//	public void playerRespawn(PlayerRespawnEvent event)
//	{
//		if(games.dead.contains(event.getPlayer().getName()))
//		{
//			event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), -1573, 71, -643));
//		}
//		else
//		{
//			event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), -1576, 64, -628));
//		}
//	}
}
