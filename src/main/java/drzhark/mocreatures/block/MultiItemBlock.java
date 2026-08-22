package drzhark.mocreatures.block;

import drzhark.mocreatures.MoCreatures;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class MultiItemBlock extends ItemBlock {

    public MultiItemBlock(Block block) 
    {
        super(block);
        setHasSubtypes(true);
        //setItemName("multiBlock"); //TODO
        ((net.minecraft.item.Item)this).setUnlocalizedName("multiBlock");
    }

    @Override
    public int getMetadata (int damageValue) {
        return damageValue;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int meta = itemStack.getItemDamage();
        if (meta < 0 || meta >= MoCreatures.multiBlockNames.size()) {
            meta = 0;
        }
        return getUnlocalizedName() + "." + MoCreatures.multiBlockNames.get(meta);
    }
}