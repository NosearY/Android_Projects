using UnityEngine;

[AddComponentMenu("NGUI/Examples/Item Attachment Point")]
public class InvAttachmentSword : MonoBehaviour
{
	/// <summary>
	/// Item slot that this attachment point covers.
	/// </summary>

	public InvBaseItem.Slot slot;
	
	public GameObject mSword;

	GameObject mPrefab;
	GameObject mChild;

	/// <summary>
	/// Attach an instance of the specified game object.
	/// </summary>

	public GameObject Attach (GameObject prefab)
	{
		Debug.Log("Attaching");
		if (mPrefab != prefab)
		{
			mPrefab = prefab;

			// Remove the previous child
			//if (mChild != null) Destroy(mChild);
			mSword.SetActive(false);

			// If we have something to create, let's do so now
			if (mPrefab != null)
			{
				mSword.SetActive(true);
			}
		}
		return mChild;
	}
}