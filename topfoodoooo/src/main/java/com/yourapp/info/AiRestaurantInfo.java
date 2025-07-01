package com.yourapp.info;

public class AiRestaurantInfo {
    private String name;
    private String url;
    private String photoUrl;  // ✅ 新增圖片欄位
    
 // ⭐ 加這個無參建構子
    public AiRestaurantInfo() {
    }
    public AiRestaurantInfo(String name, String url, String photoUrl) {
        this.name = name;
        this.url = url;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhotoUrl() {           // ✅ 加入 getter
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) { // ✅ 加入 setter
        this.photoUrl = photoUrl;
    }
    
}
