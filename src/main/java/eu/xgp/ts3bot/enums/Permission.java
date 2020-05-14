package eu.xgp.ts3bot.enums;

public enum Permission {
    HELPER(30),
    HEADHELPER(29),
    MOD(28),
    HEADMOD(27),
    BUILDER(26),
    Developer(25),
    Pluginner(24),
    BLACK(31),
    LEGEND(32),
    CRYSTAL(33),
    DEFINITIVE(34),
    XGPRIME(35),
    Owner(6),
    Founder(16),
    CoFounder(15),
    Manager(22),
    StaffManager(42),
    Admin(23);

    int id;

    Permission(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
