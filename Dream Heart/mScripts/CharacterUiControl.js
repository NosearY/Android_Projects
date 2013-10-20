#pragma strict

public var inventoryScene : GameObject;
public var characterCtrl : GameObject;

function Start () {

}

function Update () {

}

function OnGUI ()
{

	if (GUI.Button (Rect (0,0,100,50), "Inventory")) {
		if( true==inventoryScene.active ){
			inventoryScene.SetActive(false);
			characterCtrl.SetActive(true);
		}else{
			inventoryScene.SetActive(true);
			characterCtrl.SetActive(false);
		}
	}
//	if (GUI.Button (Rect (0,50,100,50), "Pick")) {
//		character.animation.CrossFadeQueued(pickAnimation.name, 0.3, QueueMode.PlayNow, PlayMode.StopSameLayer);
//		
//		//yield WaitForSeconds(character.animation.clip.length);
//		//character.active=true;
//		Debug.Log(character.active+character.animation[pickAnimation.name].name);
//	}
}