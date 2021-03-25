// Made with Blockbench 3.8.3
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class Modelturretnormal extends EntityModel<Entity> {
	private final ModelRenderer legs;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer gun;
	private final ModelRenderer ammobox;
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;

	public Modelturretnormal() {
		textureWidth = 64;
		textureHeight = 64;

		legs = new ModelRenderer(this);
		legs.setRotationPoint(-8.5F, 24.0F, 0.5F);
		setRotationAngle(legs, 0.0F, -1.5708F, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		legs.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.48F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(0, 43).addBox(-1.0F, -19.0F, -1.0F, 2.0F, 19.0F, 2.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, -17.0F);
		legs.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.48F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(0, 43).addBox(-1.0F, -19.0F, -1.0F, 2.0F, 19.0F, 2.0F, 0.0F, false);

		gun = new ModelRenderer(this);
		gun.setRotationPoint(2.0F, 24.0F, 0.0F);
		gun.setTextureOffset(22, 22).addBox(-2.0F, -19.25F, -20.5F, 1.0F, 1.0F, 17.0F, 0.0F, false);
		gun.setTextureOffset(0, 8).addBox(-4.5F, -21.25F, -3.5F, 5.0F, 5.0F, 11.0F, 0.0F, false);
		gun.setTextureOffset(0, 25).addBox(-2.0F, -19.25F, -22.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		gun.setTextureOffset(0, 0).addBox(-2.0F, -20.0F, -20.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		gun.setTextureOffset(0, 0).addBox(-2.0F, -22.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		ammobox = new ModelRenderer(this);
		ammobox.setRotationPoint(-0.5F, -18.25F, 0.5F);
		gun.addChild(ammobox);
		setRotationAngle(ammobox, 0.0F, 1.5708F, 0.0F);
		ammobox.setTextureOffset(40, 0).addBox(-7.0F, -2.0F, 1.0F, 8.0F, 4.0F, 2.0F, 0.0F, false);
		ammobox.setTextureOffset(56, 59).addBox(-6.0F, -1.0F, 3.0F, 3.0F, 4.0F, 1.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, -8.0F);
		bb_main.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.48F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(0, 43).addBox(-1.0F, -19.0F, -1.0F, 2.0F, 19.0F, 2.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 9.0F);
		bb_main.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.48F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(0, 43).addBox(-1.0F, -19.0F, -1.0F, 2.0F, 19.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		legs.render(matrixStack, buffer, packedLight, packedOverlay);
		gun.render(matrixStack, buffer, packedLight, packedOverlay);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.gun.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.gun.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}