#pragma strict

public var deactiveObject : GameObject;

function Start () {

}

function Update () {

}


function OnClick() {
	deactiveObject.SetActive(false);
}