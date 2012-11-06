package de.schaf.space.util;

public class Vec3 {
	   public Float x,y,z;
	   
	   public Vec3() {
	  }
	    
	   public Vec3(Float x, Float y, Float z) {
	      this.x = x;
	      this.y = y;
	      this.z = z;
	  }

	  public Vec3(Vec3 Vec2) {
	      x = Vec2.x;
	      y = Vec2.y;
	      z = Vec2.z;
	  }
	  public Vec3 multiply(Float magnitude)
	  {
	      Vec3 Float = new Vec3(this);
	      Float.x = this.x * magnitude;
	      Float.y = this.y * magnitude;
	      return Float;
	  }
	  public Vec3 add(Float magnitude, Float Angle, Float Angle2)
	  {
	      Vec3 V = new Vec3();

	      V.x = x + magnitude * (float)Math.cos(Angle) * (float)Math.sin(Angle2);
	      V.y = y - magnitude * (float)Math.sin(Angle) * (float)Math.sin(Angle2);
	      V.z = z + magnitude * (float)Math.cos(Angle2);
	      return V;
	  }
	  public Vec3 add(Vec3 Vec2)
	  {
	      Vec3 Float = new Vec3();
	      Float.x = x+ Vec2.x;
	      Float.y = y+ Vec2.y;
	      Float.z = z+ Vec2.z;
	      return Float;
	  }
	  public Vec3 subtract(Vec3 Vec2)
	  {
	      Vec3 Float = new Vec3();
	      Float.x = x- Vec2.x;
	      Float.y = y- Vec2.y;
	      return Float;
	  }
	  public Vec3 rotate(Float Rotation, Float Rotation2) {

	      Vec3 V2 = new Vec3();
	      Float AngleX = getAngleX()+Rotation;
	      Float AngleY = getAngleY()+Rotation2;

	      V2.x = getMagnitude()  * (float)Math.cos(AngleX) * (float)Math.sin(AngleY);
	      V2.y = -getMagnitude() * (float)Math.sin(AngleX); 
	      V2.z = getMagnitude()  * (float)Math.cos(AngleY);
	      return V2;
	     
	  }
	  public Float getAngleX() {
	      return (float)Math.atan2(y,x);
	  }
	  public Float getAngleY() {
	      return (float)Math.atan2(z,y);
	  }
	  public Float getAngleZ() {
	      return (float)Math.atan2(z,x);
	  }
	  
	  public void wrap(float xlimit, float ylimit,float zlimit)
	  {
	      while(x < 0)      x += xlimit;
	      while(x > xlimit) x -= xlimit;
	      while(y < 0)      y += ylimit;
	      while(y > ylimit) y -= ylimit;
	      while(z < 0)      z += zlimit;
	      while(z > zlimit) z -= zlimit;
	  }
	  public void bounce(float xlimit, float ylimit,float zlimit)
	  {
	      if (x < 0) x = 0.0f;
	      if (x > xlimit) x = xlimit;
	      if (y < 0) y = 0.0f;
	      if (y > ylimit) y = ylimit;  
	      if (z < 0) z = 0.0f;
	      if (z > ylimit) z = zlimit; 
	  }
	  public boolean bounds(float xlimit, float ylimit,float zlimit)
	  {
	      if (x < 0) return false;
	      if (x > xlimit) return false;
	      if (y < 0) return false;
	      if (y > ylimit) return false;
	      if (z < 0) return false;
	      if (z > ylimit) return false; 
	      return true;
	  }

	  public Float getMagnitude()
	  {
	      return (float)Math.sqrt(x*x+y*y+z*z);
	  }
	  public String toString()
	  {
	     return "Vec: x: "+x+" y: "+y+" z: "+z; 
	  }
	  
	      
	  public static   Vec3 VectorOffset(Vec3 pIn, Vec3 pOffset) {
	        Vec3 pOut = new Vec3();
	        pOut.x = pIn.x - pOffset.x;
	        pOut.y = pIn.y - pOffset.y;
	        pOut.z = pIn.z - pOffset.z;
	        return pOut;
	    }
	// Compute the cross product a X b into pOut
	   public static  Vec3 VectorGetNormal(Vec3 a, Vec3 b) {
	        Vec3 pOut = new Vec3();
	        pOut.x = a.y * b.z - a.z * b.y;
	        pOut.y = a.z * b.x - a.x * b.z;
	        pOut.z = a.x * b.y - a.y * b.x;
	        return pOut;
	    }
	// Normalize pIn vector into pOut
	   public static  Vec3 VectorNormalize(Vec3 pIn) {
	        Vec3 pOut = new Vec3();
	        float len = pIn.getMagnitude();
	        
	        pOut.x = pIn.x / len;
	        pOut.y = pIn.y / len;
	        pOut.z = pIn.z / len;
	        
	        return pOut;
	    }
	// Compute p1,p2,p3 face normal into pOut
	   public static Vec3 ComputeFaceNormal(Vec3 p1, Vec3 p2, Vec3 p3) {
	// Uses p2 as a new origin for p1,p3
	        Vec3 a = VectorOffset(p3, p2);
	        Vec3 b = VectorOffset(p1, p2);

	// Compute the cross product a X b to get the face normal
	        Vec3 pn = VectorGetNormal(a, b);
	// Return a normalized vector
	        return VectorNormalize(pn);
	    }
}
