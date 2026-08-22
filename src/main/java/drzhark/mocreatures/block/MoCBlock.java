package drzhark.mocreatures.block;

import static net.minecraftforge.common.util.ForgeDirection.UP;

import cpw.mods.fml.common.registry.GameRegistry;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class MoCBlock extends Block
{
    public MoCBlock(String name, Material material)
    {
        super(material);
        setBlockName(name);
        setCreativeTab(MoCreatures.MOC_CREATIVE_TAB);
        GameRegistry.registerBlock(this, MultiItemBlock.class, name);
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
        Block block = plant.getPlant(world, x, y + 1, z);
        EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);

        if (plant instanceof BlockFlower)
        {
            return true;
        }

        switch (plantType)
        {
            case Desert: return this.getMaterial() == Material.sand;
            case Nether: return this == Blocks.soul_sand;
            case Crop:   return this == Blocks.farmland;
            case Cave:   return isSideSolid(world, x, y, z, UP);
            case Plains: return this.getMaterial() == Material.grass || this.getMaterial() == Material.ground;
            case Water:  return world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlockMetadata(x, y, z) == 0;
            case Beach:
                boolean isBeach = (this.getMaterial() == Material.grass || this.getMaterial() == Material.ground || this.getMaterial() == Material.sand);
                boolean hasWater = (world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
                                    world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
                                    world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
                                    world.getBlock(x,     y, z + 1).getMaterial() == Material.water);
                return isBeach && hasWater;
        }

        return super.canSustainPlant(world, x, y, z, direction, plant);
    }
}