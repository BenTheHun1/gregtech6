/**
 * Copyright (c) 2018 Gregorius Techneticies
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.tileentity.sensors;

import static gregapi.data.CS.*;

import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.MultiTileEntitySensorTE;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityItemometer extends MultiTileEntitySensorTE {
	static {LH.add("gt.tooltip.sensor.itemometer", "Measures the precise amount of Items");}
	@Override public String getSensorDescription() {return LH.get("gt.tooltip.sensor.itemometer");}
	
	@Override
	public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof IInventory) {
			long rAmount = 0;
			if (aDelegator.mTileEntity instanceof ISidedInventory) {
				int[] tSlots = ((ISidedInventory)aDelegator.mTileEntity).getAccessibleSlotsFromSide(aDelegator.mSideOfTileEntity);
				if (tSlots == null || tSlots.length <= 0) try {tSlots = ((ISidedInventory)aDelegator.mTileEntity).getAccessibleSlotsFromSide(SIDE_ANY);} catch(Throwable e) {tSlots = null;}
				if (tSlots != null && tSlots.length >  0) {
					for (int i : tSlots) {
						ItemStack tStack = ((IInventory)aDelegator.mTileEntity).getStackInSlot(i);
						if (tStack != null) rAmount += tStack.stackSize;
					}
					return rAmount;
				}
			}
			for (int i = 0, j = ((IInventory)aDelegator.mTileEntity).getSizeInventory(); i < j; i++) {
				ItemStack tStack = ((IInventory)aDelegator.mTileEntity).getStackInSlot(i);
				if (tStack != null) rAmount += tStack.stackSize;
			}
			return rAmount;
		}
		return 0;
	}
	
	@Override
	public long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof IInventory) {
			if (aDelegator.mTileEntity instanceof ISidedInventory) return ((ISidedInventory)aDelegator.mTileEntity).getAccessibleSlotsFromSide(aDelegator.mSideOfTileEntity).length * ((IInventory)aDelegator.mTileEntity).getInventoryStackLimit();
			return ((IInventory)aDelegator.mTileEntity).getSizeInventory() * ((IInventory)aDelegator.mTileEntity).getInventoryStackLimit();
		}
		return 0;
	}
	
	@Override public short[] getSymbolColor() {return CA_WHITE;}
	@Override public IIconContainer getSymbolIcon() {return null;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}
	
	public static IIconContainer
	sTextureFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/itemometer/colored/front"),
	sTextureBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/itemometer/colored/back"),
	sTextureSide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/itemometer/colored/side"),
	sOverlayFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/itemometer/overlay/front"),
	sOverlayBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/itemometer/overlay/back"),
	sOverlaySide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/itemometer/overlay/side");
	
	@Override public String getTileEntityName() {return "gt.multitileentity.redstone.sensors.itemometer";}
}
