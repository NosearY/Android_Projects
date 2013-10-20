using UnityEngine;  
using System.Collections;  
  
  
  
public class PlayerRelativeJoystickControl : MonoBehaviour {  
    
	public VCDPadBase moveDpad;
	public VCAnalogJoystickBase rotateJoystick;
	
	public Transform cameraPivot;						// The transform used for camera rotation
		
	public Transform mainCharacter;
	
	public AnimationClip idleAnimation;
	public AnimationClip walkAnimation;
	public AnimationClip runAnimation;
	public AnimationClip jumpPoseAnimation;
	public AnimationClip pickPoseAnimation;
	
	float forwardSpeed  = 4f;
	float backwardSpeed = 1f;
	float sidestepSpeed  = 1f;
	float jumpSpeed = 8f;
	float inAirMultiplier = 0.25f;					// Limiter for ground speed while jumping
	//Vector2 rotationSpeed = Vector2( 50, 25 );	// Camera rotation speed for each axis
	
    //人物的行走方向状态  
    public const int HERO_UP= 0;  
    public const int HERO_RIGHT= 1;  
    public const int HERO_DOWN= 2;  
    public const int HERO_LEFT= 3;  
      
    //人物当前行走方向状态  
    public int state = 0;  
      
    //备份上一次人物当前行走方向状态  
    //这里暂时没有用到  
    public int backState = 0;  
      
    //游戏摇杆对象  
    public MPJoystick moveJoystick;    
      
    //这个方法只调用一次，在Start方法之前调用  
    public void Awake() {  
          
    }  
      
    //这个方法只调用一次，在Awake方法之后调用  
    void Start () {  
        state = HERO_DOWN;  
    }  
      
      
    void Update () {  
      
    //获取摇杆控制的方向数据 上一章有详细介绍    
    float touchKey_x =  moveJoystick.position.x;    
    float touchKey_y =  moveJoystick.position.y;    
          
        
       
    if(touchKey_x == -1){    
       setHeroState(HERO_LEFT);  
            
    }else if(touchKey_x == 1){    
       setHeroState(HERO_RIGHT);  
            
    }    
       
    if(touchKey_y == -1){    
        setHeroState(HERO_DOWN);  
   
    }else if(touchKey_y == 1){    
        setHeroState(HERO_UP);           
    }    
      
    if(touchKey_x == 0 && touchKey_y ==0){  
        //松开摇杆后播放默认动画，  
        //不穿参数为播放默认动画。  
        animation.Play();  
    }  
      
          
    }  
      
    public void setHeroState(int newState)  
    {  
          
        //根据当前人物方向 与上一次备份方向计算出模型旋转的角度  
        int rotateValue = (newState - state) * 90;  
        Vector3 transformValue = new Vector3();  
          
        //播放行走动画  
        animation.Play("walk");  
          
        //模型移动的位移的数值  
        switch(newState){  
            case HERO_UP:  
                transformValue = Vector3.forward * Time.deltaTime;  
            break;    
            case HERO_DOWN:  
                transformValue = -Vector3.forward * Time.deltaTime;  
            break;    
            case HERO_LEFT:  
                transformValue = Vector3.left * Time.deltaTime;  
                  
            break;    
            case HERO_RIGHT:  
                transformValue = -Vector3.left * Time.deltaTime;  
            break;                
        }  
          
          
        //模型旋转  
        transform.Rotate(Vector3.up, rotateValue);  
          
        //模型移动  
        transform.Translate(transformValue, Space.World);  
          
        backState = state;  
        state = newState;  
          
    }  
      
}  