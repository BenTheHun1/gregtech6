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

package gregapi.item;

import static gregapi.data.CS.*;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.GT_API;
import gregapi.api.Abstract_Mod;
import gregapi.cover.CoverRegistry;
import gregapi.cover.covers.CoverSelectorTag;
import gregapi.data.ANY;
import gregapi.data.MD;
import gregapi.data.OP;
import gregapi.lang.LanguageHandler;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * @author Gregorius Techneticies
 */
public class ItemIntegratedCircuit extends ItemBase {
	public ItemIntegratedCircuit(String aUnlocalized, String aEnglish) {
		super(MD.GAPI.mID, aUnlocalized, aEnglish, "");
		setHasSubtypes(T);
		setMaxDamage(0);
		
		CR.shaped(ST.make(this, 1, 0), CR.DEF_REV_NCC, "GhG", "SSS", "GwG", 'G', OP.gearGtSmall.dat(ANY.Iron), 'S', OP.stick.dat(ANY.Iron));
		CR.shapeless(ST.make(this, 1, 0), CR.DEF, new Object[] {ST.make(this, 1, W)});
		
		CR.shaped(ST.make(this, 1, 1), CR.DEF, "d  ", " P ", "   ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1, 2), CR.DEF, " d ", " P ", "   ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1, 3), CR.DEF, "  d", " P ", "   ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1, 4), CR.DEF, "   ", " Pd", "   ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1, 5), CR.DEF, "   ", " P ", "  d", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1, 6), CR.DEF, "   ", " P ", " d ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1, 7), CR.DEF, "   ", " P ", "d  ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1, 8), CR.DEF, "   ", "dP ", "   ", 'P', ST.make(this, 1, W));
		
		CR.shaped(ST.make(this, 1, 9), CR.DEF, "P d", "   ", "   ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,10), CR.DEF, "P  ", "  d", "   ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,11), CR.DEF, "P  ", "   ", "  d", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,12), CR.DEF, "P  ", "   ", " d ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,13), CR.DEF, "  P", "   ", "  d", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,14), CR.DEF, "  P", "   ", " d ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,15), CR.DEF, "  P", "   ", "d  ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,16), CR.DEF, "  P", "d  ", "   ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,17), CR.DEF, "   ", "   ", "d P", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,18), CR.DEF, "   ", "d  ", "  P", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,19), CR.DEF, "d  ", "   ", "  P", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,20), CR.DEF, " d ", "   ", "  P", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,21), CR.DEF, "d  ", "   ", "P  ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,22), CR.DEF, " d ", "   ", "P  ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,23), CR.DEF, "  d", "   ", "P  ", 'P', ST.make(this, 1, W));
		CR.shaped(ST.make(this, 1,24), CR.DEF, "   ", "  d", "P  ", 'P', ST.make(this, 1, W));
		
		for (byte i = 0; i < 16; i++) CoverRegistry.put(ST.make(this, 1, i), new CoverSelectorTag(i));
	}
	
	protected IIcon[] mIcons = new IIcon[256];
	
	@Override
	public IIcon getIconFromDamage(int aMeta) {
		return mIcons[aMeta & 255];
	}
	
	@Override
	public void addAdditionalToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		super.addAdditionalToolTips(aList, aStack, aF3_H);
		aList.add(LanguageHandler.get(getUnlocalizedName() + ".configuration", "Configuration: ") + getConfigurationString(getDamage(aStack)));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack aStack) {
		return getUnlocalizedName();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public final void getSubItems(Item var1, CreativeTabs aCreativeTab, List aList) {
		aList.add(ST.make(this, 1, 0));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aIconRegister) {
		for (int i = 0; i < 25/*TODO mIcons.length*/; i++) mIcons[i] = aIconRegister.registerIcon(mModID + ":" + mName + "/" + (byte)(i&255));
		// Useful hack to register Item Icons. That is why the Integrated Circuit Item has to exist always.
		if (Abstract_Mod.sFinalized >= Abstract_Mod.sModCountUsingGTAPI) {
			OUT.println("GT_API: Setting up Icon Register for Items");
			GT_API.sItemIcons = aIconRegister;
			OUT.println("GT_API: Starting Item Icon Load Phase");
			for (Runnable tRunnable : GT_API.sItemIconload) {
				try {
					tRunnable.run();
				} catch(Throwable e) {
					e.printStackTrace(ERR);
				}
			}
			OUT.println("GT_API: Finished Item Icon Load Phase");
		}
	}
	
	private static String getModeString(int aMetaData) {
		switch ((byte)(aMetaData >>> 8)) {
		case  0: return "==";
		case  1: return "<=";
		case  2: return ">=";
		case  3: return "<";
		case  4: return ">";
		default: return "";
		}
	}
	
	private static String getConfigurationString(int aMetaData) {
		return getModeString(aMetaData) + " " + (byte)(aMetaData & 255);
	}
}
