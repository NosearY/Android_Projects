using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System;

public class LoadPicCountDown : MonoBehaviour {
    public int currentTime;
    public int startTime;
    public int second;
	public List<GameObject> pictures;
	
	public int pauseSecond = 4;
	public bool gotoScene = false;
	public string sceneName;
	
	public int picturePlayCount=0;
	
    void Start () {
        startTime = Mathf.CeilToInt(Time.fixedTime);
    }
	
    void Update () {
        currentTime = Mathf.CeilToInt(Time.fixedTime);
		second = (currentTime - startTime) % 60;
		
		if(gotoScene){
			StartCoroutine(LoadSession());
			gotoScene=false;
		}
		
        if(second>pauseSecond && picturePlayCount<pictures.Count-1){
			
			pictures[picturePlayCount].SetActive(false);
			picturePlayCount++;
			pictures[picturePlayCount].SetActive(true);
			
			if(picturePlayCount>=pictures.Count-1) gotoScene=true;
			
			startTime = Mathf.CeilToInt(Time.fixedTime);
		}
    }
	
	IEnumerator LoadSession() {
        AsyncOperation async = Application.LoadLevelAsync(sceneName);
        yield return async;
        Debug.Log("Loading complete");
    }
}