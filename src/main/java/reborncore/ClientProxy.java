/*
 * Copyright (c) 2018 modmuss50 and Gigabit101
 *
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package reborncore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import reborncore.client.HolidayRenderEvent;
import reborncore.client.IconSupplier;
import reborncore.client.hud.StackInfoHUD;
import reborncore.client.multiblock.MultiblockRenderEvent;
import reborncore.client.shields.RebornItemStackRenderer;
import reborncore.client.shields.ShieldTextureStore;
import reborncore.common.multiblock.MultiblockClientTickHandler;

import java.util.List;

public class ClientProxy extends CommonProxy {

	public static MultiblockRenderEvent multiblockRenderEvent;

	@Override
	public void setup(FMLCommonSetupEvent event) {
		super.setup(event);
		MinecraftForge.EVENT_BUS.register(HolidayRenderEvent.class);
		MinecraftForge.EVENT_BUS.register(new IconSupplier());
	}

	@Override
	public void loadComplete(FMLLoadCompleteEvent event) {
		super.loadComplete(event);
		MinecraftForge.EVENT_BUS.register(new StackInfoHUD());
		multiblockRenderEvent = new MultiblockRenderEvent();
		MinecraftForge.EVENT_BUS.register(multiblockRenderEvent);
		TileEntityItemStackRenderer.instance = new RebornItemStackRenderer(TileEntityItemStackRenderer.instance);
		MinecraftForge.EVENT_BUS.register(new MultiblockClientTickHandler());
	}

	@Override
	public void loadShieldTextures() {
		super.loadShieldTextures();
		ShieldTextureStore.load();
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getInstance().world;
	}

	@Override
	public EntityPlayer getPlayer() {
		return Minecraft.getInstance().player;
	}

	@Override
	public void getCrashData(List<String> list) {
		super.getCrashData(list);
		list.add("RenderEngine: " + (ModList.get().isLoaded("optifine") ? "1" : "0"));
	}
}
