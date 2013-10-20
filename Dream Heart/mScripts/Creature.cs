using UnityEngine;
using System.Collections;

/// <summary>
/// 生物组件
/// </summary>
public class Creature : MonoBehaviour
{
    /// <summary>
    /// 名称
    /// </summary>
    public string Name;

    /// <summary>
    /// 等级
    /// </summary>
    public int Level;

    /// <summary>
    /// 攻击力
    /// </summary>
    public int AttackPower;

    /// <summary>
    /// 护甲
    /// </summary>
    public int Armor;

    /// <summary>
    /// 生命
    /// </summary>
    public float Health;

    /// <summary>
    /// 最大生命值
    /// </summary>
    public float MaxHealth;

    /// <summary>
    /// 能量
    /// </summary>
    public float Power;

    /// <summary>
    /// 最大能量值
    /// </summary>
    public float MaxPower;

    /// <summary>
    /// 经验值
    /// </summary>
    public float Exp;

    /// <summary>
    /// 最大经验值
    /// </summary>
    public float MaxExp;

    /// <summary>
    /// 目标生物
    /// </summary>
    public Creature Target
    {
        get
        {
            return mTarget;
        }
        set
        {
            mTarget = value;
        }
    }

    private Creature mTarget;

    void Start()
    {

    }

    void Update()
    {

    }

    /// <summary>
    /// 攻击目标
    /// </summary>
    /// <param name="iTarget">目标</param>
    public void Attack(Creature iTarget)
    {
        SendMessage("OnAttack");
        iTarget.UnderAttack(this);
    }

    /// <summary>
    /// 受到攻击
    /// </summary>
    /// <param name="iSource">攻击来源</param>
    protected void UnderAttack(Creature iSource)
    {
        var damage = iSource.AttackPower - iSource.Armor;
        if (damage <= 0)
            damage = 1;
        Health -= damage;
        Debug.Log(string.Format("{0}->{1} damage={2}", iSource, this, damage));
        SendMessage("OnWounded");
        if (Health <= 0)
        {
            Health = 0;
            SendMessage("OnDead");
        }
    }
}