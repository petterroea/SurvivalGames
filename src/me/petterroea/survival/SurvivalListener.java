package me.petterroea.survival;


import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SurvivalListener implements Listener{
	static boolean open = true;
	SurvivalGames games;
	public SurvivalListener(SurvivalGames games)
	{
		this.games = games;
		//games.getServer().getPluginManager().registerEvents(this, games);
	}
	@EventHandler
	public void playerJoin(PlayerJoinEvent event)
	{
		if(SurvivalGames.started)
		{
			final PlayerJoinEvent use = event;
			games.getServer().getScheduler().scheduleAsyncDelayedTask(games, new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					use.getPlayer().teleport(new Location(use.getPlayer().getWorld(), -1573, 71, -643));
					use.getPlayer().sendMessage(ChatColor.RED + "The game has allready started. You are in the queue for next round");
					SurvivalGames.dead.add(use.getPlayer().getName());
				}}, 20L);
			
//			if(SurvivalGames.spectators.contains(event.getPlayer().getName()))
//			{
//				event.getPlayer().setGameMode(GameMode.CREATIVE);
//				for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
//				{
//					games.getServer().getOnlinePlayers()[i].hidePlayer(event.getPlayer());
//				}
//			}
//			else
//			{
//	    			Player player = (Player) event.getPlayer();
//	    			player.setGameMode(GameMode.CREATIVE);
//	    			if(games.spectators == null)
//	    			{
//	    				games.spectators = new LinkedList<String>();
//	    			}
//	    			games.spectators.add(player.getName());
//	    			for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
//	    			{
//	    				Player plyr = games.getServer().getOnlinePlayers()[i];
//	    				if(!plyr.getName().equalsIgnoreCase(player.getName()))
//	    				{
//	    					plyr.hidePlayer(player);
//	    				}
//	    			}
//			}
		}
		if(open || event.getPlayer().isOp())
		{
			event.getPlayer().sendMessage(ChatColor.GREEN + "Welcome to the survival games!");
			event.getPlayer().sendMessage(ChatColor.GREEN + "Rules:");
			event.getPlayer().sendMessage(ChatColor.GREEN + "1. Tributes may not break other blocks then leaves and mushrooms");
			event.getPlayer().sendMessage(ChatColor.GREEN + "2. You have to wait 3 minutes before you can kill anyone");
			event.getPlayer().sendMessage(ChatColor.GREEN + "3. You can only place blocks you have found in a chest");
//			if(SurvivalGames.spectators.contains(event.getPlayer().getName()))
//			{
//				event.getPlayer().setGameMode(GameMode.CREATIVE);
//				for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
//				{
//					games.getServer().getOnlinePlayers()[i].hidePlayer(event.getPlayer());
//				}
//			}
//			else
			{
				event.getPlayer().setGameMode(GameMode.SURVIVAL);
//				for(int i = 0; i < games.spectators.size(); i++)
//				{
//					Player toHide = games.getServer().getPlayer(games.spectators.get(i));
//					if(toHide != null)
//					{
//						event.getPlayer().hidePlayer(toHide);
//					}
//				}
			}
			event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), -1578, 60, -627));
	
		}
		else
		{
			event.getPlayer().kickPlayer("The server is not open now. Please come back later");
		}
	}
	@EventHandler
	public void damageEvent(EntityDamageEvent event)
	{
		if(event.getEntity() instanceof Player && !SurvivalGames.canDamage)
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
			if(SurvivalGames.dead.contains(player.getName()))
			{
				event.setCancelled(true);
			}
		}
		//games.getServer().broadcastMessage("Attack");
	}
	@EventHandler
	public void playerRespawn(PlayerRespawnEvent event)
	{
		if(SurvivalGames.dead.contains(event.getPlayer().getName()))
		{
			SurvivalGames.dead.add(event.getPlayer().getName());
		}
		event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), -1564, 71, -643));
	}
	
	@EventHandler
	public void playerDisconnect(PlayerQuitEvent event)
	{
		
	}
	
	@EventHandler
	public void playerMove(PlayerMoveEvent event)
	{
		if(SurvivalGames.dead.contains(event.getPlayer().getName()))
		{
			if(event.getPlayer().getLocation().getBlockY() < 70)
			{
				event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), -1573, 71, -643));
			}
		}
		else if(!SurvivalGames.init)
		{
			//event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void death(EntityDeathEvent event)
	{
		//games.getServer().broadcastMessage("Death");
		event.setDroppedExp(0);
		if(event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();
			SurvivalGames.dead.add(player.getName());
			games.getServer().broadcastMessage(ChatColor.RED + player.getName() + " died!");
//			player.setGameMode(GameMode.CREATIVE);
//			if(SurvivalGames.spectators == null)
//			{
//				SurvivalGames.spectators = new LinkedList<String>();
//			}
//			SurvivalGames.spectators.add(player.getName());
//			for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
//			{
//				Player plyr = games.getServer().getOnlinePlayers()[i];
//				if(!plyr.getName().equalsIgnoreCase(player.getName()))
//				{
//					plyr.hidePlayer(player);
//				}
//			}
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
			else if(aliveplayers==2)
			{
				for(int i = 0; i < games.getServer().getOnlinePlayers().length; i++)
		    		{
		    			if(!games.dead.contains(games.getServer().getOnlinePlayers()[i].getName()))
		    			{
		    				double sinex = Math.sin(i*20)*20;
		    				double cosy = Math.cos(i*20)*20;
		    				Location l = new Location(games.getServer().getOnlinePlayers()[i].getWorld(), -1576+sinex, 65, -610+cosy);
		    				games.getServer().getOnlinePlayers()[i].teleport(l);
		    			}
		    		}
				games.getServer().broadcastMessage(ChatColor.RED + "DEATHMATCH BECAUSE OF FEW PLAYERS");
			}
			//player.kickPlayer("You are dead!");
		}
	}
	public boolean isSpectator(String name)
	{
		return SurvivalGames.dead.contains(name);
	}
}
