
public enum MinionName {

    KAMANDARE_FARS(6, 4, 2, AttackKind.RANGED), SHAMSHIRZANE_FARS(6, 4, 2, AttackKind.MELEE),
    NEYZEDARE_FARS(5, 3, 1, AttackKind.HYBRID), ASBSAVARE_FARS(10, 6, 4, AttackKind.MELEE),
    PAHLEVANE_FARS(24, 6, 9, AttackKind.MELEE), SEPAHSALARE_FARS(12, 4, 7, AttackKind.MELEE),
    KAMANDARE_TOORANI(3, 4, 1, AttackKind.RANGED), GHOLLABSANGDARE_TOORANI(4, 2, 1, AttackKind.RANGED),
    NEYZEDARE_TOORANI(4, 4, 1, AttackKind.HYBRID), JASOOSE_TOORANI(6, 6, 4, AttackKind.MELEE),
    GORZDARE_TOORANI(3, 10, 2, AttackKind.MELEE), SHAHZADEYE_TOORANI(6, 10, 6, AttackKind.MELEE),
    DIVE_SIAH(14, 10, 9, AttackKind.HYBRID), GHOOLE_SANGANDAZ(12, 12, 9, AttackKind.RANGED),
    DIVE_GORAZSAVAR(16, 8, 6, AttackKind.MELEE), GHOOLE_BOZORG(12, 11, 7, AttackKind.HYBRID),
    GHOOLE_BOZORG2(30, 8, 9, AttackKind.HYBRID), GHOOLE_DOSAR(10, 4, 4, AttackKind.MELEE),
    SHAHGHOOL(10, 4, 5, AttackKind.MELEE), ARJANG_DIV(6, 6, 3, AttackKind.MELEE),
    OGHAB(0, 2, 2, AttackKind.RANGED), MARE_SAMMI(5, 6, 4, AttackKind.RANGED),
    EJDEHAYE_ATASHANDAZ(9, 5, 5, AttackKind.RANGED), SHIRE_DARANDE(1, 8, 2, AttackKind.MELEE),
    MARE_GHOOLPEIKAR(14, 7, 8, AttackKind.RANGED), GORGE_SEFID(8, 2, 5, AttackKind.MELEE),
    PALANG(6, 2, 4, AttackKind.MELEE), GORG(6, 1, 3, AttackKind.MELEE),
    GORAZE_VAHSHI(10, 14, 6, AttackKind.MELEE),
    PIRAN(20, 12, 8, AttackKind.MELEE), GIV(5, 7, 4, AttackKind.RANGED),
    BAHMAN(16, 9, 8, AttackKind.MELEE), ASHKBOOS(14, 8, 7, AttackKind.MELEE),
    IRAJ(6, 20, 4, AttackKind.RANGED), SIAVASH(8, 5, 4, AttackKind.MELEE),
    NANESARMA(3, 4, 3, AttackKind.RANGED), FOOLADZERE(1, 1, 3, AttackKind.MELEE),
    JADOOGAR(5, 4, 4, AttackKind.RANGED), JADOOGAR_AZAM(6, 6, 6, AttackKind.RANGED),
    JEN(10, 4, 5, AttackKind.RANGED);

    private int healthPoint;
    private int actionPower;
    private int manaCost;
    private AttackKind attackKind;

    private MinionName() {

    }

    private MinionName(int healthPoint, int actionPower, int manaCost, AttackKind attackKind) {
        this.healthPoint = healthPoint;
        this.actionPower = actionPower;
        this.manaCost = manaCost;
        this.attackKind = attackKind;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getActionPower() {
        return actionPower;
    }


    public int getManaCost() {
        return manaCost;
    }

    public AttackKind getAttackKind() {
        return attackKind;
    }


}
