package drzhark.mocreatures.command;

import java.util.ArrayList;
import java.util.List;

import drzhark.mocreatures.MoCPetData;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandNotFoundException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class CommandMoCPets extends CommandBase {

    private static final List<String> aliases = new ArrayList<>();

    static {
        aliases.add("mocpets");
    }

    @Override
	public String getCommandName()
    {
        return "mocpets";
    }

    @Override
	public List<String> getCommandAliases()
    {
        return aliases;
    }

    /**
     * Return the required permission level for this command.
     */
    @Override
	public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
	public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return "commands.mocpets.usage";
    }

    @Override
    public void processCommand(ICommandSender par1ICommandSender, String[] paramArray)
    {
        int unloadedCount = 0;
        int loadedCount = 0;
        List<Integer> foundIds = new ArrayList<>();
        List<String> tamedlist = new ArrayList<>();
        String playername = par1ICommandSender.getCommandSenderName();
        // search for tamed entity
        for (int dimension : DimensionManager.getIDs())
        {
            WorldServer world = DimensionManager.getWorld(dimension);
            for (int j = 0; j < world.loadedEntityList.size(); j++)
            {
                Entity entity = (Entity) world.loadedEntityList.get(j);
                if (IMoCTameable.class.isAssignableFrom(entity.getClass()))
                {
                    IMoCTameable mocreature = (IMoCTameable)entity;
                    if (mocreature.getOwnerName().equalsIgnoreCase(playername))
                    {
                        loadedCount++;
                        foundIds.add(mocreature.getOwnerPetId());
                        tamedlist.add(EnumChatFormatting.WHITE + "Found pet with " + EnumChatFormatting.DARK_AQUA + "Type" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + ((EntityLiving)mocreature).getCommandSenderName() + EnumChatFormatting.DARK_AQUA + ", Name" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + mocreature.getName() + EnumChatFormatting.DARK_AQUA + ", Owner" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + mocreature.getOwnerName() + EnumChatFormatting.DARK_AQUA + ", PetId" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + mocreature.getOwnerPetId() + EnumChatFormatting.DARK_AQUA + ", Dimension" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + entity.dimension + EnumChatFormatting.DARK_AQUA + ", Pos" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.LIGHT_PURPLE + Math.round(entity.posX) + EnumChatFormatting.WHITE + ", " + EnumChatFormatting.LIGHT_PURPLE + Math.round(entity.posY) + EnumChatFormatting.WHITE + ", " + EnumChatFormatting.LIGHT_PURPLE + Math.round(entity.posZ));
                    }
                }
            }
        }
        MoCPetData ownerPetData = MoCreatures.instance.mapData.getPetData(playername);
        if (ownerPetData != null)
        {
            MoCreatures.instance.mapData.forceSave(); // force save so we get correct information
            for (int i = 0; i < ownerPetData.getTamedList().tagCount(); i++)
            {
                NBTTagCompound nbt = ownerPetData.getTamedList().getCompoundTagAt(i);
                if (nbt.hasKey("PetId") && !foundIds.contains(nbt.getInteger("PetId")))
                {
                    unloadedCount++;
                    double posX = nbt.getTagList("Pos", 6).func_150309_d(0);
                    double posY = nbt.getTagList("Pos", 6).func_150309_d(1);
                    double posZ = nbt.getTagList("Pos", 6).func_150309_d(2);
                    if (nbt.getBoolean("InAmulet"))
                    {
                        tamedlist.add(EnumChatFormatting.WHITE + "Found unloaded pet in " + EnumChatFormatting.DARK_PURPLE + "AMULET" + EnumChatFormatting.WHITE + " with " + EnumChatFormatting.DARK_AQUA + "Type" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + (nbt.getString("id")).replace("MoCreatures.", "") + EnumChatFormatting.DARK_AQUA + ", Name" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + nbt.getString("Name") + EnumChatFormatting.DARK_AQUA + ", Owner" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + nbt.getString("Owner") + EnumChatFormatting.DARK_AQUA + ", PetId" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + nbt.getInteger("PetId") + EnumChatFormatting.WHITE + ".");
                    }
                    else tamedlist.add(EnumChatFormatting.WHITE + "Found unloaded pet with " + EnumChatFormatting.DARK_AQUA + "Type" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + (nbt.getString("id")).replace("MoCreatures.", "") + EnumChatFormatting.DARK_AQUA + ", Name" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + nbt.getString("Name") + EnumChatFormatting.DARK_AQUA + ", Owner" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + nbt.getString("Owner") + EnumChatFormatting.DARK_AQUA + ", PetId" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + nbt.getInteger("PetId") + EnumChatFormatting.DARK_AQUA + ", Dimension" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.GREEN + nbt.getInteger("Dimension") + EnumChatFormatting.DARK_AQUA + ", Pos" + EnumChatFormatting.WHITE + ":" + EnumChatFormatting.LIGHT_PURPLE + Math.round(posX) + EnumChatFormatting.WHITE + ", " + EnumChatFormatting.LIGHT_PURPLE + Math.round(posY) + EnumChatFormatting.WHITE + ", " + EnumChatFormatting.LIGHT_PURPLE + Math.round(posZ));
                }
            }
        }

        if (!tamedlist.isEmpty())
        {
            sendPageHelp(par1ICommandSender, (byte)10, tamedlist, paramArray);
            par1ICommandSender.addChatMessage(new ChatComponentTranslation("Loaded tamed count : " + EnumChatFormatting.AQUA + loadedCount + EnumChatFormatting.WHITE + ", Unloaded count : " + EnumChatFormatting.AQUA + unloadedCount + EnumChatFormatting.WHITE + ", Total count : " + EnumChatFormatting.AQUA + (ownerPetData != null ? ownerPetData.getTamedList().tagCount() : 0)));
        }
        else
        {
            par1ICommandSender.addChatMessage(new ChatComponentTranslation("Loaded tamed count : " + EnumChatFormatting.AQUA + loadedCount + EnumChatFormatting.WHITE + (!MoCreatures.isServer() ? ", Unloaded Count : " + EnumChatFormatting.AQUA + unloadedCount + EnumChatFormatting.WHITE + ", Total count : " + EnumChatFormatting.AQUA + (loadedCount + unloadedCount) : "")));
        }
    }


    public void sendPageHelp(ICommandSender sender, byte pagelimit, List<String> list, String[] par2ArrayOfStr)
    {
        int x = (list.size() - 1) / pagelimit;
        int j = 0;
        String par1 = "";
        if (par2ArrayOfStr.length > 1)
            par1 = par2ArrayOfStr[0];

        if (par2ArrayOfStr.length > 0 && Character.isDigit(par2ArrayOfStr[0].charAt(0)))
        {
            try
            {
                j = parseIntBounded(sender, par2ArrayOfStr[0], 1, x + 1) - 1;
            }
            catch (NumberInvalidException numberinvalidexception)
            {
                if (!par1.isEmpty())
                {
                    throw new WrongUsageException(par1);
                }

                throw new CommandNotFoundException();
            }
        }
        int k = Math.min((j + 1) * pagelimit, list.size());

        sender.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_GREEN + "--- Showing MoCreatures Help Info " + EnumChatFormatting.AQUA + (j + 1) + EnumChatFormatting.WHITE + " of " + EnumChatFormatting.AQUA + (x + 1) + EnumChatFormatting.GRAY + " (/mocpets <page>)" + EnumChatFormatting.DARK_GREEN + "---"));

        for (int l = j * pagelimit; l < k; ++l)
        {
            String tamedInfo = list.get(l);
            sender.addChatMessage(new ChatComponentTranslation(tamedInfo));
        }
    }
}