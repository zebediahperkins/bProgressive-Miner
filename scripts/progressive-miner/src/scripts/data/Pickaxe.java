package scripts.data;

public enum Pickaxe {
    BRONZE(1265),
    IRON(1267),
    STEEL(1269),
    BLACK(12297),
    MITHRIL(1273),
    ADAMANT(1271),
    RUNE(1275),
    DRAGON(11920),
    DECORATED(12797),
    INFERNAL(13243);

    public int id;

    Pickaxe(int id) {
        this.id = id;
    }
}
