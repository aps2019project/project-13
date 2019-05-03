package Model;

import Model.AttackKind;

public enum MinionName {

    KAMANDARE_FARS(6, 4, 2, AttackKind.RANGED, "Persian Archer"),
    SHAMSHIRZANE_FARS(6, 4, 2, AttackKind.MELEE, "Persian Swordsman"),
    NEYZEDARE_FARS(5, 3, 1, AttackKind.HYBRID, "Persian Spearman"),
    ASBSAVARE_FARS(10, 6, 4, AttackKind.MELEE, "Persian Horseman"),
    PAHLEVANE_FARS(24, 6, 9, AttackKind.MELEE, "Persian Pahlavan"),
    SEPAHSALARE_FARS(12, 4, 7, AttackKind.MELEE, "Persian General"),
    KAMANDARE_TOORANI(3, 4, 1, AttackKind.RANGED, "Toranian Archer"),
    GHOLLABSANGDARE_TOORANI(4, 2, 1, AttackKind.RANGED, "Toranian Slinger"),
    NEYZEDARE_TOORANI(4, 4, 1, AttackKind.HYBRID, "Toranian Spearman"),
    JASOOSE_TOORANI(6, 6, 4, AttackKind.MELEE, "Toranian Spy"),
    GORZDARE_TOORANI(3, 10, 2, AttackKind.MELEE, "Toranian Maceman"),
    SHAHZADEYE_TOORANI(6, 10, 6, AttackKind.MELEE, "Toranian Prince"),
    DIVE_SIAH(14, 10, 9, AttackKind.HYBRID, "Dive Siah"),
    GHOOLE_SANGANDAZ(12, 12, 9, AttackKind.RANGED, "Slinger Giant"),
    DIVE_GORAZSAVAR(16, 8, 6, AttackKind.MELEE, "BoarRider Div"),
    GHOOLE_BOZORG(12, 11, 7, AttackKind.HYBRID, "Big Giant"),
    GHOOLE_BOZORG2(30, 8, 9, AttackKind.HYBRID, "Big Giant2"),
    GHOOLE_DOSAR(10, 4, 4, AttackKind.MELEE, "Two-Headed Giant"),
    SHAHGHOOL(10, 4, 5, AttackKind.MELEE, "Giant King"),
    ARJANG_DIV(6, 6, 3, AttackKind.MELEE, "Arzhang Die"),
    OGHAB(0, 2, 2, AttackKind.RANGED, "Eagle"),
    MARE_SAMMI(5, 6, 4, AttackKind.RANGED, "Poisonous Snake"),
    EJDEHAYE_ATASHANDAZ(9, 5, 5, AttackKind.RANGED, "Firing Dragon"),
    SHIRE_DARANDE(1, 8, 2, AttackKind.MELEE, "Wild Lion"),
    MARE_GHOOLPEIKAR(14, 7, 8, AttackKind.RANGED, "Giant Snake"),
    GORGE_SEFID(8, 2, 5, AttackKind.MELEE, "White Wolf"),
    PALANG(6, 2, 4, AttackKind.MELEE, "Panther"),
    GORG(6, 1, 3, AttackKind.MELEE, "Wolf"),
    GORAZE_VAHSHI(10, 14, 6, AttackKind.MELEE, "Wild Boar"),
    PIRAN(20, 12, 8, AttackKind.MELEE, "Piran"),
    GIV(5, 7, 4, AttackKind.RANGED, "Giv"),
    BAHMAN(16, 9, 8, AttackKind.MELEE, "Bahman"),
    ASHKBOOS(14, 8, 7, AttackKind.MELEE, "Ashkboos"),
    IRAJ(6, 20, 4, AttackKind.RANGED, "Iraj"),
    SIAVASH(8, 5, 4, AttackKind.MELEE, "Siavash"),
    NANESARMA(3, 4, 3, AttackKind.RANGED, "Nane Sarma"),
    FOOLADZERE(1, 1, 3, AttackKind.MELEE, "Foolad Zereh"),
    JADOOGAR(5, 4, 4, AttackKind.RANGED, "Mage"),
    JADOOGAR_AZAM(6, 6, 6, AttackKind.RANGED, "Archmage"),
    JEN(10, 4, 5, AttackKind.RANGED, "Jin");

    private int healthPoint;
    private int actionPower;
    private int manaCost;
    private AttackKind attackKind;
    private String name;

    private MinionName() {


    }

    MinionName(int healthPoint, int actionPower, int manaCost, AttackKind attackKind, String name) {
        this.healthPoint = healthPoint;
        this.actionPower = actionPower;
        this.manaCost = manaCost;
        this.attackKind = attackKind;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
