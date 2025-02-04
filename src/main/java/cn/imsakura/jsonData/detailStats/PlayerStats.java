package cn.imsakura.jsonData.detailStats;
public class PlayerStats {
    private Data data; // Data对象
    private String errorMessage; // 错误信息
    private int statusCode; // 状态码

    // Data类
    public static class Data {
        private double adr; // Average Damage per Round
        private int assists; // Number of assists
        private String avatar; // Avatar URL or identifier
        private double avgWe; // Average Weapon Efficiency
        private int cnt; // Count of something (not specified in the JSON)
        private double commonRating; // Common rating
        private int deaths; // Number of deaths
        private double kd; // Kill-Death ratio
        private int kills; // Number of kills
        private String name; // Player's name
        private double rws; // Rating Weighted Score
        private double winRate; // Win rate

        // Getters and Setters for Data class
        public double getAdr() {
            return adr;
        }

        public void setAdr(double adr) {
            this.adr = adr;
        }

        public int getAssists() {
            return assists;
        }

        public void setAssists(int assists) {
            this.assists = assists;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public double getAvgWe() {
            return avgWe;
        }

        public void setAvgWe(double avgWe) {
            this.avgWe = avgWe;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public double getCommonRating() {
            return commonRating;
        }

        public void setCommonRating(double commonRating) {
            this.commonRating = commonRating;
        }

        public int getDeaths() {
            return deaths;
        }

        public void setDeaths(int deaths) {
            this.deaths = deaths;
        }

        public double getKd() {
            return kd;
        }

        public void setKd(double kd) {
            this.kd = kd;
        }

        public int getKills() {
            return kills;
        }

        public void setKills(int kills) {
            this.kills = kills;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getRws() {
            return rws;
        }

        public void setRws(double rws) {
            this.rws = rws;
        }

        public double getWinRate() {
            return winRate;
        }

        public void setWinRate(double winRate) {
            this.winRate = winRate;
        }
    }

    // Getters and Setters for PlayerStats class
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
