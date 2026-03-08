package cz.martinbrom.slimybees.worldgen;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.HeightMap;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.NotNull;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockTypes;
import com.xzavier0722.mc.plugin.slimefun4.storage.controller.BlockDataController;

import cz.martinbrom.slimybees.SlimyBeesPlugin;
import cz.martinbrom.slimybees.core.SlimyBeesRegistry;
import cz.martinbrom.slimybees.utils.ArrayUtils;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

@ParametersAreNonnullByDefault
public class NestPopulator extends BlockPopulator {

    public static final int TRIES_PER_CHUNK = 10;

    private final SlimyBeesRegistry registry;
    private final double chanceModifier;

    public NestPopulator(SlimyBeesRegistry registry, double chanceModifier) {
        this.registry = registry;
        this.chanceModifier = chanceModifier;
    }

    /**
     * Tries to generate a nest for given {@link World} in a given {@link Chunk}.
     *
     * @param world The {@link World} to generate a nest in
     * @param random The random generator to use
     * @param source The chunk to generate a nest in
     */
    @Override
    public void populate(World world, Random random, Chunk source) {
    	Bukkit.getScheduler().runTaskAsynchronously(SlimyBeesPlugin.instance(), () -> {
    		Block cornerBlock = world.getHighestBlockAt(source.getX() * 16, source.getZ() * 16);
            Biome chunkBiome = cornerBlock.getBiome();

            List<NestDTO> nests = registry.getNestsForBiome(world, chunkBiome);
            Collections.shuffle(nests, random);

            com.sk89q.worldedit.world.World faweworld = BukkitAdapter.adapt(world);
            try (EditSession editSession = WorldEdit.getInstance().newEditSessionBuilder()
        			.world(faweworld)
                    .maxBlocks(-1)
                    .fastMode(true)
                    .build()) {
            	
            	for (NestDTO nest : nests) {
                    double spawnChance = nest.getSpawnChance() * chanceModifier;
                    if (random.nextDouble() < spawnChance && tryGenerate(editSession, world, random, source, nest)) {
                        break;
                    }
                }
            	editSession.flushQueue();
            } catch (Exception e) {
                throw new RuntimeException("批量设置蜂箱失败", e);
            }
            
    	});
        
    }

    /**
     * Tries to generate a nest for given {@link World} in a given {@link Chunk}
     * based on data from a given {@link NestDTO}.
     * Each try, random X and Z coordinates are chosen and the highest block
     * at that point is checked. If the highest block is solid and the needed type
     * for the give {@link NestDTO} and the block above is empty, a nest
     * is generated.
     * Because the biome is only checked for the corner of the {@link Chunk},
     * it is possible for the nest to generate in a biome it wouldn't normally generate in.
     *
     * @param world The {@link World} to generate a nest in
     * @param random The random generator to use
     * @param source The chunk to generate a nest in
     * @param nest The {@link NestDTO} containing information about the nest
     * @return True if a nest was generated, false otherwise
     */
    private boolean tryGenerate(EditSession editSession, World world, Random random, Chunk source, NestDTO nest) {
        int cornerX = source.getX() * 16;
        int cornerZ = source.getZ() * 16;
        for (int i = 0; i < TRIES_PER_CHUNK; i++) {
            int x = cornerX + random.nextInt(16);
            int z = cornerZ + random.nextInt(16);

            Block groundBlock = world.getHighestBlockAt(x, z, HeightMap.MOTION_BLOCKING_NO_LEAVES);
            Block nestBlock = groundBlock.getRelative(BlockFace.UP);

            if (ArrayUtils.contains(nest.getFloorMaterials(), groundBlock.getType()) && nestBlock.getType().isAir()) {
                createNest(editSession, nestBlock, nest);

                // TODO: 16.05.21 Change back to fine or similar logging level
                SlimyBeesPlugin.logger().info("成功生成了一个蜂箱, "
                        + "蜂箱类型: " + nest.getNestId()
                        + "位置: [x=" + x
                        + ", y=" + nestBlock.getY()
                        + ", z=" + z + "]");

                return true;
            }
        }

        return false;
    }

    /**
     * Creates a nest in place of the given {@link Block}
     * from information in the given {@link NestDTO}.
     *
     * @param b The {@link Block} where the nest should be generated
     * @param nest The {@link NestDTO} containing information about the nest
     */
    public static void createNest(EditSession editSession, Block b, NestDTO nest) {
    	BlockVector3 pos = BlockVector3.at(b.getX(), b.getY(), b.getZ());
    	editSession.setBlock(pos, BlockTypes.BEEHIVE.getDefaultState());
        //b.setType(Material.BEEHIVE);
    	BlockDataController controller = Slimefun.getDatabaseManager().getBlockDataController();
		Location location = b.getLocation();
    	if (controller.getBlockData(location) == null) {
    	    controller.createBlock(location, nest.getNestId());
    	}
    	        	
    }

}
