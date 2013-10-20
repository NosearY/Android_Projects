using UnityEngine;
using System.Collections;
using System.Collections.Generic;

[AddComponentMenu("NGUI/Examples/Equip Random Item")]
public class EquipPresetItem : MonoBehaviour {
	public InvEquipment mainChararcter;
	public InvEquipment demoChararcter;

	void OnClick()
	{
		if (demoChararcter == null) return;
		List<InvBaseItem> list = InvDatabase.list[0].items;
		if (list.Count == 0) return;

		int qualityLevels = (int)InvGameItem.Quality._LastDoNotUse;
		int index = Random.Range(0, list.Count);
		InvBaseItem item = list[index];

		InvGameItem gi = new InvGameItem(index, item);
		gi.quality = (InvGameItem.Quality)Random.Range(0, qualityLevels);
		gi.itemLevel = NGUITools.RandomRange(item.minItemLevel, item.maxItemLevel);
		demoChararcter.Equip(gi);
		mainChararcter.Equip(gi);
	}
}
