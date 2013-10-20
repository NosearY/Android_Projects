//----------------------------------------------
//            NGUI: Next-Gen UI kit
// Copyright © 2011-2012 Tasharen Entertainment
//----------------------------------------------

using UnityEngine;

/// <summary>
/// A UI script that keeps an eye on the slot in character equipment.
/// </summary>

[AddComponentMenu("NGUI/Examples/UI Equipment Slot")]
public class UIEquipmentSlotModified : UIItemSlot
{
	public InvEquipment equipmentDemo;
	public InvEquipment equipmentMain;
	public InvBaseItem.Slot slot;
	
	private InvGameItem preObservedItem;

	override protected InvGameItem observedItem
	{
		get
		{
			if(equipmentDemo != null && equipmentMain != null){
				//Sycronize Bruce equiment FUK.
				InvGameItem iItem = equipmentDemo.GetItem(slot);
				equipmentMain.Equip(iItem);
				//equipmentMain.GetItem(slot);
				return iItem;
			}
			return null;
		}
	}

	/// <summary>
	/// Replace the observed item with the specified value. Should return the item that was replaced.
	/// </summary>

	override protected InvGameItem Replace (InvGameItem item)
	{
		if(equipmentDemo != null && equipmentMain != null){
			InvGameItem iItem = null;
			if(null==item){
				equipmentMain.Unequip(slot);
				iItem = equipmentDemo.Unequip(slot);
			}else{
				equipmentMain.Equip(item);
				iItem = equipmentDemo.Equip(item);
			}
			Debug.Log("equipmentDemo "+equipmentDemo.equippedItems.Length);
			Debug.Log("equipmentMain "+equipmentMain.equippedItems.Length);
			return iItem;
		}
		Debug.LogWarning("equipmentMain or equipmentDemo not declare!");
		return null;
	}
}