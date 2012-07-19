package me.petterroea.newsurvival;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Survival extends JavaPlugin{
	public LinkedList<String> dead = new LinkedList<String>();
	PlayerListener playerListener = new PlayerListener(this);
	BlockListener blockListener = new BlockListener(this);
	public boolean started = false;
	public boolean init = false;
	public boolean canDamage = false;
	
	@Override
	public void onEnable()
	{
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
        getServer().getWorld("world").setTime(0);
        
        
        
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
        getServer().getPluginManager().registerEvents(playerListener, this);
        
        try{
        	getServer().getPluginManager().registerEvents(blockListener, this);
        } 
        catch(Exception e)
        {
        	getServer().broadcastMessage(e.getMessage());
        }
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("tpto"))
		{
			if(args.length==1)
			{
				if(sender instanceof Player)
				{
					if(dead.contains(sender.getName()))
					{
						if(getServer().getPlayer(args[0])!=null)
						{
							Player player = (Player)sender;
							player.teleport(getServer().getPlayer(args[0]).getLocation());
							sender.sendMessage(ChatColor.GREEN+"You are teleported to " + args[0]);
						}
						else
						{
							sender.sendMessage(ChatColor.RED+"The player does not exist!");
						}
					}
					else
					{
						sender.sendMessage(ChatColor.RED+"You have to be spectator to tp to!");
					}
				}
				else
				{
					sender.sendMessage("You have to be a player!");
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED+"Wrong amount of arguments");
			}
		}
		else if(cmd.getName().equalsIgnoreCase("start"))
		{
			final Survival me = this;
			if(!init&&!started)
			{
				init = true;
				getServer().broadcastMessage(ChatColor.RED + "The game is starting in 1 minute!");
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
      	    							}, 800*2L);
 								        
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
      	    							}, 20*60*20);
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
			return true;
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
		else if(cmd.getName().equalsIgnoreCase("deathmatch"))
		{
			if(sender.isOp())
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
		}
		else if(cmd.getName().equalsIgnoreCase("revive") && args.length==1)
		{
			if(sender.isOp())
			{
				if(dead.contains(args[0]))
				{
					dead.remove(args[0]);
					sender.sendMessage(ChatColor.GREEN+"Revived " + args[0]);
				}
				else
				{
					sender.sendMessage(ChatColor.RED+"The player is not dead");
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED+"You have to be OP to issue that command");
			}
			return true;
		}
		return true;
	}
}
