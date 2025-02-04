package cn.imsakura.jsonData.search;

public class PwcsUser {
    private String steamId;
    private String pvpNickName;
    private String pvpAvatar;
    private String steamNickName;
    private String steamAvatar;
    private String appNickName;
    private long userId;
    private int ladderType;

    // Getter 和 Setter
    public String getSteamId() { return steamId; }
    public void setSteamId(String steamId) { this.steamId = steamId; }

    public String getPvpNickName() { return pvpNickName; }
    public void setPvpNickName(String pvpNickName) { this.pvpNickName = pvpNickName; }

    public String getPvpAvatar() { return pvpAvatar; }
    public void setPvpAvatar(String pvpAvatar) { this.pvpAvatar = pvpAvatar; }

    public String getSteamNickName() { return steamNickName; }
    public void setSteamNickName(String steamNickName) { this.steamNickName = steamNickName; }

    public String getSteamAvatar() { return steamAvatar; }
    public void setSteamAvatar(String steamAvatar) { this.steamAvatar = steamAvatar; }

    public String getAppNickName() { return appNickName; }
    public void setAppNickName(String appNickName) { this.appNickName = appNickName; }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }

    public int getLadderType() { return ladderType; }
    public void setLadderType(int ladderType) { this.ladderType = ladderType; }
}
