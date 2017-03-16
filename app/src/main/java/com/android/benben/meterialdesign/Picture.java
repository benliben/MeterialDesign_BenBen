package com.android.benben.meterialdesign;

/**
 * Time      2017/3/16 17:49 .
 * Author   : LiYuanXiong.
 * Content  :
 */

public class Picture {
    private String pictureName;
    private int pictureId;

    public Picture(int pictureId, String pictureName) {
        this.pictureId = pictureId;
        this.pictureName = pictureName;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureNmae() {
        return pictureName;
    }

    public void setPictureNmae(String pictureNmae) {
        this.pictureName = pictureNmae;
    }
}
