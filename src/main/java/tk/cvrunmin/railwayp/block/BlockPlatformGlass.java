package tk.cvrunmin.railwayp.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPlatformGlass extends BlockPane{

	public BlockPlatformGlass() {
		super(Material.glass, true);
	}
	@Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
    {
        boolean flag = this.canPaneConnectTo(worldIn, pos, EnumFacing.NORTH);
        boolean flag1 = this.canPaneConnectTo(worldIn, pos, EnumFacing.SOUTH);
        boolean flag2 = this.canPaneConnectTo(worldIn, pos, EnumFacing.WEST);
        boolean flag3 = this.canPaneConnectTo(worldIn, pos, EnumFacing.EAST);

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
        {
            if (flag2)
            {
                this.setBlockBounds(0.0F, 0.0F, 0.4f, 0.5F, 1.0F, 0.6f);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            }
            else if (flag3)
            {
                this.setBlockBounds(0.5F, 0.0F, 0.4f, 1.0F, 1.0F, 0.6f);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            }
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.4F, 1.0F, 1.0F, 0.6F);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1))
        {
            if (flag)
            {
                this.setBlockBounds(0.4F, 0.0F, 0.0F, 0.6F, 1.0F, 0.5F);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            }
            else if (flag1)
            {
                this.setBlockBounds(0.4F, 0.0F, 0.5F, 0.6F, 1.0F, 1.0F);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            }
        }
        else
        {
            this.setBlockBounds(0.4F, 0.0F, 0.0F, 0.6F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }
    }
	@Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        float f = 0.4F;
        float f1 = 0.6F;
        float f2 = 0.4F;
        float f3 = 0.6F;
        boolean flag = this.canConnectToBlock(worldIn.getBlockState(pos.north()).getBlock());
        boolean flag1 = this.canConnectToBlock(worldIn.getBlockState(pos.south()).getBlock());
        boolean flag2 = this.canConnectToBlock(worldIn.getBlockState(pos.west()).getBlock());
        boolean flag3 = this.canConnectToBlock(worldIn.getBlockState(pos.east()).getBlock());

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
        {
            if (flag2)
            {
                f = 0.0F;
            }
            else if (flag3)
            {
                f1 = 1.0F;
            }
        }
        else
        {
            f = 0.0F;
            f1 = 1.0F;
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1))
        {
            if (flag)
            {
                f2 = 0.0F;
            }
            else if (flag1)
            {
                f3 = 1.0F;
            }
        }
        else
        {
            f2 = 0.0F;
            f3 = 1.0F;
        }

        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }
	@Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return worldIn.getBlockState(pos).getBlock() == this ? false : (worldIn.getBlockState(pos).getBlock() instanceof BlockPlatformDoor.Base ? false : super.shouldSideBeRendered(worldIn, pos, side));
    }
	@Override
    public boolean canPaneConnectTo(IBlockAccess world, BlockPos pos, EnumFacing dir)
    {
        BlockPos off = pos.offset(dir);
        Block block = world.getBlockState(off).getBlock();
        return canConnectToBlock(block) || block.isSideSolid(world, off, dir.getOpposite());
    }
    public final boolean canConnectToBlock(Block blockIn)
    {
        return blockIn.isFullBlock() || blockIn == this || blockIn == Blocks.glass || blockIn == Blocks.stained_glass || blockIn == Blocks.stained_glass_pane || blockIn instanceof BlockPane || blockIn instanceof BlockPlatformDoor.Base;
    }
}
