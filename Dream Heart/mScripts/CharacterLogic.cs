using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class CharacterLogic : MonoBehaviour {
	//Chracter is both animation performer and logic reciever!
	public GameObject character = null;
	public GameObject player;
	public UISlider hpbar = null;
	public AnimationClip pickAnimation = null;
	public AnimationClip attackAnimation = null;
	public AnimationClip dieAnimation = null;
	
	public float Damage=60;
	public float AttackSpeed=0.7f;
	public float pickableRange = 3.0f;
	
	private bool kill;
	public bool dead;
	private bool playd;
	private float atime;
	private bool dealdamage;
	public int TotalAICount;
	
	public List<Transform> KillList;
	
	private Health mhp;

	// Use this for initialization
	void Start () {
		mhp = (Health)player.GetComponent("Health");
	}
	
	// Update is called once per frame
	void Update () {
		
		UpdateHpBar();
		CheckButton();
		UpdateFighting();
	}
	
	void RangeMessger(Vector3 center, float radius) {
        Collider[] hitColliders = Physics.OverlapSphere(center, radius);
        int i = 0;
        while (i < hitColliders.Length) {
            hitColliders[i].SendMessage("PickableObjectPickToInv", SendMessageOptions.DontRequireReceiver);
            i++;
        }
    }
	
	void UpdateHpBar(){
		hpbar.sliderValue = mhp.CurrentHealth/mhp.MaxHealth;
		if(mhp.CurrentHealth<=0f){
			dead=true;
		}
		if(dead){
			if(playd){
			character.animation.CrossFade( dieAnimation.name, 0.15f);	
			}
			playd=false;
			
		}
	}
	
	void CheckButton(){
		//Handle button A for Attack
		VCButtonBase abtn = VCButtonBase.GetInstance("A");
		if (abtn != null )
		{
			if( abtn.Pressed )
			{
				character.animation.CrossFadeQueued(attackAnimation.name, 0.3f
					, QueueMode.PlayNow, PlayMode.StopSameLayer);
				
				dealdamage=true;
				kill=true;	
			}
		}
		
		//Handle button F for PICK UP or other action eg: OPEN DOOR
		VCButtonBase fbtn = VCButtonBase.GetInstance("F");
		if (fbtn != null )
		{
			if( fbtn.Pressed )
			{
				character.animation.CrossFadeQueued(pickAnimation.name, 0.3f
					, QueueMode.PlayNow, PlayMode.StopSameLayer);
				
				//Perform pick up logic
				//P.L. 1: detect pickable object within a range
				Vector3 center = character.transform.position;
				RangeMessger(center, pickableRange);
				//character.SendMessage("playTest","apple teset!");
			}
		}
	}
	
	void UpdateFighting(){
		if(kill){
			atime+=Time.deltaTime;
			character.animation[attackAnimation.name].speed = character.animation[attackAnimation.name].length / AttackSpeed;
			character.animation.CrossFade( attackAnimation.name, 0.15f);
			
			if(atime>=AttackSpeed*0.35f&atime<=AttackSpeed*0.48f){
			if(KillList.Count>0&dealdamage){
				int	ls=KillList.Count;
				for (int i = 0; i < ls; i++){
					Health hp=(Health)KillList[i].transform.GetComponent("Health");
							
						hp.CurrentHealth=hp.CurrentHealth-Damage;
							if(hp.Dead){}
							else if(hp.CurrentHealth<=0)TotalAICount=TotalAICount-1;
					}
					dealdamage=false;
				}
			}
			
			if(atime>=AttackSpeed){
				kill=false;
				atime=0;
			}
		}
	}
	
	void OnEnemeyEnter(Collider other){
		Debug.Log("OnEnemeyEnter");
		Health AI=(Health)other.transform.GetComponent("Health");
		if(AI){
		KillList.Add(other.transform);
			
		}
	}
	
	void OnEnemeyExit(Collider other){
		
		Health AI=(Health)other.transform.GetComponent("Health");
		if(AI){
		KillList.Remove(other.transform);
			
		}
	}
	
}
