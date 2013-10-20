#pragma strict

public var standAnimation : AnimationClip;

function Start () {
		animation.CrossFadeQueued(standAnimation.name, 0.3, QueueMode.PlayNow, PlayMode.StopSameLayer);
		animation.CrossFadeQueued(standAnimation.name, 0.3, QueueMode.PlayNow, PlayMode.StopSameLayer);
		animation.CrossFadeQueued(standAnimation.name, 0.3, QueueMode.PlayNow, PlayMode.StopSameLayer);
}

function Update () {

}