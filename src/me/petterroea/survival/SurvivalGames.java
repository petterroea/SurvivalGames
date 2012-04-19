package me.petterroea.survival;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SurvivalGames extends JavaPlugin {
	static LinkedList<String> spectators;
    public void onEnable(){ 
    	getServer().broadcastMessage("SurvivalGames enabled");
    	final SurvivalGames me = this;
    	final SurvivalListener listener = new SurvivalListener(this);
    	getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			   public void run() {
				   getServer().getPluginManager().registerEvents(listener, me);
			   }
			}, 5L);
        
        spectators = new LinkedList<String>();
    }
     
    public void onDisable(){ 
     
    }
    int count = 5;
   public static boolean started = false;
    public static boolean init = false;
    public static boolean canDamage = false;
    static boolean canSpectate = true;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
    	if(cmd.getName().equalsIgnoreCase("dontstart"))
    	{
    		if(sender instanceof Player)
    		{
    			Player player = (Player)sender;
    			getServer().broadcastMessage(ChatColor.GREEN + player.getName() + " stopped the countdown!");
    			init = false;
    			return true;
    		}
    	}
    	else if(cmd.getName().equalsIgnoreCase("tpto"))
    	{
    		if(sender instanceof Player && args.length == 1)
    		{
    			Player from = (Player) sender;
    			if(spectators.contains(from.getName()))
    			{
    				Player to = getServer().getPlayer(args[0]);
        			if(to == null)
        			{
        				sender.sendMessage(ChatColor.RED + "The player does not exist: " + args[0]);
        			}
        			else
        			{
        				from.teleport(to.getLocation());
        			}
    			}
    			else
    			{
    				 from.sendMessage(ChatColor.RED + "You are not a spectator!");
    			}
    		}
    	}
    	else if(cmd.getName().equalsIgnoreCase("start")){ 
    		if(sender instanceof Player)
    		{
    			if(getServer().getOnlinePlayers().length > 1 && !started && !init)
    			{
    				getServer().broadcastMessage(ChatColor.RED + "The game begins in 60 seconds! Type /spectate if you want to spectate - you cant while we are playing! Type /dontstart to stop the countdown");
    				init = true;
    				final SurvivalGames me = this;
    				getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

    					   public void run() {
    						   if(init)
    						   {
    							   getServer().getScheduler().scheduleSyncDelayedTask(me, new Runnable() {

        							   public void run() {
        								   if(init)
        								   {
        									   if(started = true)
            								   {
            									   getServer().broadcastMessage(ChatColor.RED + "The game has started!");
            									   started = true;
            								   }
            								   else
            								   {
            									   getServer().broadcastMessage(ChatColor.RED + "The game has started!");
            									   started = true;
            								   }
            								   getServer().getScheduler().scheduleSyncDelayedTask(me, new Runnable() {

            	    							   public void run() {
            	    							       getServer().broadcastMessage(ChatColor.BLACK + "You may now kill.");
            	    							       canDamage = true;
            	    							   }
            	    							}, 2400L);
        								   }
        							   }
        							}, 100L);
        						   getServer().getScheduler().scheduleSyncDelayedTask(me, new Runnable() {

        							   public void run() {
        								   if(init)
        							       getServer().broadcastMessage(ChatColor.RED + "1 second");
        							   }
        							}, 80L);
        						   getServer().getScheduler().scheduleSyncDelayedTask(me, new Runnable() {

        							   public void run() {
        								   if(init)
        							       getServer().broadcastMessage(ChatColor.RED + "2 seconds");
        							   }
        							}, 60L);
        						   getServer().getScheduler().scheduleSyncDelayedTask(me, new Runnable() {

        							   public void run() {
        								   if(init)
        							       getServer().broadcastMessage(ChatColor.RED + "3 seconds");
        							   }
        							}, 40L);
        						   getServer().getScheduler().scheduleSyncDelayedTask(me, new Runnable() {

        							   public void run() {
        								   if(init)
        							       getServer().broadcastMessage(ChatColor.RED + "4 seconds");
        							   }
        							}, 20L);
        					   }
    						   
    					   }
    						   
    					}, 1100L);
    			}
    		}
    		else
    		{
    			sender.sendMessage("You have to be a player to do this");
    			return false;
    		}
    		return true;
    	} 
    	else if(cmd.getName().equalsIgnoreCase("spectate"))
    	{
    		if(!started && (sender instanceof Player))
    		{
    			Player player = (Player) sender;
    			player.setGameMode(GameMode.CREATIVE);
    			if(spectators == null)
    			{
    				spectators = new LinkedList<String>();
    			}
    			spectators.add(player.getName());
    			for(int i = 0; i < getServer().getOnlinePlayers().length; i++)
    			{
    				Player plyr = getServer().getOnlinePlayers()[i];
    				if(!plyr.getName().equalsIgnoreCase(player.getName()))
    				{
    					plyr.hidePlayer(player);
    				}
    			}
    		}
    		else
    		{
    			sender.sendMessage(ChatColor.RED + "You can't use that command now");
    			return true;
    		}
    		return true;
    	}
    	return false; 
    }



}
