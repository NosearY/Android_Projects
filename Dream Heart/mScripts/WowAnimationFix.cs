using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class WowAnimationFix : MonoBehaviour
{
    private const float ANIMATION_CUT_FRAME = 2.5f;

    void Start()
    {
        var defaultClip = animation.clip;

        //获取所有的AnimationState
        var states = new List<AnimationState>();
        foreach (AnimationState state in animation)
            states.Add(state);
		
//		for(int i=0;i<states.Count;i++){
//			if( states[i].clip.name.Equals("Death") )states[i].clip.isLooping = false;
//		}
        //剪掉所有动画最后崩坏的部分
        foreach (var state in states)
        {
            var clip = state.clip;
            animation.RemoveClip(clip);
			//if( clip.name.Equals("Death") )clip.wrapMode = WrapMode.Once;
            animation.AddClip(clip, clip.name, 0, (int)(clip.frameRate * clip.length - ANIMATION_CUT_FRAME), true);
        }

        if (defaultClip != null)
        {
            animation.clip = animation.GetClip(defaultClip.name);
            if (animation.playAutomatically)
            {
                animation.wrapMode = WrapMode.Loop;
                animation.Play();
            }
        }
    }
}