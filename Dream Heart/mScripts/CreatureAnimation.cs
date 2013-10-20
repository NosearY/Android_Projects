using UnityEngine;
using System.Collections;

public class CreatureAnimation : MonoBehaviour
{
    #region AnimationNameSetting

    /// <summary>
    /// 待命动画名称
    /// </summary>
    public string IdleAnimationName = "Stand";

    /// <summary>
    /// 行走动画名称
    /// </summary>
    public string WalkAnimationName = "Walk";

    /// <summary>
    /// 左转动画名称
    /// </summary>
    public string ShuffleLeftAnimationName = "ShuffleLeft";

    /// <summary>
    /// 右转动画名称
    /// </summary>
    public string ShuffleRightAnimationName = "ShuffleRight";

    /// <summary>
    /// 后退动画名称
    /// </summary>
    public string WalkBackwardAnimationName = "Walkbackwards";

    /// <summary>
    /// 跑步动画名称
    /// </summary>
    public string RunAnimationName = "Run";

    /// <summary>
    /// 跳跃动画名称
    /// </summary>
    public string JumpStartAnimationName = "JumpStart";

    /// <summary>
    /// 跳跃结束继续跑动动画名称
    /// </summary>
    public string JumpLandRunAnimationName = "JumpLandRun";

    /// <summary>
    /// 跳跃结束动画名称
    /// </summary>
    public string JumpEndAnimationName = "JumpEnd";

    /// <summary>
    /// 游泳待命动画名称
    /// </summary>
    public string SwimIdleAnimationName = "SwimIdle";

    /// <summary>
    /// 向左游泳动画名称
    /// </summary>
    public string SwimLeftAnimationName = "SwimLeft";

    /// <summary>
    /// 向右游泳动画名称
    /// </summary>
    public string SwimRightAnimationName = "SwimRight";

    /// <summary>
    /// 向前游泳动画名称
    /// </summary>
    public string SwimForwardAnimationName = "Swim";

    /// <summary>
    /// 后退游泳动画名称
    /// </summary>
    public string SwimBackwardAnimationName = "SwimBackward";

    /// <summary>
    /// 下落动画名称
    /// </summary>
    public string FallAnimationName = "Fall";

    /// <summary>
    /// 攻击动画名称
    /// </summary>
    public string AttackUnarmedAnimationName = "AttackUnarmed";

    /// <summary>
    /// 准备攻击动画名称
    /// </summary>
    public string ReadyUnarmedAnimationName = "ReadyUnarmed";

    /// <summary>
    /// 死亡动画名称
    /// </summary>
    public string DeadAnimationName = "Death";

    #endregion

    /// <summary>
    /// 获取或者设置动画的对象
    /// </summary>
    public Animation AnimationTarget;

    /// <summary>
    /// 播放待命状态动画
    /// </summary>
    public void PlayIdle()
    {
        playAnimation(IdleAnimationName);
    }

    /// <summary>
    /// 播放左转动画
    /// </summary>
    public void PlayShuffleLeft()
    {
        playAnimation(ShuffleLeftAnimationName);
    }

    /// <summary>
    /// 播放右转动画
    /// </summary>
    public void PlayShuffleRight()
    {
        playAnimation(ShuffleRightAnimationName);
    }

    /// <summary>
    /// 播放行走动画
    /// </summary>
    public void PlayWalk()
    {
        playAnimation(WalkAnimationName);
    }

    /// <summary>
    /// 播放后退动画
    /// </summary>
    public void PlayWalkBackward()
    {
        playAnimation(WalkAnimationName, 0.5f);
    }

    /// <summary>
    /// 播放向前游泳的动画
    /// </summary>
    public void PlaySwimForward()
    {
        playAnimation(SwimForwardAnimationName);
    }

    /// <summary>
    /// 播放向后游泳的动画
    /// </summary>
    public void PlaySwimBackward()
    {
        playAnimation(SwimBackwardAnimationName);
    }

    /// <summary>
    /// 播放游泳待机的动画
    /// </summary>
    public void PlaySwimIdle()
    {
        playAnimation(SwimIdleAnimationName);
    }

    /// <summary>
    /// 播放跑步动画
    /// </summary>
    public void PlayRun()
    {
        playAnimation(RunAnimationName);
    }

    /// <summary>
    /// 播放跳跃动画
    /// </summary>
    public void PlayJumpStart()
    {
        playAnimation(JumpStartAnimationName, 1.0f, WrapMode.ClampForever);
    }

    /// <summary>
    /// 播放跳跃动画
    /// </summary>
    public void PlayJump()
    {
        playAnimation(FallAnimationName);
    }

    /// <summary>
    /// 播放跳跃结束后继续奔跑的动画
    /// </summary>
    public void PlayJumpLandRun()
    {
        playAnimation(JumpLandRunAnimationName);
    }

    /// <summary>
    /// 播放跳跃结束动画
    /// </summary>
    public void PlayJumpEnd()
    {
        playAnimation(JumpEndAnimationName, 1.0f, WrapMode.ClampForever);
    }

    /// <summary>
    /// 播放下落动画
    /// </summary>
    public void PlayFall()
    {
        playAnimation(FallAnimationName);
    }

    /// <summary>
    /// 播放攻击等待中的动画
    /// </summary>
    public void PlayAttackReady()
    {
        if (!isPlaying(AttackUnarmedAnimationName))
            playAnimation(IdleAnimationName);
    }

    /// <summary>
    /// 播放死亡动画
    /// </summary>
    public void PlayDead()
    {
        playAnimation(DeadAnimationName, 1.0f, WrapMode.ClampForever);
    }

    /// <summary>
    /// 获取一个值，表示是否正在播放指定动画
    /// </summary>
    /// <param name="iAnimationName">动画名称</param>
    /// <returns></returns>
    public bool IsPlaying(string iAnimationName)
    {
        return isPlaying(iAnimationName);
    }

    /// <summary>
    /// 当生物攻击时调用此方法
    /// </summary>
    public void OnAttack()
    {
        playAnimation(AttackUnarmedAnimationName);
    }

    private void playAnimation(string iAnimationName, float iSpeed = 1.0f, WrapMode iWarpMode = WrapMode.Loop)
    {
        //播放指定动画（必须存在且未在播放状态）
        if (AnimationTarget == null)
            throw new MissingReferenceException("AnimationTarget");
        if (string.IsNullOrEmpty(iAnimationName) || AnimationTarget[iAnimationName] == null)
            throw new UnityException(string.Format("The animation state '{0}' could not be played because it couldn't be found!", iAnimationName));

        var isPlaying = AnimationTarget.IsPlaying(iAnimationName);
        if (!isPlaying)
        {
            AnimationTarget.wrapMode = iWarpMode;
            AnimationTarget[iAnimationName].speed = iSpeed;
            AnimationTarget.CrossFade(iAnimationName);
        }
    }
    private bool isPlaying(string iAnimationName)
    {
        //获取一个值，表示某个动画是否正在播放
        if (string.IsNullOrEmpty(iAnimationName))
            return false;
        var isPlaying = AnimationTarget.IsPlaying(iAnimationName);
        return isPlaying;
    }
}