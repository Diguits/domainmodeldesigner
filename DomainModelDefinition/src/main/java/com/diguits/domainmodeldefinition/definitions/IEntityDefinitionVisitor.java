package com.diguits.domainmodeldefinition.definitions;

public interface IEntityDefinitionVisitor {
	void visitDomainModelDef(DomainModelDef domainModelDef);
    void visitBoundedContextDef(BoundedContextDef boundedContext, BaseDef owner);
    void visitEntityGroupDef(ModuleDef module, BaseDef owner);
    void visitEnumDef(EnumDef enumDef, BaseDef owner);
    void visitEnumValueDef(EnumValueDef enumValue, BaseDef owner);
    void visitEntityDef(EntityDef entity, BaseDef owner);
    void visitFieldGroupDef(FieldGroupDef fieldGroup, BaseDef owner);
    void visitFieldSubgroupDef(FieldSubgroupDef fieldSubgroup, BaseDef owner);
    void visitFieldDef(FieldDef field, BaseDef owner);
    void visitFieldRelationDataDef(FieldRelationshipDataDef relationshipData, BaseDef owner);
    void visitIndexDef(IndexDef index, BaseDef owner);
    void visitRelationshipDef(RelationshipDef relationship, BaseDef owner);
    void visitRelationOverrideDef(RelationOverrideDef relationshipOverride, BaseDef owner);
    void visitFilterDef(FilterDef filterDef, BaseDef owner);
    void visitColumnDef(ColumnDef columnDef, BaseDef owner);
    void visitApplicationDef(ApplicationDef applicationDef, BaseDef owner);
    void visitRelationshipPartDef(RelationshipPartDef RelationshipPartDef, BaseDef owner);
	void visiCustomFieldDef(CustomFieldDef customFieldDef, BaseDef owner);
	void visitCustomFieldValueDef(CustomFieldValueDef customFieldValueDef, BaseDef owner);
	void visitLocalizedDataDef(LocalizedDataDef localizedDataDef, BaseDef owner);
	void visitLocaleDef(LocaleDef localeDef, BaseDef owner);
	void visitFilterValueDef(FilterValueDef filterValueDef, BaseDef owner);
	void visitValueObjectDef(ValueObjectDef valueObjectDef, BaseDef owner);
}
