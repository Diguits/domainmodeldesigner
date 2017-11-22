package com.diguits.domainmodeldefinition.definitions;

public class DomainModelDefinitionVisitorBase implements IEntityDefinitionVisitor {

	protected void visitBaseDef(BaseDef baseDef, BaseDef owner) {

	}

	public void visitDomainModelDef(DomainModelDef domainModelDef) {
		visitBaseDef(domainModelDef, null);
	}

	public void visitBoundedContextDef(BoundedContextDef boundedContext, BaseDef owner) {
		visitBaseDef(boundedContext, owner);
	}

	public void visitEntityGroupDef(ModuleDef module, BaseDef owner) {
		visitBaseDef(module, owner);
	}

	public void visitEnumDef(EnumDef enumDef, BaseDef owner) {
		visitBaseDef(enumDef, owner);
	}

	public void visitEnumValueDef(EnumValueDef enumValue, BaseDef owner) {
		visitBaseDef(enumValue, owner);
	}

	public void visitEntityDef(EntityDef entity, BaseDef owner) {
		visitBaseDef(entity, owner);
	}

	public void visitFieldGroupDef(FieldGroupDef fieldGroup, BaseDef owner) {
		visitBaseDef(fieldGroup, owner);
	}

	public void visitFieldSubgroupDef(FieldSubgroupDef fieldSubgroup, BaseDef owner) {
		visitBaseDef(fieldSubgroup, owner);
	}

	public void visitFieldDef(FieldDef field, BaseDef owner) {
		visitBaseDef(field, owner);
	}

	public void visitIndexDef(IndexDef index, BaseDef owner) {
		visitBaseDef(index, owner);
	}

	public void visitRelationshipDef(RelationshipDef relationship, BaseDef owner) {
		visitBaseDef(relationship, owner);
	}

	public void visitRelationOverrideDef(RelationOverrideDef relationshipOverride, BaseDef owner) {
		visitBaseDef(relationshipOverride, owner);
	}

	public void visitFilterDef(FilterDef filterDef, BaseDef owner) {
		visitBaseDef(filterDef, owner);
	}

	public void visitColumnDef(ColumnDef columnDef, BaseDef owner) {
		visitBaseDef(columnDef, owner);
	}

	public void visitApplicationDef(ApplicationDef applicationDef, BaseDef owner) {
		visitBaseDef(applicationDef, owner);
	}

	public void visitRelationshipPartDef(RelationshipPartDef RelationshipPartDef, BaseDef owner) {
		visitBaseDef(RelationshipPartDef, owner);
	}

	@Override
	public void visitFieldRelationDataDef(FieldRelationshipDataDef relationshipData, BaseDef owner) {
		visitBaseDef(relationshipData, owner);
	}

	@Override
	public void visitCustomFieldDef(CustomFieldDef customFieldDef, BaseDef owner) {
		visitBaseDef(customFieldDef, owner);
	}

	@Override
	public void visitCustomFieldValueDef(CustomFieldValueDef customFieldValueDef, BaseDef owner) {
		visitBaseDef(customFieldValueDef, owner);
	}

	@Override
	public void visitLocalizedDataDef(LocalizedDataDef localizedDataDef, BaseDef owner) {
		visitBaseDef(localizedDataDef, owner);
	}

	@Override
	public void visitLocaleDef(LocaleDef localeDef, BaseDef owner) {
		visitBaseDef(localeDef, owner);
	}

	@Override
	public void visitFilterValueDef(FilterValueDef filterValueDef, BaseDef owner) {
		visitBaseDef(filterValueDef, owner);
	}

	@Override
	public void visitValueObjectDef(ValueObjectDef valueObjectDef, BaseDef owner) {
		visitBaseDef(valueObjectDef, owner);
	}
}
