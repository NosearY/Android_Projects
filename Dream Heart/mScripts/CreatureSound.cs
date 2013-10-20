using UnityEngine;
using System.Collections;

[RequireComponent(typeof(AudioSource))]
public class CreatureSound : MonoBehaviour
{
    /// <summary>
    /// 攻击音效
    /// </summary>
    public AudioClip AttackClip;

    /// <summary>
    /// 受伤音效
    /// </summary>
    public AudioClip WoundedClip;

    /// <summary>
    /// 发现敌人音效
    /// </summary>
    public AudioClip ArrgoClip;

    /// <summary>
    /// 死亡音效
    /// </summary>
    public AudioClip DeadClip;

    private AudioSource mAudioSource;

    void Start()
    {
        mAudioSource = GetComponent<AudioSource>();
        mAudioSource.rolloffMode = AudioRolloffMode.Linear;
    }

    void Update()
    {

    }

    /// <summary>
    /// 当生物受到攻击时调用此方法
    /// </summary>
    public void OnAttack()
    {
        playSound(AttackClip);
    }

    /// <summary>
    /// 当生物发现目标并进入攻击状态时调用此方法
    /// </summary>
    public void OnArrgo()
    {
        playSound(ArrgoClip);
    }

    /// <summary>
    /// 当生物受伤时调用此方法
    /// </summary>
    public void OnWounded()
    {
        playSound(WoundedClip);
    }

    /// <summary>
    /// 当生物死亡时调用此方法
    /// </summary>
    public void OnDead()
    {
        playSound(DeadClip);
    }

    private void playSound(AudioClip iAudioClip)
    {
        //播放指定声音
        if (iAudioClip == null)
            return;
        mAudioSource.clip = iAudioClip;
        mAudioSource.Play();
    }
}