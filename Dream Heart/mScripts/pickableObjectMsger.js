#pragma strict

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

public var itemType : ItemType;
	
function Start () {

}

function Update () {

}
function PickableObjectPickToInv () {
	
}

function PickableObjectDestory () {
	Destroy (gameObject, 0.5);
}

function PickableObjectVanish () {
	gameObject.SetActive(false);
}