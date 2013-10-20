using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class EnemyAiControler : MonoBehaviour {
	//THE CHARACTER COLLISION LAYER FOR TARGETS
	public Transform aiCharacter;
	public GameObject deadDisableCharacterObejct;
	public int characterCollisionLayer=~((1 << 8) | (1 << 2));
	//ENABLES MELEE COMBAT
	public bool enableCombat=true;
	//THE TARGET WHICH HE FOLLOWS AND ATTACKS
	public Transform target;
	//THE VECTOR OF THE TARGET
	private Vector3 currentTarget;
	private float targetYPostionBias = 2f;
	//TARGET VISIBIL BOOL
	private bool targetVisible;
	private bool moveToTarget;
	//SPEED WHICH THE AI TURNS
	public float turnSpeed=5;
	//SPEED WHICH AI RUNS
	public float runSpeed=4;
	public float damage=10;
	public float attackSpeed=1;
	public float attackRange=5;
	public float visionRange=20;
	
	//WHEN THE DAMAGE HAS BEEN DEALT
	private bool damageDealt;
	
	
	//ANIMATIONS
	public AnimationClip RunAnimation;
	public AnimationClip IdleAnimation;
	public AnimationClip AttackAnimation;
	public AnimationClip DeathAnimation;
	private bool stop;
	private bool wwing;
	public bool isDead = false;
	private bool deadPlayed;
	public bool disappearAfterDead = false;
	
	private Vector3 stopPosition;
	
	private float Atimer;
	private bool startFollow;
	//PATHFINDING STUFF
	public bool EnableFollowNodePathFinding;
	public bool DebugShowPath = true;
	public float DistanceNodeChange=1.5f;
	public List<Vector3> Follownodes;
	private int curf;
	
	// Use this for initialization
	void Start () {
		if(aiCharacter){}
		else aiCharacter=transform;
	}
	
	// Update is called once per frame
	void Update () {
		
		Health mhp=(Health)gameObject.GetComponent("Health");	
		if(mhp.CurrentHealth<=0.1f){
			isDead = true;
			if(disappearAfterDead)deadDisableCharacterObejct.SetActive(false);
		}
		
		if(isDead){
			if(DeathAnimation){
				if(deadPlayed){
					aiCharacter.animation.Stop();
					
				}else{
					Debug.Log("Enemy Dead!");
					aiCharacter.animation.CrossFadeQueued(DeathAnimation.name, 0.1f
						, QueueMode.PlayNow, PlayMode.StopSameLayer);
					//Lay down
					aiCharacter.eulerAngles = new Vector3(0,0,aiCharacter.eulerAngles.z-90f);
					aiCharacter.position = new Vector3(aiCharacter.position.x,aiCharacter.position.y-1f,aiCharacter.position.z);
				}
				deadPlayed=true;
			}
			
		}else{//ELSE isDead
			//COMBAT BEHAVE
			if(target){
				
				float tDist=Vector3.Distance(target.position, transform.position);
				//Debug.Log("tDist "+tDist);
				if(tDist<=attackRange){
					if(false==stop)stopPosition = transform.position;//record first time stop to fix animation problem
					stop=true;
					targetYPostionBias=4f;
				}else stop=false;
				
				//RAYCAST VISION SYSTEM	
				RaycastHit hit = new RaycastHit();	
				LayerMask lay=characterCollisionLayer;
				//Vector3 pdir = (target.transform.position - transform.position).normalized;
				//float playerDirection = Vector3.Dot(pdir, transform.forward);
//				if(Physics.Linecast(transform.position, target.position, out hit, lay)){
//					Debug.Log("Target hidden! "+playerDirection);
//					targetVisible=false;	
//				}else{
//					Debug.Log("Target saw! "+playerDirection);
//					if(playerDirection > 0){
//						startFollow=true;
//						targetVisible=true;	
//					}
//						//TargetVisible=false;	
//				}
				
				if( visionRange>0 ){
					if(visionRange>=tDist){ //playerDirection>0 && 
						startFollow=true;
						targetVisible=true;
						currentTarget.y+=targetYPostionBias;
						transform.rotation = Quaternion.Slerp(transform.rotation
							, Quaternion.LookRotation(currentTarget - transform.position), turnSpeed * Time.deltaTime);
					}else{
						//Debug.Log("Target hidden! "+playerDirection);
						targetVisible=false;	
					}
				}else{
					startFollow=true;
					targetVisible=true;	
				}
				
				//Debug.Log("playerDirection "+playerDirection);
			}//END target
			
			//IF THE TARGET IS VISIBLE
			if(targetVisible){
				currentTarget=target.position;
					moveToTarget=true;
			}
			
			//MOVES/RUNS TO TARGET
			if(moveToTarget){
				
				if(stop){
					//Debug.Log("Stop");
					//Fix wow animation problem
					transform.position = stopPosition;
				}else{
					//Debug.Log("Move");
					transform.position += transform.forward * +runSpeed * Time.deltaTime;
				}
				
				if(RunAnimation){
					if(stop){
						//COMBAT!
						if(enableCombat){
							Health hp=(Health)target.transform.GetComponent("Health");	
							if(hp.CurrentHealth>0){
								
								Atimer+=Time.deltaTime;	
								aiCharacter.animation[AttackAnimation.name].speed = aiCharacter.animation[AttackAnimation.name].length / attackSpeed;
								aiCharacter.animation.CrossFadeQueued(AttackAnimation.name, 0.3f
									, QueueMode.PlayNow, PlayMode.StopSameLayer);
								
								if(damageDealt){}
								else{
									if(Atimer>=attackSpeed*0.35&Atimer<=attackSpeed*0.45){
									//LETS DO SOME DAMAGE!
										if(hp){
											hp.CurrentHealth=hp.CurrentHealth-damage;
											damageDealt=true;
										}
									}
								}
									
								if(Atimer>=attackSpeed){
									damageDealt=false;
									Atimer=0;
								}
									
							}else{
								aiCharacter.animation.CrossFadeQueued(IdleAnimation.name, 0.3f
									, QueueMode.CompleteOthers, PlayMode.StopSameLayer);
								//isDead = true;
							}
						}else{
							aiCharacter.animation.CrossFadeQueued(IdleAnimation.name, 0.3f
								, QueueMode.CompleteOthers, PlayMode.StopSameLayer);
						}
					}else{
						Atimer=0;
						aiCharacter.animation.CrossFadeQueued(RunAnimation.name, 0.3f
							, QueueMode.CompleteOthers, PlayMode.StopSameLayer);
					}//END stop
				}//END RunAnimation
				
			}else{//ELSE moveToTarget
				if(IdleAnimation&&!enableCombat){
					aiCharacter.animation.CrossFadeQueued(IdleAnimation.name, 0.3f
						, QueueMode.CompleteOthers, PlayMode.StopSameLayer);
				}
			}//END moveToTarget
			
			//FOLLOW PATHFINDING
			if(targetVisible){}
			else{
				if(EnableFollowNodePathFinding&startFollow){
				if(Follownodes.Count<=0)Follownodes.Add(currentTarget);
			
				RaycastHit hit = new RaycastHit();	
				LayerMask lay=characterCollisionLayer;
						
				if(Physics.Linecast(Follownodes[Follownodes.Count-1], target.position, out hit, lay)){	
					Follownodes.Add(target.position);
				}
					
				float dist=Vector3.Distance(transform.position, Follownodes[0]);
					if(dist<DistanceNodeChange){
						 Follownodes.Remove(Follownodes[0]);
					}
				}
			}//END TargetVisible
			
		}//END isDead
		
		
		
		if(targetVisible&Follownodes.Count>0){
			Follownodes.Clear();
		}
		
		if(DebugShowPath){
			if(Follownodes.Count>0){
				int listsize=Follownodes.Count;
				Debug.DrawLine(Follownodes[0], transform.position, Color.green);
				for (int i = 0; i < listsize; i++){
					if(i<Follownodes.Count-1){
					Debug.DrawLine(Follownodes[i], Follownodes[i+1], Color.green);
					}
				}
			}
			//POINT AT TARGET
			if(moveToTarget){
				if(Follownodes.Count>0){
					transform.rotation = Quaternion.Slerp(transform.rotation
							, Quaternion.LookRotation(Follownodes[0] - transform.position), turnSpeed * Time.deltaTime);	
				}else{
					transform.rotation = Quaternion.Slerp(transform.rotation
							, Quaternion.LookRotation(currentTarget - transform.position), turnSpeed * Time.deltaTime);
				}
			}
				
			transform.eulerAngles = new Vector3(0,transform.eulerAngles.y,0);
	
		}//END DebugShowPath
		
		
	}//END OF Update
}
