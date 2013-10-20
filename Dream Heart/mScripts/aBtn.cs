using UnityEngine;
using System.Collections;

[ExecuteInEditMode]
public class aBtn : MonoBehaviour {
	public GameObject character = null;
	public AnimationClip attackAnimation = null;
	
	// Use this for initialization
	void Start () {
		Debug.Log("A btn created!");
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	void OnClick() {
		Debug.Log("A btn clicked!");
		character.animation.CrossFadeQueued(attackAnimation.name, 0.3f, QueueMode.PlayNow, PlayMode.StopSameLayer);
	}
	
	void OnPress(bool isDown) {
		Debug.Log("A btn OnPress"+isDown);
		character.animation.CrossFadeQueued(attackAnimation.name, 0.3f, QueueMode.PlayNow, PlayMode.StopSameLayer);
	}
}
