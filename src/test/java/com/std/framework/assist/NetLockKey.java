package com.std.framework.assist;

/**
 * Created by gfy on 2016/4/15.
 */
public class NetLockKey {
    public String getFeedID() {
        return feedID;
    }

    public void setFeedID(String feedID) {
        this.feedID = feedID;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public BeaconSetting getToonBeaconSetting() {
        return toonBeaconSetting;
    }

    public void setToonBeaconSetting(BeaconSetting toonBeaconSetting) {
        this.toonBeaconSetting = toonBeaconSetting;
    }

    public String feedID;
    public String created_at;
    public String expiredTime;
    public BeaconSetting toonBeaconSetting;

    public static class BeaconSetting {
        public String gettBeaconID() {
            return tBeaconID;
        }

        public void settBeaconID(String tBeaconID) {
            this.tBeaconID = tBeaconID;
        }

        public String gettBeaconAdminPWD() {
            return tBeaconAdminPWD;
        }

        public void settBeaconAdminPWD(String tBeaconAdminPWD) {
            this.tBeaconAdminPWD = tBeaconAdminPWD;
        }

        public String gettBeaconTitle() {
            return tBeaconTitle;
        }

        public void settBeaconTitle(String tBeaconTitle) {
            this.tBeaconTitle = tBeaconTitle;
        }

        public String gettBeaconUUID() {
            return tBeaconUUID;
        }

        public void settBeaconUUID(String tBeaconUUID) {
            this.tBeaconUUID = tBeaconUUID;
        }

        public String gettBeaconType() {
            return tBeaconType;
        }

        public void settBeaconType(String tBeaconType) {
            this.tBeaconType = tBeaconType;
        }

        public String tBeaconID;
        public String tBeaconAdminPWD;
        public String tBeaconTitle;
        public String tBeaconUUID;
        public String tBeaconType;
    }
}
