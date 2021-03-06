package com.bytehawks.engine;

import javax.microedition.khronos.opengles.GL10;

/**
 * Base physic object
 * 
 * @author Tobias Schult
 * 
 */
public class BytehawksPhysicObject extends BytehawksObject
{
	public float mMass; // Masa
	public float mBounce; // Coefficient of restitution
	public BytehawksVector mVelocity;
	protected int mMaxCircleColliders;
	protected BytehawksCircleCollider mCircleColliders[];
	protected int mCircleCollidersCount;
	protected int mMaxSegmentColliders;
	protected BytehawksSegmentCollider mSegmentColliders[];
	protected int mSegmentCollidersCount;
	public BytehawksVector mDelta;
	private int mFriction;
	public BytehawksVector mPosition; //Set to change the position of the sprite

	public BytehawksPhysicObject(int maxSegmentColliders, int maxCircleColliders)
	{
		mMaxSegmentColliders = maxSegmentColliders;
		mMaxCircleColliders = maxCircleColliders;
		mCircleColliders = new BytehawksCircleCollider[mMaxCircleColliders];
		mCircleCollidersCount = 0;
		mSegmentColliders = new BytehawksSegmentCollider[mMaxSegmentColliders];
		mSegmentCollidersCount = 0;
		mPosition = new BytehawksVector(0, 0);
		mBounce = 1;
		mFriction = 1;
		mMass = 0; // Infinite mass
		mVelocity = new BytehawksVector();
		mDelta = new BytehawksVector();
	}

	public void addCircleCollider(BytehawksCircleCollider collider)
	{
		if (mCircleCollidersCount < mMaxCircleColliders)
		{
			collider.mObject = this;
			mCircleColliders[mCircleCollidersCount++] = collider;
		}
	}

	public void addSegmentCollider(BytehawksSegmentCollider collider)
	{
		if (mSegmentCollidersCount < mMaxSegmentColliders)
		{
			collider.mObject = this;
			mSegmentColliders[mSegmentCollidersCount++] = collider;
		}
	}

	public boolean collide(BytehawksPhysicObject other)
	{
		for (int mc = 0; mc < mCircleCollidersCount; mc++)
		{
			for (int oc = 0; oc < other.mSegmentCollidersCount; oc++)
			{
				if (mCircleColliders[mc].test(other.mSegmentColliders[oc]))
				{
					kynetics(other, other.mSegmentColliders[oc].mNormal);
					return true;
				}
			}
			for (int oc = 0; oc < other.mCircleCollidersCount; oc++)
			{
				if (mCircleColliders[mc].test(other.mCircleColliders[oc]))
				{
					kynetics(other, other.mCircleColliders[oc].mNormal);
					return true;
				}
			}
		}
		return false;
	}

	public boolean test(BytehawksPhysicObject other)
	{
		for (int mc = 0; mc < mCircleCollidersCount; mc++)
		{
			for (int oc = 0; oc < other.mSegmentCollidersCount; oc++)
			{
				if (mCircleColliders[mc].test(other.mSegmentColliders[oc]))
					return true;
			}
			for (int oc = 0; oc < other.mCircleCollidersCount; oc++)
			{
				if (mCircleColliders[mc].test(other.mCircleColliders[oc]))
					return true;
			}
		}
		return false;
	}

	public float getSurface()
	{
		return 0;
	}

	public void kynetics(BytehawksPhysicObject other, float normal)
	{
		// TODO rotar el sistema +normal, hacer los c�lculos y volver a rotar
		// -normal
		float nCos = (float) Math.cos(normal);
		float nSin = (float) Math.sin(normal);

		float mVelX = mVelocity.mX * nCos - mVelocity.mY * nSin;
		float mVelY = mVelocity.mY * nCos + mVelocity.mX * nSin;
		float oVelX = other.mVelocity.mX * nCos - other.mVelocity.mY * nSin;
		float oVelY = other.mVelocity.mY * nCos + other.mVelocity.mX * nSin;

		float e = mBounce * other.mBounce;
		float f = mFriction * other.mFriction;
		float momentum = mVelY * mMass + oVelY * other.mMass;
		float totalMass = mMass + other.mMass;
		float mFinalVelX = 0f;
		float mFinalVelY = 0f;
		float oFinalVelX = 0f;
		float oFinalVelY = 0f;
		if (mMass > 0) // Mass is not infinite
		{
			mFinalVelY = (momentum + other.mMass * e * (oVelY - mVelY)) / totalMass;
			// mFinalVelY=((mMass-other.mMass*e)*mVelY+other.mMass*(1+e)*oVelY)/totalMass;
			mFinalVelX = mVelX * (1 / f) - oVelX * (1 - (1 / f)); // Friction
		}

		if (other.mMass > 0) // Other mass is not infinite
		{
			oFinalVelY = (momentum + mMass * e * (mVelY - oVelY)) / totalMass;
			// oFinalVelY=(mMass*(1+e)*mVelY+(other.mMass-mMass*e)*oVelY)/totalMass;
			oFinalVelX = oVelX * (1 / f) + mVelX * (1 - (1 / f)); // Friction
		}
		else
			mFinalVelY = -mFinalVelY;

		if (mMass == 0) // Mass is infinite
			oFinalVelY = -oFinalVelY;

		nCos = (float) Math.cos(-normal);
		nSin = (float) Math.sin(-normal);

		// devuelve el sistema a su sitio
		mVelocity.mX = mFinalVelX * nCos - mFinalVelY * nSin;
		mVelocity.mY = mFinalVelY * nCos + mFinalVelX * nSin;
		other.mVelocity.mX = oFinalVelX * nCos - oFinalVelY * nSin;
		other.mVelocity.mY = oFinalVelY * nCos + oFinalVelX * nSin;
	}

	
	public void drawColliders(GL10 gl)
	{
		for (int mc = 0; mc < mCircleCollidersCount; mc++)
			mCircleColliders[mc].draw(gl);
		for (int mc = 0; mc < mSegmentCollidersCount; mc++)
			mSegmentColliders[mc].draw(gl);
	}

}
