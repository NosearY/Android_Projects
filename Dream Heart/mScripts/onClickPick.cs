using UnityEngine;
using System.Collections;

public class onClickPick : MonoBehaviour {
	public GameObject player;
	public AnimationClip pickAnimation;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
	
	void OnClick(){
		//Do pick up staff
		player.animation.Play(pickAnimation.name);
		
	}
}
