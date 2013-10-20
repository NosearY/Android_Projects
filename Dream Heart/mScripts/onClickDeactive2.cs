using UnityEngine;
using System.Collections;

public class onClickDeactive2 : MonoBehaviour {
	public GameObject deactiveObject;
	public string dialogContent;
	public UILabel dialogContentLabel;
	public string[] array = new string[10];
	public int mark = 0;
	public int count = 0;
	public enum state{
		introduction,
		around,
		
	}
	public state currentState;
	// Use this for initialization
	void Start () {
	
		array[0] = "hello";
		array[1] = "world";
		for(int i = 0; i < array.Length; i++){
			if(array[i] == "")
				break;
			count = count + 1;

		}
		currentState = state.around;
		//Debug.Log(array[0]+"   " + array[1]+ "  "+count);
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	void OnClick(){
		//onClickDeactive c = (onClickDeactive)gameObject.GetComponent("onClickDeactive");
		//dialogContentLabel.text = c.dialogContent;
		Debug.Log(currentState);
		if(currentState == state.introduction){//The first time(Introduction)
			Debug.Log("test into");
		     dialogContentLabel.text = array[mark];
		
		     if(count == mark){
			      deactiveObject.active = false;
				  currentState = state.around;
			     //deactiveObject.activeSelf = true;
             }
		
		     mark = mark + 1;
			 
		}else if(currentState == state.around){
			//dialogContentLabel.text = "There are serveral way, please select...";
		     deactiveObject.active = false;	
		}

	}
}
