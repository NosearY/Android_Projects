using UnityEngine;
using System.Collections;

public class TriggerSphere : MonoBehaviour {
	public UILabel dialogContentLabel;
	public GameObject dialogBox;
	public string dialogContent;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	void OnTriggerEnter(Collider myTrigger){
	
		Debug.Log ("Test by C sharp: "+myTrigger.gameObject.name);
		//dialogContentLabel = GameObject.Find("DialogContent");
		if(myTrigger.gameObject.name == "Blade_girl_main"){
			dialogBox.active = true;
			dialogContentLabel.text = "Please select the way!";
			
		}
		
	}
}
