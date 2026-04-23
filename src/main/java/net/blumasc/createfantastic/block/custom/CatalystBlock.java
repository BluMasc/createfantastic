package net.blumasc.createfantastic.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class CatalystBlock extends Block {

    private static final VoxelShape SHAPE = Block.box(5, 5, 5, 11, 11, 11);

    private static final double CUBE_MIN = 5 / 16.0;
    private static final double CUBE_SIZE = 6 / 16.0;

    @Nullable
    private final Supplier<ParticleOptions> particle;

    public CatalystBlock(Properties properties, @Nullable Supplier<ParticleOptions> particle) {
        super(properties);
        this.particle = particle;
    }

    public CatalystBlock(Properties properties) {
        this(properties, null);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (particle == null) return;
        if (random.nextInt(4) != 0) return;

        double x = pos.getX() + CUBE_MIN + random.nextDouble() * CUBE_SIZE;
        double y = pos.getY() + CUBE_MIN + random.nextDouble() * CUBE_SIZE;
        double z = pos.getZ() + CUBE_MIN + random.nextDouble() * CUBE_SIZE;

        double dx = (random.nextDouble() - 0.5) * 0.05;
        double dy = random.nextDouble() * 0.05;
        double dz = (random.nextDouble() - 0.5) * 0.05;

        level.addParticle(particle.get(), x, y, z, dx, dy, dz);
    }
}