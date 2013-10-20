using UnityEngine;
using System.Collections;

[ExecuteInEditMode]
public class fBtn : MonoBehaviour {
	public GameObject character = null;
	public AnimationClip pickAnimation = null;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	void OnClick() {
		character.animation.CrossFadeQueued(pickAnimation.name, 0.3f, QueueMode.PlayNow, PlayMode.StopSameLayer);
	}
}
