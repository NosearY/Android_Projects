using UnityEngine;
using System.Collections;

public class SceneLoaderProgress : MonoBehaviour {
	public UISlider progressbar;
	public string PlaySessionName;
	public UIPopupList sessionList;
	
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	void OnClick(){
		
		PlaySessionName = sessionList.selection;
		
		StartCoroutine(LoadSession());
		progressbar.sliderValue-=0.1f;
	}
	
	IEnumerator LoadSession() {
        AsyncOperation async = Application.LoadLevelAsync(PlaySessionName);
        yield return async;
        Debug.Log("Loading complete");
    }
}
