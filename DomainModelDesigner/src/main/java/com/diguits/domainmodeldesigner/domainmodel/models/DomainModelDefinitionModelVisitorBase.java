package com.diguits.domainmodeldesigner.domainmodel.models;

public class DomainModelDefinitionModelVisitorBase implements IEntityDefinitionModelVisitor{

	 protected void visitBaseDefModel(BaseDefModel baseDefModel, BaseDefModel owner)
     {

     }

     public void visitDomainModelDefModel(DomainModelDefModel domainModelDefModel)
     {
         visitBaseDefModel(domainModelDefModel, null);
     }

     public void visitBoundedContextDefModel(BoundedContextDefModel boundedContext, BaseDefModel owner)
     {
         visitBaseDefModel(boundedContext, owner);
     }

     public void visitEntityGroupDefModel(ModuleDefModel module, BaseDefModel owner)
     {
         visitBaseDefModel(module, owner);
     }

     public void visitEnumDefModel(EnumDefModel enumDefModel, BaseDefModel owner)
     {
         visitBaseDefModel(enumDefModel, owner);
     }

     public void visitEnumValueDefModel(EnumValueDefModel enumValue, BaseDefModel owner)
     {
         visitBaseDefModel(enumValue, owner);
     }

     public void visitEntityDefModel(EntityDefModel entity, BaseDefModel owner)
     {
         visitBaseDefModel(entity, owner);
     }

     public void visitFieldGroupDefModel(FieldGroupDefModel fieldGroup, BaseDefModel owner)
     {
         visitBaseDefModel(fieldGroup, owner);
     }

     public void visitFieldSubgroupDefModel(FieldSubgroupDefModel fieldSubgroup, BaseDefModel owner)
     {
         visitBaseDefModel(fieldSubgroup, owner);
     }

     public void visitFieldDefModel(FieldDefModel field, BaseDefModel owner)
     {
         visitBaseDefModel(field, owner);
     }

     public void visitIndexDefModel(IndexDefModel index, BaseDefModel owner)
     {
         visitBaseDefModel(index, owner);
     }

     public void visitRelationshipDefModel(RelationshipDefModel relationship, BaseDefModel owner)
     {
         visitBaseDefModel(relationship, owner);
     }

     public void visitRelationOverrideDefModel(RelationOverrideDefModel relationshipOverride, BaseDefModel owner)
     {
         visitBaseDefModel(relationshipOverride, owner);
     }

     public void visitFilterDefModel(FilterDefModel filterDefModel, BaseDefModel owner)
     {
         visitBaseDefModel(filterDefModel, owner);
     }

     public void visitColumnDefModel(ColumnDefModel columnDefModel, BaseDefModel owner)
     {
         visitBaseDefModel(columnDefModel, owner);
     }

     public void visitApplicationDefModel(ApplicationDefModel applicationDefModel, BaseDefModel owner)
     {
         visitBaseDefModel(applicationDefModel, owner);
     }

     public void visitRelationshipPartDefModel(RelationshipPartDefModel RelationshipPartDefModel, BaseDefModel owner)
     {
         visitBaseDefModel(RelationshipPartDefModel, owner);
     }

	@Override
	public void visitLocalizedDataDefModel(LocalizedDataDefModel localizedDataDefModel, BaseDefModel owner) {
		 visitBaseDefModel(localizedDataDefModel, owner);
	}

	@Override
	public void visitLocaleDefModel(LocaleDefModel localizedDataDefModel, BaseDefModel owner) {
		 visitBaseDefModel(localizedDataDefModel, owner);
	}

	@Override
	public void visitFieldRelationshipDataDefModel(FieldRelationshipDataDefModel fieldRelationshipDataDefModel,
			BaseDefModel owner) {
		 visitBaseDefModel(fieldRelationshipDataDefModel, owner);

	}

	@Override
	public void visitFilterValueDefModel(FilterValueDefModel filterValueDefModel, BaseDefModel owner) {
		visitBaseDefModel(filterValueDefModel, owner);
	}

	@Override
	public void visitCustomFieldDefModel(CustomFieldDefModel customFieldDefModel, BaseDefModel owner) {
		visitBaseDefModel(customFieldDefModel, owner);
	}

	@Override
	public void visitCustomFieldValueDefModel(CustomFieldValueDefModel customFieldValueDefModel, BaseDefModel owner) {
		visitBaseDefModel(customFieldValueDefModel, owner);
	}

	@Override
	public void visitValueObjectDefModel(ValueObjectDefModel valueObjectDefModel, BaseDefModel owner) {
		visitBaseDefModel(valueObjectDefModel, owner);

	}
 }
