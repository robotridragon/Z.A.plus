// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelthedeadone extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer arm1;
	private final ModelRenderer bone;
	private final ModelRenderer arm0;
	private final ModelRenderer bone2;
	private final ModelRenderer head;
	private final ModelRenderer leg1;
	private final ModelRenderer leg3;
	private final ModelRenderer leg0;
	private final ModelRenderer leg2;

	public Modelthedeadone() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		setRotationAngle(body, 0.1745F, 0.0F, 0.0F);
		body.setTextureOffset(0, 40).addBox(-9.0F, -2.9848F, -5.8264F, 18.0F, 12.0F, 11.0F, 0.0F, false);
		body.setTextureOffset(0, 70).addBox(-4.5F, 9.0152F, -2.8264F, 9.0F, 5.0F, 6.0F, 0.5F, false);
		body.setTextureOffset(2, 88).addBox(-4.5F, 15.0977F, -2.8834F, 9.0F, 5.0F, 6.0F, 0.5F, false);

		arm1 = new ModelRenderer(this);
		arm1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(arm1, -0.3927F, 0.0F, 0.0F);
		arm1.setTextureOffset(23, 38).addBox(9.0F, -9.4239F, -3.3827F, 4.0F, 18.0F, 6.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 21.1272F, -19.7486F);
		arm1.addChild(bone);
		setRotationAngle(bone, -0.6981F, 0.0F, 0.0F);
		bone.setTextureOffset(60, 58).addBox(9.0F, -24.8319F, 3.0421F, 4.0F, 18.0F, 6.0F, 0.0F, false);

		arm0 = new ModelRenderer(this);
		arm0.setRotationPoint(0.0F, -7.0F, 0.0F);
		arm0.setTextureOffset(17, 39).addBox(-13.0F, -2.5F, -3.0F, 4.0F, 18.0F, 6.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-22.0F, 28.1272F, -19.7486F);
		arm0.addChild(bone2);
		setRotationAngle(bone2, -0.6981F, 0.0F, 0.0F);
		bone2.setTextureOffset(60, 58).addBox(9.0F, -25.1362F, 3.2863F, 4.0F, 18.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -7.0F, -2.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -8.5F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(6.0F, 11.0F, 0.0F);
		setRotationAngle(leg1, -0.5672F, 0.0F, 0.0F);
		leg1.setTextureOffset(37, 0).addBox(-3.5F, -5.8434F, -0.5373F, 6.0F, 10.0F, 5.0F, 0.0F, true);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(4.0F, 11.0F, 6.0F);
		leg1.addChild(leg3);
		setRotationAngle(leg3, 0.5236F, 0.0F, 0.0F);
		leg3.setTextureOffset(60, 0).addBox(-7.5F, -8.999F, -3.0436F, 6.0F, 10.0F, 5.0F, 0.0F, true);

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(-4.0F, 11.0F, 0.0F);
		setRotationAngle(leg0, 0.0F, 0.0436F, 0.0F);
		leg0.setTextureOffset(37, 0).addBox(-3.5F, -6.0F, 0.0F, 6.0F, 10.0F, 5.0F, 0.0F, true);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(4.0F, 11.0F, 6.0F);
		leg0.addChild(leg2);
		setRotationAngle(leg2, 0.5236F, 0.0F, 0.0F);
		leg2.setTextureOffset(60, 0).addBox(-7.5F, -8.866F, -2.5F, 6.0F, 10.0F, 5.0F, 0.0F, true);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		arm1.render(matrixStack, buffer, packedLight, packedOverlay);
		arm0.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay);
		leg0.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.leg0.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.arm1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.arm0.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}