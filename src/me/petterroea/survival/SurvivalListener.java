package me.petterroea.survival;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class SurvivalListener implements Listener{
	static boolean open = true;
	SurvivalGames games;
	public SurvivalListener(SurvivalGames games)
	{
		this.games = games;
	}
	@EventHandler
	public void playerJoin(PlayerJoinEvent event)
	{
		if(SurvivalGames.started && !event.getPlayer().isOp())
		{
			event.getPlayer().kickPlayer("The round has allready started");
		}
		if(open || event.getPlayer().isOp())
		{
			event.getPlayer().sendMessage(ChatColor.GREEN + "Welcome to the survival games!");
			event.getPlayer().sendMessage(ChatColor.GREEN + "Rules:");
			event.getPlayer().sendMessage(ChatColor.GREEN + "1. Tributes may not break other blocks then leaves and mushrooms");
			event.getPlayer().sendMessage(ChatColor.GREEN + "2. You have to wait 3 minutes before you can kill anyone");
			event.getPlayer().sendMessage(ChatColor.GREEN + "3. You can only place blocks you have found in a chest");
			if(SurvivalGames.spectators.contains(event.getPlayer().getName()))
			{
				event.getPlayer().setGameMode(GameMode.CREATIVE);
				for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
				{
					games.getServer().getOnlinePlayers()[i].hidePlayer(event.getPlayer());
				}
			}
			else
			{
				event.getPlayer().setGameMode(GameMode.SURVIVAL);
				for(int i = 0; i < games.spectators.size(); i++)
				{
					Player toHide = games.getServer().getPlayer(games.spectators.get(i));
					if(toHide != null)
					{
						event.getPlayer().hidePlayer(toHide);
					}
				}
			}
		}
		else
		{
			event.getPlayer().kickPlayer("The server is not open now. Please come back later");
		}
	}
	@EventHandler
	public void blockPlace(BlockPlaceEvent event)
	{
		if(isSpectator(event.getPlayer().getName()))
		{
			event.setCancelled(true);
			Player player = event.getPlayer();
			player.sendMessage(ChatColor.RED + "Spectators cannot place bocks");
		}
	}
	@EventHandler
	public void bucket(PlayerBucketEvent event)
	{
		if(isSpectator(event.getPlayer().getName()))
		{
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void blockBreak(BlockBreakEvent event)
	{
		if(isSpectator(event.getPlayer().getName()))
		{
			event.setCancelled(true);
			Player player = event.getPlayer();
			player.sendMessage(ChatColor.RED + "Spectators cannot break bocks");
			return;
		}
		else
		{
			if(event.getBlock().getTypeId() == 18 || event.getBlock().getTypeId() == 39 || event.getBlock().getTypeId() == 40)
			{
				
			}
			else
			{
				event.getPlayer().sendMessage(ChatColor.RED + "You cant break this block!");
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void damageEvent(EntityDamageEvent event)
	{
		if(event.getCause() == DamageCause.ENTITY_ATTACK && event.getEntity() instanceof Player && !SurvivalGames.canDamage)
		{
			event.setCancelled(true);
			Player player = (Player) event.getEntity();
			if(player.getHealth() <= 0)
			{
				//games.getServer().broadcastMessage("Death");
			}
		}
		//games.getServer().broadcastMessage("Attack");
	}
	
	@EventHandler
	public void death(EntityDeathEvent event)
	{
		//games.getServer().broadcastMessage("Death");
		event.setDroppedExp(0);
		if(event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();
			player.setGameMode(GameMode.CREATIVE);
			if(SurvivalGames.spectators == null)
			{
				SurvivalGames.spectators = new LinkedList<String>();
			}
			SurvivalGames.spectators.add(player.getName());
			for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
			{
				Player plyr = games.getServer().getOnlinePlayers()[i];
				if(!plyr.getName().equalsIgnoreCase(player.getName()))
				{
					plyr.hidePlayer(player);
				}
			}
			int aliveplayers = 0;
			String lastName = "ANON";
			for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
			{
				if(!games.getServer().getOnlinePlayers()[i].getName().equals(player.getName()))
				{
					if(!isSpectator(games.getServer().getOnlinePlayers()[i].getName()))
					{
						aliveplayers++;
						lastName = games.getServer().getOnlinePlayers()[i].getName();
					}
				}
			}
			if(aliveplayers == 1)
			{
				games.getServer().broadcastMessage(ChatColor.RED + lastName + " has won the game! The game will reset in 60 seconds...");
				games.getServer().getScheduler().scheduleSyncDelayedTask(games, new Runnable() {

					   public void run() {
						   games.getServer().shutdown();
					   }
					}, 1200L);
			}
		}
	}
	public boolean isSpectator(String name)
	{
		return SurvivalGames.spectators.contains(name);
	}
}
