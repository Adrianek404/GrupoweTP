package pl.patrykv220.grupowetpcore.helper;


import net.minecraft.server.v1_8_R3.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.*;
import org.bukkit.Material;

public enum ParticleType
{
    FIRE("&6Ogien", EnumParticle.FLAME, new ItemStack(Material.FLINT_AND_STEEL), 18), 
    HEART("&cSerca", EnumParticle.HEART, new ItemStack(Material.RED_ROSE), 19);
    
    private final String name;
    private final EnumParticle particle;
    private final ItemStack guiItem;
    private final int guiSlot;
    
    public String getName() {
        return this.name;
    }
    
    public EnumParticle getParticle() {
        return this.particle;
    }
    
    public ItemStack getGuiItem() {
        return this.guiItem;
    }
    
    public int getGuiSlot() {
        return this.guiSlot;
    }
    
    private ParticleType(final String name, final EnumParticle particle, final ItemStack guiItem, final int guiSlot) {
        this.name = name;
        this.particle = particle;
        this.guiItem = guiItem;
        this.guiSlot = guiSlot;
    }
}
