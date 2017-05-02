package com.zmm.unityshoestest;

import java.io.Serializable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/5/2
 * Time:上午9:21
 */

public class GetAngleXYZ implements Serializable{
    public float xPosition;
    public float yposition;
    public float zPosition;

    public GetAngleXYZ(float x, float y,float z)
    {
        this.xPosition = x;
        this.yposition = y;
        this.zPosition = z;
    }
}
