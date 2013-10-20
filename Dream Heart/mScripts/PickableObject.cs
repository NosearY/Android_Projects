using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class PickableObject : MonoBehaviour {

	public enum ItemType
	{
		None,			// First element MUST be 'None'
		Weapon,			// All the following elements are yours to customize -- edit, add or remove as you desire
		Shield,
		Body,
		Shoulders,
		Bracers,
		Boots,
		Trinket,
		Food,
		_LastDoNotUse,	// Flash export doesn't support Enum.GetNames :(
	} 
	
	//public int inventoryItemId = 1;
	public ItemType itemType = ItemType.None;
	public InvGameItem.Quality quality = InvGameItem.Quality.Sturdy;
	public UIItemStorage storage; //= GameObject.Find("Backpack").GetComponent<UIItemStorage>();
	
//	public int databaseID = 0;
//	public InvDatabase inventoryDatabase;
	
	public InvGameItem inventoryItem;
	
	bool PickableObjectPickToInv () {
		Debug.Log("Pick up detected!");
		if (storage == null) return false;
		
		if(storage != null){
			for(int i=0; i<=storage.maxItemCount; i++){
				if( null==storage.GetItem(i) ){
					Debug.Log("Item put in "+i+" slot");
					
					List<InvBaseItem> list = InvDatabase.list[0].items;
					if (list.Count == 0) return false;
			
					int qualityLevels = (int)quality;
					int index = Random.Range(0, list.Count);
					InvBaseItem item = list[inventoryItem.baseItemID];
					
					InvGameItem gi = new InvGameItem(index, item);
					gi.quality = inventoryItem.quality;
					gi.itemLevel = inventoryItem.itemLevel;
					
					storage.Replace(i, gi);
					PickableObjectDestory();
					return true;
				}
			}
		}
		Debug.Log("Package full or INVALID package!");
		return false;
	}

	void PickableObjectDestory () {
		Destroy (gameObject);
	}
	
	void PickableObjectVanish () {
		gameObject.SetActive(false);
	}
}
