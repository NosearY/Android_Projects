#pragma strict
private var baseW : float = 780;
private var baseH : float = 640;

private var scale : Vector2;
function Awake(){ 
    scale = Vector2(parseFloat(Screen.width)/baseW,parseFloat(Screen.height)/baseH);
}

function Start () {

}

function Update () {

}

function OnGUI(){
    transform.localScale = new Vector3(scale.x,scale.y,transform.localScale.z); 
   
}