package scripts.task;

import org.tribot.api2007.types.RSTile;
import scripts.data.FullInventoryAction;
import scripts.data.Pickaxe;

public class ProgressiveTask {
    private final RSTile[] rockLocations;
    private final int lowLevel;
    private final int highLevel;
    private final FullInventoryAction fullInventoryAction;
    private final Pickaxe pickaxe;
    private final boolean shouldHopOnNoRocks;
    private final boolean usePvpWorlds;
    private final boolean useMembersWorlds;
    private final boolean useAntiPK;

    public ProgressiveTask(
            RSTile[] rockLocations,
            int lowLevel,
            int highLevel,
            FullInventoryAction fullInventoryAction,
            Pickaxe pickaxe,
            boolean shouldHopOnNoRocks,
            boolean usePvpWorlds,
            boolean useMembersWorlds,
            boolean useAntiPK
    ) {
        this.rockLocations = rockLocations;
        this.lowLevel = lowLevel;
        this.highLevel = highLevel;
        this.fullInventoryAction = fullInventoryAction;
        this.pickaxe = pickaxe;
        this.shouldHopOnNoRocks = shouldHopOnNoRocks;
        this.usePvpWorlds = usePvpWorlds;
        this.useMembersWorlds = useMembersWorlds;
        this.useAntiPK = useAntiPK;
    }

    public RSTile[] getRockLocations() {
        return rockLocations;
    }

    public int getLowLevel() {
        return lowLevel;
    }

    public int getHighLevel() {
        return highLevel;
    }

    public boolean isInLevelRange(int level) {
        return level >= lowLevel && level < highLevel;
    }

    public FullInventoryAction getFullInventoryAction() {
        return fullInventoryAction;
    }

    public Pickaxe getPickaxe() {
        return pickaxe;
    }

    public boolean getShouldHopOnNoRocks() {
        return shouldHopOnNoRocks;
    }

    public boolean getUsePvpWorlds() {
        return usePvpWorlds;
    }

    public boolean getUseMembersWorlds() {
        return useMembersWorlds;
    }

    public boolean getUseAntiPK() {
        return useAntiPK;
    } //TODO: Implement anti-pk

    public String getTilesAsString() {
        String tiles = "{ ";
        for (RSTile rockLocation : rockLocations)
            tiles += rockLocation.toString() + " ";
        tiles += "}";
        return tiles;
    }

    @Override
    public String toString() {
        return "pick="
                + pickaxe + " : range="
                + lowLevel + "-" + highLevel + " : fullinv="
                + fullInventoryAction + " : hop="
                + shouldHopOnNoRocks + " : pvpwrld="
                + usePvpWorlds + " : memwrld="
                + useMembersWorlds + " : antpk="
                + useAntiPK + " : tiles="
                + getTilesAsString();
    }
}
