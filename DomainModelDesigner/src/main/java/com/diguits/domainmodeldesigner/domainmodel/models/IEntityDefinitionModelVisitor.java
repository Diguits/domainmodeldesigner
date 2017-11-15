package com.diguits.domainmodeldesigner.domainmodel.models;

public interface IEntityDefinitionModelVisitor {
	void visitDomainModelDefModel(DomainModelDefModel domainModelDefModel);
    void visitBoundedContextDefModel(BoundedContextDefModel boundedContext, BaseDefModel owner);
    void visitEntityGroupDefModel(ModuleDefModel module, BaseDefModel owner);
    void visitEnumDefModel(EnumDefModel enumDefModel, BaseDefModel owner);
    void visitEnumValueDefModel(EnumValueDefModel enumValue, BaseDefModel owner);
    void visitEntityDefModel(EntityDefModel entity, BaseDefModel owner);
    void visitFieldGroupDefModel(FieldGroupDefModel fieldGroup, BaseDefModel owner);
    void visitFieldSubgroupDefModel(FieldSubgroupDefModel fieldSubgroup, BaseDefModel owner);
    void visitFieldDefModel(FieldDefModel field, BaseDefModel owner);
    void visitIndexDefModel(IndexDefModel index, BaseDefModel owner);
    void visitRelationshipDefModel(RelationshipDefModel relationship, BaseDefModel owner);
    void visitRelationOverrideDefModel(RelationOverrideDefModel relationshipOverride, BaseDefModel owner);
    void visitFilterDefModel(FilterDefModel filterDefModel, BaseDefModel owner);
    void visitColumnDefModel(ColumnDefModel columnDefModel, BaseDefModel owner);
    void visitApplicationDefModel(ApplicationDefModel applicationDefModel, BaseDefModel owner);
    void visitRelationshipPartDefModel(RelationshipPartDefModel RelationshipPartDefModel, BaseDefModel owner);
    void visitLocalizedDataDefModel(LocalizedDataDefModel localizedDataDefModel, BaseDefModel owner);
    void visitLocaleDefModel(LocaleDefModel localizedDataDefModel, BaseDefModel owner);
	void visitFieldRelationshipDataDefModel(FieldRelationshipDataDefModel fieldRelationshipDataDefModel,
			BaseDefModel owner);
	void visitFilterValueDefModel(FilterValueDefModel filterValueDefModel, BaseDefModel owner);
	void visitCustomFieldDefModel(CustomFieldDefModel customFieldDefModel, BaseDefModel owner);
	void visitCustomFieldValueDefModel(CustomFieldValueDefModel customFieldValueDefModel, BaseDefModel owner);
	void visitValueObjectDefModel(ValueObjectDefModel valueObjectDefModel, BaseDefModel owner);
}
