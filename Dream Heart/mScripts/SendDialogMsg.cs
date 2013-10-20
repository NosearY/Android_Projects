using UnityEngine;
using System.Collections;

public class SendDialogMsg : MonoBehaviour {
	
	public UILabel dialogContentLabel;
	public GameObject dialogBox;
	public string dialogContent;

	// Use this for initialization
	void Start () {
		if( null!=dialogContent && null!=dialogBox){
			dialogContentLabel.text = dialogContent;
			dialogBox.SetActive(true);
		}
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
