package me.petterroea.newsurvival;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {
	Survival games;
	public BlockListener(Survival games)
	{
		this.games = games;
	}
	@EventHandler
	public void blockPlace(BlockPlaceEvent event)
	{
			event.setCancelled(true);
			Player player = event.getPlayer();
			player.sendMessage(ChatColor.RED + "This is PvP, not survival");
	}
	@EventHandler
	public void blockBreak(BlockBreakEvent event)
	{
		if(games.dead.contains(event.getPlayer().getName()))
		{
			event.setCancelled(true);
			Player player = event.getPlayer();
			player.sendMessage(ChatColor.RED + "Dead people can't break blocks");
		}
		else
		{
			if(event.getBlock().getTypeId() == 18 || event.getBlock().getTypeId() == 39 || event.getBlock().getTypeId() == 40 || event.getBlock().getTypeId() == 51)
			{
				
			}
			else
			{
				event.getPlayer().sendMessage(ChatColor.RED + "You can't break this block!");
				event.setCancelled(true);
			}
		}
	}
//	@EventHandler
//	public void bucket(PlayerBucketEvent event)
//	{
//		event.setCancelled(true);
//		Player player = event.getPlayer();
//		player.sendMessage(ChatColor.RED + "This is PvP, not survival");
//	}
}
