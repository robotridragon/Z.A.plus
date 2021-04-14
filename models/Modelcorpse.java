// Made with Blockbench 3.8.3
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class Modelcorpse extends EntityModel<Entity> {
	private final ModelRenderer steve;
	private final ModelRenderer left_leg_r1;
	private final ModelRenderer right_leg_r1;
	private final ModelRenderer left_arm_r1;
	private final ModelRenderer right_arm_r1;
	private final ModelRenderer body_r1;

	public Modelcorpse() {
		textureWidth = 64;
		textureHeight = 64;

		steve = new ModelRenderer(this);
		steve.setRotationPoint(0.0F, 16.0F, 0.0F);

		left_leg_r1 = new ModelRenderer(this);
		left_leg_r1.setRotationPoint(8.0F, 8.0F, -8.0F);
		steve.addChild(left_leg_r1);
		setRotationAngle(left_leg_r1, -1.5708F, -0.7418F, -0.0436F);
		left_leg_r1.setTextureOffset(0, 16).addBox(-4.0F, -1.5F, -3.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		right_leg_r1 = new ModelRenderer(this);
		right_leg_r1.setRotationPoint(8.0F, 8.0F, -8.0F);
		steve.addChild(right_leg_r1);
		setRotationAngle(right_leg_r1, -1.5708F, 0.48F, 0.0F);
		right_leg_r1.setTextureOffset(0, 16).addBox(-16.0F, 8.5F, -3.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		left_arm_r1 = new ModelRenderer(this);
		left_arm_r1.setRotationPoint(8.0F, 8.0F, -8.0F);
		steve.addChild(left_arm_r1);
		setRotationAngle(left_arm_r1, -1.5708F, -0.9599F, 0.0F);
		left_arm_r1.setTextureOffset(40, 16).addBox(8.0F, -4.5F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		right_arm_r1 = new ModelRenderer(this);
		right_arm_r1.setRotationPoint(8.0F, 8.0F, -8.0F);
		steve.addChild(right_arm_r1);
		setRotationAngle(right_arm_r1, -1.5708F, -2.7925F, 0.0F);
		right_arm_r1.setTextureOffset(40, 16).addBox(23.0F, 0.5F, -3.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(8.0F, 8.0F, -8.0F);
		steve.addChild(body_r1);
		setRotationAngle(body_r1, -1.5708F, 0.0F, 0.0F);
		body_r1.setTextureOffset(16, 16).addBox(-12.0F, -14.5F, -3.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		body_r1.setTextureOffset(0, 0).addBox(-12.0F, -31.5F, -6.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		steve.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}