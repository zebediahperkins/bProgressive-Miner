package scripts.task;

import org.apache.commons.lang3.Range;
import org.tribot.api2007.types.RSTile;
import scripts.data.FullInventoryAction;
import scripts.data.Pickaxe;

import java.util.List;

public class ProgressiveTask {
    private final List<RSTile> rockLocations;
    private final Range<Integer> levelRange;
    private final FullInventoryAction fullInventoryAction;
    private final Pickaxe pickaxe;
    private final boolean shouldHopOnNoRocks;
    private final boolean usePvpWorlds;
    private final boolean useMembersWorlds;
    private final boolean useAntiPK;

    public ProgressiveTask(
            List<RSTile> rockLocations,
            Range<Integer> levelRange,
            FullInventoryAction fullInventoryAction,
            Pickaxe pickaxe,
            boolean shouldHopOnNoRocks,
            boolean usePvpWorlds,
            boolean useMembersWorlds,
            boolean useAntiPK
    ) {
        this.rockLocations = rockLocations;
        this.levelRange = levelRange;
        this.fullInventoryAction = fullInventoryAction;
        this.pickaxe = pickaxe;
        this.shouldHopOnNoRocks = shouldHopOnNoRocks;
        this.usePvpWorlds = usePvpWorlds;
        this.useMembersWorlds = useMembersWorlds;
        this.useAntiPK = useAntiPK;
    }

    public List<RSTile> getRockLocations() {
        return rockLocations;
    }

    public boolean isInLevelRange(int level) {
        return level != levelRange.getMaximum() && levelRange.contains(level);
    }

    public Range<Integer> getLevelRange() {
        return levelRange;
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
    }

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
                + levelRange.getMinimum() + "-" + levelRange.getMaximum() + " : fullinv="
                + fullInventoryAction + " : hop="
                + shouldHopOnNoRocks + " : pvpwrld="
                + usePvpWorlds + " : memwrld="
                + useMembersWorlds + " : antpk="
                + useAntiPK + " : tiles="
                + getTilesAsString();
    }
}
