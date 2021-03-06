package tk.cvrunmin.railwayp;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.cvrunmin.railwayp.init.RPBlocks;
import tk.cvrunmin.railwayp.init.RPItems;
import tk.cvrunmin.railwayp.tileentity.TileEntityColorful;
import tk.cvrunmin.railwayp.tileentity.TileEntityNameBanner;
import tk.cvrunmin.railwayp.tileentity.TileEntityPFDoor;
import tk.cvrunmin.railwayp.tileentity.TileEntityPlatformBanner;
import tk.cvrunmin.railwayp.tileentity.TileEntityRailNoticer;
import tk.cvrunmin.railwayp.tileentity.TileEntityRouteSignage;
import tk.cvrunmin.railwayp.tileentity.TileEntityWHPF;

public class CommonProxy {
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	RPBlocks.register();
    	RPItems.register();
    	registerEntity();
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
    private void registerEntity(){
//    	ClientRegistry.registerTileEntity(TileEntityPlatformBanner.class, "PlatformBanner", new TileEntityPlatformBannerRenderer());
    	GameRegistry.registerTileEntity(TileEntityPlatformBanner.class, "PlatformBanner");
    	GameRegistry.registerTileEntity(TileEntityNameBanner.class, "NameBanner");
    	GameRegistry.registerTileEntity(TileEntityRouteSignage.class, "RouteSignage");
    	GameRegistry.registerTileEntity(TileEntityPFDoor.class, "PlatformDoor");
    	GameRegistry.registerTileEntity(TileEntityWHPF.class, "whpf");
    	GameRegistry.registerTileEntity(TileEntityColorful.class, "Colorful");
    	GameRegistry.registerTileEntity(TileEntityRailNoticer.class, "noticer");
    }
	protected void blockRend(Block block, String registerName){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
                new ModelResourceLocation("railwayp:" + registerName, "inventory"));
	}
	protected void itemRend(Item item, String registerName){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation("railwayp:" + registerName, "inventory"));
	}
	protected void itemRend(Item item, int damage, String ideniter){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, damage,
                new ModelResourceLocation("railwayp:" + ideniter, "inventory"));
	}
	public EntityPlayer getPlayerFromNetHandler(INetHandler handler)
	{
		if (handler instanceof NetHandlerPlayServer)
		{
			return ((NetHandlerPlayServer) handler).playerEntity;
		}
		else
		{
			return null;
		}
	}
}
