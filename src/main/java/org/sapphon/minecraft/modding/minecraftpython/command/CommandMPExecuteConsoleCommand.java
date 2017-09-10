package org.sapphon.minecraft.modding.minecraftpython.command;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandNotFoundException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;

public class CommandMPExecuteConsoleCommand extends
		CommandMPServer {
	
	private String commandString;
	private String playerName;
	
	public CommandMPExecuteConsoleCommand(String commandText){
		this.commandString = commandText;
		this.playerName = Minecraft.getMinecraft().thePlayer.getDisplayName();
	}
	
	public CommandMPExecuteConsoleCommand(String[] commandAndArgsToDeserialize) {
		this.commandString = commandAndArgsToDeserialize[1];
		System.out.println(commandString);
		this.playerName = commandAndArgsToDeserialize[2];
	}
	
    /**
     * creates a new array and sets elements 0..n-2 to be 0..n-1 of the input (n elements)
     */
    private static String[] dropFirstString(String[] par0ArrayOfStr)
    {
        String[] astring1 = new String[par0ArrayOfStr.length - 1];

        for (int i = 1; i < par0ArrayOfStr.length; ++i)
        {
            astring1[i - 1] = par0ArrayOfStr[i];
        }

        return astring1;
    }

	
	@Override
	public void doWork() {
		EntityPlayer playerObject = getPlayerByName(playerName);
        String[] astring = commandString.split(" ");
        String s1 = astring[0];
        astring = dropFirstString(astring);
        ICommand icommand = (ICommand)MinecraftServer.getServer().getCommandManager().getCommands().get(s1);
        int i = this.getUsernameIndex(icommand, astring);
        int j = 0;

        try
        {
            if (icommand == null)
            {
                throw new CommandNotFoundException();
            }

                if (i > -1)
                {
                    EntityPlayerMP[] aentityplayermp = PlayerSelector.matchPlayers(playerObject, astring[i]);
                    String s2 = astring[i];
                    EntityPlayerMP[] aentityplayermp1 = aentityplayermp;
                    int k = aentityplayermp.length;

                    for (int l = 0; l < k; ++l)
                    {
                        EntityPlayerMP entityplayermp = aentityplayermp1[l];
                        astring[i] = entityplayermp.getCommandSenderName();

                        try
                        {
                            icommand.processCommand(playerObject, astring);
                            ++j;
                        }
                        catch (CommandException commandexception)
                        {
                            ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation(commandexception.getMessage(), commandexception.getErrorOjbects());
                            chatcomponenttranslation1.getChatStyle().setColor(EnumChatFormatting.RED);
                            playerObject.addChatMessage(chatcomponenttranslation1);
                        }
                    }

                    astring[i] = s2;
                }
                else
                {
                    icommand.processCommand(playerObject, astring);
                    ++j;
                }
        }catch(Exception e){
        	
        }

	}

	private EntityPlayer getPlayerByName(String name) {
		return MinecraftServer.getServer().getEntityWorld().getPlayerEntityByName(name);
	}
	
    /**
     * Return a command's first parameter index containing a valid username.
     */
    private int getUsernameIndex(ICommand par1ICommand, String[] par2ArrayOfStr)
    {
        if (par1ICommand == null)
        {
            return -1;
        }
        else
        {
            for (int i = 0; i < par2ArrayOfStr.length; ++i)
            {
                if (par1ICommand.isUsernameIndex(par2ArrayOfStr, i) && PlayerSelector.matchesMultiplePlayers(par2ArrayOfStr[i]))
                {
                    return i;
                }
            }

            return -1;
        }
    }
				
	@Override
	public String serialize() {
		//TODO this will never work if it ever needs to, commands must certainly contain commas sometimes...?
		return CONSOLECOMMAND_NAME + SERIAL_DIV + commandString + SERIAL_DIV + playerName;
	}
}
