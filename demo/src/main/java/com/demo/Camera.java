package com.demo;

/**
 * Camera looks at our image in 3D world.
 * @author Muslima Karimova (2130288) & Fahim Samady (2035827)
 * @version 1.0
 */
public class Camera {
    public Vector VRP; // view reference point - position of camera
    public Vector VUV; // view up vector - y-axis of the camera
    public Vector VRV; // view right vector - x-axis of the camera
    public Vector lookAt; // where is camera pointing
    public Vector VPN; // view plane normal - direction camera is looking
    public final int DISTANCE = 300;

    public Camera(Vector lookAt){
        this.lookAt = lookAt;
        VRP = new Vector(0,0, DISTANCE);
        VUV = new Vector(0,1,0); // approximate - up
        VRV = new Vector(1,0,0); // approximate - right
    }

    public void setup() {
        VPN = lookAt.sub(VRP);
        VPN.normalise();
        VRV = VPN.cross(VUV);
        VRV.normalise();
        VUV = VRV.cross(VPN);
        VUV.normalise();
    }

}
