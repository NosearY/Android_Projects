#pragma strict

public var playButton :Transform;
public var sessionPlay :String;

function Start () {

}

function Update ()
{
//	if (Input.GetButton(playButton.name) )
//	{
//		StartCoroutine(LoadPlaySession());
//	}
}

function LoadPlaySession()
{
	var async : AsyncOperation = Application.LoadLevelAsync (sessionPlay);
	yield async;
	Debug.Log ("Loading complete");
}