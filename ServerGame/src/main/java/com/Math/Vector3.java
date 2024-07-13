package com.Math;

public class Vector3 {
    private float X;
    private float Y;
    private float Z;

    public Vector3(float X, float Y, float Z){
       this.X = X;
       this.Y = Y;
       this.Z = Z;
    }

    public float getX() {
        return X;
    }
    public float getY() {
        return Y;
    }
    public float getZ() {
        return Z;
    }
    public void setX(float x) {
        X = x;
    }
    public void setY(float y) {
        Y = y;
    }public void setZ(float z) {
        Z = z;
    }

    @Override
    public String toString() {
        return "(" + X +", "+ Y + ", "+ Z + ")";
    }
}
