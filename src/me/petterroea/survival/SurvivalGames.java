package me.petterroea.survival;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SurvivalGames extends JavaPlugin {
	static LinkedList<String> spectators;
	static LinkedList<String> dead;
	private PluginManager pm;
	SurvivalGames instance;
    private PluginDescriptionFile desc;
	SurvivalListener listener = new SurvivalListener(this);        
	public SurvivalGames()
	{
		instance = this;
	}
    public void onEnable(){ 
    	getServer().broadcastMessage("SurvivalGames enabled");
    	final SurvivalGames me = this;
    	spectators = new LinkedList<String>();
    	dead = new LinkedList<String>();
        Block b;
        b = getServer().getWorld("world").getBlockAt(-1576-1, 60, -607-1);
        b.setTypeId(7);

        b = getServer().getWorld("world").getBlockAt(-1578-1, 60, -608-1);
        b.setTypeId(7);

        b = getServer().getWorld("world").getBlockAt(-1579-1, 60, -610-1);
        b.setTypeId(7);

        b = getServer().getWorld("world").getBlockAt(-1578-1, 60, -612-1);
        b.setTypeId(7);

        b = getServer().getWorld("world").getBlockAt(-1576-1, 60, -613-1);
        b.setTypeId(7);

        b = getServer().getWorld("world").getBlockAt(-1574-1, 60, -612-1);
        b.setTypeId(7);

        b = getServer().getWorld("world").getBlockAt(-1573-1, 60, -610-1);
        b.setTypeId(7);

        b = getServer().getWorld("world").getBlockAt(-1574-1, 60, -608-1);
        b.setTypeId(7);
        
        
        
        
        //entrance to admin hole
        
        b = getServer().getWorld("world").getBlockAt(-1574, 79, -646);
        b.setTypeId(7);

        b = getServer().getWorld("world").getBlockAt(-1574, 60, -646);
        b.setTypeId(7);
        
        
        
        
        
//        b = this.getServer().getWorld("world").getBlockAt(-1571, 66, -645);
//        b.setTypeId(7);
//
//        b = this.getServer().getWorld("world").getBlockAt(-1572, 66, -645);
//        b.setTypeId(7);
        
        
        
        b = this.getServer().getWorld("world").getBlockAt(-1574, 72, -645);
        b.setTypeId(0);

        b = this.getServer().getWorld("world").getBlockAt(-1574, 71, -645);
        b.setTypeId(0);
        
        
        
        b = this.getServer().getWorld("world").getBlockAt(-1574, 71, -645);
        b.setTypeId(0);
        pm = getServer().getPluginManager();
        desc = getDescription();
        
        pm.registerEvents(listener, this);
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
    	if(cmd.getName().equalsIgnoreCase("tpto"))
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
    				getServer().broadcastMessage(ChatColor.RED + "The game begins in 60 seconds! Type /spectate if you want to spectate - you cant while we are playing!");
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
         								  getServer().broadcastMessage(ChatColor.RED + "The game has started!");
         								 started = true;
     									   Block b;
     								        b = getServer().getWorld("world").getBlockAt(-1576-1, 60, -607-1);
     								        b.setTypeId(0);

     								        b = getServer().getWorld("world").getBlockAt(-1578-1, 60, -608-1);
     								        b.setTypeId(0);

     								        b = getServer().getWorld("world").getBlockAt(-1579-1, 60, -610-1);
     								        b.setTypeId(0);

     								        b = getServer().getWorld("world").getBlockAt(-1578-1, 60, -612-1);
     								        b.setTypeId(0);

     								        b = getServer().getWorld("world").getBlockAt(-1576-1, 60, -613-1);
     								        b.setTypeId(0);

     								        b = getServer().getWorld("world").getBlockAt(-1574-1, 60, -612-1);
     								        b.setTypeId(0);

     								        b = getServer().getWorld("world").getBlockAt(-1573-1, 60, -610-1);
     								        b.setTypeId(0);

     								        b = getServer().getWorld("world").getBlockAt(-1574-1, 60, -608-1);
     								        b.setTypeId(0);
     								        
     								        
     								        b = getServer().getWorld("world").getBlockAt(-1574, 72, -645);
     								        b.setTypeId(7);

     								        b = getServer().getWorld("world").getBlockAt(-1574, 71, -645);
     								        b.setTypeId(7);
     								        
     								        
     								        b = getServer().getWorld("world").getBlockAt(-1575, 64, -640);
     								        b.setTypeId(76);
     								        
     								        
     								        getServer().getScheduler().scheduleSyncDelayedTask(me, new Runnable() {

          	    							   public void run() {
          	    							       getServer().broadcastMessage(ChatColor.BLACK + "You may now kill.");
          	    							       canDamage = true;
          	    							   }
          	    							}, 800L);
     								        
     								        getServer().getScheduler().scheduleSyncDelayedTask(me, new Runnable() {

          	    							   public void run() {
          	    								  for(int i = 0; i < getServer().getOnlinePlayers().length; i++)
          	    						    		{
          	    						    			if(!dead.contains(getServer().getOnlinePlayers()[i].getName()))
          	    						    			{
          	    						    				double sinex = Math.sin(i*20)*20;
          	    						    				double cosy = Math.cos(i*20)*20;
          	    						    				Location l = new Location(getServer().getOnlinePlayers()[i].getWorld(), -1576+sinex, 65, -610+cosy);
          	    						    				getServer().getOnlinePlayers()[i].teleport(l);
          	    						    			}
          	    						    		}
          	    								  getServer().broadcastMessage(ChatColor.RED + "DEATHMATCH");
          	    								  
          	    								 getServer().getScheduler().scheduleSyncDelayedTask(me, new Runnable() {

                	    							   public void run() {
                	    								  for(int i = 0; i < getServer().getOnlinePlayers().length; i++)
                	    						    		{
                	    						    			if(!dead.contains(getServer().getOnlinePlayers()[i].getName()))
                	    						    			{
                	    						    				double sinex = Math.sin(i*20)*20;
                	    						    				double cosy = Math.cos(i*20)*20;
                	    						    				Location l = new Location(getServer().getOnlinePlayers()[i].getWorld(), -1576+sinex, 65, -610+cosy);
                	    						    				getServer().getOnlinePlayers()[i].teleport(l);
                	    						    			}
                	    						    		}
                	    								  getServer().broadcastMessage(ChatColor.RED + "DEATHMATCH");
                	    							   }
                	    							}, 800L);
          	    							   }
          	    							}, 20*60*20L);
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
    	else if(cmd.getName().equalsIgnoreCase("alive"))
    	{
    		sender.sendMessage(ChatColor.GREEN + "Alive players: ");
    		for(int i = 0; i < getServer().getOnlinePlayers().length; i++)
    		{
    			if(!dead.contains(getServer().getOnlinePlayers()[i].getName()))
    			{
    				sender.sendMessage(ChatColor.YELLOW+"==="+ChatColor.GREEN+getServer().getOnlinePlayers()[i].getName()+ChatColor.YELLOW+"===");
    			}
    		}
    	}
    	else if(cmd.getName().equalsIgnoreCase("sort"))
    	{
    		if(sender.isOp())
    		{
    			for(int i = 0; i < getServer().getOnlinePlayers().length; i++)
        		{
        			if(dead.contains(getServer().getOnlinePlayers()[i].getName()))
        			{
        				getServer().getOnlinePlayers()[i].teleport(new Location(getServer().getOnlinePlayers()[i].getWorld(), -1573, 71, -643));
        				getServer().getOnlinePlayers()[i].getInventory().clear();
        			}
        		}
    		}
    	}
    	else if(cmd.getName().equalsIgnoreCase("deathmatch"))
    	{
    		for(int i = 0; i < getServer().getOnlinePlayers().length; i++)
	    		{
	    			if(!dead.contains(getServer().getOnlinePlayers()[i].getName()))
	    			{
	    				double sinex = Math.sin(i*20)*20;
	    				double cosy = Math.cos(i*20)*20;
	    				Location l = new Location(getServer().getOnlinePlayers()[i].getWorld(), -1576+sinex, 65, -610+cosy);
	    				getServer().getOnlinePlayers()[i].teleport(l);
	    			}
	    		}
			  getServer().broadcastMessage(ChatColor.RED + "DEATHMATCH");
    	}
    	else if(cmd.getName().equalsIgnoreCase("spectate"))
    	{
//    		if(false)
//    		{
//    			Player player = (Player) sender;
//    			player.setGameMode(GameMode.CREATIVE);
//    			if(spectators == null)
//    			{
//    				spectators = new LinkedList<String>();
//    			}
//    			spectators.add(player.getName());
//    			for(int i = 0; i < getServer().getOnlinePlayers().length; i++)
//    			{
//    				Player plyr = getServer().getOnlinePlayers()[i];
//    				if(!plyr.getName().equalsIgnoreCase(player.getName()))
//    				{
//    					plyr.hidePlayer(player);
//    				}
//    			}
//    		}
    		//else
    		{
    			if(sender instanceof Player)
    			{
    				Player player = (Player) sender;
    				if(player.isOp())
    				{
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
    	    			return true;
    				}
    			}
    			sender.sendMessage(ChatColor.RED + "You can't use that command now");
    			return true;
    		}
    	}
    	return false; 
    }



}
